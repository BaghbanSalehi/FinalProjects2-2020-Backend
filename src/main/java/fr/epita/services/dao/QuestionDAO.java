package fr.epita.services.dao;

import java.util.Map;

import fr.epita.datamodel.Question;

public class QuestionDAO extends GenericDAO<Question, Long> {

//	public List<Question> search(Question question) {
//
//		String titleP = question.getTitle();
//
////		Query searchQuery = em.createQuery("from Question where title = :pTitle", Question.class);(1)
////		searchQuery.setParameter("pTitle", titleP);(2)
////		List<Question> resultList = searchQuery.getResultList();(3)
//		return resultList;
//
//	}

//	public Question getById(Long id) {
//
//		return em.find(Question.class, id);
////		Session Session = em.openSession();
////		return Session.get(Question.class, id);
//
//	}

	@Override
	public String getQuery() {
		return "from Question where title = :pTitle";
	}

	@Override
	public void setParam(Map<String, Object> parameters, Question criteria) {

		parameters.put("pTitle", criteria.getTitle());

	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

}
