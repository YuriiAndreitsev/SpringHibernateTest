package com.servletController;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.configuration.Config;
import com.model.Category;
import com.model.Product;
import com.service.ParseFile;
import com.service.ProductService;

@Controller
public class ParseFileServlet extends HttpServlet {
	private static final long serialVersionUID = 4529000706510413443L;
	private static final String SAVE_DIR = "uploadFiles";

	@RequestMapping(value = "/parseXML", method = RequestMethod.POST)
	@ResponseBody
	public String parseFile(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "name", required = false) String name) {

		JSONObject j = new JSONObject();
		ParseFile parser = new ParseFile();
		String appPath = request.getServletContext().getRealPath("");
		String fileToParsePath = appPath + File.separator + SAVE_DIR + File.separator + name;

		List<Product> productList = parser.parseFile(fileToParsePath);
		// ==============
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		ProductService productService = ctx.getBean(ProductService.class);
		Category cat;
		
		for (Product product : productList) {
			cat = new Category();
			cat.setCategory(product.getCategory());			
			productService.addProduct(product, cat);
		}
		try {
			j.put("parseMessage", "File Has Been Parsed Successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return j.toString();
	}
}
