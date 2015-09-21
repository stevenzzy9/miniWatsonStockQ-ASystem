package com.QA.Model;

/**
 * 投资家
 * @author li
 *
 */
public class Investor {
	
	private int id;
	private String name;//姓名
	private String introduction;//个人简介
	private String style;//投资风格
	private String saying;//名言
	private String event;//重大事件
	private String books;//数据
	private String field;//研究领域
	
	public Investor(int id,String name,String introduction,String style,String saying,
			String event,String books,String field)
	{
		this.id=id;
		this.name=name;
		this.introduction=introduction;
		this.style=style;
		this.saying=saying;
		this.event=event;
		this.books=books;
		this.field=field;		
		
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getStyle() {
		return style;
	}

	public String getSaying() {
		return saying;
	}

	public String getEvent() {
		return event;
	}

	public String getBooks() {
		return books;
	}

	public String getField() {
		return field;
	}

}
