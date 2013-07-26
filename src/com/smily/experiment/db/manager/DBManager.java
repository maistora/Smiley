package com.smily.experiment.db.manager;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smily.experiment.db.util.DBConnectionProvider;

public class DBManager<RESULT_TYPE> {

	protected EntityManager entityManager;
	protected String sql;
	protected Map<String, Object> queryParams;
	protected RESULT_TYPE result; 

	public DBManager(String sql, Map<String, Object> queryParams) {
		this.sql = sql;
		this.queryParams = queryParams;
	}
	
	public RESULT_TYPE executeQuery() {
		begin();
		result = executeQueryByCondition();
		finish();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private RESULT_TYPE executeQueryByCondition() {
		final Query query = buildNativeQuery();
		if (isSelectQuery()) {
			return (RESULT_TYPE) query.getResultList();
		} else {
			return (RESULT_TYPE) (new Integer(query.executeUpdate()));
		}
	}
	
	private Query buildNativeQuery() {
		final Query query = entityManager.createNativeQuery(sql);
		for (String paramName : queryParams.keySet()) {
			query.setParameter(paramName, queryParams.get(paramName));
		}
		
		return query;
	}

	private void begin() {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		entityManager = DBConnectionProvider.getEntityManager();
	}

	private void finish() {
		DBConnectionProvider.rollbackTransaction();
		DBConnectionProvider.close();
	}
	
	private boolean isSelectQuery() {
		return sql.trim().toLowerCase().startsWith("select");
	}
}
