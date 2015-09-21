package com.QA.Extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.QA.Model.AnalyResult;
import com.QA.Model.AnswerType;
import com.QA.Model.InfoPosition;
import com.QA.Model.Investor;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.Util.DBUtil;

/**
 * 
 * @author li
 *
 */
public class InvestorAnsExtra {

	
	
	private static QuestionAnswerPair getProperty(AnalyResult result)
	{
		String name=result.getName();
		InfoPosition infoposition=result.getInfoPosition();
	
		String query="select "+"id,"+infoposition.getColumn()+" from investor where investor=?";
		System.out.println(query);
		ArrayList<Object>params=new ArrayList<Object>();
		params.add(name);
		ResultSet res;
		String tmp = null;
		int id = 0;
		try {
			res=DBUtil.query(query, params);
			if(res.next())
			{
				tmp=res.getString(infoposition.getColumn());
				id=res.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QuestionAnswerPair answer=new QuestionAnswerPair(id, 
				name,
				infoposition.getProperty(),
				QuestionType.TYPE_PERSONINFO, AnswerType.TYPE_STRING, tmp);
		
		return answer;
		
	}
	
	private static QuestionAnswerPair getAll(String name)
	{
		String query="select id from investor where investor=?";
		System.out.println(query);
		ArrayList<Object>params=new ArrayList<Object>();
		params.add(name);
		ResultSet res;
		int id=-1;
		try {
				res=DBUtil.query(query, params);
				if(res.next())
				{
					id=res.getInt("id");
				}
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QuestionAnswerPair answer=new QuestionAnswerPair(id,
				name,"没有找到你要的答案！",
				QuestionType.TYPE_PERSONINFO, AnswerType.TYPE_NULL, null);
		
		return answer;
	}
	public static ArrayList<QuestionAnswerPair> getAnswers(ArrayList<AnalyResult> results)
	{
		ArrayList<QuestionAnswerPair> answers=new ArrayList<QuestionAnswerPair>();
		for(AnalyResult result:results)
		{
			if (result.getInfoPosition() != null) {
				QuestionAnswerPair answer =getProperty(result);
				answers.add(answer);
			}else
			{
				String name=result.getName();
				QuestionAnswerPair answer = getAll(name);
				answers.add(answer);
				break;
			}
		}
		return answers;
	}
	
}
