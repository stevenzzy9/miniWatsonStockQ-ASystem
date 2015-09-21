package com.QA.Model;

public class KeywordPair {

	
	private String keyword1;
	private String keyword2;
	private int type;
	public final static int TYPE_COMMON=1;
	public final static int TYPE_SEARCHTEXT=2;
	public final static int TYPE_SEARCHSAMEKEYWORD=3;
	
	public KeywordPair(String keyword1,String keyword2,int type)
	{
		this.keyword1=keyword1;
		this.keyword2=keyword2;
		this.type=type;
	}
	
	public String getKeyword1() {
		return keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public int getType() {
		return type;
	}
	
}
