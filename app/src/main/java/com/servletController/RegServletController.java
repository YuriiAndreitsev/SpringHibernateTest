package com.servletController;

import com.configuration.Config;
import com.model.ErrorObj;
import com.model.Users;
import com.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegServletController {
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegForm() {
		return "reg";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(ModelMap model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "passwordConfirmation", required = false) String passwordConfirmation,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "comment", required = false) String comment) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		UserService userService = ctx.getBean(UserService.class);
		ErrorObj errors = new ErrorObj();

		if (errors.checkForMistakes(email, password, passwordConfirmation, location, gender)) {
			StringBuilder errorMessage = errors.getErrorText();
			model.addAttribute("errorObj", errorMessage);
			switch (location) {
			case "dnr":
				request.setAttribute("dnr", "selected");
				break;
			case "crimea":
				request.setAttribute("crimea", "selected");
				break;
			case "lnr":
				request.setAttribute("lnr", "selected");
				break;
			}
			request.setAttribute("email", email);
			if (gender!=null) {
			if (gender.equals("male")) {
				request.setAttribute("male", "checked");
			}else {
				request.setAttribute("female", "checked");
			}
			}
			request.setAttribute("textArea", comment);
			showRegForm();
		} else {
			Users user = new Users(email, password, location, gender, comment);
			userService.addUser(user);
			model.addAttribute("regComplete", "Registration Successful");
		}
		return "reg";
	}
}
