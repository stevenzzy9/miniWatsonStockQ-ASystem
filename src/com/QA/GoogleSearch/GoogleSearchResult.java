package com.QA.GoogleSearch;

public class GoogleSearchResult {


	public String toString() {
		String tmp=gSearchResultClass+"\n"+
		unescapedUrl+"\n"+
		url+"\n"+
		visibleUrl+"\n"+
		cacheUrl+"\n"+
		title+"\n"+
		titleNoFormatting+"\n"+
		content+"\n";
		return tmp;
	}

	protected String gSearchResultClass;
	protected String unescapedUrl;
	protected String url;
	protected String visibleUrl;
	protected String cacheUrl;
	protected String title;
	protected String titleNoFormatting;
	protected String content;
	
	public String getSearchResultClass() {
		return gSearchResultClass;
	}

	public void setGSearchResultClass(String searchResultClass) {
		this.gSearchResultClass = searchResultClass;
	}

	public String getUnescapedUrl() {
		return unescapedUrl;
	}

	public void setUnescapedUrl(String unescapedUrl) {
		this.unescapedUrl = unescapedUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVisibleUrl() {
		return visibleUrl;
	}

	public void setVisibleUrl(String visibleUrl) {
		this.visibleUrl = visibleUrl;
	}

	public String getCacheUrl() {
		return cacheUrl;
	}

	public void setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleNoFormatting() {
		return titleNoFormatting;
	}

	public void setTitleNoFormatting(String titleNoFormatting) {
		this.titleNoFormatting = titleNoFormatting;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
