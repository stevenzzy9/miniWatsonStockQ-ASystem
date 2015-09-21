package com.QA.QuestionAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import com.QA.Model.InfoSynonyms;
import com.QA.Model.AnalyResult;
import com.QA.Model.InfoPosition;
import com.QA.Model.QuestionType;
import com.QA.NLP.LTP;
import com.QA.Util.PathUtil;
import com.QA.Util.SimilarityUtil;


/**
 * 个股相关问题分析类
 * @author li
 *
 */
public class CompInfoQuesAnaly {

	private String companyName;//公司名称
	private ArrayList<String> words;
	private ArrayList<String> poses;
	private String question;//问题
	private ArrayList<AnalyResult> results;//答案
	private static ArrayList<InfoSynonyms> companyPropertys;
	private static HashMap<String,InfoPosition> propertyPositionMap;;
	static{
		initSynonyms();//初始化同义词表
		initPropertyMap();
	}
	/**
	 * 初始化同义词表
	 * @param args
	 */
	private static void initSynonyms() {
		try {
			companyPropertys=new ArrayList<InfoSynonyms>();
			String filePath = PathUtil.getPath() + "Files//dic//test.txt";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String line=in.readLine();
			while(line!=null)
			{
				String[]words=line.split("\t");
				companyPropertys.add(new InfoSynonyms(words));
				
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
			String filePath = PathUtil.getPath() + "Files//dic//companyinfoposition.txt";
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
	 * 获得公司的名称 
	 * 但是目前因为只能回答问句中有一个公司名得问题 
	 * 所以针对的是一个公司名得提取
	 */
	private void identifyCompanyName()
	{
		for(int i=0;i<poses.size();i++)
		{
			String pos=poses.get(i);
			if(pos.equals("cn"))
			{
				this.companyName=words.get(i);
				poses.remove(i);
				words.remove(i);
				break;
			}
		}
	}
	
	/**
	 * 构造函数
	 * @param question
	 * @param words
	 * @param poses
	 */
	public CompInfoQuesAnaly(String question,ArrayList<String> words,ArrayList<String> poses)
	{
		this.question=question;
		this.words=words;
		this.poses=poses;
	}
	
	/**
	 * 获得相匹配的公司属性
	 * @return
	 */
	private ArrayList<InfoSynonyms>getCompanyProperty()
	{
		ArrayList<String>keywords=getKeywords();
		ArrayList<InfoSynonyms> propertys=companyPropertys;
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
		if(propertys.size()==companyPropertys.size())
		{
			 propertys=new ArrayList<InfoSynonyms>();
		}
		return  propertys;
	}
	/**
	 * 获得与word相匹配的公司属性
	 * @param word
	 * 输入的word
	 * @param targetPropertys
	 * 要匹配的公司属性
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
	 * 处理
	 */
	public void process()
	{
		identifyCompanyName();
		ArrayList<InfoSynonyms> propertys=getCompanyProperty();
		this.results=new ArrayList<AnalyResult>();
		if (propertys.size() != 0) {
			for (int i = 0; i < propertys.size(); i++) {
				InfoSynonyms p = propertys.get(i);
				AnalyResult result = new AnalyResult(companyName, propertyPositionMap.get(p.getWord()));
				results.add(result);
			}
		}else//如果没有匹配到公司的属性 则result中填写公司名 属性为空
		{
			AnalyResult result = new AnalyResult(companyName,null);
			results.add(result);
		}
	}
	

	public String getQuestion() {
		return question;
	}

	
	public String getCompanyName() {
		return companyName;
	}

	public ArrayList<AnalyResult> getResults() {
		return results;
	}

	public static void main(String[] args) throws IOException {
		QuestionPreprocess a = new QuestionPreprocess();
		BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream("companyquestion.txt"),"utf-8"));
		PrintWriter out =new PrintWriter(new File("testResult.txt"));
//		
//	
	//	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		//String line=in.readLine().trim();
		//ArrayList<String>line=new ArrayList<String>();
		//String line="浦发银行的在哪里注册的 ";
		String line=in.readLine();
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
			}
			ArrayList<String> words = a.getWords();
			ArrayList<String> poses = a.getPoses();
			for (int i = 0; i < words.size(); i++) {
				out.print(words.get(i) + "\\" + poses.get(i) + " ");
			}
			out.println();
		CompInfoQuesAnaly ciq=new CompInfoQuesAnaly(a.getQuestion(),words,poses);
	//	double score1=ciq.getSimilarity("分红送配", words);
//		double socre2=ciq.getSimilarity("公司简介", words);
		ciq.process();
		
		//AnalyResult result=ciq.getResult();
		//System.out.println(result.getName());
		//InfoPosition infop=result.getInfoPosition();
		//System.out.println(infop.getTable()+"   "+infop.getColumn());
		//System.out.println(str);
		//int i=123;
		out.println("---------------------------");
		line=in.readLine();	
		
		}
		out.close();
		
			}

		
		
	}

	


