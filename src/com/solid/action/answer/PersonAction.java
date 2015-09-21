package com.solid.action.answer;

import com.QA.Model.AnswerType;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 此action用来进一步处理人物类的问题答案
 * 
 * @author Jeremy Lee
 * 
 */
public class PersonAction extends ActionSupport {

	/** 答案类型，和GetAnswer公用一个answerType */
	private int answerType;

	@Override
	public String execute() throws Exception {
		if (answerType == AnswerType.TYPE_STRING)
			return "answer";
		else if (answerType == AnswerType.TYPE_NULL)
			return "null";
		return "failed";
	}

	public int getAnswerType() {
		return answerType;
	}

	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}

}
