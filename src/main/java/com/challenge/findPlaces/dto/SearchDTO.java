package com.challenge.findPlaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDTO {

	private String longitude; 
	private String latitude;
	private String query; 
	private String closer;
	private String popularity; 
	private String radius;
	private String limit;

}
