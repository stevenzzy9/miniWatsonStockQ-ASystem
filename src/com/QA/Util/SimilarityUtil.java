package com.QA.Util;

import java.util.Arrays;

import edu.ucla.sspace.hal.GeometricWeighting;

import ruc.irm.similarity.sentence.editdistance.GregorEditDistance;
import ruc.irm.similarity.sentence.morphology.MorphoSimilarity;
import ruc.irm.similarity.word.CharBasedSimilarity;
import ruc.irm.similarity.word.cilin.Cilin;
import ruc.irm.similarity.word.hownet.concept.LiuConceptParser;
import ruc.irm.similarity.word.hownet.concept.XiaConceptParser;

public class SimilarityUtil {

	/**
	 * 计算四种相似度，通过某相似度标准来衡量两个关键字是否可以进行互换
	 * 
	 * @param k2
	 *            关键字
	 * @param key
	 *            关键字
	 * @return 是否能够匹配互换
	 */
	public static boolean analyseSimilarity(String k2, String key) {
		// 得到四种相似度
		double score1=Cilin.getInstance().getSimilarity(k2, key);
		double score2=LiuConceptParser.getInstance().getSimilarity(k2, key);
		double score3=XiaConceptParser.getInstance().getSimilarity(k2,key);
		//double score4= new CharBasedSimilarity().getSimilarity(k2, key) ;
		//double[] similarities = { score2,score3}; 
			
//		String text = "[" + k2 + "]与[" + key + "]的相似度为:";
//		text = text + "\n词林:" + score1;
//		text = text + "\n刘群:" + score2;
//		text = text + "\n夏天:" + score3;
//		text = text + "\n字面:" + score4;
//		// 打印
//		System.out.println("\n" + text);

		// 标准：选取最大的相似度的值，如果大于0.5则通过
		//Arrays.sort(similarities);
		if ((score1!=0&&score2!=0&&score3!=0)&&(score1 > 0.45||score2>0.6||score3>0.75)) {
			return true;
		} else
			return false;
	}
	public static double getWordSimilarityScore(String k2, String key) {
		double score2=LiuConceptParser.getInstance().getSimilarity(k2, key);
		double score3=XiaConceptParser.getInstance().getSimilarity(k2,key);
		//double score4= new CharBasedSimilarity().getSimilarity(k2, key) ;
		double[] similarities = { score2,score3}; 
		Arrays.sort(similarities);
		return similarities[1];
	}
	
	public static double getSentenctSimilarityScore(String sentence1,String sentence2)
	{
		double score1=MorphoSimilarity.getInstance().getSimilarity(sentence1, sentence2);
		return score1;
	}
	
	
}
