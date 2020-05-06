package fr.epita.services.dao;

import java.util.Map;

import fr.epita.datamodel.Answer;
import fr.epita.datamodel.Question;

public class AnswerDAO extends GenericDAO<Answer, Long> {

	@Override
	public String getQuery() {
		return "from Answer";
	}

	@Override
	public void setParam(Map<String, Object> parameters, Answer criteria) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<Answer> getEntityClass() {
		// TODO Auto-generated method stub
		return Answer.class;
	}

}
