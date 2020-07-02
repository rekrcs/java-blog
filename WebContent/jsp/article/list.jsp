<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

<%
	int totalPage = (int) request.getAttribute("totalPage");
%>
<%
	int cateItemId = (int) request.getAttribute("cateItemId");
%>

<div class="body-content">
	<section class="body-section">
		<%
			for (Article article : articles) {
		%>
		<article>
			<header>
				<h1 class="list-title">
					<a class="title-underline"
						href="${pageContext.request.contextPath}/s/article/detail?id=<%=article.getId()%>"><%=article.getTitle()%></a>
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
			<div class="list-body">
				<div class="list-body2-box">
					<h3>미리보기 or 소재목</h3>
					<ul>
						<li>링크 혹은 참조</li>
					</ul>
				</div>
				<div class="btn-box">
					<div class="list-detail-btn">
						<a
							href="${pageContext.request.contextPath}/s/article/detail?id=<%=article.getId()%>"
							+ <%=article.getId()%> class="btn">자세히»</a>
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
			<span class="page-number"> <%for (int i = 1; i <= totalPage; i++) {%>
						<%if(cateItemId == 0) {%> 
			<a href="${pageContext.request.contextPath}/s/article/list?page=<%=i%>">[<%=i%>]</a><%}%>	
				<%if(cateItemId != 0) { %>
			<a href="${pageContext.request.contextPath}/s/article/list?cateItemId=${param.cateItemId}&page=<%=i%>">[<%=i%>]</a><%}%>
			<%}%></span>
		</div>
	</section>
</div>
<%@ include file="/jsp/part/foot.jspf"%>
