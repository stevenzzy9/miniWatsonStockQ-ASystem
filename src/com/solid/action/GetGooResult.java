package com.solid.action;

import java.util.ArrayList;
import java.util.HashMap;

import com.QA.GoogleSearch.GoogleSearchResult;
import com.QA.Model.QuestionAnswerPair;
import com.solid.service.AnswerHandle;

public class GetGooResult {
	/** 为用户输入的需要进行处理的问题 */
	private String question;
	private ArrayList<GoogleSearchResult> gresults;

	public String execute() throws Exception {
		
		System.out.println("fafjalfja");
		System.out.println(question+"fafa");
		AnswerHandle ah = new AnswerHandle();
		setGresults(ah.getGoogleResult(question));
		System.out.println(gresults.get(0).getTitle());
		return "success";
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<GoogleSearchResult> getGresults() {
		return gresults;
	}

	public void setGresults(ArrayList<GoogleSearchResult> gresults) {
		this.gresults = gresults;
	}
}
