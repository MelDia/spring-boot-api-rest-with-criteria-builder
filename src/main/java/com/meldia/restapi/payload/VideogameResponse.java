package com.meldia.restapi.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.meldia.restapi.dto.VideogameDTO;

import lombok.Builder;

@Builder
@JsonInclude(Include.NON_NULL)
public class VideogameResponse {
	
	private String responseCode;
	private List<VideogameDTO> data;
	private String responseMessage;
	
	public VideogameResponse() {
		
	}
	
	public VideogameResponse(String responseCode, List<VideogameDTO> data, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.data = data;
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<VideogameDTO> data) {
		this.data = data;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	

}
