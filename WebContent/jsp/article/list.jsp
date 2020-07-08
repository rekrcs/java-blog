<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	List<Article> cateNameForArticles = (List<Article>) request.getAttribute("cateNameForArticles");
	int totalPage = (int) request.getAttribute("totalPage");
	int cateItemId = (int) request.getAttribute("cateItemId");
	int paramPage = (int) request.getAttribute("page");
	String cateItemName = (String)request.getAttribute("cateItemName");
%>

<style>
.current {
	background-color: #afafaf;
	border-radius: 5px;
}
</style>
<h1 class="con">
	<%=cateItemName%>
</h1>
<div class="body-content">
	<section class="body-section">
</div>
<%
	String cateName = null;
	int cateNum = 0;
	for (Article article : articles) {
		for (Article article2 : cateNameForArticles) {
			if (article.getId() == article2.getId()) {
				cateName = article2.getExtra().get("cateItemName") + "";
				cateNum = article2.getCateItemId();
				break;
			}
		}
%>
<article>
	<header>
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
	<div class="list-body">
		<div class="list-body2-box">
			<span class="list-cateName"><a
				href="${pageContext.request.contextPath}/s/article/list?page=1&cateItemId=<%=cateNum%>">#
					<%=cateName%></a></span>
			<!--					<h3>미리보기 or 소재목</h3>
 					<ul>
						<li>링크 혹은 참조</li>
					</ul> -->
		</div>
		<div class="btn-box">
			<div class="list-detail-btn">
				<a
					href="${pageContext.request.contextPath}/s/article/detail?id=<%=article.getId()%>" class="btn">자세히»</a>
			</div>
		</div>
	</div>
	<footer>
		<div class="list-footer"></div>
	</footer>
</article>
<%
	}
%>

<div class="page-number-box">
	<span class="page-number"> <%
 	for (int i = 1; i <= totalPage; i++) {
 %> <%
 	if (cateItemId == 0) {
 %> <a class="<%=i == paramPage ? "current" : "" %>"
		href="${pageContext.request.contextPath}/s/article/list?page=<%=i%>"><%=i%></a>
		<%
			}
		%> <%
 	if (cateItemId != 0) {
 %> <a class="<%=i == paramPage ? "current" : "" %>"
		href="${pageContext.request.contextPath}/s/article/list?cateItemId=${param.cateItemId}&page=<%=i%>"><%=i%></a>
		<%
			}
		%> <%
 	}
 %>

	</span>
	<div class="con search-box flex flex-jc-c" style="margin-top: 20px">
		<form style="border: 1px solid black"
			action="${pageContext.request.contextPath}/s/article/list">
			<input type="hidden" name="page" value="1" /> <input type="hidden"
				name="cateItemId" value="${param.cateItemId}" /> <input
				type="hidden" name="searchKeywordType" value="title" /> <input
				type="text" name="searchKeyword" value="${param.searchKeyword}" />
			<button type="submit">검색</button>
		</form>

	</div>
	</section>
</div>
<%@ include file="/jsp/part/foot.jspf"%>
