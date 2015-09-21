package com.QA.Indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class MySearcher {

	private String indexDir;
	
	public MySearcher(String indexDir) {
		this.indexDir=indexDir;

	}

	
	public ArrayList<String> search(String fieldName,ArrayList<String> keyWords,String targetField) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexDir)), true);
		    Searcher searcher = new IndexSearcher(reader);
		    //Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_29);    
		    
			TopScoreDocCollector collector = TopScoreDocCollector.create(1000, false);
			BooleanQuery query = new BooleanQuery();
			for (String word : keyWords) {
				Term term = new Term(fieldName, word);
				TermQuery termQuery = new TermQuery(term);
				query.add(termQuery, BooleanClause.Occur.MUST);
			}

			searcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				int docID = hits[i].doc;
				Document doc = searcher.doc(docID);
				results.add(doc.get(targetField));
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	private String getContent(String path) {
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line = in.readLine();
			while (line != null) {
				content += line.trim();
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	public static void main(String []args)
	{
		MySearcher searcher=new MySearcher("src/Files/index/operationindex");
		ArrayList<String> keyWords=new ArrayList<String>();
		keyWords.add("农行");
		ArrayList<String>contents=searcher.search("question",keyWords,"id");
		int index=0;
		for(String content :contents)
		{
			System.out.println(content);
		
		}
		
	}
	 

}
