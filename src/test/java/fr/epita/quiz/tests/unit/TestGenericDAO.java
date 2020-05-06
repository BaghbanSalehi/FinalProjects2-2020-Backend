package fr.epita.quiz.tests.unit;

import fr.epita.datamodel.Question;
import fr.epita.services.dao.GenericDAO;
import fr.epita.services.dao.QuestionDAO;

public class TestGenericDAO {

	public void testGenericDAO() {
		// GenericDAO<Question> dao = new GenericDAO<>();// hala <Question> mire jaye Z
		// mishine ke unja mige agha in dao Question bashe
		QuestionDAO dao = new QuestionDAO();
		dao.create(new Question());

	}

}
