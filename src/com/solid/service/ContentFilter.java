package com.solid.service;

import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ContentFilter implements Filter {

	public static String filter(String html) {
		if (html == null)
			return null;
		Parser p = Parser.createParser(html, "gb2312");
		HasAttributeFilter attrFilter = new HasAttributeFilter("class",
				NewsUtilImpl.CONTENT_AREA);
		
		try {
			NodeList nodes = p.extractAllNodesThatMatch(attrFilter);
			if (nodes.size() == 0)
				return null;
			return nodes.elementAt(0).toHtml();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}

}
