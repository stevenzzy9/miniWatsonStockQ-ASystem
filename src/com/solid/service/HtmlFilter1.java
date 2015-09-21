package com.solid.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

/**
 * 过滤网页信息
 * 
 * @author Jeremy Lee
 *
 */
public class HtmlFilter1 implements Filter {
	private String html;

	/**
	 * 
	 * @param 网页url
	 * @return网 过滤之后的网页信息
	 */
	public String filter(String url) {
		try {
			Parser p = new Parser(new URL(url).openConnection());
			p.setEncoding("gb2312");
			NodeList htmlNode = p.extractAllNodesThatMatch(new TagNameFilter(
					"html"));
			html = htmlNode.elementAt(0).toHtml();
			filterContent();
			filterPath();
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * 过滤内容
	 */
	private void filterContent() {
		if (html == null)
			return;
		Parser p = Parser.createParser(html, "gb2312");
		HasAttributeFilter attrFilter = new HasAttributeFilter("class",
				NewsUtilImpl.CONTENT_AREA1);

		try {
			NodeList nodes = p.extractAllNodesThatMatch(attrFilter);
			if (nodes.size() == 0)
				return;
			html = nodes.elementAt(0).toHtml();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 过滤链接地址
	 */
	private void filterPath() {
		if (html == null)
			return;
		Parser p = Parser.createParser(html, "gb2312");
		try {
			NodeList nodes = p.parse(null);
			recurse(nodes);
			html = nodes.toHtml();
			// System.out.println(html);
			return;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 遍历结点
	 * @param 结点List
	 */
	private void recurse(NodeList list) {
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
			/*
			 * if (node instanceof InputTag) { InputTag tag = (InputTag) node;
			 *//** 隐藏输入框 */
			/*
			 * tag.setAttribute("style", "visibility:hidden;"); }
			 */
			/*
			 * if (node instanceof ImageTag) { ImageTag tag = (ImageTag) node;
			 * String alt = tag.getAttribute("alt"); if (alt != null &&
			 * alt.equals("搜索")) tag.setAttribute("style",
			 * "visibility:hidden;"); }
			 */
			if (node instanceof Div) {
				Div tag = (Div) node;
				String cla = tag.getAttribute("class");
				if (cla != null && cla.equals("Extra_PRight")) {
					tag.setAttribute("style", "visibility:hidden;");
					continue;
				}
			}
			if (node instanceof LinkTag) {
				LinkTag tag = (LinkTag) node;
				String target = tag.getAttribute("target");
				if (target != null && target.equals("_self")) {
					tag.setLink(tag.getLink().replace("/ipo/data_ipo.aspx",
							"ScanNewShare"));
					continue;
				}
				String text = tag.getLinkText();
				if (text != null && text.equals("查看档案")) {
					tag.setLink(NewsUtilImpl.BASE_PATH1 + tag.getLink());
				}
				continue;
			}
			recurse(node.getChildren());

		}
		return;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}