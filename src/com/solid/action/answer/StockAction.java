package com.solid.action.answer;

import com.QA.Model.AnswerType;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 此action用来进一步处理股票类的问题答案
 * 
 * @author Jeremy Lee
 * 
 */
public class StockAction extends ActionSupport {

	/** 答案类型，和GetAnswer公用一个answerType */
	private int answerType;

	@Override
	public String execute() throws Exception {
		if (answerType == AnswerType.TYPE_STRING) {
			return "answer";
		} else if (answerType == AnswerType.TYPE_ARRAY_BONUS
				|| answerType == AnswerType.TYPE_ARRAY_EXECUTIVE
				|| answerType == AnswerType.TYPE_ARRAY_FINANCE
				|| answerType == AnswerType.TYPE_ARRAY_HISTORY
				|| answerType == AnswerType.TYPE_ARRAY_INCOME)
			return "table";
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
