package com.mkyong.common.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.springframework.core.io.FileSystemResource;
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

	@RequestMapping(value="/", headers="Accept=*/*", method=RequestMethod.GET)
    public String greetingSubmit( Model model) {
        
        return "no hello";
    }
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletResponse response, @RequestParam("file") MultipartFile file){
        ModelAndView mv = new ModelAndView("dispxml");
		if (!file.isEmpty()) {
        try{
        	
            response.setContentType("text/xml");
            File inputFile = new File(file.getOriginalFilename());
            file.transferTo(inputFile);         
            DocumentBuilderFactory dbFactory 
               = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            dbFactory.setFeature( "http://apache.org/xml/features/disallow-doctype-decl", false);
            doc.getDocumentElement().normalize();
            OutputFormat format = new OutputFormat(doc);
            String filename = "mydocument"+System.currentTimeMillis()+".xml";
            @SuppressWarnings("deprecation")
            XMLSerializer serializer = new XMLSerializer(
                new FileOutputStream(new File(filename)),format);
            serializer.serialize(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\r", "");
            System.out.println(output);            
            mv.addObject("file", output);
            response.setContentType("text/xml");
            return mv; 
            
         } catch (Exception e) {
        	 
            e.printStackTrace();
         }
      }
        return null;
    }
	
}