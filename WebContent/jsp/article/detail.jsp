<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<style>
h1 {
	text-align: center;
}

section>div>div {
	width: 70%;
	margin-left: auto;
	margin-right: auto;
}
</style>
<div id="origin1">
# 제목 : <%=article.getTitle() %>
## 내용 : <%=article.getBody() %>
## 번호 : <%=article.getId() %>
## 작성일 : <%=article.getRegDate() %>
## 수정일 : <%=article.getUpdateDate() %>

```php
$a = 10;
$b = 20;
```
</div>
<div id="viewer1"></div>