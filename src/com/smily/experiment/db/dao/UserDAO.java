package com.smily.experiment.db.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.smily.experiment.db.entity.Company;
import com.smily.experiment.db.entity.User;
import com.smily.experiment.db.transformers.UserTransformer;
import com.smily.experiment.web.dto.UserDTO;

public class UserDAO extends BaseDAO {

	/**
	 * Hibernate Query - used with entityManager.createQuery(HQL_ALL_USERS)
	 */
	private static final String HQL_ALL_USERS		 = "SELECT u FROM User u";

	/**
	 * Regular query - used with entityManager.createNativeQuery(SQL_GET_USER_BY_LOGIN);
	 */
	private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = :userLogin";

	
	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public BigInteger createUser(String login, String password, String email, BigInteger companyId) {
		final Company company = entityManager.find(Company.class, companyId);
		final User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
		user.setCompany(company);

		final User mergedUser = entityManager.merge(user);
		
		return mergedUser.getId();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		final Query query = entityManager.createQuery(HQL_ALL_USERS);
		final List<User> resultList = (List<User>) query.getResultList();

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public UserDTO findUserByLogin(String login) {
		final Query query = entityManager.createNativeQuery(SQL_GET_USER_BY_LOGIN);
		query.setParameter("userLogin", login);

		final List<Object[]> resultList = (List<Object[]>) query.getResultList();
		final List<UserDTO> usersList = new UserTransformer().transform(resultList);
		
		if (usersList.isEmpty()) {
			return null;
		}
		
		return usersList.get(0);
	}
}
