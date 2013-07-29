package com.smily.experiment.db.dao;

import javax.persistence.EntityManager;

public class BaseDAO {

	protected EntityManager entityManager;
	
	public BaseDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
