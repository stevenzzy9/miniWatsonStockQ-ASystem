package test;

import ruc.irm.similarity.word.cilin.Cilin;

public class Test4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cilin t=Cilin.getInstance();
		double score=t.getSimilarity("股权分置", "股票");
		System.out.println(score);
	}

}
