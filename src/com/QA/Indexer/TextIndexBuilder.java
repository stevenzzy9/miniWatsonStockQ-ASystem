package com.QA.Indexer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * 建立txt文件索引的类
 * @author li
 *
 */
public class TextIndexBuilder {
	
	private File indexDir;//索引文件目录
	private File fileDir;//文件目录
	
	public TextIndexBuilder(String indexPath,String filePath) throws IOException
	{
		this.indexDir=new File(indexPath);
		this.fileDir=new File(filePath);		
		if(!fileDir.exists()||!fileDir.isDirectory()||!fileDir.canRead())
		{
			throw new IOException(fileDir+"出错");
		}
	}
	public void process()
	{
		
		try {
			IndexWriter FSWriter=new IndexWriter(FSDirectory.open(indexDir), new ChineseAnalyzer(), true, IndexWriter.MaxFieldLength.LIMITED);
			FSWriter.setUseCompoundFile(true);
			subindexBuilder(FSWriter,fileDir);
			FSWriter.optimize();
			FSWriter.close();
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void subindexBuilder(IndexWriter fsWriter,File subPath) throws CorruptIndexException, LockObtainFailedException, IOException
	{
		File[]fileList=subPath.listFiles();
		for(int i=0;i<fileList.length;i++)
		{
			File file=fileList[i];
			if(file.isDirectory())
			{
				subindexBuilder(fsWriter, file);
			}else if(isValidType(file.getName()))
			{
				fileindexBuilder(fsWriter, file);
			}
		}
	}
	private void fileindexBuilder(IndexWriter fsWriter,File subfile) throws CorruptIndexException, LockObtainFailedException, IOException
	{
		if(subfile.isHidden()||!subfile.exists()||!subfile.canRead())
		{
			return ;
		}
		Directory ramdirectory=new RAMDirectory();
		IndexWriter ramWriter=new IndexWriter(ramdirectory,new ChineseAnalyzer(),true,IndexWriter.MaxFieldLength.UNLIMITED);
		FileReader fpReader=new FileReader(subfile);
		Document document=new Document();
		Field field_fileName=new Field("fileName",subfile.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED);
		Field field_path=new Field("path",subfile.getAbsolutePath(),Field.Store.YES,Field.Index.NOT_ANALYZED);
		Field field_content=new Field("content",fpReader);
		document.add(field_fileName);
		document.add(field_path);
		document.add(field_content);
		ramWriter.addDocument(document);
		ramWriter.close();
		fsWriter.addIndexesNoOptimize(new Directory[]{ramdirectory});	
		
	}
	/**
	 * 判断是否是txt文件
	 * @param name
	 * @return
	 */	
	private static boolean isValidType(String name)
	{
		if(name.endsWith(".txt"))
		{
			return true;
		}else
		{
			return false;
		}
	}
		
	public static void main(String[] args) {
		
		try {
			String indexPath="Files\\Index";
			String filePath="Files\\WebContent";
			TextIndexBuilder builder=new TextIndexBuilder(indexPath, filePath);
			builder.process();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
