package com.solid.action;

import com.QA.Model.Investor;
import com.opensymphony.xwork2.ActionSupport;
import com.solid.service.InvestorService;

public class GetInvestor extends ActionSupport {

	private static final long serialVersionUID = -950265668262495738L;
	private String name;
	private Investor investor;
	private int[] flags;

	@Override
	public String execute() throws Exception {
		setInvestor(new InvestorService().getInvestor(name));
		flags = new int[6];
		if (investor != null) {
			if (investor.getIntroduction() != null
					&& !investor.getIntroduction().equals(""))
				flags[0] = 1;
			if (investor.getStyle() != null
					&& !investor.getStyle().equals(""))
				flags[1] = 1;
			if (investor.getSaying()!= null
					&& !investor.getSaying().equals(""))
				flags[2] = 1;
			if (investor.getEvent()!= null
					&& !investor.getEvent().equals(""))
				flags[3] = 1;
			if (investor.getBooks()!= null
					&& !investor.getBooks().equals(""))
				flags[4] = 1;
			if (investor.getField()!= null
					&& !investor.getField().equals(""))
				flags[5] = 1;
			return "success";
		} else
			return "failed";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public int[] getFlags() {
		return flags;
	}

	public void setFlags(int[] flags) {
		this.flags = flags;
	}

}
