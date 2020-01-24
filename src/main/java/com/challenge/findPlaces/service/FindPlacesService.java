package com.challenge.findPlaces.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.findPlaces.client.FoursquareClient;
import com.challenge.findPlaces.dto.SearchDTO;
import com.challenge.findPlaces.model.PlaceModel;
import com.challenge.findPlaces.model.PlacePK;
import com.challenge.findPlaces.repository.PlaceRepository;
import com.challenge.findPlaces.vo.PlaceVO;

@Service
public class FindPlacesService {

	@Autowired
	private FoursquareClient client;

	@Autowired
	private PlaceRepository repository;

	@Value("${foursquare.id}")
	private String client_id;

	@Value("${foursquare.secret}")
	private String client_secret;

	@Value("${foursquare.version}")
	private String v;

	public List<PlaceVO> find(SearchDTO searchDTO) {

		var venuesExplore = client.venuesExplore(this.formatLongLat(searchDTO.getLongitude(), searchDTO.getLatitude()),
				searchDTO.getRadius(), searchDTO.getQuery(), searchDTO.getLimit(), searchDTO.getCloser(),
				searchDTO.getPopularity(), client_id, client_secret, v);

		var placeList = PlaceVO.toPlaceList(venuesExplore);

		placeList.forEach(place -> {
			this.savePlace(searchDTO, place);
		});

		return placeList;

	}

	private void savePlace(SearchDTO searchDTO, PlaceVO place) {
		var placePK = new PlacePK();
		placePK.setDistance(place.getDistance());
		placePK.setCategorie(place.getCategorie());
		placePK.setName(place.getName());

		var placeModel = new PlaceModel();
		placeModel.setPk(placePK);
		placeModel.setAddress(place.getAddress());
		placeModel.setLastUpdate(new Date());

		var count = (!Objects.isNull(searchDTO.getQuery()) && !searchDTO.getQuery().isEmpty())
				? placeModel.getQueryCountText() + 1l
				: placeModel.getQueryCountText();

		placeModel.setQueryCountText(count);
		
		repository.save(placeModel);
	}

	private String formatLongLat(String longitude, String latitude) {
		return new StringBuilder().append(longitude).append(",").append(latitude).toString();
	}

}
