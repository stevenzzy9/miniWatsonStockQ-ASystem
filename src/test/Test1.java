package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.QA.Extractor.ConceptAnsExtra;
import com.QA.Model.KeywordPair;
import com.QA.Model.QuestionAnswerPair;
import com.QA.Model.QuestionType;
import com.QA.QuestionAnalyzer.ConceptQuesAnaly;
import com.QA.QuestionAnalyzer.QuestionPreprocess;

public class Test1 {

	// public static PrintWriter out=new PrintWriter(System.out);
	// public static BufferedReader in=new BufferedReader(new
	// InputStreamReader(System.in));

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		System.out.println("请输入问题：");
		BufferedReader in;
			in = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		try {
			line = in.readLine();
			while (line != null) {

				System.out.println("Question:" + line);
				QuestionPreprocess prep = new QuestionPreprocess();
				prep.process(line);
				String question = prep.getQuestion();
				ArrayList<String> words = prep.getWords();
				ArrayList<String> poses = prep.getPoses();
				System.out.println("预处理结构:——————————————————————————");
				System.out.println("Question:" + question);
				for (int i = 0; i < words.size(); i++) {
					System.out.print(words.get(i) + "\\" + poses.get(i));
				}
				System.out.println();
				int questionType = prep.getQuestionType();
				System.out.print("问句类型:  ");
				switch (questionType) {
				case QuestionType.TYPE_CONCEPT:
					System.out.println("基本概念");
					break;
				case QuestionType.TYPE_OPERATION:
					System.out.println("其他");
					break;
				case QuestionType.TYPE_PERSONINFO:
					System.out.println("投资家信息");
					break;
				case QuestionType.TYPE_STOCKINFO:
					System.out.println("个股信息");
					break;
				}
				System.out.println("-----------------------------------");
				if (questionType != QuestionType.TYPE_CONCEPT) {
					line = in.readLine();
					continue;
				}
				System.out.print("基本概念分析结果：");
				ConceptQuesAnaly cqa = new ConceptQuesAnaly(question, words, poses);
				ArrayList<KeywordPair> keywordpair = cqa.process();
				if (keywordpair != null) {
					for (int i = 0; i < keywordpair.size(); i++) {
						String type = "";
						switch (keywordpair.get(i).getType()) {
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
						System.out.println("(" + keywordpair.get(i).getKeyword1() + "," + keywordpair.get(i).getKeyword2() + "," + type + ")");
					}
					ArrayList<QuestionAnswerPair> questionAnswerPairs = ConceptAnsExtra.getAnswer(keywordpair);
					for (int i = 0; i < questionAnswerPairs.size(); i++) {
						QuestionAnswerPair pair = questionAnswerPairs.get(i);
						System.out.println("title" + pair.getQuestion());
						System.out.println("answer" + pair.getAnswer());
					}
				}

				line = in.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
