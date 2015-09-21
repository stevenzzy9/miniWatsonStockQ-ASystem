package com.QA.Model;

/**
 * 词条百科
 * @author Administrator
 *
 */
public class Baike {

	private int id;
	private String title;
	private String answer;
	
	public Baike() {
		super();
	}
	
	public Baike(int id, String title, String answer) {
		super();
		this.id = id;
		this.title = title;
		this.answer = answer;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
