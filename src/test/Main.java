package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.QA.Model.QuestionAnswerPair;
import com.QA.Process.AnswerExtractor;

public class Main {

	public static void main(String args[])
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in,"gbk"));
			String question;
			AnswerExtractor tmp=new AnswerExtractor();
			question = in.readLine();
			while (question != null) {
				ArrayList<QuestionAnswerPair>answers=tmp.process(question);
				if(answers!=null)
					for(int i=0;i<answers.size();i++)
					{
						System.out.println(answers.get(i));
					}
				question=in.readLine();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
