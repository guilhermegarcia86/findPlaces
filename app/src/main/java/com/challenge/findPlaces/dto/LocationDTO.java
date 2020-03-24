package com.challenge.findPlaces.dto;

import lombok.Data;

@Data
public class LocationDTO {
	
	private String address;
	private String crossStreet;
	private float lat;
	private float lng;
	private float distance;
	private String cc;
	private String city;
	private String state;
	private String country;

}
