package com.QA.NLP;

	public class TSTItem {
		/** The key to the node. */
		public  Object data;

		/** The char used in the split. */
		public  String key;

		/**
		 *  Constructor method.
		 *
		 *@param  splitchar  The char used in the split.
		 *@param  parent     The parent node.
		 */
		public TSTItem(String key, Object data) {
			this.key = key;
			this.data = data;
		}
	}