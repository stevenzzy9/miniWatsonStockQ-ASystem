package com.solid.service;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class PathFilter implements Filter {

	public static String filter(String html) {
		if (html == null)
			return null;
		Parser p = Parser.createParser(html, "gk2312");
		try {
			NodeList nodes = p.parse(null);
			recurse(nodes);
			html = nodes.toHtml();
			// System.out.println(html);
			return html;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void recurse(NodeList list) {
		if (list == null)
			return;
		Node node = null;
		SimpleNodeIterator iterator = list.elements();

		while (iterator.hasMoreNodes()) {
			node = iterator.nextNode();
			if (node == null)
				break;
			/*
			 * if (node instanceof FormTag) { list.remove(node); continue; }
			 */
			if (node instanceof InputTag) {
				InputTag tag = (InputTag) node;
				/** 隐藏输入框 */
				tag.setAttribute("style", "visibility:hidden;");
			}
			if (node instanceof ImageTag) {
				ImageTag tag = (ImageTag) node;
				String alt = tag.getAttribute("alt");
				if (alt != null && alt.equals("搜索"))
					tag.setAttribute("style", "visibility:hidden;");
			}
			if (node instanceof LinkTag) {
				LinkTag tag = (LinkTag) node;
				if (!tag.getAttribute("target").equals("_self"))
					tag.setLink(NewsUtilImpl.BASE_PATH + tag.getLink());
				else tag.setLink("GetNews?code="+600000+"&page="+tag.getLinkText());
			}
			recurse(node.getChildren());
		}
		return;
	}
	
	/*
	 * private static void deleteForm(NodeList list) { if(list==null)return;
	 * Node node = null; SimpleNodeIterator iterator = list.elements();
	 * 
	 * while (iterator.hasMoreNodes()) { node = iterator.nextNode(); if (node ==
	 * null) break; if (node instanceof FormTag) { list.remove(node); continue;
	 * } recurse(node.getChildren()); } return; }
	 */
}
