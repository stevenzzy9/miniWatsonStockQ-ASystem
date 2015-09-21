package com.QA.Model;

/**
 * 个股信息类问题分析结果
 * @author li
 *
 */
public class AnalyResult {
	

	private String name;
	private InfoPosition infoPosition;
	
	public AnalyResult(String name,InfoPosition infoPosition)
	{
		this.name=name;
		this.infoPosition=infoPosition;
	}
	
	public String getName() {
		return name;
	}

	public InfoPosition getInfoPosition() {
		return infoPosition;
	}

	public String toString() {
		return "AnalyResult [name=" + name + "\tproperty"+infoPosition+"]";
	}

}
