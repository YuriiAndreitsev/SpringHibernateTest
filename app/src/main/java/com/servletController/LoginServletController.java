package com.servletController;

import com.configuration.Config;
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
import javax.servlet.http.HttpSession;

@Controller
public class LoginServletController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginFrom(ModelMap model, HttpSession session,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password) {
		return "log";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLoginPage(ModelMap model, HttpSession session, HttpServletRequest req,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "logout", required = false) String logout) {

		model.addAttribute("color", "red");
		model.addAttribute("message", "Access Denied");

		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		UserService userService = ctx.getBean(UserService.class);

		if (email != null && password != null) {
			Users user = userService.getUserByEmail(email);
			if (user != null) {
				if (user.getPassword().equals(userService.getHash(password))) {
					session.setAttribute("user", user);
					model.addAttribute("color", "green");
					model.addAttribute("message", "Access Granted");
					return "log";
				} else {
					req.setAttribute("email", email);
					model.addAttribute("errorMessage", "Wrong Email or Password");
				}
			} else {
				req.setAttribute("email", email);
				model.addAttribute("errorMessage", "Wrong Email or Password");
			}
		}
		if (logout != null) {
			session.invalidate(); // null
		}
		return "log";
	}
}
