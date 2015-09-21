package com.QA.NLP;

import java.io.File;
import java.io.IOException;

/**
 * 字典类
 * @author li
 *
 */
public class Dictionary {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;//字典名称
	private TernarySearchTrie content;
	
	public Dictionary(String name,String dicPath)
	{
		this.name=name;
		try {
			content=new TernarySearchTrie(new File(dicPath));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public TSTNode getNode(String key)
	{
		return content.getNode(key);
	}
	
	public Object get(String key)
	{
		return content.get(key);
	}
	
	
}
