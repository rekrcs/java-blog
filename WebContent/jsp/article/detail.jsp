<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
	Article articleNext = (Article) request.getAttribute("articleNext");
%>
<style>
#viewer1 {
	width: 800px;
	margin-left:auto;
	margin-right: auto;
}
.forMargin {
	margin-bottom: 100px;
}
.previous-next-box {
	margin:50px 0;
	width: 900px;
	margin-left:auto;
	margin-right: auto;
	display:flex;
	justify-content: space-between;
}
</style>
<header>
				<h1 class="list-title">
					<a class="title-underline" href="${pageContext.request.contextPath}/s/article/detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
				</h1>
				<div class="list-info">
					<span class="list-regDate"> <span class="list-icon">
							<i class="far fa-calendar"></i>
					</span> <span class="list-text"> 작성일 </span> <span> <%=article.getRegDate()%>
					</span>
					</span> <span class="list-updateDate"> <span class="list-bar">|</span>
						<span class="list-icon"><i class="far fa-folder-open"></i></span>
						<span class="list-text"> 게시판 </span> <span> <a
							class="boardName" href="#">공지사항</a>
					</span>
					</span>
				</div>
			</header>
<div id="origin1">
작성일 : <%=article.getRegDate()%>  
수정일 : <%=article.getUpdateDate()%>
### 번호 : <%=article.getId()%>  
## 내용 
<%=article.getBody()%>
</div>
<div id="viewer1"></div>
<div class="previous-next-box">
	<span class="previous-btn"><a href=""></a><i class="fas fa-angle-left"></i> 이전</span>
	<span class="next-btn"><a href="">다음 <i class="fas fa-angle-right"></i></a></span>
</div>
<div class="forMargin"></div>