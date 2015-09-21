package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;

import com.QA.GoogleSearch.GoogleSearchResult;
import com.QA.NLP.Dictionary;
import com.QA.NLP.LTP;
import com.QA.QuestionAnalyzer.QuestionPreprocess;
import com.QA.Util.DBUtil;
import com.QA.Util.PathUtil;

import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.lsa.LatentSemanticAnalysis;
import edu.ucla.sspace.vector.DoubleVector;

public class SSpacetest {

	private static HashSet<String> stopwordsDictionary;
	private static HashSet<String> punctuationDictionary;

	static {
	
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

	// 删除停用词
	private void deleteStopWord(ArrayList<String> words, ArrayList<String> poses) {
		for (int i = 0; i < words.size(); i++) {
			if (stopwordsDictionary.contains(words.get(i))) {
				words.remove(i);
				//poses.remove(i);
				i--;
			}
		}
	}

	
	public void process()
	{
		QuestionPreprocess pre = new QuestionPreprocess();
		String question="道氏理论的发明人是谁";
		pre.process(question);
		ArrayList<String> words = pre.getWords();
		ArrayList<String> poses = pre.getPoses();
		ArrayList<String> keywords = new ArrayList<String>();
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			String word = poses.get(i);
			if (pos.equals("fw")) {
				keywords.add(word);
			}
		}
		LTP ltp=new LTP();
		ltp.createDOMFromString(question);
		ltp.posTag();
		words=ltp.getWordsFromSentence(0);
		deleteStopWord(words, poses);
		
		
		
		String query = "select * from baike2view where baike2view.word=?";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("道氏理论");
		ArrayList<Document> documents = new ArrayList<Document>();
		try {
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				Document document = new Document(res.getInt("id"),res.getString("title"), res.getString("answer"), 0);
				documents.add(document);

			}

//			query = "select * from dictionaryview where dictionaryview.word=?";
//			params.clear();
//			params.add("阳线");
//			DBUtil.CloseResources(res);
//			res = DBUtil.query(query, params);
//			while (res.next()) {
//				Document document = new Document(res.getInt("id"),res.getString("title"),res.getString("answer"), 0);
//				documents.add(document);
//
//			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Document>results=documentPostag(documents,words);
		int j=0;
		try {
			LatentSemanticAnalysis lsa = new LatentSemanticAnalysis();
			ArrayList<Document> tmp = new ArrayList<Document>(documents.size());
			for (Document document : results) {
				if (!document.content.equals("")) {
					System.out.println(document.id);
					lsa.processDocument(new BufferedReader(new StringReader(document.content)));
					System.out.println(document.content);
					System.out.println("----------------------------");
					tmp.add(document);
				}
			}
			documents = tmp;

			 question = "";
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
			
			if(simIndex!=-1)
			{
				//this.results.add(searchResults.get(documents.get(simIndex).getId()));
				System.out.println(String.format("Similar document found:'%s'", documents.get(simIndex).id));
				System.out.println(String.format("Similar document found:'%s'", documents.get(simIndex).title));
				
			}else 
			{
				//this.results.add(new GoogleSearchResult());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public ArrayList<Document> documentPostag(ArrayList<Document>documents,ArrayList<String>words)
	{
		LTP ltp=new LTP();
		ArrayList<Document>results=new ArrayList<Document>(documents.size());
		for(int i=0;i<documents.size();i++)
		{
			Document document=documents.get(i);
			String tmp="";
			System.out.println(document.content);
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
			results.add(new Document(document.id,document.title,tmp,0));
		}
		
		return results;
	}
	
	class Document
	{
		public int id;
		public String content;
		public String title;
		public double score;
		
		public Document(int id,String title,String content,double score)
		{
			this.id=id;
			this.title=title;
			this.content=content;
			this.score=score;
		}
	}
	
	public static void main(String[] args) {

		new SSpacetest().process();
	}

}
