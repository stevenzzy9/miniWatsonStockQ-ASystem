package com.QA.Model;

import java.util.ArrayList;

/**
 * 公司信息同义词表
 * 
 * @author li
 * 
 */
public class InfoSynonyms {

	private String word;// 公司的一项基本信息（归一化）
	private ArrayList<String> synonyms;// 同义词

	public InfoSynonyms(String word, ArrayList<String> synonyms) {
		this.word = word.trim();
		this.synonyms = synonyms;
	}

	public InfoSynonyms(String[] words) {
		if (words.length == 0) {
			return;
		}
		this.word = words[0].trim();
		this.synonyms = new ArrayList<String>();
		for (int i = 1; i < words.length; i++) {
			synonyms.add(words[i].trim());
		}
	}

	public String getWord() {
		return word;
	}

	public ArrayList<String> getSynonyms() {
		return synonyms;
	}

}
