package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ruc.irm.similarity.word.hownet.concept.LiuConceptParser;

import com.QA.NLP.LTP;
import com.QA.Util.DBUtil;


public class Test {

	private void getCompany()
	{
		try {
			PrintWriter out=new PrintWriter(new File("companyName"));
			String query="select code,abbreviation from companyInfo";
			ArrayList<Object>params=new ArrayList<Object>();
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				String code=res.getString("code");
				String abbreviation=res.getString("abbreviation");
				abbreviation=abbreviation.replaceAll(" ", "");
				abbreviation=abbreviation.replaceAll("","A");
				abbreviation=abbreviation.replaceAll("","B");
				System.out.println(code+"\t"+abbreviation);
				out.println(abbreviation+"\t"+code);
			}
			out.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void getFinicialWord()
	{
		try {
			PrintWriter out=new PrintWriter(new File("financialwordDic"));
			String query="select word from dictionary";
			ArrayList<Object>params=new ArrayList<Object>();
			ResultSet res=DBUtil.query(query, params);
			while(res.next())
			{
				String word=res.getString("word");
				System.out.println(word);
				out.println(word+"\t1");
			}
			query="select word from dictionary2";
			res=DBUtil.query(query, params);
			while(res.next())
			{
				String word=res.getString("word");
				System.out.println(word);
				out.println(word+"\t1");
			}
			
			out.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	
	
	public static void main(String[] args) throws Exception {
		//new Test().getCompany();
		//new Test().getFinicialWord();
		System.out.println(LiuConceptParser.getInstance().getSimilarity("定义", "意义"));
	}

}
