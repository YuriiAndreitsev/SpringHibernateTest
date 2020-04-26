package com.servletController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageUploadController extends HttpServlet {
	private static final long serialVersionUID = 8501470434480978417L;
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public String showResult(HttpServletRequest request, HttpServletResponse response) {
		return "message";
	}
}
