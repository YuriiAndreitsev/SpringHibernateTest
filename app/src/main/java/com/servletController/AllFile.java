package com.servletController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Servlet implementation class AllFile
 */
@Controller
public class AllFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@RequestMapping(value="allFile", method = RequestMethod.GET)
public String allFile(HttpServletRequest req, HttpServletResponse resp) {
	return "allFile";
}
}
