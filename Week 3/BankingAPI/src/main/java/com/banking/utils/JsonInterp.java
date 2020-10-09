package com.banking.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.banking.models.User;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonInterp<T> {
	final Class<T> typeParamClass;
	
	public JsonInterp(Class<T> typeParamClass) {
		this.typeParamClass = typeParamClass;
	}
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public String marshal(T t) {
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String marshal(List<T> t) {
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public T unmarshal(HttpServletRequest request) {
		
		try {
			BufferedReader br = request.getReader();
			return mapper.readValue(br,typeParamClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JsonNode getNode(HttpServletRequest request) {
		try {
			return mapper.readTree(request.getReader());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
