package com.QA.Model;

public class CompanyInfo {

	private int id;
	private String code;
	private String abbreviation;
	private String fullname;
	private String usedname;
	private String markettype;
	private String securitiestype;
	private String foundationdate;
	private String listingdate;
	private String registrationcapital;
	private String manager;
	private String legalperson;
	private String secretary;
	private String registrationaddress;
	private String officeaddress;
	private String tel;
	private String fax;
	private String email;
	private String internetsite;
	private String industry;
	private String area;
	private String field;
	private String business;
	private String introduction;
	private String issuesecurities;

	public CompanyInfo(int id, String code, String abbreviation, String tel,
			String internetsite) {
		super();
		this.id = id;
		this.code = code;
		this.abbreviation = abbreviation;
		this.tel = tel;
		this.internetsite = internetsite;
	}

	public CompanyInfo() {
	}

	public CompanyInfo(int id, String code, String abbreviation,
			String fullname, String usedname, String markettype,
			String securitiestype, String foundationdate, String listingdate,
			String registrationcapital, String manager, String legalperson,
			String secretary, String registrationaddress, String officeaddress,
			String tel, String fax, String email, String internetsite,
			String industry, String area, String field, String business,
			String introduction, String issuesecurities) {
		super();
		this.id = id;
		this.code = code;
		this.abbreviation = abbreviation;
		this.fullname = fullname;
		this.usedname = usedname;
		this.markettype = markettype;
		this.securitiestype = securitiestype;
		this.foundationdate = foundationdate;
		this.listingdate = listingdate;
		this.registrationcapital = registrationcapital;
		this.manager = manager;
		this.legalperson = legalperson;
		this.secretary = secretary;
		this.registrationaddress = registrationaddress;
		this.officeaddress = officeaddress;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.internetsite = internetsite;
		this.industry = industry;
		this.area = area;
		this.field = field;
		this.business = business;
		this.introduction = introduction;
		this.issuesecurities = issuesecurities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsedname() {
		return usedname;
	}

	public void setUsedname(String usedname) {
		this.usedname = usedname;
	}

	public String getMarkettype() {
		return markettype;
	}

	public void setMarkettype(String markettype) {
		this.markettype = markettype;
	}

	public String getSecuritiestype() {
		return securitiestype;
	}

	public void setSecuritiestype(String securitiestype) {
		this.securitiestype = securitiestype;
	}

	public String getFoundationdate() {
		return foundationdate;
	}

	public void setFoundationdate(String foundationdate) {
		this.foundationdate = foundationdate;
	}

	public String getListingdate() {
		return listingdate;
	}

	public void setListingdate(String listingdate) {
		this.listingdate = listingdate;
	}

	public String getRegistrationcapital() {
		return registrationcapital;
	}

	public void setRegistrationcapital(String registrationcapital) {
		this.registrationcapital = registrationcapital;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getLegalperson() {
		return legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	public String getSecretary() {
		return secretary;
	}

	public void setSecretary(String secretary) {
		this.secretary = secretary;
	}

	public String getRegistrationaddress() {
		return registrationaddress;
	}

	public void setRegistrationaddress(String registrationaddress) {
		this.registrationaddress = registrationaddress;
	}

	public String getOfficeaddress() {
		return officeaddress;
	}

	public void setOfficeaddress(String officeaddress) {
		this.officeaddress = officeaddress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInternetsite() {
		return internetsite;
	}

	public void setInternetsite(String internetsite) {
		this.internetsite = internetsite;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIssuesecurities() {
		return issuesecurities;
	}

	public void setIssuesecurities(String issuesecurities) {
		this.issuesecurities = issuesecurities;
	}

}
