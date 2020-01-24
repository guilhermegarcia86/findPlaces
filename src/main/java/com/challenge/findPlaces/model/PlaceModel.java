package com.challenge.findPlaces.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Place")
@Getter
@Setter
@ToString
public class PlaceModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlacePK pk;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "QUERY_COUNT_TEXT")
	private long queryCountText;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlaceModel) {
			PlaceModel anotherObj = (PlaceModel) obj;

			return Objects.equals(pk, anotherObj.pk);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(pk);
	}

}
