package com.servletController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BrowseFilesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";

	@RequestMapping(value = "/browseFiles", method = RequestMethod.GET)
	public String showFiles(ModelMap model, HttpSession session, HttpServletRequest request) {

		String appPath = request.getServletContext().getRealPath(""); // gets absolute path of the web application
		String browsePath = appPath + File.separator + SAVE_DIR;
		File file = new File(browsePath);

		List<File> allFiles = new ArrayList<File>();

//		if (file.toString().endsWith("..")) {
//			file = new File(file.getParent());
//			String f = file.getParent();
//
//			if (f == null) {
//				f = file.getPath();
//			}
//		}
//		if (file.getParent() != null) {
//			allFiles.add(new File(file.getParent() + "\\.."));
//		}
//		for (File f : file.listFiles()) {
//			if (f.isDirectory()) {
//				allFiles.add(f);
//			}
//		}
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				allFiles.add(f);
			}
		}

		System.out.println(allFiles);

		if (allFiles.size() > 0) {
			model.addAttribute("allFiles", allFiles);
		} else {
			model.addAttribute("noFiles", "No Files Added!");
		}
		return "browseAllFilesView";

	}
}
