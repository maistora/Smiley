package com.smily.experiment.db.dao;

import java.util.List;

import com.smily.experiment.db.manager.DBManager;
import com.smily.experiment.web.dto.UserDTO;

public class UserDAO {
	
	private static final String SQL_ALL_USERS = "SELECT * FROM user"; 

	public List<UserDTO> getAllUsers() {
		final DBManager<List<Object[]>> dbManager = new DBManager<List<Object[]>>(SQL_ALL_USERS, null);
		final List<Object[]> result = dbManager.executeQuery();

		for (Object[] objects : result) {
			for (Object object : objects) {
				System.out.println(object);
			}
			System.out.println();
		}
		return null;
	}
}
