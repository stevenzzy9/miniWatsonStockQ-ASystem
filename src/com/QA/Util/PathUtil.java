package com.QA.Util;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

//此类为了得到服务器的一般路径
public class PathUtil {
	
	private  static String path=null;

	static{
		try {
			String tmpPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().toString()
					.substring(6);
			path=URLDecoder.decode(tmpPath, "utf-8");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public static String getPath()
	{
		return path;
	}

	
	
}
