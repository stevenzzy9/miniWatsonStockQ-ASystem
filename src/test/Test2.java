package test;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


import ruc.irm.similarity.sentence.morphology.MorphoSimilarity;

import com.QA.NLP.Dictionary;
import com.QA.NLP.LTP;
import com.QA.Util.DBUtil;
import com.QA.Util.PathUtil;
import com.QA.Util.SimilarityUtil;

/**
 * 问题预处理
 * 
 * @author li
 * 
 */
public class Test2 {

	private String question;
	private ArrayList<String> words;
	private ArrayList<String> poses;
	private int questionType;

	private static Dictionary financialWordDictionary;
	private static HashSet<String> stopwordsDictionary;
	private static HashSet<String> punctuationDictionary;
	public final static int TYPE_CONCEPT = 1;
	public final static int TYPE_STOCKINFO = 2;
	public final static int TYPE_PERSONINFO = 3;
	public final static int TYPE_OTHER = 4;

	static {
		financialWordDictionary = new Dictionary("financialwordDic", PathUtil.getPath() + "Files/dic/financialwordDic");
	
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
		this.words = ltp.getWordsFromSentence(0);
		this.poses = ltp.getPOSsFromSentence(0);
		//mergeWord(financialWordDictionary);

	}

	private int search(Dictionary dictionary, ArrayList<String> words, String word, int start) {

		
		String preWord = word.replaceAll(words.get(start), "");
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
			} else if (dictionary.get(word) != null && dictionary.get(word).equals(1)) {
				// words.set(i, word);
				poses.set(i, tag);
			}
		}
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
		this.question = clearPunctuation(question);
		posTag();
		deleteStopWord(words, poses);
		
		queryDB();
		
	}
	public class Pair{
		public int id;
		public String question;
		public String tag;
		public double score;
		public Pair(int id,String question,String tag)
		{
			this.id=id;
			this.question=question;
			this.tag=tag;
			this.score=0;
		}
	}
	public  void queryDB() 
	{
		try {
			//PrintWriter out=new PrintWriter(new File("result123.txt"));
			String query = "select * from operation where ";
			int index=0;
			for (int i = 0; i < words.size(); i++) {
				String pos=poses.get(i);
				if (pos.equals("n") ||pos.equals("ni")||pos.equals("ns")||pos.equals("nl")||
						pos.equals("nz")||pos.equals("j")||pos.equals("nh")||pos.equals("ws")||pos.equals("v")) {
					if (index != 0) {
						query += "or ";
					}
					query += "tag like '%" + words.get(i) + "%' ";
					query += "and answer like '%" + words.get(i) + "%' ";
					++index;
				}
			}
			ResultSet res;
		
			res = DBUtil.query(query, new ArrayList<Object>());

			ArrayList<Pair> pairs = new ArrayList<Pair>();
			while (res.next()) {
				Pair pair = new Pair(res.getInt("id"), res.getString("question"), res.getString("tag"));
				pairs.add(pair);
			}

			MorphoSimilarity b=  MorphoSimilarity.getInstance();
			//b.getSimilarity("中国人", "中国人");
			ArrayList<Thread> threads=new ArrayList<Thread>();
			
			int a=pairs.size()/5;
			if(a<5)
				a=1;
			for(int i=0;i<pairs.size();i+=a)
			{
				if(i+a<pairs.size())
					threads.add(new Process(pairs.subList(i, i+a),question,b));
				else 
					threads.add(new Process(pairs.subList(i, pairs.size()),question,b));
			}
			for(int i=0;i<threads.size();i++)
			{
				threads.get(i).start();
			}
			for(int i=0;i<threads.size();i++)
			{
				threads.get(i).join();
			}
			ArrayList<Pair> results=new ArrayList<Pair>();
			for(int i=0;i<threads.size();i++)
			{
				Process tmp=(Process)threads.get(i);
				results.addAll(tmp.getResults());
			}
			for(int i=0;i<results.size();i++)
			{
				System.out.println("与问题["+results.get(i).question+"]相识度为 "+results.get(i).score);
			}
						
			//out.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private class Process extends Thread
	{
		private List<Pair> sentences;
		private String question;
		private MorphoSimilarity b;
		private ArrayList<Pair>results;
		
		public Process(List<Pair>sentences,String question,MorphoSimilarity b2)
		{
			this.sentences=sentences;
			this.question=question;
			this.b=b2;
			this.results=new ArrayList<Pair>();
		}
		
		
		public  void run() {
			
			for(int i=0;i<sentences.size();i++)
			{
				Pair sentence=sentences.get(i);
				String tmp = sentences.get(i).question;
				double score = b.getSimilarity(question, tmp);
				
				if(score>=0.6)
				{
					sentence.score=score;
					results.add(sentence);
				}
			}
			
			
		}
		
		public Collection<? extends Pair> getResults() {
			return results;
		}


		
	}
	
	


	public static void main(String[] args) {

		long t1=System.currentTimeMillis();
		new Test2().process("什么是利空");
		//运行测试内容
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
		//new Test2(89).get();
		//71605
	}
}
