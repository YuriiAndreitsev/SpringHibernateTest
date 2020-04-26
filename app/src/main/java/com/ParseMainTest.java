package com;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.service.XmlDOMParser;
import com.service.XmlSAXParser;

public class ParseMainTest {

	public static void main(String[] args) {
//		XmlDOMParser parser = new XmlDOMParser();
//		parser.parseFile("products.xml");

		File fileToParse = new File("products.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxPaser = factory.newSAXParser();
			DefaultHandler myHandler = new XmlSAXParser();
			saxPaser.parse(fileToParse, myHandler);
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
		
	}

}
