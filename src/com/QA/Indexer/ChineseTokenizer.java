package com.QA.Indexer;

import java.io.Reader;

import org.apache.lucene.analysis.WhitespaceTokenizer;

public class ChineseTokenizer extends WhitespaceTokenizer{
	
	  public ChineseTokenizer(Reader in) {
		super(in);
	}

	
}
