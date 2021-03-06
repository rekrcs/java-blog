<%@ page import="java.util.List"%>

<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
	List<Article> cateNameForArticles = (List<Article>) request.getAttribute("cateNameForArticles");
	Article articlePrevious = (Article) request.getAttribute("articlePrevious");
	Article articleNext = (Article) request.getAttribute("articleNext");
	int firstId = (int) request.getAttribute("firstId");
	int lastId = (int) request.getAttribute("lastId");
%>
<style>
#viewer1 {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}

.forMargin {
	margin-bottom: 100px;
}

.detail-body-container {
	width: 700px;
	margin-left: auto;
	margin-right: auto;
}

@media ( max-width :799px) {
	.detail-body-container {
		width: 100%;
	}
	body {
	margin: 15px;
	}
}
.previous-next-box {
	margin: 50px 0;
	width: 100%;
	display: flex;
	justify-content: space-between;
}

/* 수정 삭제 버튼 시작 */
.option-box {
	display:flex;
	justify-content: flex-end;
}

.option-box > span:nth-child(2) {
	margin:0 3px;
}
.option-box > span > a:hover {
	border-bottom: 1px solid black;
}
</style>
<div class="detail-body-container">
	<header>

		<%
			String cateName = null;
			int cateNum = 0;
			for (Article article2 : cateNameForArticles) {
				if (article.getId() == article2.getId()) {
					cateName = article2.getExtra().get("cateItemName") + "";
					cateNum = article2.getCateItemId();
				}
			}
		%>
		<h1 class="list-title">
			<a class="title-underline"
				href="${pageContext.request.contextPath}/s/article/detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
		</h1>
		<div class="list-info">
			<span class="list-regDate"> <span class="list-icon"> <i
					class="far fa-calendar"></i>
			</span> <span class="list-text"> 작성일 </span> <span> <%=article.getRegDate().substring(0, 10)%>
			</span>
			</span> <span class="list-updateDate"> <span class="list-bar">|</span>
				<span class="list-icon"><i class="far fa-folder-open"></i></span> <span
				class="list-text"> 카테고리 </span> <span> <a class="boardName"
					href="${pageContext.request.contextPath}/s/article/list?page=1&cateItemId=<%=cateNum%>"><%=cateName%></a>
			</span>
			</span>
		</div>
	</header>
<script type="text/x-template" id="origin1">	
작성일 : <%=article.getRegDate()%>
수정일 : <%=article.getUpdateDate()%>
### 번호 : <%=article.getId()%>
## 내용
<%=article.getBody()%></script>

	<div id="viewer1"></div>
	<script>
		var editor1__initialValue = $('#origin1').html();
		var editor1 = new toastui.Editor({
			el : document.querySelector('#viewer1'),
			initialValue : editor1__initialValue,
			viewer : true,
			plugins : [ toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin, replPlugin, codepenPlugin ]
		});
	</script>
	<div class="option-box">
		<span class="option-modify"><a href="${pageContext.request.contextPath}/s/article/modify?id=${param.id}">수정</a></span><span></span><span class="option-delete"><a href="delete?id=${param.id}">삭제</a></span>
	</div>
	<div class="previous-next-box">
		<%
			if (article.getId() == firstId) {
		%>

		<span class="previous-btn"></span> <span class="next-btn"><a
			href="${pageContext.request.contextPath}/s/article/detail?id=<%=articleNext.getId()%>"><%=articleNext.getTitle()%>
				<i class="fas fa-angle-right"></i></a></span>

		<%
			} else if (article.getId() == lastId) {
		%>
		<span class="previous-btn"><a
			href="${pageContext.request.contextPath}/s/article/detail?id=<%=articlePrevious.getId()%>"><i
				class="fas fa-angle-left"></i> <%=articlePrevious.getTitle()%></a></span> <span
			class="next-btn"></span>
		<%
			} else {
		%>
		<span class="previous-btn"><a
			href="${pageContext.request.contextPath}/s/article/detail?id=<%=articlePrevious.getId()%>"><i
				class="fas fa-angle-left"></i> <%=articlePrevious.getTitle()%></a></span> <span
			class="next-btn"><a
			href="${pageContext.request.contextPath}/s/article/detail?id=<%=articleNext.getId()%>"><%=articleNext.getTitle()%>
				<i class="fas fa-angle-right"></i></a>
			<%
				}
			%>
	</div>
</div>
<div class="forMargin"></div>