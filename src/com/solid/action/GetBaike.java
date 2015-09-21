package com.solid.action;

import java.util.ArrayList;

import com.QA.Model.Baike;
import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.BaikeService;

public class GetBaike extends ActionSupport {

	private static final long serialVersionUID = 3242616827592101641L;
	private String name;
	private ArrayList<Baike> baikes = new ArrayList<Baike>();

	@Override
	public String execute() throws Exception {
		BaikeService bs = new BaikeService();
		System.out.println(name);
		setBaikes(bs.getBaikes(name));
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Baike> getBaikes() {
		return baikes;
	}

	public void setBaikes(ArrayList<Baike> baikes) {
		this.baikes = baikes;
	}

}
