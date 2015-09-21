package com.QA.QuestionAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import com.QA.Extractor.InvestorAnsExtra;
import com.QA.Model.AnalyResult;
import com.QA.Model.InfoPosition;
import com.QA.Model.InfoSynonyms;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.Util.PathUtil;
import com.QA.Util.SimilarityUtil;


/**
 * 投资家问题分析类
 * @author li
 *
 */
public class InvestorQuesAnaly {

	private String name;//投资家名字
	private ArrayList<String>words;
	private ArrayList<String>poses;
	private String question;//问题
	private ArrayList<AnalyResult> results;//答案
	private static ArrayList<InfoSynonyms>investorProperty;
	private static HashMap<String, InfoPosition>propertyPositionMap;
	
	static {
		
		initPropertyMap();
		initSynonyms();
		
	}
	/**
	 * 初始化同义词表
	 * @param args
	 */
	private static void initSynonyms() {
		try {
			investorProperty=new ArrayList<InfoSynonyms>();
			String filePath = PathUtil.getPath() + "Files//dic//investorsynonyms.txt";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String line=in.readLine();
			while(line!=null)
			{
				String[]words=line.split("\t");
				investorProperty.add(new InfoSynonyms(words));
				
				line=in.readLine();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化属性与数据库的对应表
	 */
	private static void initPropertyMap()
	{
		try {
			propertyPositionMap=new HashMap<String,InfoPosition>();
			String filePath = PathUtil.getPath() + "Files//dic//investorinfoposition.txt";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String line=in.readLine();
			while(line!=null)
			{
				InfoPosition info=new InfoPosition(line);
				propertyPositionMap.put(info.getProperty(), info);
				line=in.readLine();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获得投资家的名字 
	 * 但是目前因为只能回答问句中有一个投资家得问题 
	 * 所以针对的是一个投资家得提取
	 */
	private void identifyInvestorName()
	{
		for(int i=0;i<poses.size();i++)
		{
			String pos=poses.get(i);
			if(pos.equals("pn"))
			{
				this.name=words.get(i);
				poses.remove(i);
				words.remove(i);
				break;
			}
		}
	}
	/**
	 * 构造函数
	 * @param question
	 * 问题
	 * @param words
	 * 分词结果
	 * @param poses
	 * 词性标注结果
	 */
	public InvestorQuesAnaly(String question,ArrayList<String>words,ArrayList<String>poses)
	{
		this.question=question;
		this.words=words;
		this.poses=poses;
	}
	
	/**
	 * 获得问句中ArrayList<String>words中词性为fw，n的词语
	 * @return
	 */
	private ArrayList<String> getKeywords()
	{
		ArrayList<String>results=new ArrayList<String>();
		for(String word:words)
		{
			if(!word.equals("cn"))
			{
				results.add(word);
			}
		}
		return results;
	}
	/**
	 * 根据words里面的词获得公司的相关属性
	 * @return
	 */
	private ArrayList<InfoSynonyms> getCompanyProperty()
	{
		ArrayList<String>keywords=getKeywords();
		ArrayList<InfoSynonyms> propertys=investorProperty;
		for(int i=keywords.size()-1;i>=0;--i)
		{
			String key=keywords.get(i);
			ArrayList<InfoSynonyms> tmpProps=getSimProperty(key, propertys);
			if(tmpProps.size()!=0&&tmpProps.size()<propertys.size())
			{
				propertys=tmpProps;
			}
			if(propertys.size()==1)
			{
				break;
			}
		}
		if(propertys.size()==investorProperty.size())
		{
			 propertys=new ArrayList<InfoSynonyms>();
		}
		return  propertys;
	}
	/**
	 * 用word与投资家的属性对比，获得最相似的属性
	 * @param word
	 * @param targetPropertys
	 * @return
	 */
	private ArrayList<InfoSynonyms> getSimProperty(String word,ArrayList<InfoSynonyms> targetPropertys)
	{
		ArrayList<InfoSynonyms> results=new ArrayList<InfoSynonyms>();
		double score=-1;
		for(InfoSynonyms property:targetPropertys)
		{
			double tmpScore=getSimScore(property.getSynonyms(), word);
			if(tmpScore>score&&tmpScore>0.8)
			{
				results.clear();
				results.add(property);
				score=tmpScore;
			}else if(tmpScore==score)
			{
				results.add(property);
			}
		}
		
		return results;
	}
	
	/**
	 * word 与targets的相识度分数 去最高值
	 * @param targets
	 * @param word
	 * @return
	 */
	private double getSimScore(ArrayList<String>targets,String word)
	{
		double score=-1;
		for(String target:targets)
		{
			double tmpScore=SimilarityUtil.getWordSimilarityScore(target, word);
			if(tmpScore>score)
			{
				score=tmpScore;
			}
		}
		return score;
	}	
	public void process()
	{
		identifyInvestorName();
		ArrayList<InfoSynonyms> propertys=getCompanyProperty();
		this.results=new ArrayList<AnalyResult>();
		if (propertys.size() != 0) {
			for (int i = 0; i < propertys.size(); i++) {
				InfoSynonyms p = propertys.get(i);
				AnalyResult result = new AnalyResult(name, propertyPositionMap.get(p.getWord()));
				results.add(result);
			}
		}else//如果没有匹配到投资家的属性 则Infoposition中填写公司名属性为空
		{
			AnalyResult result = new AnalyResult(name,null);
			results.add(result);
		}
	}
	
	public String getName() {
		return name;
	}

	public String getQuestion() {
		return question;
	}
	
	public ArrayList<AnalyResult> getResults() {
		return results;
	}

	
	public static void main(String[] args) throws IOException {

		QuestionPreprocess a = new QuestionPreprocess();
		BufferedReader in = new BufferedReader(new FileReader("investorquestion.txt"));
		PrintWriter out = new PrintWriter(new File("investresult.txt"));
		String line = in.readLine();
		while (line != null) {
			a.process(line);

			out.println("-------------------------------------------");
			out.println("问题:" + a.getQuestion());
			switch (a.getQuestionType()) {
			case QuestionType.TYPE_CONCEPT:
				out.println("基本概念");
				break;
			case QuestionType.TYPE_STOCKINFO:
				out.println("公司基本");
				break;
			case QuestionType.TYPE_PERSONINFO:
				out.println("投资家");
				break;
			}
			ArrayList<String> words = a.getWords();
			ArrayList<String> poses = a.getPoses();
			for (int i = 0; i < words.size(); i++) {
				out.print(words.get(i) + "\\" + poses.get(i) + " ");
			}
			out.println();
			InvestorQuesAnaly iqa = new InvestorQuesAnaly(a.getQuestion(), words, poses);

			iqa.process();

			line = in.readLine();

		}
		out.close();

	}

}
