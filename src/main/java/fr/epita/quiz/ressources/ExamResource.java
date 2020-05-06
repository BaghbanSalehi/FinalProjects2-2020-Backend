package fr.epita.quiz.ressources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import fr.epita.datamodel.Answer;
import fr.epita.datamodel.Question;
import fr.epita.datamodel.User;
import fr.epita.services.business.ExamBusinessException;
import fr.epita.services.business.ExamDataService;
import fr.epita.services.dao.AnswerDAO;

@Path("/exam")

public class ExamResource {

	@Inject
	ExamDataService examDS;

	@Inject
	AnswerDAO dao;

	@POST
	@Path("/answer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addAnswerToQuestion(@RequestBody AnswerDTO answerd) throws ExamBusinessException {

		Answer answer = new Answer();
		answer.setContent(answerd.getContent());
		answer.setId(answerd.getId());
		System.out.println("got post with : " + answerd.getContent());
		Question question = new Question();
		question.setTitle(answerd.getQuestion());
		question.setId(answerd.getId());
		// answer.setQuestion(question);
		// call examDS on this answer
		examDS.asnwerToAQuestion(question, answer);
		System.out.println(examDS.findAnwer(answer));
		System.out.println(examDS.findQuestion());
		System.out.println(examDS.findAll());
		try {
			return Response.created(new URI("/rest/exam/answer/" + answer.getId())).build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.serverError().build();

	}

//	@GET
//	@Path("/answer/{id}")
//	@Produces(value = MediaType.APPLICATION_JSON)
//	public Response getAnswer(@PathParam("id") long answerId) {
//
//		Answer answer = new Answer();
//		answer.setContent("This is a ans with id" + answerId);
//		answer.setId(answerId);
//		return Response.ok(Arrays.asList(answer)).build();
//
//	}
//
//	@GET
//	@Path("/answer")
//	@Produces(value = MediaType.APPLICATION_JSON)
//	public Response getAnswers() {
//
//		// begin dummy
//
//		Answer answer = new Answer();
//		answer.setContent("ddd");
//		// AnswerDTO answerd = new AnswerDTO(answer);
//		System.out.println(examDS.findAll());
//		return Response.ok(Arrays.asList(answer)).build();
//
//	}

	@PUT
	@Path("/answer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response updateAnswer(@RequestBody AnswerDTO answerd) {
		Answer answer = new Answer();
		Question question = new Question();
		answer.setContent(answerd.getContent());
		answer.setId(answerd.getId());
		question.setTitle(answerd.getQuestion());
		question.setId(answerd.getId());
		answer.setQuestion(question);
		examDS.update(question, answer);
		System.out.println(examDS.findAnwer(answer));
		System.out.println(examDS.findQuestion());
		System.out.println(examDS.findAll());

		return Response.ok(Arrays.asList(answer)).build();

	}

	/**
	 * NOTE : Sending body with a http delete request from angular is not possible
	 * atm using PATCH as a work around for deleting data from database for sake of
	 * this project.
	 **/
	@PATCH
	@Path("/answer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response removeAnswer(@RequestBody AnswerDTO answerd) {
		Answer answer = new Answer();
		Question question = new Question();
		answer.setContent(answerd.getContent());
		answer.setId(answerd.getId());
		question.setTitle(answerd.getQuestion());
		question.setId(answerd.getId());

//		dao.delete(answer);
//

		examDS.removeFunc(question, answer);
		System.out.println(examDS.findAll());
		System.out.println(examDS.findQuestion());

		return Response.ok(Arrays.asList(answerd)).build();

	}

}
