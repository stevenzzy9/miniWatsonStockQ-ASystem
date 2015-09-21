package com.QA.QuestionAnalyzer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

import com.QA.Model.KeywordPair;
import com.QA.Model.QuestionType;
import com.QA.Util.PathUtil;

/**
 * 基本概念分析类
 * 
 * @author li
 * 
 */
public class ConceptQuesAnaly {

	private String question;
	private ArrayList<String> words;
	private ArrayList<String> poses;
	private ArrayList<String> keywords;
	private int questionType;

	private static ArrayList<String> reasonDic = new ArrayList<String>();
	private static ArrayList<String> comparisionDic = new ArrayList<String>();
	private static ArrayList<String> descriptionDic = new ArrayList<String>();
	private static ArrayList<String> enumerationDic = new ArrayList<String>();
	private static ArrayList<String> personDic = new ArrayList<String>();
	private static ArrayList<String> timeDic = new ArrayList<String>();
	private static ArrayList<String> locationDic = new ArrayList<String>();
	private static ArrayList<String> definitionDic = new ArrayList<String>();
	private static ArrayList<String> negativeWordsDic = new ArrayList<String>();
	private static ArrayList<String> definitionDic2 = new ArrayList<String>();

	public final static int TYPE_REASON = 1;//原因类
	public final static int TYPE_COMPARISON = 2;//比较类
	public final static int TYPE_DESCRIPTION = 3;//描述类
	public final static int TYPE_ENUMERATION = 4;//列举类
	public final static int TYPE_DEFINITION = 8;//定义类
	public final static int TYPE_SPECIALDEFINITION = 9;//特殊定义类
	public final static int TYPE_DEATT=10;//de+att类别
	public final static int TYPE_OTHER=11;//其他类别 用searchtext解决
	
	public final static int TYPE_PERSON = 5;//人物类（暂时不用）
	public final static int TYPE_TIME = 6;//时间类（暂时不用）
	public final static int TYPE_LOCATION = 7;//地点类（暂时不用）
	

	private final static String reasonDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/reasonDic.txt";
	private final static String comparisionDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/comparisionDic.txt";
	private final static String descriptionDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/descriptionDic.txt";
	private final static String enumerationDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/enumerationDic.txt";
	private final static String personDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/personDic.txt";
	private final static String timeDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/timeDic.txt";
	private final static String locationDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/locationDic.txt";
	private final static String definitionDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/definitionDic.txt";
	private final static String negativeWordsDicPath=PathUtil.getPath()
	+"Files/dic/questiontypedic/negativeWordsDic.txt";
	private final static String definitionDic2Path=PathUtil.getPath()
	+"Files/dic/questiontypedic/definitionDic2.txt";
	static {
		initDic(reasonDic, reasonDicPath);
		initDic(comparisionDic, comparisionDicPath);
		initDic(descriptionDic, descriptionDicPath);
		initDic(enumerationDic, enumerationDicPath);
		initDic(personDic, personDicPath);
		initDic(timeDic, timeDicPath);
		initDic(locationDic, locationDicPath);
		initDic(definitionDic, definitionDicPath);
		initDic(negativeWordsDic, negativeWordsDicPath);
		initDic(definitionDic2, definitionDic2Path);
	}

