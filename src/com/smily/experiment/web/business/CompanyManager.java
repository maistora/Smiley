package com.smily.experiment.web.business;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import com.smily.experiment.db.dao.CompanyDAO;
import com.smily.experiment.db.entity.Company;
import com.smily.experiment.db.util.DBConnectionProvider;

public class CompanyManager {

	public BigInteger createCompany(String companyName, BigDecimal capital, String companyAddress) {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();
		final CompanyDAO companyDAO = new CompanyDAO(entityManager);

		final BigInteger compId = companyDAO.createCompany(companyName, capital, companyAddress);

		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();
		
		return compId;
	}
	
	public void listCompanies() {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();
		
		final CompanyDAO companyDAO = new CompanyDAO(entityManager);
		
		final List<Company> companies = companyDAO.getCompanies();
		for (Company company : companies) {
			System.out.println(company);
		}

		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();		
	}

	public Company findCompanyById(BigInteger companyId) {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();
		
		final CompanyDAO companyDAO = new CompanyDAO(entityManager);
		final Company company = companyDAO.findCompanyById(companyId);

		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();
		
		return company;
	}
}
