package com.challenge.findPlaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.findPlaces.model.PlaceModel;
import com.challenge.findPlaces.model.PlacePK;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, PlacePK> {

}
