package com.QA.Model;

/**
 * 公司一项信息在数据库中的位置
 * @author li
 *
 */
public class InfoPosition {

	private String property;//公司一项基本信息
	private String table;//表名
	private String column;//列名
	
	public InfoPosition(String property,String table,String column)
	{
		this.property=property;
		this.table=table;
		if(column.equals("-1"))
		{
			this.column=null;
		}else
		{
			this.column=column;
		}
	}
	@Override
	public String toString() {
		return "InfoPosition [column=" + column + ", property=" + property + ", table=" + table + "]";
	}
	public InfoPosition(String line)
	{
		String[] words=line.split("\t");
		this.property=words[0].trim();
		this.table=words[1].trim();
		if(words[2].equals("-1"))
		{
			this.column=null;
		}else
		{
			this.column=words[2].trim();
		}
	}
	
	public String getProperty() {
		return property;
	}
	public String getTable() {
		return table;
	}
	public String getColumn() {
		return column;
	}
}
