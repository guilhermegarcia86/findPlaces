package com.challenge.findPlaces.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.challenge.findPlaces.dto.FoursaquareDTO;

@FeignClient(name = "foursquare", url = "https://api.foursquare.com")
public interface FoursquareClient {

	@GetMapping(value = "/v2/venues/explore", consumes = MediaType.APPLICATION_JSON_VALUE)
	FoursaquareDTO venuesExplore(@RequestParam(value = "ll", required = true) String ll,
			@RequestParam(value = "radius", defaultValue = "1000") String radius,
			@RequestParam(value = "query", defaultValue = "") String query,
			@RequestParam(value = "limit", defaultValue = "1") String limit,
			@RequestParam(value = "sortByDistance", defaultValue = "1") String sortByDistance,
			@RequestParam(value = "sortByPopularity", defaultValue = "0") String sortByPopularity,
			@RequestParam(value = "client_id", required = true) String client_id,
			@RequestParam(value = "client_secret", required = true) String client_secret,
			@RequestParam(value = "v", required = true) String v);

}
