package com.challenge.findPlaces.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.findPlaces.dto.SearchDTO;
import com.challenge.findPlaces.service.FindPlacesService;
import com.challenge.findPlaces.vo.PlaceVO;

@RestController
@RequestMapping("/api")
public class FindPlacesController {

	@Autowired
	private FindPlacesService service;

	@GetMapping("/find")
	public ResponseEntity<List<PlaceVO>> getPlaces(@RequestParam(value = "longitude", required = true) String longitude,
			@RequestParam(value = "latitude", required = true) String latitude,
			@RequestParam(value = "query", defaultValue = "") String query,
			@RequestParam(value = "radius", defaultValue = "1000") String radius,
			@RequestParam(value = "closer", defaultValue = "1") String closer,
			@RequestParam(value = "popularity", defaultValue = "0") String popularity,
			@RequestParam(value = "limit", defaultValue = "10") String limit) {

		List<PlaceVO> find = service.find(new SearchDTO(longitude, latitude, query, closer, popularity, radius, limit));

		return ResponseEntity.ok(find);
	}

}
