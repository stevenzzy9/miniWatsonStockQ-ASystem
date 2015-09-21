<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="gresults" status="st" id="googleres">
	<div class="words_more">
		<a href="<s:property value="url" escape="false" />"><s:property
				value="title" escape="false" />
		</a>
	<pre>
		<s:property value="content" escape="false" />
	</pre>
	</div>
</s:iterator>