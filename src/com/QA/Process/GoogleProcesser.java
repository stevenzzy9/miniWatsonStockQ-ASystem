package com.QA.Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.QA.GoogleSearch.GoogleSearchResult;
import com.QA.GoogleSearch.GoogleSearcher;
import com.QA.GoogleSearch.TextExtractor;
import com.QA.NLP.LTP;
import com.QA.QuestionAnalyzer.QuestionPreprocess;
import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.lsa.LatentSemanticAnalysis;
import edu.ucla.sspace.vector.DoubleVector;

/**
 * 搜索google 
 * 获得google 搜索的链接结果 
 * 然后获得内容
 * 利用lsa算法 获得与问题最相似的答案
 * @author li
 *
 */
public class GoogleProcesser {

	private ArrayList<GoogleSearchResult> results;//处理结果
	private ArrayList<GoogleSearchResult> searchResults;//google搜索结果
	private ArrayList<Document>documents;//google搜索结果的内容
	private String question;//用户输入的问句
	private ArrayList<String>words;//分词结果
	private ArrayList<String>poses;//词性标注结果
	

	/**
	 * 根据用户输入的问题获得的document
	 * @param question
	 * 用户输入的问题
	 * @return
	 */
	private ArrayList<Document>search(String question)
	{
		GoogleSearcher g=new GoogleSearcher();
		g.search(question);
		this.searchResults=g.getSearchResults();
		if(searchResults==null)//出现异常 可能是没有网
		{
			return new ArrayList<Document>(1);
		}
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		int interval = 5;
		for (int i = 0; i < searchResults.size(); i += interval) {
			int end = i + interval;
			Thread tmp;
			if (end > searchResults.size()) {
				tmp = new DownloadDocument(searchResults.subList(i, searchResults.size()));
			} else {
				tmp = new DownloadDocument(searchResults.subList(i, end));
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
		ArrayList<Document>documents=new ArrayList<Document>();
		for (int i = 0; i < threads.size(); i++) {
			DownloadDocument dd=(DownloadDocument)threads.get(i);
			documents.addAll(dd.getDocuments());
		}
		
		return documents;
	}
	
	
	
	
	/**
	 * 对document进行分词 将内容替换成问题中有的词的序列
	 * @param documents
	 * 文章内容
	 */
	private void documentPostag(ArrayList<Document>documents)
	{
		LTP ltp=new LTP();
		ArrayList<Document>results=new ArrayList<Document>(documents.size());
		for(int i=0;i<documents.size();i++)
		{
			Document document=documents.get(i);
			String tmp="";
			String content=document.getContent();
			if(content.equals(""))
			{
				results.add(new Document(document.getId(),document.getTitle(),tmp));
				continue;
			}
			ltp.createDOMFromString(content);
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
			results.add(new Document(document.getId(),document.getTitle(),tmp));
		}
		
		this.documents=results;		
	}
	/**
	 * 对问题进行预处理
	 * @param question
	 * 用户输入的问题
	 */
	private void questionProcess(String question)
	{
		QuestionPreprocess pre=new QuestionPreprocess();
		pre.process(question);
		this.question=pre.getQuestion();
		this.words=pre.getWords();
		this.poses=pre.getPoses();
	}
	
	/**
	 * 
	 * @param question
	 */
	public void process(String question)
	{
		if(this.results==null)
		{
			results=new ArrayList<GoogleSearchResult>();
		}else
		{
			results.clear();
		}
		questionProcess(question);
		System.out.println("开始下载网页");
		ArrayList<Document> documents = search(question);
		System.out.println("网页分词");
		documentPostag(documents);

		try {
			LatentSemanticAnalysis lsa = new LatentSemanticAnalysis();
			ArrayList<Document> tmp = new ArrayList<Document>(documents.size());
			for (Document document : documents) {
				if (!document.getContent().equals("")) {
					System.out.println(document.getTitle());
					lsa.processDocument(new BufferedReader(new StringReader(document.getContent())));
					System.out.println(document.getContent());
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
				if (sim > simillarity) {
					simillarity = sim;
					simIndex = i;
				}
			}
			System.out.println("simIndex:"+simIndex);
			if(simIndex!=-1)
			{
				this.results.add(searchResults.get(documents.get(simIndex).getId()));
				System.out.println(String.format("Similar document found:'%s'", documents.get(simIndex).getTitle()));
			}else 
			{
				this.results.add(new GoogleSearchResult());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	

	public ArrayList<GoogleSearchResult> getResult() {
		return results;
	}
	
	public static void main(String[] args) throws IOException {
		GoogleProcesser pre = new GoogleProcesser();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		while (line != null) {
			long start=System.currentTimeMillis();
			pre.process(line);
			ArrayList<GoogleSearchResult> results = pre.getResult();
			long end=System.currentTimeMillis();
			System.out.println("---------------------结果-----------------------");
			System.out.println(results.get(0));
			long time=end-start;
			System.out.println("运行时间："+time);
			line=in.readLine();
		}
	}

}
 class DownloadDocument extends Thread
{
	private ArrayList<Document>documents;
	private List<GoogleSearchResult>searchResults;
	
	
	public DownloadDocument (List<GoogleSearchResult> list)
	{
		this.searchResults=list;
		documents=new ArrayList<Document>();
	}
	
	public void run() {
		
		TextExtractor te=new TextExtractor();
		for(int i=0;i<searchResults.size();i++)
		{
			GoogleSearchResult r=searchResults.get(i);
			String url=r.getUrl();
			te.extractURL(url);
			Document document=new Document(i,te.getTitle(),te.getText());
			documents.add(document);
		}
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}
		
}
 
	class Document
	{
		private int id;//对应googleresult中的index
		private String title;//对应搜索出来的文章标题
		private String content;//搜索出来的文章内容
		public Document(int id,String title,String content)
		{
			this.id=id;
			this.title=title;
			this.content=content;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}