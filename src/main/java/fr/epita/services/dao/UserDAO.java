package fr.epita.services.dao;

import java.util.Map;

import fr.epita.datamodel.User;

public class UserDAO extends GenericDAO<User, String> {

	@Override
	public String getQuery() {
		return "from User where email = :pEmail";
	}

	@Override
	public void setParam(Map<String, Object> parameters, User criteria) {
		parameters.put("pEmail", criteria.getEmail());
	}

	@Override
	public Class<User> getEntityClass() {

		return User.class;
	}

}
