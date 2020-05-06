package fr.epita.quiz.tests.integration;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.datamodel.Answer;
import fr.epita.datamodel.Question;
import fr.epita.datamodel.User;
import fr.epita.services.business.ExamBusinessException;
import fr.epita.services.business.ExamDataService;
import fr.epita.services.dao.AnswerDAO;
import fr.epita.services.dao.QuestionDAO;
import fr.epita.services.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestExamDataService {

	@Inject
	ExamDataService ds;

	@Inject
	AnswerDAO answerDao;

	@Inject
	UserDAO userDao;

	@Inject
	QuestionDAO questionDao;

	@Test
	public void testAnswerToQuestion() {
		// given
		User user = new User();
		user.setLoginName("test");
		user.setLoginName("test@test.com");
		userDao.create(user);

		Question question = new Question();
		question.setTitle("what is java?");
		questionDao.create(question);

		// when
		Answer answer = new Answer();
		// answer.setContent("I don't know");
		try {
			ds.asnwerToAQuestion(user, question, answer);
		} catch (ExamBusinessException e) {
			// Log exception using logger todo!
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// then
		Answer fetchedAnswer = answerDao.getById(answer.getId());
		Assert.assertEquals(fetchedAnswer.getUser().getLoginName(), user.getLoginName());

	}

}
