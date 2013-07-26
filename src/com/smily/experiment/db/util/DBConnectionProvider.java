package com.smily.experiment.db.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * This class provides connection to the database.
 * It uses the Hibernates's JPA implementation - "smiley-hibernate-unit" (see persistence.xml)
 */
public class DBConnectionProvider {

	private static final String PERSISTENCE_UNIT = "smiley-hibernate-unit";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction transaction;
	
	/**
	 * Initialize fields.
	 * Before class in Unit tests.
	 */
	public static void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	/**
	 * Close resources - entityManagerFactory and entityManager
	 * After class in Unit tests.
	 */
	public static void close() {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	/**
	 * Begins transaction.
	 * Before methods in Unit tests.
	 */
	public static void beginTransaction() {
		transaction.begin();
	}

	/**
	 * Not used in Unit tests - we do not need to mess our database when testing.
	 */
	public static void commitTransaction() {
		transaction.commit();
	}
	
	/**
	 * After methods in Unit tests. 
	 */
	public static void rollbackTransaction() {
		transaction.rollback();
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static EntityTransaction getTransaction() {
		return transaction;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DBConnectionProvider.init();
		final EntityManager em = DBConnectionProvider.getEntityManager();
		
		DBConnectionProvider.beginTransaction();
		final Query query = em.createNativeQuery("SELECT * FROM dummy");
		final List<String> result = (List<String>)query.getResultList();
		if (result.size() > 0) {
			System.out.println("Its working!");
			for (String val : result) {
				System.out.println(val);
			}
		} else {
			System.out.println("Damn! It's not working :(");
		}
		
		DBConnectionProvider.rollbackTransaction();
		DBConnectionProvider.close();
	}
}
