package com.challenge.findPlaces.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ErrorResponseDTO {
	
	private UUID id;
	private String type;
	private String errorMessage;
	private String source;
	
	public ErrorResponseDTO(Exception e, String source, String errorMessage){
		this.id = java.util.UUID.randomUUID();
		this.type = e.getClass().getName();
		this.errorMessage = errorMessage.isEmpty() ? e.getMessage() : errorMessage;
		this.source = source;
	}

	public ErrorResponseDTO(Exception e, String source){
		this(e,source, "");
	}

}
