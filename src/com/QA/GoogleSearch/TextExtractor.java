package com.QA.GoogleSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 采用《基于行块分布函数的通用网页正文抽取》的算法
 */
public class TextExtractor {


	private static Integer blockWidth = 3;
	
	private final static String _titlePattern = "<title>(.*?)</title>";
	private final static Pattern _titleRegexPattern = Pattern.compile(
			_titlePattern, Pattern.CANON_EQ | Pattern.DOTALL
					| Pattern.CASE_INSENSITIVE);

	private String title="";
	private String text="";
	private static int threshold= 86; 

	private static void setBlock(Integer block) {
		blockWidth = block;
	}

	
	public void extractURL(String url) {
		url = url.trim();
		if (isLegalURL(url)) {
			if (isContentURL(url)) {
				String htmlText = DownloadURL.downURL(url, "IE8.0");
				extractHTML(htmlText);
			} else {
				text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			}
		} else {
			title = "*非法URL:-)";
		}
	}

	/**
	 * Extract url.
	 * 
	 * @param url
	 *            the url
	 * @param encoding
	 *            the encoding
	 */
	
	public void extractURL(String url, String encoding) {
		url = url.trim();
		if (isLegalURL(url)) {
			if (isContentURL(url)) {
				String htmlText = DownloadURL.downURL(url, encoding, "IE8.0");
				extractHTML(htmlText);
			} else {
				text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			}
		} else {
			title = "*非法URL:-)";
		}
	}

	/**
	 * Checks if is content url.
	 * 
	 * @param url
	 *            the url
	 * 
	 * @return true, if is content url
	 */
	private boolean isContentURL(String url) {
		int count = 0;
		for (int i = 0; i < url.length() - 1 && count < 3; i++) {
			if (url.charAt(i) == '/')
				count++;
		}

		return count > 2;
	}

	/**
	 * Checks if is legal url.
	 * 
	 * @param url
	 *            the url
	 * 
	 * @return true, if is legal url
	 */
	private boolean isLegalURL(String url) {
		if (url == null || url.length() == 0) {
			return false;
		}

		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

	/**
	 * Extract html.
	 * 
	 * @param htmlText
	 *            the html text
	 */
	public void extractHTML(String htmlText) {

		text="";
		extractTitle(htmlText);
		htmlText = preProcess(htmlText);
		//System.out.println(htmlText);
		if (!isContentPage(htmlText)) {
			text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			return;
		}

		List<String> lines = Arrays.asList(htmlText.split("\n"));
		List<Integer> indexDistribution = lineBlockDistribute(lines);

		int start = -1;
		int end = -1;
		boolean boolstart = false, boolend = false;
		for (int i = 0; i < indexDistribution.size() - 1; i++) {
			if (indexDistribution.get(i) > threshold && ! boolstart) {
				if (indexDistribution.get(i+1).intValue() != 0 
					|| indexDistribution.get(i+2).intValue() != 0
					|| indexDistribution.get(i+3).intValue() != 0) {
					boolstart = true;
					start = i;
					continue;
				}
			}
			if (boolstart) {
				if (indexDistribution.get(i).intValue() == 0 
					|| indexDistribution.get(i+1).intValue() == 0) {
					end = i;
					boolend = true;
				}
			}
			StringBuilder tmp = new StringBuilder();
			if (boolend) {
				//System.out.println(start+1 + "\t\t" + end+1);
				for (int ii = start; ii <= end; ii++) {
					if (lines.get(ii).length() < 5) continue;
					tmp.append(lines.get(ii));
				}
				String str = tmp.toString();
				//System.out.println(str);
				if (str.contains("Copyright")  || str.contains("版权所有") ) continue; 
				text+=str;
				boolstart = boolend = false;
			}
		}
	}

	/**
	 * Checks if is content page.
	 * 
	 * @param htmlText
	 *            the html text
	 * 
	 * @return true, if is content page
	 */
	private boolean isContentPage(String htmlText) {
		int count = 0;
		for (int i = 0; i < htmlText.length() && count < 5; i++) {
			if (htmlText.charAt(i) == '，' || htmlText.charAt(i) == '。'
					|| ',' == htmlText.charAt(i) || '.' == htmlText.charAt(i))
				count++;
		}

		return count >= 5;
	}

	/**
	 * Extract title.
	 * 
	 * @param htmlText
	 *            the html text
	 */
	private void extractTitle(String htmlText) {
		Matcher m1 = _titleRegexPattern.matcher(htmlText);

		if (m1.find()) {
			title = m1.group(1);
		}
		title = title.replaceAll("\n+", "");
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	
	private List<Integer> lineBlockDistribute(List<String>lines) {
		
		List<Integer> indexDistribution = new ArrayList<Integer>();
		for (int i = 0; i < lines.size() - blockWidth; i++) {
			int wordsNum = 0;
			for (int j = i; j < i + blockWidth; j++) { 
				lines.set(j, lines.get(j).replaceAll("\\s+", ""));
				wordsNum += lines.get(j).length();
			}
			indexDistribution.add(wordsNum);
		}
		return indexDistribution;
	}


	private String preProcess(String htmlText) {
		
		htmlText = htmlText.replaceAll("(?is)<!DOCTYPE.*?>", "");		// DTD
		htmlText = htmlText.replaceAll("(?is)<!--.*?-->", "");	// html comment
		htmlText = htmlText.replaceAll("(?is)<script.*?>.*?</script>", "");// js
		htmlText = htmlText.replaceAll("(?is)<style.*?>.*?</style>", "");// css
		//htmlText = htmlText.replaceAll("(?is)<.*?>", "");// html
		htmlText= htmlText.replaceAll("\\&[a-zA-Z]{1,10};", "") ;
        htmlText=htmlText.replaceAll("<[^>]*>", "");
        htmlText=htmlText.replaceAll("[(/>)<]", "");  
		
		return htmlText;
	}

	

	public static void main(String[] args) {
	

		TextExtractor te = new TextExtractor();
		te.extractURL("http://www.chaoguba.com/rumen/6729.html");
		System.out.println(te.getTitle());
		System.out.println(te.getText());
	}

}
