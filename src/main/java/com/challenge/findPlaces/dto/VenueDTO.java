package com.challenge.findPlaces.dto;

import java.util.List;

import lombok.Data;

@Data
public class VenueDTO {
	
	private String name;
	private LocationDTO location;
	private List<CategorieDTO> categories;

}
