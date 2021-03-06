import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {
		DemoClass dc = new DemoClass();
		dc.setB(false);
		dc.setI(10);
		dc.setS("hi");
		
		// XML
		marshalToXml(dc);
		
		String demoString = "<DemoClass>\r\n" + 
				"    <b>false</b>\r\n" + 
				"    <i>10</i>\r\n" + 
				"    <s>hi</s>\r\n" + 
				"</DemoClass>";
		
		//new DemoClass dc2 = unmarshalFromXml(demoString);
		
		//JSON
		
		
		System.out.println(marshalToJson(dc));
		
		//as if from request.getReader
		BufferedReader jsonData = new BufferedReader(new StringReader("{\"i\":56,\"s\":\"Hi there!\",\"b\":false}"));
		
		//String json = jsonData.lines().collect(Collectors.joining("\n"));
		System.out.println(unmarshalFromJson(jsonData).getS());
		
	}
	
	
	
	
	
	public static String marshalToXml(DemoClass dClass) {
		String result;
		try {
			JAXBContext context = JAXBContext.newInstance(DemoClass.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(dClass, System.out); // Writes the XML to out
			//marshaller.marshal(dClass, new File("DemoClass.xml")); // To File
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static DemoClass unmarshalFromXml(String xml) {
		
		
		return null;
	}
	public static String marshalToJson(DemoClass dClass) {
		try {
			return mapper.writeValueAsString(dClass);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static DemoClass unmarshalFromJson(BufferedReader json) {
		try {
			return mapper.readValue(json,DemoClass.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static DemoClass unmarshalFromJson(String json) {
		try {
			return mapper.readValue(json,DemoClass.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}
