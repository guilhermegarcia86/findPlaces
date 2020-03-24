package com.challenge.findPlaces.validator;

import java.util.Collection;

import com.challenge.findPlaces.exception.ValidatorException;

import static java.util.Objects.isNull;

public class Validator {
	
	public static <E> Collection<E> validate(Collection<E> collection) throws ValidatorException {
		if(isNull(collection) || collection.isEmpty()) {
			throw new ValidatorException("Collection cannot be null and should contain elements");
		}
		
		return collection;
	}
	
	public static Object validate(Object obj) throws ValidatorException {
		if(isNull(obj)) {
			throw new ValidatorException("Object cannot be null");
		}
		return obj;
	}

}
