package fr.epita.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class Answer {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "A_CONTENT")
	private String[] content;

	@ManyToOne(cascade = CascadeType.ALL) // hibarenate mifahme ke beyne q and a
	// relation
	// hast va
	// rabetashun 1 be n hast
	// baraye 1 soal n ta javab(a daneshjuhaye mokhtalef)
	@JoinColumn(name = "A_QUESTION_FK")
	private Question question;

//	@ManyToOne
//	@JoinColumn(name = "A_USER_FK")
//	private User user;

	public Answer() {

	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
