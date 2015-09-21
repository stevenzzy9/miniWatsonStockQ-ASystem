package com.QA.QuestionAnalyzer;

import java.util.ArrayList;

public class OperationQuesAnaly {

	
	
	private String question;
	private ArrayList<String>words;
	private ArrayList<String>poses;
	private String result;//分析结果 一条sql语句
	
	
	public OperationQuesAnaly(String question,ArrayList<String>words,ArrayList<String>poses)
	{
		this.question=question;
		this.words=words;
		this.poses=poses;
	}
	
	
	public void process()
	{
		String query = "select * from operation where ";
		String tmp="";
		int index=0;
		for (int i = 0; i < words.size(); i++) {
			String pos=poses.get(i);
			if (pos.equals("n") ||pos.equals("ni")||pos.equals("ns")||pos.equals("nl")||
					pos.equals("nz")||pos.equals("j")||pos.equals("nh")||pos.equals("ws")||pos.equals("v")||pos.equals("fw")) {
				if (index != 0) {
					tmp += "or ";
				}
				tmp += "tag like '%" + words.get(i) + "%' ";
				tmp += "and answer like '%" + words.get(i) + "%' ";
				++index;
			}
		}
		if(!tmp.equals(""))
		{
			this.result=query+tmp;
		}else
		{
			this.result="";
		}
		
	}
	
	public String getResult() {
		return result;
	}
	
	public static void main(String[] args) {
		
		QuestionPreprocess qp=new QuestionPreprocess();
		qp.process("该如何选股?");
		
		OperationQuesAnaly a=new OperationQuesAnaly(qp.getQuestion(), qp.getWords(), qp.getPoses());
		a.process();
		System.out.println(a.getResult());
		
	}

}
