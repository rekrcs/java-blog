<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

<div class="body-content">
	<section class="body-section">
		<%
			for (Article article : articles) {
		%>
		<article>
			<header>
				<h1 class="list-title">
					<a class="title-underline" href=""><%=article.getTitle()%></a>
				</h1>
				<div class="list-info">
					<span class="list-regDate"> <span class="list-icon">
							<i class="far fa-calendar"></i>
					</span> <span class="list-text"> 작성일 </span> <span> <%=article.getRegDate()%>
					</span>
					</span> <span class="list-updateDate"> <span class="list-bar">|</span>
						<span class="list-icon"><i class="far fa-edit"></i></span> <span
						class="list-text"> 수정일 </span> <span> <%=article.getUpdateDate()%>
					</span>
					</span>
				</div>
			</header>
			<div class="list-body">
				<h3>미리보기 or 소재목</h3>
				<ul>
					<li>링크 혹은 참조</li>
				</ul>
				<div class="list-detail-btn">
					<a href="" class="btn">자세히»</a>
				</div>
			</div>
			<footer>
				<div class="list-footer"></div>
			</footer>
		</article>
		<%
			}
		%>

	</section>
</div>
<%@ include file="/jsp/part/foot.jspf"%>