package com.QA.Model;

/**
 * 公司高管信息
 * @author li
 *
 */
public class CompanyExecutive {


	private String name;//姓名
	private String sex;//性别
	private String position;//职务
	private String education;//学历
	private String salary;//年薪
	private String shares;//持股数
	private String officeholding;//任职起日始
	private String positionend;//任职截止日
	
	public CompanyExecutive(String name,String sex,String position,String education,String salary,
			String shares,String officeholding,String positionend)
	{
		this.name=name;
		this.sex=sex;
		this.position=position;
		this.education=education;
		this.salary=salary;
		this.shares=shares;
		this.officeholding=officeholding;
		this.positionend=positionend;
		
	}
	
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public String getPosition() {
		return position;
	}
	public String getEducation() {
		return education;
	}
	public String getSalary() {
		return salary;
	}
	public String getShares() {
		return shares;
	}
	public String getOfficeholding() {
		return officeholding;
	}
	public String getPositionend() {
		return positionend;
	}
	
	@Override
	public String toString() {
		return "CompanyExecutive [姓名=" + name + ", 性别=" + sex + ", 职务=" + position + ", 学历=" + education
				+ ", 年薪=" + salary + ", 持股数=" + shares + ", 任职起日始=" + officeholding + ", 任职截止日=" + positionend + "]";
	}
	
	
}
