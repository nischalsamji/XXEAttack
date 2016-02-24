package com.mkyong.common.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mkyong.common.model.Coffee;

@Controller
public class XMLController {

	@RequestMapping(value="/",headers="Accept=*/*", method = RequestMethod.GET)
	public String getCoffeeInXML() {

		return "hello";
	    

	}
	
	@RequestMapping(value="/greeting",headers="Accept=*/*", method=RequestMethod.POST)
    public String greetingSubmit( Model model) {
        
        return "no hello";
    }
	
	
	@RequestMapping(value="/upload",headers="Accept=*/*", method=RequestMethod.POST)
    public @ResponseBody Document handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
        try{
            File inputFile = new File(file.getOriginalFilename());
            file.transferTo(inputFile);
            DocumentBuilderFactory dbFactory 
               = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            
            doc.getDocumentElement().normalize();
            System.out.println(doc.toString());
            System.out.println("Root element :" 
               + doc.getDocumentElement().getNodeName());
            
            NodeList nList = doc.getChildNodes();
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp);
               System.out.println("\nCurrent Element :" 
                  + nNode.getNodeName());
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
 
               }
            }
            System.out.println(doc);
            return doc;
            
         } catch (Exception e) {
        	 
            e.printStackTrace();
         }
      }
        return null;
    }
	
}