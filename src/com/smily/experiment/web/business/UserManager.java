package com.smily.experiment.web.business;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import com.smily.experiment.db.dao.UserDAO;
import com.smily.experiment.db.entity.User;
import com.smily.experiment.db.util.DBConnectionProvider;
import com.smily.experiment.web.dto.UserDTO;

public class UserManager {

	public BigInteger createUser(String login, String password, String email, BigInteger companyId) {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();

		final UserDAO userDAO = new UserDAO(entityManager);
		final BigInteger userId = userDAO.createUser(login, password, email, companyId);

		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();
		
		return userId;
	}

	public List<User> listUsers() {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();

		final UserDAO userDAO = new UserDAO(entityManager);
		final List<User> users = userDAO.getAllUsers();
		
		for (User user : users) {
			System.out.println(user);
		}

		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();
		
		return users;
	}
	
	public UserDTO findUserByLogin(String login) {
		DBConnectionProvider.init();
		DBConnectionProvider.beginTransaction();

		final EntityManager entityManager = DBConnectionProvider.getEntityManager();

		final UserDAO userDAO = new UserDAO(entityManager);
		final UserDTO userDTO = userDAO.findUserByLogin(login);
		
		DBConnectionProvider.commitTransaction();
		DBConnectionProvider.close();
		
		return userDTO;
	}
}
