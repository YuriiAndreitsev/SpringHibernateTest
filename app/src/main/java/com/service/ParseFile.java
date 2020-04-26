package com.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.model.Product;

public class ParseFile {
	ExecutorService es = Executors.newCachedThreadPool();

	public List<Product> parseFile(String filePath) {

		Callable<List<Product>> parseXmlTask = () -> {
			List<Product> productList = null;
			File fileToParse = new File(filePath);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			try {
				SAXParser saxPaser = factory.newSAXParser();
				DefaultHandler myHandler = new XmlSAXParser();
				saxPaser.parse(fileToParse, myHandler);

				productList = ((XmlSAXParser) myHandler).getProductList();

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return productList;

		};
		Future<List<Product>> productList = es.submit(parseXmlTask);
		List<Product> resultList = null;
		try {
			resultList = productList.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

}
