package com.smily.experiment.db.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smily.experiment.db.entity.Company;

public class CompanyDAO extends BaseDAO {

	/**
	 * Query in Hibernate Query Language.
	 * Gets all companies. 
	 */
	private static final String HQL_GET_COMPANIES = "SELECT c FROM Company c";

	private static final String HQL_FIND_COMPANY_BY_ID = "SELECT c FROM Company c WHERE c.id = :compId";

	public CompanyDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public BigInteger createCompany(String name, BigDecimal capital, String address) {
		final Company company = new Company();
		company.setName(name);
		company.setCapital(capital);
		company.setAddress(address);

		Company mergedComp = entityManager.merge(company);
		entityManager.flush();

		return mergedComp.getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		final Query query = entityManager.createQuery(HQL_GET_COMPANIES);
		final List<Company> resultList = (List<Company>)query.getResultList();
		
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public Company findCompanyById(BigInteger companyId) {
		final Query query = entityManager.createQuery(HQL_FIND_COMPANY_BY_ID);
		query.setParameter("compId", companyId);
		
		final List<Company> compList = (List<Company>)query.getResultList();
		if (compList.isEmpty()) {
			return null;
		}
		
		return compList.get(0);
	}
}
