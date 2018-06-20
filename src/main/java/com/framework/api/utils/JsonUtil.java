package com.framework.api.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static<T> String getJson(T data){
		if(data!=null) {
			try {
				return objectMapper.writeValueAsString(data);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}			
		}
		return null;
	}
	
	public static <T> T convertToPojo(String json, Class<T> t) throws Exception{
		return objectMapper.readValue(json, t);
	}
	
	public static <T> List<T> convertToListPojo(String json, Class<T> t) throws Exception{
		return objectMapper.readValue(json, new TypeReference<List<T>>(){});
	}
	
	public static void writeValue(Writer w, Object data) throws IOException {
		objectMapper.writeValue(w, data);
	}
}
