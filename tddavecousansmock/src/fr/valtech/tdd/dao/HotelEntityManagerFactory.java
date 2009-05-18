package fr.valtech.tdd.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HotelEntityManagerFactory {
	private static final String PERSISTENCE_UNIT_NAME = "chambres";

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public static EntityManagerFactory getFactory() {
		return factory;
	}

}
