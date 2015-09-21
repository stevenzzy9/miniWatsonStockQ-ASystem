package com.QA.QuestionAnalyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import com.QA.Model.QuestionType;
import com.QA.NLP.Dictionary;
import com.QA.NLP.LTP;
import com.QA.Util.PathUtil;

/**
 * 问题预处理
 * 
 * @author li
 * 
 */
public class QuestionPreprocess {

	private String question;
	private ArrayList<String> words;
	private ArrayList<String> poses;
	private int questionType;

	private static Dictionary financialWordDictionary;
	private static Dictionary companyNameDictionary;
	private static Dictionary personNameDictionary;
	private static HashSet<String> stopwordsDictionary;
	private static HashSet<String> punctuationDictionary;

	static {
		financialWordDictionary = new Dictionary("financialwordDic", PathUtil.getPath() + "Files/dic/financialwordDic");
		companyNameDictionary = new Dictionary("companynameDic", PathUtil.getPath() + "Files/dic/companynameDic");
		 personNameDictionary=new Dictionary("personNameDictionary", PathUtil.getPath() + "Files/dic/personnameDic");
		// Dictionary("personNameDictionary","personNameDictionary");
		initStopwordsDictionary();
		initPunctuationDictionary();
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
	 * 初始化标点符号字典
	 * 
	 * @param question
	 */
	private static void initPunctuationDictionary() {
		try {
			String filePath = PathUtil.getPath() + "Files/dic/punctuation.txt";
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String line = in.readLine();
			punctuationDictionary = new HashSet<String>();
			while (line != null) {
				punctuationDictionary.add(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param question
	 */
	public QuestionPreprocess() {

	}

	/**
	 * 清楚标点符号
	 * 
	 * @return
	 */
	private String clearPunctuation(String line) {
		String str = "";
		for (int i = 0; i < line.length(); i++) {
			String tmp = String.valueOf(line.charAt(i));
			if (!punctuationDictionary.contains(tmp)) {
				str += tmp;
			}

		}
		return str;
	}

	private void posTag() {
		LTP ltp = new LTP();
		ltp.createDOMFromString(question);
		ltp.posTag();
		int senCount=ltp.countSentenceInDocument();
		this.words=new ArrayList<String>();
		this.poses=new ArrayList<String>();
		for(int i=0;i<senCount;i++)
		{
			this.words.addAll(ltp.getWordsFromSentence(i));
			this.poses.addAll(ltp.getPOSsFromSentence(i));
		}
		mergeWord(companyNameDictionary);
		mergeWord(financialWordDictionary);
		mergeWord(personNameDictionary);

	}

	private int search(Dictionary dictionary, ArrayList<String> words, String word, int start) {

		
		String preWord = word.substring(0,word.length()-words.get(start).length());
		com.QA.NLP.TSTNode node = dictionary.getNode(word);
		if (node != null && start + 1 < words.size()) {
			String nextWord = word + words.get(start + 1);
			return search(dictionary, words, nextWord, start + 1) + 1;
		} else if (node != null) {
			return 1;
		} else if (node == null && !preWord.equals("") && dictionary.get(preWord) == null) {
			return -1;
		} else {
			return 0;
		}
	
	}

	private void mergeWord(Dictionary dictionary) {
		String tag = "";
		if (dictionary.getName().equals("financialwordDic")) {
			tag = "fw";// finicialkeyword;
		} else if (dictionary.getName().equals("companynameDic")) {
			tag = "cn";// companyname
		} else if (dictionary.getName().equals("personNameDictionary")) {
			tag = "pn";// personName
		}
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			int p = search(dictionary, words, word, i);// 1
			// 1为不合并
			if (p > 1) {
				String tmpAdd = words.get(i);
				for (int j = i + 1; j < i + p; j++)// 合词
				{
					tmpAdd += words.get(j);
				}
				for (int j = i + 1; j < i + p; j++)// 删词
				{
					words.remove(j);
					poses.remove(j);
					j--;
					p--;
				}
				words.set(i, tmpAdd);
				poses.set(i, tag);
			} else if (dictionary.get(word) != null) {
				// words.set(i, word);
				poses.set(i, tag);
			}
		}
	}

	/**
	 * 判断是否包含金融专有名词
	 * 
	 * @return
	 */
	private boolean containFinicialWord() {
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if (pos.equals("fw")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否包含公司名称
	 * 
	 * @param args
	 */
	private boolean containCompanyName() {
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if (pos.equals("cn")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否包含人名
	 * 
	 * @param args
	 */
	private boolean containPersonName() {
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if (pos.equals("pn")) {
				return true;
			}
		}
		return false;
	}

	// 删除停用词
	private void deleteStopWord(ArrayList<String> words, ArrayList<String> poses) {
		for (int i = 0; i < words.size(); i++) {
			if (stopwordsDictionary.contains(words.get(i))) {
				words.remove(i);
				poses.remove(i);
				i--;
			}
		}
	}

	public void process(String question) {
		this.question = clearPunctuation(question).toLowerCase();
		posTag();
		deleteStopWord(words, poses);

		if (containCompanyName()) {
			questionType = QuestionType.TYPE_STOCKINFO;
		} else if(containPersonName())
		{
			questionType=QuestionType.TYPE_PERSONINFO;
		}else if (containFinicialWord()) {
			questionType =QuestionType.TYPE_CONCEPT;
		}else 
		{
			questionType=QuestionType.TYPE_OPERATION;
		}
	}

	public String getQuestion() {
		return question;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public ArrayList<String> getPoses() {
		return poses;
	}

	public int getQuestionType() {
		return questionType;
	}

	public static void main(String[] args) {

//		QuestionPreprocess a = new QuestionPreprocess();
//
//		PrintWriter out = new PrintWriter(System.out, true);
//		// String line = in.readLine();
//		String line = "浦发银行今年分红有多少？";
//		// while (line != null) {
//		a.process(line);
//		out.println("-------------------------------------------");
//		out.println("问题:" + a.getQuestion());
//		switch (a.getQuestionType()) {
//		case QuestionPreprocess.TYPE_CONCEPT:
//			out.println("基本概念");
//			break;
//		case QuestionPreprocess.TYPE_STOCKINFO:
//			out.println("公司基本");
//			break;
//		}
//		ArrayList<String> words = a.getWords();
//		ArrayList<String> poses = a.getPoses();
//		for (int i = 0; i < words.size(); i++) {
//			out.print(words.get(i) + "\\" + poses.get(i) + " ");
//		}
//		out.println();

	}
}
