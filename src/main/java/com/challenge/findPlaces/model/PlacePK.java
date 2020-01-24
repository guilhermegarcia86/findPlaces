package com.challenge.findPlaces.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class PlacePK implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private float distance;
	private String categorie;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlacePK) {
			PlacePK anotherObj = (PlacePK) obj;

			return Objects.equals(name, anotherObj.name) && Objects.equals(distance, anotherObj.distance)
					&& Objects.equals(categorie, anotherObj.categorie);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, distance, categorie);
	}

}
