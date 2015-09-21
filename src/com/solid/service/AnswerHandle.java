package com.solid.service;

import java.util.ArrayList;

import com.QA.Extractor.OperationAnsExtra;
import com.QA.GoogleSearch.GoogleSearchResult;
import com.QA.GoogleSearch.GoogleSearcher;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Process.AnswerExtractor;
import com.QA.Process.GoogleProcesser;
import com.solid.service.AnswerHandle;

/**
 * 处理问题的业务逻辑
 * 
 * @author Jeremy Lee
 * 
 */
public class AnswerHandle {

	/**
	 * 
	 * @param 未经处理的问题
	 * @return 答案集
	 */
	public ArrayList<QuestionAnswerPair> getAnswer(String question) {
		AnswerExtractor ae = new AnswerExtractor();
		ArrayList<QuestionAnswerPair> answers = ae.process(question);

		return answers;
	}

	public ArrayList<QuestionAnswerPair> getAnswer(String question, int method) {
		AnswerExtractor ae = new AnswerExtractor();
		ArrayList<QuestionAnswerPair> answers = new ArrayList<QuestionAnswerPair>();
		System.out.println(method);
		if (method == 1)
			answers = ae.conceptProcess(question);
		else if (method == 2)
			answers = ae.companyInfoProcess(question);
		else if (method == 3)
			answers = ae.investorProcess(question);
		else if (method == 4)
			answers = ae.operationAnsProcess(question);
		return answers;
	}

	public ArrayList<GoogleSearchResult> getGoogleResult(String question) {
		// GoogleProcesser pre=new GoogleProcesser();
		// pre.process(question);
		// ArrayList<GoogleSearchResult> results=pre.getResult();
		GoogleSearcher g = new GoogleSearcher();
		g.search(question);
		ArrayList<GoogleSearchResult> results = g.getSearchResults();
		ArrayList<GoogleSearchResult> tmp = new ArrayList<GoogleSearchResult>();
		for (int i = 0; i < results.size() && i < 3; i++) {
			tmp.add(results.get(i));
		}
		return tmp;
	}

}