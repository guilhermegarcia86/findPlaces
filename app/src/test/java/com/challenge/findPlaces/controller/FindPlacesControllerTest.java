package com.challenge.findPlaces.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.findPlaces.service.FindPlacesService;

@WebMvcTest(controllers = FindPlacesController.class)
public class FindPlacesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FindPlacesService service;

	@Test
	public void testGetBadRequest() throws Exception {
		this.mockMvc.perform(get("/api/find")).andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetSuccess() throws Exception {
		this.mockMvc.perform(get("/api/find").param("longitude", "-23.540446").param("latitude", "-46.635793")).andDo(print())
				.andExpect(status().isOk());
	}

}
