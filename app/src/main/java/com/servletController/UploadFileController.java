package com.servletController;

import javax.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploadFileController extends HttpServlet {

	private static final long serialVersionUID = -1635435403427013282L;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String selectFileToUpload() {

		return "upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String showUploadMessage() {

		return "upload";
	}

}
