package com.QA.Indexer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;

import com.QA.Util.DBUtil;
import com.QA.Util.PathUtil;

/**
 * 利用lucene建立数据库全文索引类
 * 
 * @author li
 * 
 */
public class DBIndexBuilder {

	private String tableName;
	private ArrayList<Column> columns;
	private String indexDir;

	public DBIndexBuilder(String tableName, ArrayList<Column> columns, String indexDir) {
		this.tableName = tableName;
		this.columns = columns;
		this.indexDir = indexDir;
	}

	public void process() {
		try {
			IndexWriter FSWriter = new IndexWriter(FSDirectory.open(new File(indexDir)), new ChineseAnalyzer(), true,
					IndexWriter.MaxFieldLength.LIMITED);
			FSWriter.setUseCompoundFile(true);
			buildIndex(FSWriter);
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

	private void buildIndex(IndexWriter fsWriter) {
		try {
			Directory ramdirectory = new RAMDirectory();
			IndexWriter ramWriter = new IndexWriter(ramdirectory, new ChineseAnalyzer(), true, IndexWriter.MaxFieldLength.UNLIMITED);

			String query = "select * from "+tableName;
			ArrayList<Object> params = new ArrayList<Object>();
			ResultSet res = DBUtil.query(query, params);
			while (res.next()) {
				int id=res.getInt("id");
				System.out.println(id);
				Document document = new Document();
				for (Column column : columns) {
					Field field = new Field(column.columnName, res.getString(column.columnName), column.isStore, column.index);
					document.add(field);
				}
				ramWriter.addDocument(document);
								
			}
			int i=0;
			System.out.println(ramWriter.numDocs());
			ramWriter.close();
			fsWriter.addIndexesNoOptimize(new Directory[] { ramdirectory });
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ArrayList<Column> columns = new ArrayList<Column>();
		Column column = new Column("id", Store.YES, Index.NOT_ANALYZED);
		columns.add(column);
		column=new Column("question", Store.NO, Index.ANALYZED);
		columns.add(column);
		column=new Column("tag", Store.NO, Index.ANALYZED);
		columns.add(column);
		String indexDir="src/Files/index/operationindex";
		new DBIndexBuilder("operation2", columns, indexDir).process();
	}

}

class Column {
	public String columnName;
	public Field.Store isStore;
	public Field.Index index;

	public Column(String columnName, Store isStore, Index index) {
		super();
		this.columnName = columnName;
		this.isStore = isStore;
		this.index = index;
	}
}