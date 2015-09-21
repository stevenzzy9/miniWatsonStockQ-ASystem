package com.QA.Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.QA.Extractor.CompanyInfoExtra;
import com.QA.Extractor.ConceptAnsExtra;
import com.QA.Extractor.InvestorAnsExtra;
import com.QA.Extractor.OperationAnsExtra;
import com.QA.Model.AnalyResult;
import com.QA.Model.InfoPosition;
import com.QA.Model.KeywordPair;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.QuestionAnalyzer.CompInfoQuesAnaly;
import com.QA.QuestionAnalyzer.ConceptQuesAnaly;
import com.QA.QuestionAnalyzer.InvestorQuesAnaly;
import com.QA.QuestionAnalyzer.OperationQuesAnaly;
import com.QA.QuestionAnalyzer.QuestionPreprocess;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class AnswerExtractor {

	private String question;
	private ArrayList<String>words;
	private ArrayList<String>poses;
	public ArrayList<QuestionAnswerPair> operationAnsProcess(String question)
	{
		long t1=System.currentTimeMillis();
		System.out.println("Start……………………");
		ArrayList<QuestionAnswerPair> answers=OperationAnsExtra.getAnswers(question);
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
		return answers;
	}
	private ArrayList<QuestionAnswerPair> conceptProcess()
	{
		ConceptQuesAnaly cqa = new ConceptQuesAnaly(question, words, poses);
		ArrayList<KeywordPair> keywordpairs = cqa.process();
		for(KeywordPair pair:keywordpairs)
		{
			System.out.println(pair.getKeyword1()+"\t"+pair.getKeyword2());
		}
		ArrayList<QuestionAnswerPair> questionAnswerPairs = ConceptAnsExtra.getAnswer(keywordpairs);
	
		return questionAnswerPairs;
	}
	
	private ArrayList<QuestionAnswerPair> companyInfoProcess()
	{
		CompInfoQuesAnaly ciq = new CompInfoQuesAnaly(question, words, poses);
		ciq.process();
		ArrayList<AnalyResult> results = ciq.getResults();
		for(AnalyResult result:results)
		{
			System.out.println(result);
		}
		ArrayList<QuestionAnswerPair> answers= CompanyInfoExtra.getAnswers(results);
		return answers;
	}

	private ArrayList<QuestionAnswerPair> investorProcess() {
		InvestorQuesAnaly iqa = new InvestorQuesAnaly(question, words, poses);
		iqa.process();
		ArrayList<AnalyResult> results = iqa.getResults();
		for(AnalyResult result:results)
		{
			System.out.println(result);
		}
		ArrayList<QuestionAnswerPair> answers= InvestorAnsExtra.getAnswers(results);
		return answers;
	}
	public  ArrayList<QuestionAnswerPair> conceptProcess(String question)
	{
		QuestionPreprocess qp = new QuestionPreprocess();
		qp.process(question);
		this.question = qp.getQuestion();
		this.words = qp.getWords();
		this.poses = qp.getPoses();
		System.out.println("-----------------结束-----------------------");
		return conceptProcess();
	}
	public  ArrayList<QuestionAnswerPair> investorProcess(String question)
	{
		QuestionPreprocess qp = new QuestionPreprocess();
		qp.process(question);
		this.question = qp.getQuestion();
		this.words = qp.getWords();
		this.poses = qp.getPoses();
		System.out.println("-----------------结束-----------------------");
		return investorProcess();
	}
	public  ArrayList<QuestionAnswerPair> companyInfoProcess(String question)
	{
		QuestionPreprocess qp = new QuestionPreprocess();
		qp.process(question);
		this.question = qp.getQuestion();
		this.words = qp.getWords();
		this.poses = qp.getPoses();
		System.out.println("-----------------结束-----------------------");
		return companyInfoProcess();
	}
	public  ArrayList<QuestionAnswerPair> process(String question)
	{
		QuestionPreprocess qp = new QuestionPreprocess();
		qp.process(question);
		int questionType = qp.getQuestionType();
		this.question = qp.getQuestion();
		this.words = qp.getWords();
		this.poses = qp.getPoses();
//		ArrayList<QuestionAnswerPair> answers=new ArrayList<QuestionAnswerPair>();
//		ArrayList<QuestionAnswerPair> answers=operationAnsProcess(question);
//		if(answers.size()!=0)
//		{
//			return answers;
//		}
		System.out.println("-----------------结束-----------------------");
		switch (questionType) {
		case QuestionType.TYPE_PERSONINFO:
			//answers.addAll(investorProcess());
			//break;
			return investorProcess();
		case QuestionType.TYPE_STOCKINFO:
			//answers.addAll(CompanyInfoProcess());
			//break;
			return companyInfoProcess();
		case QuestionType.TYPE_CONCEPT:
			//answers.addAll(conceptProcess());
			//break;
			return conceptProcess();
		default:
				return null;
		}
//		if(answers.size()==1&&answers.get(0).getAnswer()==null)
//		{
//			ArrayList<QuestionAnswerPair> tmp=OperationAnsProcess(question);
//			if(tmp.size()!=0)
//			{
//				answers.clear();
//				answers.addAll(tmp);
//			}
//		}
		//return answers;

	}
	
	public static void main(String args[])
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in,"GBK"));
			String question;
			AnswerExtractor tmp=new AnswerExtractor();
			question = in.readLine();
			while (question != null) {
				ArrayList<QuestionAnswerPair>answers=tmp.process(question);
				if(answers!=null)
					for(int i=0;i<answers.size();i++)
					{
						System.out.println(answers.get(i));
					}
				question=in.readLine();	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
