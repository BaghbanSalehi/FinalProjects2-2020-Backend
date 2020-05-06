package fr.epita.datamodel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question {

	@Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "Q_TITLE")
	private String title;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "A_QUESTION_FK") // yani age
	// in pak shod hamechi pak she behesh
	// vasle ya na (weak
//											// strong)//(mappedBy = "A_QUESTION_FK")//in eyne manytoOne mimune toye
//											// answers , toye answertable soton ro misaze
	// @JoinTable(name = "ANSWERS_QUESTIONS") // ye table answer q misaze ke hame
	// toshe
	// List<Answer> answer; // dar kol manytoOne khube in darde sar dare.

	public Question() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
