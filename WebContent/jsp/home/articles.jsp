<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.CateItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>

<style>
.cateItem-container {
	padding-top: 40px;
	text-align: center;
}

.cateList {
	padding-top: 40px;
	opacity:0.7;
}

.cateList>a {
	margin: 10px;
	font-size: 2rem;
	border-bottom:2px solid #afafaf;
	coler:#afafaf;
	
}

.cateList>a:hover {
	opacity:1;
}
</style>
<div class="cateItem-container">
	<div class="cate-title">
		<h1>카테고리</h1>
	</div>
	<div class="cateList">
		<%
			for (CateItem cateItem : cateItems) {
		%>
		<a
			href="${pageContext.request.contextPath}/s/article/list?page=1&cateItemId=<%=cateItem.getId()%>"><%=cateItem.getName()%></a>
		<%
			}
		%>
	</div>
</div>
<%@ include file="/jsp/part/foot.jspf"%>