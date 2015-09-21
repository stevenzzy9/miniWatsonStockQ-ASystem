package com.QA.Extractor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Properties;

import com.QA.Model.AnswerType;
import com.QA.Model.KeywordPair;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.NLP.LTP;
import com.QA.Util.DBUtil;
import com.QA.Util.PathUtil;
import com.QA.Util.SimilarityUtil;

import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.lsa.LatentSemanticAnalysis;
import edu.ucla.sspace.vector.DoubleVector;

public class ConceptAnsExtra {

	private static HashSet<String> stopwordsDictionary;

	static {
		initStopwordsDictionary();
	}
	/**
	 * 删除停用词
	 * @param words
	 * @param poses
	 */
	private static void deleteStopWord(ArrayList<String> words) {
		for (int i = 0; i < words.size(); i++) {
			if (stopwordsDictionary.contains(words.get(i))) {
				words.remove(i);
				//poses.remove(i);
				i--;
			}
		}
	}
	/**
	 * 初始化停用词表
	 */
	private static void initStopwordsDictionary() {
		try {
			String filePath = PathUtil.getPath() + "Files/dic/stopwords.txt";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String line = in.readLine();
			stopwordsDictionary = new HashSet<String>();
			while (line != null) {
				stopwordsDictionary.add(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入(k1,k2,type)对baike2表查询活的title answer.
	 * 
	 * @param KeywordPair
	 *            关键字对
	 * @return 问题答案对线性表
	 */
	private static ArrayList<QuestionAnswerPair> getAnswer(KeywordPair pair) {
		String k1 = pair.getKeyword1();
		String k2 = pair.getKeyword2();
		System.out.println("\n" + pair);
		ArrayList<QuestionAnswerPair> answers = new ArrayList<QuestionAnswerPair>();

		String query="select * from baikeview where word = ?";
		//String query="select * from dictionaryview where word = ?";
		ArrayList<Object>params=new ArrayList<Object>();
		params.add(k1);
		try {
			ResultSet rs = DBUtil.query(query, params);
			while (rs.next()) {
				String keyword = rs.getString("keyword");
				System.out.println("\n------["+k2+"]\t["+ keyword + "]-----------");
				String[] keys = keyword.split("\\s");
				if(isSimilarity(keys, k2))
				{
					answers.add(new QuestionAnswerPair(rs.getInt("id"),
							rs.getString("word"),
							rs.getString("title"),
							QuestionType.TYPE_CONCEPT,
							AnswerType.TYPE_STRING,
							rs.getString("answer")));
					System.out.println("匹配成功");
				}else
				{
					System.out.println("匹配失败");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * 判断keys[]与keyword是否相识
	 * @param keys
	 * @param keyword
	 * @return
	 */
	private static boolean isSimilarity(String[] keys,String keyword)
	{
		for (String key : keys) {
			if (key.length()<5&&SimilarityUtil.analyseSimilarity(key,keyword) == true) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * 传入ArrayList<(k1,k2)>pair活的title answer. 不同的type使用不同的函数进行搜索
	 * 
	 * @param 关键字对线性表
	 * @return问题答案线性表
	 */
	public static ArrayList<QuestionAnswerPair> getAnswer(ArrayList<KeywordPair> keywords) {
		ArrayList<QuestionAnswerPair> answers = new ArrayList<QuestionAnswerPair>();
		for (KeywordPair keyword : keywords) {
			switch (keyword.getType()) {
			case KeywordPair.TYPE_COMMON:
				answers.addAll(getAnswer(keyword));
				break;
			case KeywordPair.TYPE_SEARCHSAMEKEYWORD:
				answers.addAll(getAnswer(searchSameKeyword(keyword)));
				break;
			case KeywordPair.TYPE_SEARCHTEXT:
				answers.addAll(searchText(keyword));
			default:
				break;
			}
		}
		return clearAnswer(keywords,answers);

	}
	
	/**
	 * 在k1的answer中去搜索与问题最相似的答案
	 * @param pair
	 * @return
	 */
	private static ArrayList<QuestionAnswerPair> searchText(KeywordPair pair) {
		ArrayList<QuestionAnswerPair> answers=new ArrayList<QuestionAnswerPair>();
		String keyword=pair.getKeyword1();
		String question=pair.getKeyword2();
		LTP ltp=new LTP();
		ltp.createDOMFromString(question);
		ltp.posTag();
		ArrayList<String>words=ltp.getWordsFromSentence(0);
		deleteStopWord(words);
		String query = "select * from baikeview where baikeview.word=?";
		//String query="select * from baike2view where baike2view.word=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(keyword);
		ArrayList<Document> documents = new ArrayList<Document>();

		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				Document document = new Document(
						res.getInt("id"),
						res.getString("word"),
						res.getString("title"),
						res.getString("answer"),
						0);
				documents.add(document);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Document>results=documentPostag(documents,words);
		int id= getMatchingDocumentId(results,words);
		if(id==-1)
		{
			return answers;
		}
		for(Document document:documents)
		{
			if(document.id==id)
			{
				QuestionAnswerPair answer=new QuestionAnswerPair(document.id,
						document.word,
						document.title,
						QuestionType.TYPE_CONCEPT,
						AnswerType.TYPE_STRING, document.content);
				answers.add(answer);
				break;
			}
		}
		return answers;
	}
	/**
	 * lsa 计算与最相似的document
	 * @param documents
	 * @param words
	 * @return
	 */
	private static int getMatchingDocumentId(ArrayList<Document>documents,ArrayList<String>words)
	{
		int id=-1;
		try {
			LatentSemanticAnalysis lsa = new LatentSemanticAnalysis();
			ArrayList<Document> tmp = new ArrayList<Document>(documents.size());
			for (Document document : documents) {
				if (!document.content.equals("")) {
					lsa.processDocument(new BufferedReader(new StringReader(document.content)));
					System.out.println(document.title);
					System.out.println(document.content);
					System.out.println("------------------------------------------------------");
					tmp.add(document);
				}
			}
			documents = tmp;
			String question = "";
			for (String word : words) {
				question += word + " ";
			}
			lsa.processDocument(new BufferedReader(new StringReader(question)));

			lsa.processSpace(new Properties());

			double simillarity = 0;
			int simIndex = -1;
			DoubleVector targetDocument = lsa.getDocumentVector(documents.size());
			for (int i = 0; i < documents.size(); i++) {
				double sim = Similarity.getSimilarity(Similarity.SimType.COSINE, lsa.getDocumentVector(i), targetDocument);
				System.out.println("score"+i+" :"+sim);
				if (sim > simillarity) {
					simillarity = sim;
					simIndex = i;
				}
			}
			System.out.println("Sim score:"+simillarity);
			if(simIndex!=-1&&simillarity>0.4)
			{
				Document document=documents.get(simIndex);
				System.out.println(String.format("Similar document found:'%s'", document.id));
				System.out.println(String.format("Similar document found:'%s'", document.title));
				id=document.id;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 文档分词 建立-文档矩阵
	 * @param documents
	 * @param words
	 * @return
	 */
	private static ArrayList<Document> documentPostag(ArrayList<Document>documents,ArrayList<String>words)
	{
		LTP ltp=new LTP();
		ArrayList<Document>results=new ArrayList<Document>(documents.size());
		for(int i=0;i<documents.size();i++)
		{
			Document document=documents.get(i);
			String tmp="";
			ltp.createDOMFromString(document.content+"。"+document.title);
			ltp.posTag();
			int sentNum=ltp.countSentenceInDocument();
			for(int j=0;j<sentNum;j++)
			{
				ArrayList<String>tmpwords=ltp.getWordsFromSentence(j);
				for(int k=0;k<tmpwords.size();k++)
				{
					String tmpword=tmpwords.get(k);
					if(words.contains(tmpword))
					{
						tmp+=tmpwords.get(k)+" ";
					}
				}
			}
			results.add(new Document(document.id,document.word,document.title,tmp,0));
		}
		
		return results;
	}
	
	/**
	 * 答案去重
	 * 
	 * @param questionAnswerPair
	 */
	private static ArrayList<QuestionAnswerPair> clearAnswer(ArrayList<KeywordPair>keywords,ArrayList<QuestionAnswerPair> answers) {
		ArrayList<QuestionAnswerPair> result = new ArrayList<QuestionAnswerPair>();
		if (answers.size() == 0) {//如果没有找出答案 把keyword加进去 ，然后把answertype=null
			HashSet<String> flag = new HashSet<String>();
			for(KeywordPair keyword:keywords)
			{
				String k1=keyword.getKeyword1();
				if(!flag.contains(k1))
				{
					flag.add(k1);
					result.add(new QuestionAnswerPair(-1,k1,"没有找到你要的答案",QuestionType.TYPE_CONCEPT,AnswerType.TYPE_NULL,null));
				}
			}
			
		} else {//如果找到答案了 去除重复的
			HashSet<Integer> flag = new HashSet<Integer>();
			for (int i = 0; i < answers.size(); i++) {
				QuestionAnswerPair pair = answers.get(i);
				if (!flag.contains(pair.getId())) {
					result.add(pair);
					flag.add(pair.getId());
				}
			}
		}
		Collections.sort(result,new ResultComparator());
		return result;

	}

	/**
	 * 两个keyword中包含相同keyword2组合.
	 * 
	 * @param pair
	 *            TYPE_SEARCHSAMEKEYWORD类型的keywordpair
	 * @return ArrayList<keywordPair>类型为common类型
	 */
	private static ArrayList<KeywordPair> searchSameKeyword(KeywordPair pair) {

		String k1 = pair.getKeyword1();
		String k2 = pair.getKeyword2();

		// 初始化答案组
		ArrayList<KeywordPair> keywordpairs = new ArrayList<KeywordPair>();

	
		String query = "select * from  baikeview where word = ?";
		//String query="select * from dictionaryview where word = ?";

		// 存放所有对应的第二关键词
		ArrayList<String> ks1 = new ArrayList<String>();
		ArrayList<String> ks2 = new ArrayList<String>();

		// 检验避免重复
		ArrayList<String> kss1 = new ArrayList<String>();
		ArrayList<String> kss2 = new ArrayList<String>();

		try {
			ArrayList<Object>params=new ArrayList<Object>();
			params.add(k1);
			ResultSet res = DBUtil.query(query, params);

			while (res.next()) {
				String keyword1 = res.getString("keyword");
				String[] keys1 = keyword1.split("\\s");// 将k2关键字组进行拆分
				for (String string : keys1) {
					if (!ks1.contains(string))
						ks1.add(string);
				}
			}
			params.clear();
			params.add(k2);
			DBUtil.CloseResources(res);
			res = DBUtil.query(query, params);

			while (res.next()) {
				String keyword2 = res.getString("keyword");
				String[] keys2 = keyword2.split("\\s");// 将k2关键字组进行拆分
				for (String string : keys2) {
					if (!ks2.contains(string))
						ks2.add(string);
				}
			}

			// 遍历两个key的集合，通过相似度匹配，来进行问题
			for (String key1 : ks1) {
				for (String key2 : ks2) {
					// 两次能够相似匹配
					if (SimilarityUtil.analyseSimilarity(key1, key2) == true) {
						// 如果之前没有将key1包括到问题中
						if (!kss1.contains(key1))
							keywordpairs.add(new KeywordPair(k1, key1, KeywordPair.TYPE_COMMON));
						// 如果之前没有将key2包括到问题中
						if (!kss2.contains(key2))
							keywordpairs.add(new KeywordPair(k2, key2, KeywordPair.TYPE_COMMON));
						kss1.add(key1);
						kss2.add(key2);
					}
				}
			}
			return keywordpairs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}

class Document {
	public int id;
	public String word;
	public String content;
	public String title;
	public double score;

	public Document(int id, String word, String title, String content, double score) {
		this.id = id;
		this.word = word;
		this.title = title;
		this.content = content;
		this.score = score;
	}
}
class ResultComparator implements Comparator{

    public int compare(Object o1,Object o2) {
    	QuestionAnswerPair q1=(QuestionAnswerPair)o1;
    	QuestionAnswerPair q2=(QuestionAnswerPair)o2;  
       if(q1.getId()>q2.getId())
           return 1;
       else
           return 0;
       }

}
