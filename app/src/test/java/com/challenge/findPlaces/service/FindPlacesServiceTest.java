package com.challenge.findPlaces.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.findPlaces.client.FoursquareClient;
import com.challenge.findPlaces.dto.CategorieDTO;
import com.challenge.findPlaces.dto.FoursaquareDTO;
import com.challenge.findPlaces.dto.GroupDTO;
import com.challenge.findPlaces.dto.ItemDTO;
import com.challenge.findPlaces.dto.LocationDTO;
import com.challenge.findPlaces.dto.ResponseDTO;
import com.challenge.findPlaces.dto.SearchDTO;
import com.challenge.findPlaces.dto.VenueDTO;
import com.challenge.findPlaces.exception.ValidatorException;
import com.challenge.findPlaces.model.PlaceModel;
import com.challenge.findPlaces.model.PlacePK;
import com.challenge.findPlaces.repository.PlaceRepository;
import com.challenge.findPlaces.vo.PlaceVO;

@SpringBootTest
public class FindPlacesServiceTest {

	@InjectMocks
	private FindPlacesService service;

	@Mock
	private FoursquareClient client;

	@Mock
	private PlaceRepository repository;

	@Test
	public void searchSuccess() {

		var entity = this.entity();
		when(repository.save(entity)).thenReturn(entity);

		var search = this.search();
		when(client.venuesExplore(any(), any(), any(), any(), any(), any(), any(), any(), any()))
				.thenReturn(this.response());

		List<PlaceVO> placeResponse = service.find(search);

		assertEquals(entity.getPk().getName(), placeResponse.get(0).getName(),
				"Name recorded in database is equals to response name");
	}

	@Test
	public void searchFail() {

		var search = this.search();
		when(client.venuesExplore(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(null);

		ValidatorException exception = assertThrows(ValidatorException.class, () -> service.find(search));
		
		assertEquals(ValidatorException.class, exception.getClass());
		assertEquals("Object cannot be null", exception.getMessage());
	}
	
	@Test
	public void searchCollectionNullFail() {

		var search = this.search();
		
		var responseDto = new ResponseDTO();
		responseDto.setGroups(List.of());
		
		var foursaquereDto = new FoursaquareDTO();
		foursaquereDto.setResponse(responseDto);
		
		when(client.venuesExplore(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(foursaquereDto);

		ValidatorException exception = assertThrows(ValidatorException.class, () -> service.find(search));
		
		assertEquals(ValidatorException.class, exception.getClass());
		assertEquals("Collection cannot be null and should contain elements", exception.getMessage());
	}

	private PlaceModel entity() {
		var id = new PlacePK();
		id.setName("Local");
		id.setDistance(1l);
		id.setCategorie("Market");

		var placeModel = new PlaceModel();
		placeModel.setPk(id);
		placeModel.setAddress("Street fake");
		placeModel.setQueryCountText(1l);
		placeModel.setLastUpdate(new Date());

		return placeModel;
	}

	private SearchDTO search() {
		var searchDto = new SearchDTO();
		searchDto.setLongitude("-23.540446");
		searchDto.setLatitude("-46.635793");

		return searchDto;
	}

	private FoursaquareDTO response() {

		var categorie = new CategorieDTO();
		categorie.setName("Market");

		var location = new LocationDTO();
		location.setAddress("Street fake");
		location.setCrossStreet("Street Parallel");
		location.setCity("Fake");
		location.setState("FK");
		location.setCountry("Brazil");
		location.setDistance(1l);

		var venue = new VenueDTO();
		venue.setName("Local");
		venue.setLocation(location);
		venue.setCategories(List.of(categorie));

		var item = new ItemDTO();
		item.setVenue(venue);

		var groupDTO = new GroupDTO();
		groupDTO.setItems(List.of(item));

		var responseDto = new ResponseDTO();
		responseDto.setGroups(List.of(groupDTO));

		var foursaquereDto = new FoursaquareDTO();
		foursaquereDto.setResponse(responseDto);

		return foursaquereDto;
	}

}