	private static void initDic(ArrayList<String> dic, String filePath) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"utf-8"));
			String line = in.readLine();
			while (line != null) {
				dic.add(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConceptQuesAnaly(String question, ArrayList<String> words, ArrayList<String> poses) {
		this.question = question;
		this.words = words;
		this.poses = poses;
	}

	/**
	 * 识别问句类型
	 */
	private int identifyQuestionType(String question) {
		if (isSpecialDefinitionType(question)) {
			return ConceptQuesAnaly.TYPE_SPECIALDEFINITION;
		} else if (isComparisionType(question)) {
			return ConceptQuesAnaly.TYPE_COMPARISON;
		} else if (isReasonType(question)) {
			return ConceptQuesAnaly.TYPE_REASON;
		} else if (isDescriptionType(question)) {
			return ConceptQuesAnaly.TYPE_DESCRIPTION;
		} else if (isEnumerationType(question)) {
			return ConceptQuesAnaly.TYPE_ENUMERATION;
		} else if (isDefinitonType(question)) {
			return ConceptQuesAnaly.TYPE_DEFINITION;
		} else if (isDeAttType()){
			return ConceptQuesAnaly.TYPE_DEATT;
		} else{
			return ConceptQuesAnaly.TYPE_OTHER; 
		}
	}
/**
 * 判断是否为特殊定义类型
 * @param question
 * @return
 */
	private boolean isSpecialDefinitionType(String question) {
		for (int i = 0; i < definitionDic2.size(); i++) {
			if (question.contains(definitionDic2.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是原因类
 * @param question
 * @return
 */
	private boolean isReasonType(String question) {
		for (int i = 0; i < reasonDic.size(); i++) {
			if (question.contains(reasonDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是比较类
 * @param question
 * @return
 */
	private boolean isComparisionType(String question) {
		for (int i = 0; i < comparisionDic.size(); i++) {
			if (question.contains(comparisionDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是描述类
 * @param question
 * @return
 */
	private boolean isDescriptionType(String question) {
		for (int i = 0; i < descriptionDic.size(); i++) {
			if (question.contains(descriptionDic.get(i).trim())) {
				return true;
			}
		}
		return false;

	}
/**
 * 判读是否是列举类
 * @param question
 * @return
 */
	private boolean isEnumerationType(String question) {
		for (int i = 0; i < enumerationDic.size(); i++) {
			if (question.contains(enumerationDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是人物类
 * @param question
 * @return
 */
	private boolean isPersonType(String question) {
		for (int i = 0; i < personDic.size(); i++) {
			if (question.contains(personDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否含有否定意思的词
 * @param question
 * @return
 */
	private boolean hasNegativeWord(String question) {
		for (int i = 0; i < negativeWordsDic.size(); i++) {
			if (question.contains(negativeWordsDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是时间类型
 * @param question
 * @return
 */
	private Boolean isTimeType(String question) {
		for (int i = 0; i < timeDic.size(); i++) {
			if (question.contains(timeDic.get(i).trim())) {
				return true;
			}
		}
		return false;

	}
/**
 * 判断是否是定义类型
 * @param question
 * @return
 */
	private boolean isDefinitonType(String question) {
		for (int i = 0; i < definitionDic.size(); i++) {
			if (question.contains(definitionDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判读是否是位置类型
 * @param question
 * @return
 */
	private boolean isLocationType(String question) {
		for (int i = 0; i < locationDic.size(); i++) {
			if (question.contains(locationDic.get(i).trim())) {
				return true;
			}
		}
		return false;
	}
/**
 * 判断是否是DEAtt类型
 * @return
 */
	private boolean isDeAttType()
	{
		ArrayList<String>targetNorms=getNouns();
		if(targetNorms!=null&&targetNorms.size()<=2)
		{
			return true;
		}
		return false;
	}
	
	private ArrayList<String> getKeyword() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if (pos.equals("fw")) {
				tmp.add(words.get(i));
			}
		}
		return tmp;
	}

	/**
	 * 获得所有名词(length>1)
	 */
	private ArrayList<String> getNouns() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if ((pos.equals("ni")||pos.equals("nl")||pos.equals("ns")||pos.equals("nt")
					||pos.equals("nz")||pos.equals("j")||pos.equals("n")||
					pos.equals("nd")||pos.equals("nh")||pos.equals("ws"))&&words.get(i).length() > 1) {
				tmp.add(words.get(i));
			}
		}
		return tmp;
	}
	/**
	 * 获得所有除了关键词之外的其他词（length>1）
	 * @return
	 */
	private ArrayList<String> getAllOtherWord() {
		ArrayList<String> tmp = new ArrayList<String>();
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if (!pos.equals("fw") && words.get(i).length() > 1) {
				tmp.add(words.get(i));
			}
		}
		return tmp;
	}
	/**
	 * 清理kewords 去除相同 去股票，股份，股市这类词
	 */
	private void clearKeywords()
	{
		ArrayList<String>results=new ArrayList<String>(keywords.size());
		HashSet<String>flag=new HashSet<String>();
		for(String keyword:keywords)
		{
			if(!flag.contains(keyword))
			{
				results.add(keyword);
				flag.add(keyword);
			}
		}
		this.keywords.clear();
		this.keywords.addAll(results);
		results.clear();
		for(String keyword:keywords)
		{
			if(!((keyword.equals("股票")||keyword.equals("股市")||keyword.equals("股份"))&&keywords.size()>1))
			{
				results.add(keyword);
			}
		}
		this.keywords=results;
	}

	
	/**
	 * 定义类问题处理
	 * @return
	 */
	private ArrayList<KeywordPair> difinitionTypeProcess() {
		ArrayList<KeywordPair> keywordsPairs = new ArrayList<KeywordPair>();
		ArrayList<String> nouns = getNouns();
		for (int i = 0; i < keywords.size(); i++) {
			String keyword1 = keywords.get(i);
			for (int j = 0; j < nouns.size(); j++) {
				String keyword2 = nouns.get(j);
				keywordsPairs.add(new KeywordPair(keyword1, keyword2, KeywordPair.TYPE_COMMON));
			}
			keywordsPairs.add(new KeywordPair(keyword1, "定义", KeywordPair.TYPE_COMMON));
			keywordsPairs.add(new KeywordPair(keyword1, "概述", KeywordPair.TYPE_COMMON));
			keywordsPairs.add(new KeywordPair(keyword1, "简介", KeywordPair.TYPE_COMMON));
		}
		return keywordsPairs;
	}

	
	/**
	 * 原因类问题处理
	 */
	private ArrayList<KeywordPair> reasonTypeProcess() {
		ArrayList<KeywordPair> keywordpairs = new ArrayList<KeywordPair>();
		if (keywords.size() == 1) {
			if (hasNegativeWord(question)) {
				keywordpairs.add(new KeywordPair(keywords.get(0), "缺点", KeywordPair.TYPE_COMMON));
			} else {
				keywordpairs.add(new KeywordPair(keywords.get(0), "定义", KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(0), "优点", KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(0), "目的", KeywordPair.TYPE_COMMON));
			}
		} else if (keywords.size() == 2) {
			if (hasNegativeWord(question)) {
				keywordpairs.add(new KeywordPair(keywords.get(0), "缺点", KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(1), "缺点", KeywordPair.TYPE_COMMON));
			} else {
				ArrayList<String> nouns = getNouns();
				for (int i = 0; i < nouns.size(); i++) {
					keywordpairs.add(new KeywordPair(keywords.get(0), nouns.get(i), KeywordPair.TYPE_COMMON));
					keywordpairs.add(new KeywordPair(keywords.get(1), nouns.get(i), KeywordPair.TYPE_COMMON));
				}
				keywordpairs.add(new KeywordPair(keywords.get(0), question, KeywordPair.TYPE_SEARCHTEXT));
				keywordpairs.add(new KeywordPair(keywords.get(1), question, KeywordPair.TYPE_SEARCHTEXT));
			}

		}
		return keywordpairs;
	}

	/**
	 * 比较类
	 */
	private ArrayList<KeywordPair> comparisonTypeProcess() {
		ArrayList<KeywordPair> keywordpairs = new ArrayList<KeywordPair>();
		if(keywords.size()==1)
		{
			keywordpairs.add(new KeywordPair(keywords.get(0), "区别", KeywordPair.TYPE_COMMON));
		}else if(keywords.size() == 2) {
			ArrayList<String> nouns = getNouns();
			for (int i = 0; i < nouns.size(); i++) {
				keywordpairs.add(new KeywordPair(keywords.get(0), nouns.get(i), KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(1), nouns.get(i), KeywordPair.TYPE_COMMON));
			}
			keywordpairs.add(new KeywordPair(keywords.get(0), keywords.get(1), KeywordPair.TYPE_SEARCHSAMEKEYWORD));
		}
		return keywordpairs;
	}

	/**
	 * 描述类问题
	 */
	private ArrayList<KeywordPair> descriptionTypeProcess() {
		ArrayList<KeywordPair> keywordpairs = new ArrayList<KeywordPair>();
		ArrayList<String> otherWord = getAllOtherWord();
		if (keywords.size() == 1) {
			for (int i = 0; i < otherWord.size(); i++) {
				keywordpairs.add(new KeywordPair(keywords.get(0), otherWord.get(i), KeywordPair.TYPE_COMMON));
			}
			keywordpairs.add(new KeywordPair(keywords.get(0), "方法", KeywordPair.TYPE_COMMON));
			keywordpairs.add(new KeywordPair(keywords.get(0), "手段", KeywordPair.TYPE_COMMON));
			keywordpairs.add(new KeywordPair(keywords.get(0), "过程", KeywordPair.TYPE_COMMON));
		} else if (keywords.size() == 2) {
			for (int i = 0; i < otherWord.size(); i++) {
				keywordpairs.add(new KeywordPair(keywords.get(0), otherWord.get(i), KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(1), otherWord.get(i), KeywordPair.TYPE_COMMON));
			}
			keywordpairs.add(new KeywordPair(keywords.get(0), "方法", KeywordPair.TYPE_COMMON));
			keywordpairs.add(new KeywordPair(keywords.get(1), "方法", KeywordPair.TYPE_COMMON));
		}
		return keywordpairs;
	}

	/**
	 * 获得距离指定位置近的专有名词或名词
	 * @param afterPosition
	 * @return
	 */
	private String getNOrFwNearPosition(int afterPosition)
	{
		String result="";
		if(afterPosition==-1)
		{
			return result;
		}
		int position=-999;
		for (int i = 0; i < poses.size(); i++) {
			String pos = poses.get(i);
			if ((pos.contains("n")||pos.equals("fw"))) {
				String tmp=words.get(i);
				int tmpPosition=question.indexOf(tmp);
				if(tmpPosition<afterPosition)
				{
					tmpPosition+=tmp.length();
				}else if(tmpPosition>afterPosition)
				{
					tmpPosition--;
				}
				if(Math.abs(tmpPosition-afterPosition)<=Math.abs(position-afterPosition))
				{
					position=tmpPosition;
					result=tmp;
				}
			}
		}
		return result;
		
	}
	/**
	 * 列举类
	 * @return
	 */
	private ArrayList<KeywordPair> enumerationTypeProcess() {
		ArrayList<KeywordPair> keywordpairs = new ArrayList<KeywordPair>();
		int enumkeywordposition = -1;
		String enumkeyword = "";
		for (int i = 0; i < enumerationDic.size(); i++) {
			if (question.indexOf(enumerationDic.get(i)) != -1) {
				enumkeyword = enumerationDic.get(i);
				enumkeywordposition = question.indexOf(enumerationDic.get(i));
				break;
			}
		}
		if (keywords.size() == 1) {//只有一个关键词
			String targetNorm = getNOrFwNearPosition(enumkeywordposition);
			if (targetNorm.equals("")||targetNorm.equals(keywords.get(0))) {
				keywordpairs.add(new KeywordPair(keywords.get(0), "定义", KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(0), "分类", KeywordPair.TYPE_COMMON));
			} else {
				keywordpairs.add(new KeywordPair(keywords.get(0), targetNorm, KeywordPair.TYPE_COMMON));
			}	
		} else if (keywords.size() == 2) {//有两个关键词
			String targetNorm=getNOrFwNearPosition(enumkeywordposition);
			if(targetNorm.equals(keywords.get(0)))
			{
				keywordpairs.add(new KeywordPair(keywords.get(1), keywords.get(0), KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(0), "分类", KeywordPair.TYPE_COMMON));
			}else if(targetNorm.equals(keywords.get(1)))
			{
				keywordpairs.add(new KeywordPair(keywords.get(0), keywords.get(1), KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(1), "分类", KeywordPair.TYPE_COMMON));
			}else //如果是名词 eg 哪些现象是庄家出货
			{
				keywordpairs.add(new KeywordPair(keywords.get(0), targetNorm, KeywordPair.TYPE_COMMON));
				keywordpairs.add(new KeywordPair(keywords.get(1), targetNorm, KeywordPair.TYPE_COMMON));
			}		
		}
		return keywordpairs;

	}

	/**
	 * deatt类别处理
	 * @return
	 */
	private ArrayList<KeywordPair> deAttTypeProcess()
	{
		ArrayList<KeywordPair>keywordPairs=new ArrayList<KeywordPair>();
		ArrayList<String>targetNorms=getNouns();
		for(String keyword:keywords)
		{
			for(String norm:targetNorms)
			{
				keywordPairs.add(new KeywordPair(keyword, norm, KeywordPair.TYPE_COMMON));
			}
			keywordPairs.add(new KeywordPair(keyword, question, KeywordPair.TYPE_SEARCHTEXT));
		}
	
		return keywordPairs;
	}
	/**
	 * 其他类别处理
	 * @return
	 */
	private ArrayList<KeywordPair> otherTypeProcess()
	{
		ArrayList<KeywordPair>keywordPairs=new ArrayList<KeywordPair>();
		for(String keyword:keywords)
		{
			keywordPairs.add(new KeywordPair(keyword, question, KeywordPair.TYPE_SEARCHTEXT));
		}
	
		return keywordPairs;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<KeywordPair> process() {
		keywords = getKeyword();
		clearKeywords();
		switch (identifyQuestionType(question)) {
		case TYPE_REASON:
			return reasonTypeProcess();
		case TYPE_COMPARISON:
			return comparisonTypeProcess();
		case TYPE_DESCRIPTION:
			return descriptionTypeProcess();
		case TYPE_ENUMERATION:
			return enumerationTypeProcess();
		case TYPE_DEFINITION:
		case TYPE_SPECIALDEFINITION:
			return difinitionTypeProcess();
		case TYPE_DEATT:
			return deAttTypeProcess();
		default:
			return otherTypeProcess();
		}
	}

	public static void main(String[] args) {

		try {
			QuestionPreprocess a = new QuestionPreprocess();

			BufferedReader in = new BufferedReader(new FileReader("question.txt"));
		//	PrintWriter out = new PrintWriter(new F);
			String line = in.readLine();
			while (line != null) {
				String question = "股票盘整期间有哪些典型的K线组合";
				a.process(question);
				System.out.println("-------------------------------------------");
				System.out.println("问句:" + a.getQuestion());
				switch (a.getQuestionType()) {
				case QuestionType.TYPE_CONCEPT:
					System.out.println("类型：基本概念");
					break;
				case QuestionType.TYPE_STOCKINFO:
					System.out.println("类型：公司信息");
					break;

				}
				ArrayList<String> words = a.getWords();
				ArrayList<String> poses = a.getPoses();
				for (int i = 0; i < words.size(); i++) {
					System.out.print(words.get(i) + "\\" + poses.get(i) + " ");
				}
				System.out.println();
				System.out.println("---------------------------------------------");
				ConceptQuesAnaly cqa = new ConceptQuesAnaly(question, words, poses);
				ArrayList<KeywordPair> tmp = cqa.process();
				if (tmp != null) {
					for (int i = 0; i < tmp.size(); i++) {
						String type = "";
						switch (tmp.get(i).getType()) {
						case KeywordPair.TYPE_COMMON:
							type = "comman";
							break;
						case KeywordPair.TYPE_SEARCHSAMEKEYWORD:
							type = "keyword2相同搜索方法";
							break;
						case KeywordPair.TYPE_SEARCHTEXT:
							type = "文本互搜";
							break;
						}
						System.out.println("(" + tmp.get(i).getKeyword1() + "," + tmp.get(i).getKeyword2() + "," + type + ")");
					}
				}

				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
