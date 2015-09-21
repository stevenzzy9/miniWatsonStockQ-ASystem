package com.QA.GoogleSearch;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleSearcher {

	private List<String> content;
	protected String keyword;
	protected int amountOfResults;
	protected ArrayList<GoogleSearchResult> searchResults;
	protected final String generalUrl = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&rsz=large&q=";

	public GoogleSearcher() {
		this(10);
	}

	public GoogleSearcher(int amount) {
		this.amountOfResults = amount;
		this.searchResults = null;
	}

	public void search(String keyword) {
		try {// 处理中文时必须用utf-8格式，否则无法传给JOSN
			keyword = java.net.URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.keyword = keyword;
		searchResults = new ArrayList<GoogleSearchResult>();

		URL url = null;
		String urlString = generalUrl + keyword.replaceAll("\\s+", "%20");
		try {
			JSONArray jsonArr = null;
			int index = 0;
			for (int i = 0; i < amountOfResults; ++i) {
				if (jsonArr == null || index >= jsonArr.length()) {
					url = new URL(urlString + "&start=" + i);
					//System.out.println(url.toString());
					URLConnection conn = url.openConnection();
					conn.addRequestProperty("Referer", "http://www.informatik.uni-trier.de/~ley/db/");
					StringBuilder builder = new StringBuilder();
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
					String line;

					while ((line = br.readLine()) != null) {
						builder.append(line);
						//System.out.print(line);
					}

					br.close();
					jsonArr = new JSONObject(builder.toString()).getJSONObject("responseData").getJSONArray("results");
					index = 0;
					if (jsonArr.length() == 0)
						break;
				}
				JSONObject jsonObj = jsonArr.getJSONObject(index);
				GoogleSearchResult result = new GoogleSearchResult();
				result.setGSearchResultClass(jsonObj.getString("GsearchResultClass"));
				result.setUnescapedUrl(jsonObj.getString("unescapedUrl"));
				result.setUrl(jsonObj.getString("url"));
				result.setVisibleUrl(jsonObj.getString("visibleUrl"));
				result.setCacheUrl(jsonObj.getString("cacheUrl"));
				result.setTitle(jsonObj.getString("title"));
				result.setTitleNoFormatting(jsonObj.getString("titleNoFormatting"));
				result.setContent(jsonObj.getString("content"));
				searchResults.add(result);
				++index;
			}
			//initContent();

		} catch (Exception e) {
			searchResults = null;
			e.printStackTrace();
		}
	}

	private void initContent() {

		if (searchResults != null) {
			content = new ArrayList<String>();
			TextExtractor te = new TextExtractor();
			for (int i = 0; i < searchResults.size(); ++i) {
				te.extractURL(searchResults.get(i).getUnescapedUrl());
				if (!"*推测您提供的网页为非主题型网页，目前暂不处理！:-)".equals(te.getText())) {
					content.add(te.getText());
				}
			}
		}
	}

	public ArrayList<GoogleSearchResult> getSearchResults() {
		return searchResults;
	}

	public List<String> getContent() {
		return this.content;
	}
	public static void main(String args[])
	{
		GoogleSearcher g=new GoogleSearcher();
		g.search("中石油今年分红有多少？");
		ArrayList<GoogleSearchResult> results=g.getSearchResults();
		for(GoogleSearchResult r:results)
		{
			System.out.println(	r.gSearchResultClass+"\n"+
			r.unescapedUrl+"\n"+
			
			r.url+"\n"+
			r.visibleUrl+"\n"+
			r.cacheUrl+"\n"+
			
			r.title+"\n"+
			r.titleNoFormatting+"\n"+
			r.content);
			System.out.println("------------------------------");
		}
		
	}

}
