package fr.epita.quiz.ressources;

import java.util.List;

import fr.epita.datamodel.Answer;
import fr.epita.datamodel.Question;

public class AnswerDTO {

	private String content[];
	private long id;
	private String question;

	public AnswerDTO(Answer entity) {
		this.content = entity.getContent();
		this.id = entity.getId();
		this.question = entity.getQuestion().getTitle();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public AnswerDTO() {

	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

}
