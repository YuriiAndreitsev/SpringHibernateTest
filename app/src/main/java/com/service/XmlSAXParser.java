package com.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.model.Category;
import com.model.Product;

public class XmlSAXParser extends DefaultHandler {
	private List<Product> productList = new ArrayList<Product>();
	private Product product = new Product();
	private boolean name = false;
	private boolean description = false;
	private boolean price = false;
	private boolean category = false;
	private StringBuilder value = new StringBuilder();
	


	public List<Product> getProductList() {
		return productList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		for (int i = start; i < start + length; i++) {
			value.append(ch[i]);
		}

		if (name) {
			product.setName(value.toString());
			name = false;
		} else if (description) {
			product.setDescription(value.toString());
			description = false;
		} else if (price) {
			product.setPrice(Integer.parseInt(value.toString()));
			price = false;
		} else if (category) {
			Category cat = new Category();
			cat.setCategory(value.toString());
			product.addCategories(cat);
			category = false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(productList);


	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Started Parsing");
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("name")) {
			name = true;
		} else if (qName.equals("description")) {
			description = true;
		} else if (qName.equals("price")) {
			price = true;
		} else if (qName.equals("category")) {
			category = true;
		}
		value = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("product")) {
			productList.add(product);
			product = new Product();
		}
	}

}
