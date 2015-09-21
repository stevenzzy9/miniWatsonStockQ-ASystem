package com.QA.Extractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.QA.Indexer.MySearcher;
import com.QA.Model.AnswerType;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.QuestionAnalyzer.OperationQuesAnaly;
import com.QA.QuestionAnalyzer.QuestionPreprocess;
import com.QA.Util.DBUtil;
import com.QA.Util.PathUtil;
import com.QA.Util.SimilarityUtil;

/**
 * 操作类答案提起
 * 
 * @author li
 * 
 */
public class OperationAnsExtra {

	private final static String indexDir = PathUtil.getPath()+"Files/index/operationindex";

	/**
	 * 获得关键词 词要符合一定的词性要求
	 * 
	 * @param words
	 *            分词结果
	 * @param poses
	 *            词性
	 * @return
	 */
	private static ArrayList<String> getKeywords(ArrayList<String> words, ArrayList<String> poses) {
		ArrayList<String> results = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			String pos = poses.get(i);
			if (pos.equals("n") || pos.equals("ni") || pos.equals("ns") || pos.equals("nl") || pos.equals("nz") || pos.equals("j")
					|| pos.equals("nh") || pos.equals("ws") || pos.equals("fw")||pos.equals("v")) {
				results.add(word);
			}
		}
		return results;
	}

	/**
	 * 根据用户输入的问题获得答案
	 * 
	 * @param question
	 * @return
	 */
	public static ArrayList<QuestionAnswerPair> getAnswers(String question) {
		QuestionPreprocess qp = new QuestionPreprocess();
		qp.process(question);
		ArrayList<String> words = qp.getWords();
		ArrayList<String> poses = qp.getPoses();
		ArrayList<String> keywords = getKeywords(words, poses);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (String keyword : keywords) {
			searchRecordId(map, keyword);
		}
		Entry[] entrys = getSortedHashtableByValue(map);
		ArrayList<Question> questiones = getTopQuestion(entrys, 100);
		questiones = getSimQuestion(question, questiones);
		ArrayList<QuestionAnswerPair> results = new ArrayList<QuestionAnswerPair>();
		for (int i = 0; i < questiones.size(); i++) {
			Question q = questiones.get(i);
			QuestionAnswerPair tmp = new QuestionAnswerPair(q.id, null, q.title, QuestionType.TYPE_OPERATION, AnswerType.TYPE_STRING, getAnswer(q.id));
			results.add(tmp);
		}

		return results;
	}

	/**
	 * 根据id获得问题（title）
	 * 
	 * @param id
	 * @return
	 */
	private static String getTitle(int id) {
		String query = "select question from operation where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		String title = "";
		try {
			ResultSet res = DBUtil.query(query, params);
			if (res.next()) {
				title = res.getString("question");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return title;
	}

	/**
	 * 根据id获得答案（answer）
	 * 
	 * @param id
	 * @return
	 */
	private static String getAnswer(int id) {
		String query = "select answer from operation where id=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(id);
		String answer = "";
		try {
			ResultSet res = DBUtil.query(query, params);
			if (res.next()) {
				answer = res.getString("answer");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	/**
	 * 在数据库中搜索包含关键词keyword的record 当道map中 k1为id k2为次数 每次+1
	 * 
	 * @param map
	 * @param keyword
	 */
	private static void searchRecordId(HashMap<Integer, Integer> map, String keyword) {
		MySearcher searcher = new MySearcher(indexDir);
		ArrayList<String> keyWords = new ArrayList<String>();
		keyWords.add(keyword);
		ArrayList<String> ids = searcher.search("question", keyWords, "id");
		for (String id : ids) {
			int tmp = Integer.parseInt(id);
			if (map.containsKey(tmp)) {
				int time = map.get(tmp);
				map.remove(tmp);
				map.put(tmp, ++time);
			} else {
				map.put(tmp, 1);
			}

		}
	}

	/**
	 * 对map排序 获得top num
	 * 
	 * @param entrys
	 * @param num
	 * @return
	 */
	private static ArrayList<Question> getTopQuestion(Entry[] entrys, int num) {
		ArrayList<Question> questions = new ArrayList<Question>();
		for (int i = 0; i < entrys.length && i < num; i++) {
			Entry entry = entrys[i];
			int id = (Integer) entry.getKey();
			Question q = new Question(id, getTitle(id), 0);
			questions.add(q);
		}
		return questions;
	}

	/**
	 * 获得与question相似的问题
	 * 
	 * @param question
	 * @param targets
	 * @return
	 */
	private static ArrayList<Question> getSimQuestion(String question, ArrayList<Question> targets) {

		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		int interval = targets.size()/2>15?15:targets.size()/2;
		//int interval =targets.size();
		for (int i = 0; i < targets.size(); i += interval) {
			int end = i + interval;
			Thread tmp;
			if (end > targets.size()) {
				tmp = new CalSimilarity(question, targets.subList(i, targets.size()));
			} else {
				tmp = new CalSimilarity(question, targets.subList(i, end));
			}
			threads.add(tmp);
		}

		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<Question> tmpQ = new ArrayList<Question>();
		for (int i = 0; i < threads.size(); i++) {
			CalSimilarity tmp = (CalSimilarity) threads.get(i);
			tmpQ.addAll(tmp.getResult());
		}
		Collections.sort(tmpQ, new QuesComparator());

		ArrayList<Question> results = new ArrayList<Question>();
		for (int i = 0; i < 2 && i < tmpQ.size(); i++) {
			Question result = tmpQ.get(i);
			results.add(result);
		}
		return results;
	}

	/**
	 * 对map排序
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Entry[] getSortedHashtableByValue(HashMap map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Long key1 = Long.valueOf(((Map.Entry) arg0).getValue().toString());
				Long key2 = Long.valueOf(((Map.Entry) arg1).getValue().toString());
				return key1.compareTo(key2);
			}
		});
		return entries;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		while (line != null) {

			System.out.println("start");
			long t1=System.currentTimeMillis();
			ArrayList<QuestionAnswerPair> answers=OperationAnsExtra.getAnswers(line);
			for(QuestionAnswerPair pair :answers)
			{
				System.out.println(pair.getQuestion());
			}
			long t2=System.currentTimeMillis();
			long time=t2-t1;
			System.out.println("耗时:\t"+time);
			line = in.readLine();

		}

	}

}

class Question {
	public int id;
	public String title;
	public double score;

	public Question(int id, String title, double score) {
		this.id = id;
		this.title = title;
		this.score = score;
	}

}

class QuesComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		Question q1 = (Question) o1;
		Question q2 = (Question) o2;

		if (q1.score < q2.score)
			return 1;
		else
			return 0;
	}
}

/**
 * 计算相似度的线程
 * 
 * @author li
 * 
 */

class CalSimilarity extends Thread {
	private List<Question> sentences;
	private String question;
	private List<Question> results;

	public CalSimilarity(String question, List<Question> sentences) {
		this.question = question;
		this.sentences = sentences;
		this.results = new ArrayList<Question>();
	}

	@Override
	public void run() {

		for (int i = 0; i < sentences.size(); i++) {
			Question sentence = sentences.get(i);
			String tmp = sentences.get(i).title;
			double score = SimilarityUtil.getSentenctSimilarityScore(question, tmp);
			if (score >= 0.7) {
				sentence.score = score;
				results.add(sentence);
			}
		}
	}

	public List<Question> getResult() {
		return results;
	}
}