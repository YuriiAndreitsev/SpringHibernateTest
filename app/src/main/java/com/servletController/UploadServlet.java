package com.servletController;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.service.XmlSAXParser;

@Controller
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -6134369958180754485L;
	private static final String SAVE_DIR = "uploadFiles";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String appPath = request.getServletContext().getRealPath(""); // gets absolute path of the web application
		String savePath = appPath + File.separator + SAVE_DIR; // constructs path of the directory to save uploaded file
		File fileSaveDir = new File(savePath);// creates the save directory if it does not exists
//		ExecutorService es = Executors.newCachedThreadPool();

		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				fileName = new File(fileName).getName();// refines the fileName in case it is an absolute path
				if (fileName.isEmpty()) {
					session.setAttribute("message", "Select a File!");
				} else {
					part.write(savePath + File.separator + fileName);
					session.setAttribute("message", "Upload has been done successfully!");

//					String nameOfFile = fileName; // Java 8 effectively final variable;
//					Runnable parseXmlTask = () -> {
//						String filePath = appPath + File.separator + SAVE_DIR + File.separator + nameOfFile;
//						File fileToParse = new File(filePath);
//						SAXParserFactory factory = SAXParserFactory.newInstance();
//						try {
//							SAXParser saxPaser = factory.newSAXParser();
//							DefaultHandler myHandler = new XmlSAXParser();
//							saxPaser.parse(fileToParse, myHandler);
//						} catch (ParserConfigurationException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (SAXException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					};
//					es.execute(parseXmlTask);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		try {
			getServletContext().getRequestDispatcher("/upload.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}