package com.challenge.findPlaces.vo;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;

import com.challenge.findPlaces.dto.CategorieDTO;
import com.challenge.findPlaces.dto.FoursaquareDTO;
import com.challenge.findPlaces.dto.ItemDTO;
import com.challenge.findPlaces.dto.LocationDTO;
import com.challenge.findPlaces.exception.ValidatorException;
import com.challenge.findPlaces.validator.Validator;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
public final class PlaceVO {

	private String name;
	private float distance;
	private String categorie;
	private String address;

	private PlaceVO() {
	}

	public static List<PlaceVO> toPlaceList(final FoursaquareDTO foursquare) throws ValidatorException {

		Validator.validate(foursquare);
		var groups = foursquare.getResponse().getGroups();
		Validator.validate(groups);

		var placeList = groups.stream().map(group -> {

			var items = group.getItems();
			Validator.validate(items);

			return extractedPlaces(items);

		});

		return placeList.flatMap(list -> list.stream()).collect(Collectors.toList());
	}

	private static List<PlaceVO> extractedPlaces(List<ItemDTO> items) {
		List<PlaceVO> places = items.stream().map(item -> {

			var venue = item.getVenue();
			Validator.validate(venue);

			var location = venue.getLocation();
			Validator.validate(location);

			var categories = venue.getCategories();
			Validator.validate(categories);

			var place = new PlaceVO();
			place.name = venue.getName();
			place.distance = location.getDistance();
			place.categorie = categories.stream().map(CategorieDTO::getName).findAny().get();
			place.address = formatAddress(location);

			return place;
		}).collect(Collectors.toList());
		return places;
	}

	private static String formatAddress(final LocationDTO location) {

		// @formatter:off			
		var sb = new StringBuilder().append(location.getAddress());
		
		if(!isNull(location.getCrossStreet())) {
			sb.append(" (prox. ")
			  .append(location.getCrossStreet())
			  .append(")");
		}		
		
		if(!isNull(location.getCity()) || !isNull(location.getState())) {
			sb.append(", ")
			  .append(location.getCity())
			  .append(", ")
			  .append(location.getState());
		}
		
		sb.append(" - ")
		  .append(location.getCountry());
		// @formatter:on

		return sb.toString();
	}
}
