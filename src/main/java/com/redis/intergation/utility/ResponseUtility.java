package com.redis.intergation.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtility {
	
	public static final String RESPONSE = "response";
	public static final String SUCCESS = "success";
	final HttpHeaders httpHeaders;
	
	ResponseUtility(){
	    httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}
    
	public <T> ResponseEntity<T> setResponse(T t,HttpStatus httpStatus){
		return new ResponseEntity<T>(t,httpStatus);
	}
	
	public Map<String,String> getResponse(String responseMessage){
		Map<String,String> reponseMap = new HashMap<>();
		reponseMap.put(RESPONSE, responseMessage);
		return reponseMap;
	}
}
