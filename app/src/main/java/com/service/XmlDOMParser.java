package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.model.Category;
import com.model.Product;

public class XmlDOMParser {
	public void parseFile(String pathToFile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(pathToFile));

			NodeList productList = document.getElementsByTagName("product");
			NodeList name = document.getElementsByTagName("name");
			NodeList description = document.getElementsByTagName("description");
			NodeList price = document.getElementsByTagName("price");
			NodeList category = document.getElementsByTagName("category");

			List<Product> newProductList = new ArrayList<Product>();

			Product product = null;
			Category categoryItem = null;
			for (int i = 0; i < productList.getLength(); i++) {
				Node node = productList.item(i);
				product = new Product();
				categoryItem = new Category();
				product.setName(name.item(i).getTextContent());
				product.setDescription(description.item(i).getTextContent());
				product.setPrice(Integer.parseInt(price.item(i).getTextContent()));
				categoryItem.setCategory(category.item(i).getTextContent());
				product.addCategories(categoryItem);
				newProductList.add(product);
			}

			System.out.println(newProductList);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
