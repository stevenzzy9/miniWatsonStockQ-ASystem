package com.solid.action;

import com.QA.GoogleSearch.GoogleSearchResult;
import com.QA.Model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.AnswerHandle;

/**
 * 给用户的搜索问题提供对应的答案
 * 
 * @author Jereme Lee
 * 
 */
public class GetAnswer extends ActionSupport {

	private static final long serialVersionUID = -2450299159119052440L;

	/** 如果没有答案，flag为1 */
	private int flag;

	/** 调用的方法 */
	private int method;

	/** 为用户输入的需要进行处理的问题 */
	private String question;
	/** 问题的种类 */
	private int questionType;
	/** 返回的答案线性表 */
	private ArrayList<QuestionAnswerPair> answers;
	/** 使答案为表格，更容易显示.与公司信息的action中的表格相匹配 */
	private Object moreInfo;
	/** 答案的类型 */
	private int answerType;
	/** 为答案中的title */
	private String title;
	/** 为公司信息的tag */
	private int tag;
	/** 若问题种类为个股信息的时候，则需要id属性 ，id为公司在数据库的id */
	private int id;
	/** 用户选择回答问题的tag，1-6分别为全部、基础知识、个股信息、人物介绍、股票操作、公司新闻 */
	private int type;
	/** 给答案整理数据，便于显示 */
	private Map<String, ArrayList<QuestionAnswerPair>> answerCate;
	/** google results */
	private ArrayList<GoogleSearchResult> gresults;

	public String execute() throws Exception {
		answerCate = new HashMap<String, ArrayList<QuestionAnswerPair>>();
		System.out.println(question);
		AnswerHandle ah = new AnswerHandle();
		if (method == 0)
			setAnswers(ah.getAnswer(question));
		else
			setAnswers(ah.getAnswer(question, method));
		//setGresults(ah.getGoogleResult(question));
		if (answers == null)
			return "failed";
		if (answers.size() == 0) {
			flag = 1;
		} else {
			setAnswerType(answers.get(0).getAnswerType());
			setQuestionType(answers.get(0).getQuestionType());
			setMoreInfo(answers.get(0).getAnswer());
			setTitle(answers.get(0).getQuestion());
			setId(answers.get(0).getId());
		}
		for (QuestionAnswerPair answer : answers) {
			String name = answer.getName();
			if (answerCate.containsKey(name))
				answerCate.get(name).add(answer);
			else {
				ArrayList<QuestionAnswerPair> tem = new ArrayList<QuestionAnswerPair>();
				tem.add(answer);
				answerCate.put(name, tem);
			}
		}

		/*
		 * switch (answerType) { case AnswerType.TYPE_STRING:
		 * System.out.println("类型为String"); return "STRING"; case
		 * AnswerType.TYPE_INVESTOR: System.out.println("类型为Investor"); return
		 * "INVESTROR"; case AnswerType.TYPE_ARRAY_INCOME:
		 * System.out.println("类型为income"); setTag(5); return "INCOME"; case
		 * AnswerType.TYPE_ARRAY_HISTORY: setTag(4);
		 * System.out.println("类型为history"); return "HISTORY"; case
		 * AnswerType.TYPE_ARRAY_FINANCE: System.out.println("类型为finance");
		 * setTag(2); return "FINANCE"; case AnswerType.TYPE_ARRAY_EXECUTIVE:
		 * System.out.println("类型为executive"); setTag(1); return "EXECUTIVE";
		 * case AnswerType.TYPE_ARRAY_BONUS: setTag(3);
		 * System.out.println("类型为bonus"); return "BONUS"; case
		 * AnswerType.TYPE_NULL: System.out.println("类型为null"); return "NULL";
		 * default: return "failed"; }
		 */

		switch (questionType) {
		case QuestionType.TYPE_CONCEPT:
			return "concept";
		case QuestionType.TYPE_STOCKINFO:
			return "stock";
		case QuestionType.TYPE_PERSONINFO:
			return "person";
		case QuestionType.TYPE_OPERATION:
			return "operation";
		default:
			return "failed";
		}
	}

	public Map<String, ArrayList<QuestionAnswerPair>> getAnswerCate() {
		return answerCate;
	}

	public void setAnswerCate(
			Map<String, ArrayList<QuestionAnswerPair>> answerCate) {
		this.answerCate = answerCate;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public Object getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(Object moreInfo) {
		this.moreInfo = moreInfo;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<QuestionAnswerPair> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<QuestionAnswerPair> answers) {
		this.answers = answers;
	}

	public int getAnswerType() {
		return answerType;
	}

	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public ArrayList<GoogleSearchResult> getGresults() {
		return gresults;
	}

	public void setGresults(ArrayList<GoogleSearchResult> gresults) {
		this.gresults = gresults;
	}

}
