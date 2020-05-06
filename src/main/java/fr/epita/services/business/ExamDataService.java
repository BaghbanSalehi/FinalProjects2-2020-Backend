package fr.epita.services.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.datamodel.Answer;
import fr.epita.datamodel.Question;
import fr.epita.datamodel.User;
import fr.epita.services.dao.AnswerDAO;
import fr.epita.services.dao.QuestionDAO;
import fr.epita.services.dao.UserDAO;

public class ExamDataService {

	@Inject
	UserDAO userDAO;

	@Inject
	QuestionDAO questionDAO;

	@Inject
	AnswerDAO answerDAO;

	@Transactional(value = TxType.REQUIRED)
	public void asnwerToAQuestion(Question question, Answer answer) throws ExamBusinessException {

		// check values
		// if not valid : throw exception?

		// main logic part
		// check if the question exists in db, same for user
		// first : check if there is an id
		// then call dao.getbyId(obj) to check if it is there
		// otherwise : throw exception?

		// assign user and questions to the answer
		// answer.setUser(user);
		answer.setQuestion(question);

		answerDAO.create(answer);
		questionDAO.create(question);
	}

	@Transactional(value = TxType.REQUIRED)
	public void update(Question mcq, Answer choices) {

		answerDAO.update(choices);
		questionDAO.update(mcq);

	}

	public List<String> findAnwer(Answer answer) {
		Answer aa = new Answer();
		aa = answerDAO.getById(answer.getId());
		List<String> g = new ArrayList<String>();
		g.add(aa.getQuestion().getTitle());
		for (int i = 0; i < aa.getContent().length; i++)
			g.add(aa.getContent()[i]);

		return g;

		// public answerToMCQ(User user,MCQQuestion mcq, List<MCQChoices> choice);

	}

	public List<String> findQuestion() {
		List<Question> q = new ArrayList<Question>();
		List<String> g = new ArrayList<String>();
		q.addAll(questionDAO.getAll("Question"));
		for (int i = 0; i < q.size(); i++) {

			g.add(q.get(i).getTitle());

		}

		return g;
	}

	public List<Answer> findAll() {

		// List<String> g = new ArrayList<String>();
		List<Answer> a = new ArrayList<Answer>();
		a.addAll(answerDAO.getAll("Answer"));

		return a;

	}

	public void removeFunc(Question question, Answer answer) {

		answerDAO.delete(answer);
		questionDAO.delete(question);

	}

}
