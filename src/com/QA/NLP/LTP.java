package com.QA.NLP;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.QA.Util.PathUtil;



public class LTP {

	private static String dllPath=PathUtil.getPath()+"Files/dll";
	public native void  createDOMFromTxt(String filePath);
	public native void  createDOMFromString(String str);
	public native void  posTag();//分词
	public native void  NER();//命名实体识别
	public native int   countSentenceInDocument();
	public native int   countWordInSentence(int globalSentIdx);
	public native ArrayList<String> getWordsFromSentence(int globalSentIdx);
	public native ArrayList<String> getPOSsFromSentence(int globalSentIdx);
	public native ArrayList<String> getNEsFromSentence(int globalSentIdx);
	// 添加library
	private static void addDir(String s){
		try {
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			String[] paths = (String[]) field.get(null);
			for (int i = 0; i < paths.length; i++) {
				if (s.equals(paths[i])) {
					return;
				}
			}
			String[] tmp = new String[paths.length + 1];
			System.arraycopy(paths, 0, tmp, 0, paths.length);
			tmp[paths.length] = s;
			field.set(null, tmp);
		} catch (IllegalAccessException e) {
			System.out.println("Failed to get permissions to set library path");
		} catch (NoSuchFieldException e) {
			System.out.println("Failed to get field handle to set library path");
		}
	}
	static {
		addDir(dllPath);
		System.loadLibrary("MyQADLL");
			
	}
}
