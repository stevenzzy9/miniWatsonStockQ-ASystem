package com.QA.NLP;



public final class TSTNode {

	/**父节点*/
	public final static int PARENT = 0;
	/**左边子节点*/
	public final static int LOKID = 1;
	/**中间子节点*/
	public final static int EQKID = 2;
	/**右边子节点*/
	public final static int HIKID = 3;
	/**节点的值*/
	public Object data=null;
	/** The relative nodes. */
	public TSTNode[] relatives = new TSTNode[4];
	/**本节点表示的字符  */
	public char splitchar;
	/**
	 *  构造函数
	 *@param  splitchar  该节点表示的字符
	 *@param  parent     父节点
	 */
	public TSTNode(char splitchar, TSTNode parent) {
		this.splitchar = splitchar;
		relatives[PARENT] = parent;
	}
	public String toString()
	{
		return String.valueOf(splitchar)+":"+data;
	}
}