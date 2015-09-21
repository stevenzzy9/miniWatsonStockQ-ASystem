package ruc.irm.similarity.sentence;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ictclas4j.bean.SegNode;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.Segment;
import org.ictclas4j.utility.POSTag;

import com.QA.NLP.LTP;

/**
 * 对词法分析程序的封装代理，目前内部封装了对Ictclas4j（夏天改进版）的调用<br/>
 * 为方便演示程序快速启动，对Segment的调用采用了单例模式，实现需要时的延迟加载。
 * 
 * @author <a href="mailto:iamxiatian@gmail.com">夏天</a>
 * @organization 中国人民大学信息资源管理学院 知识工程实验室
 */
public class SegmentProxy {
	static Segment seg = null;
	private static LTP ltp=new LTP();
	private static Segment getSegment(){
		if(null==seg){
			seg = new Segment(1);
		}
		return seg;
	}
	
	public static class Word {
		/** 词语内容 */
		private String word;
		/** 词语词性数字代号 */
		private String pos;
		
		public Word(String word, String pos){
			this.word = word;
			this.pos = pos;
		}		
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public String getPos() {
			return pos;
		}
		public void setPos(String pos) {
			this.pos = pos;
		}			
	}
	
	public synchronized  static List<Word> segment(String sentence){
		
		
		List<Word> results = new ArrayList<Word>();
//		SegResult segResult = getSegment().split(sentence);
//		for(SegNode segNode: segResult.getSegNodes()){
//			if (segNode.getPos() != POSTag.SEN_BEGIN && segNode.getPos() != POSTag.SEN_END) {
//				results.add(new Word(segNode.getSrcWord(), segNode.getPos()));
//			}
//		}
		//System.out.println("分词:  ["+sentence+"]");
		ltp.createDOMFromString(sentence);
		ltp.posTag();
		ArrayList<String>words=ltp.getWordsFromSentence(0);
		ArrayList<String>poses=ltp.getPOSsFromSentence(0);
		for (int i = 0; i < words.size(); i++) {
			results.add(new Word(words.get(i), poses.get(i)));
		}
		
		return results;
		
	}
	
	
}
