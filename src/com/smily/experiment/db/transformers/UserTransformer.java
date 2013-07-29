package com.smily.experiment.db.transformers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.smily.experiment.web.dto.UserDTO;

public class UserTransformer {

	public List<UserDTO> transform(List<Object[]> resultList) {
		
		if (resultList == null || resultList.isEmpty()) {
			return new ArrayList<UserDTO>();
		}
		
		final List<UserDTO> usersList = new ArrayList<UserDTO>();
		
		for (Object[] objects : resultList) {
			final UserDTO userDTO = new UserDTO();
			userDTO.setId((BigInteger) objects[0]);
			userDTO.setLogin((String) objects[1]);
			userDTO.setPassword((String) objects[2]);
			userDTO.setEmail((String)objects[3]);
			userDTO.setCompanyId((BigInteger) objects[4]);
			
			usersList.add(userDTO);
		}
		
		return usersList;
	}

}
