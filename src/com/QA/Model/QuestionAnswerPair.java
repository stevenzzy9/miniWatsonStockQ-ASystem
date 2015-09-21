package com.QA.Model;



public class QuestionAnswerPair {

	@Override
	public String toString() {
		String tmp="----------------------------------\n";
		tmp+="id:\t"+id+"\n";
		tmp+="questionType:\t"+questionType+"\n";
//		tmp+="tags:";
//		for (int i = 0; i < tags.size(); i++) {
//			tmp+=tags.get(i)+"\t";
//		}
		tmp+="question:\t"+question+"\n";
		tmp+="answerType:\t"+answerType+"\n";
		tmp+="answer:\t"+answer+"\n";
		tmp+="----------------------------------\n";
		return tmp;
	}

	// 问题答案在数据库中所存id
	private int id;
	// 问题
	private String question;
	//问题类型
	private int questionType;
	//标签
	//private ArrayList<String>tags;
	//答案类型
	private int answerType;	
	// 答案
	private Object answer;
	//公司名 ，投资家名，基本概念专有名词
	private String name;

	public QuestionAnswerPair(int id,String name,String question,int questionType,int answerType,Object answer)
	{
		this.id=id;
		this.question=question;
		this.questionType=questionType;
		//this.tags=tags;
		this.answerType=answerType;
		this.answer=answer;
		this.name=name;
	}
	

	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public int getQuestionType() {
		return questionType;
	}

//	public ArrayList<String> getTags() {
//		return tags;
//	}

	public int getAnswerType() {
		return answerType;
	}

	public Object getAnswer() {
		return answer;
	}

	public String getName() {
		return name;
	}


}
