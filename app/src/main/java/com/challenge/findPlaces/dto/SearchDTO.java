package com.challenge.findPlaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

	private String longitude; 
	private String latitude;
	private String query; 
	private String closer;
	private String popularity; 
	private String radius;
	private String limit;

}
