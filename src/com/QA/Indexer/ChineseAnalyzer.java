package com.QA.Indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;

import com.QA.NLP.Dictionary;
import com.QA.NLP.LTP;
import com.QA.NLP.TernarySearchTrie;
import com.QA.QuestionAnalyzer.QuestionPreprocess;
import com.QA.Util.PathUtil;

/**
 * 自定义分词类 在建立索引时使用
 * 使用哈工大分词工具
 * @author li
 *
 */
public class ChineseAnalyzer extends Analyzer {
	
	public ChineseAnalyzer() {
	}

	public TokenStream tokenStream(String fieldName, Reader reader) {
		String inputString = readerToString(reader);
		String resultString = "";
		QuestionPreprocess qp=new QuestionPreprocess();		
		if (!inputString.equals("")) {
			qp.process(inputString);
			ArrayList<String>words=qp.getWords();
			for (String word : words) {
				resultString+=word+" ";
			}
		}
		System.out.println(resultString);
		
		TokenStream result = new ChineseTokenizer(new StringReader(resultString));
		result = new LowerCaseFilter(result);
		result = new PorterStemFilter(result);
		return result;
	}
	
	
	/**
	 * 将Reader转化为String
	 * @param reader
	 * @return
	 */
	private String readerToString(Reader reader) {
		String content = "";
		try {
			BufferedReader in = new BufferedReader(reader);

			String line = in.readLine();
			while (line != null) {
				content += line;
				line = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;

	}
	
}
