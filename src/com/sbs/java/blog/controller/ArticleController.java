package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	public ArticleController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConnection, actionMethodName, request, response);

	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행 된다.
		// 필요 없다면 지워도 된다.
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList(request, response);
		case "detail":
			return doActionDetail(request, response);
		case "doWrite":
			return doActionDoWrite(request, response);
		case "write":
			return doActionWrite(request, response);
		case "modify":
			return doActionModify(request, response);
		case "doModify":
			return doActionDoModify(request, response);
		case "delete":
			return doActionDelete(request, response);
		}
		return "";

	}

	private String doActionDelete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		int deleteId = articleService.delete(id);
		return "html:<script> alert('" + id + "번 게시물이 삭제되었습니다.'); location.replace('list'); </script>";
	}

	private String doActionDoModify(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int cateItemId = Util.getInt(request, "cateItemId");
		int id = Integer.parseInt(request.getParameter("id"));
		int modifyId = articleService.modify(id, cateItemId, title, body);

		return "html:<script> alert('" + id + "번 게시물이 수정되었습니다.'); location.replace('list'); </script>";
	}

	private String doActionModify(HttpServletRequest request, HttpServletResponse response) {
		return "article/modify.jsp";
	}

	private String doActionWrite(HttpServletRequest request, HttpServletResponse response) {
		return "article/write.jsp";
	}

	private String doActionDoWrite(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int cateItemId = Util.getInt(request, "cateItemId");

		int id = articleService.write(cateItemId, title, body);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('list'); </script>";
	}

	private String doActionDetail(HttpServletRequest request, HttpServletResponse response) {
		if (Util.empty(request, "id")) {
			return "html:id를 입력해 주세요.";
		}

		if (Util.isNum(request, "id") == false) {
			return "html:id를 정수로 입력해 주세요.";
		}

		int id = Util.getInt(request, "id");

		Article article = articleService.getForDetailFromArticle(id, request, response);
		request.setAttribute("article", article);

		int firstId = articleService.getFirstIdFromArticle();
		request.setAttribute("firstId", firstId);

		int lastId = articleService.getLastIdFromArticle();
		request.setAttribute("lastId", lastId);

		Article articleNext = articleService.getNextArticle(id, article);
		request.setAttribute("articleNext", articleNext);

		Article articlePrevious = articleService.getPreviousArticle(id, article);
		request.setAttribute("articlePrevious", articlePrevious);

		// extra 추가후
		List<Article> cateNameForArticles = articleService.getCateNameFromCateId();
		request.setAttribute("cateNameForArticles", cateNameForArticles);

		// 추가
		Article articlePlus = articleService.getForPrintArticle(id);

		return "article/detail.jsp";
	}

	private String doActionList(HttpServletRequest request, HttpServletResponse response) {

		int page = 1;

		if (!Util.empty(request, "page") && Util.isNum(request, "page")) {
			page = Util.getInt(request, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(request, "cateItemId") && Util.isNum(request, "cateItemId")) {
			cateItemId = Util.getInt(request, "cateItemId");
		}

		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		request.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = "";

		if (!Util.empty(request, "searchKeywordType")) {
			searchKeywordType = Util.getString(request, "searchKeywordType");
		}

		String searchKeyword = "";

		if (!Util.empty(request, "searchKeyword")) {
			searchKeyword = Util.getString(request, "searchKeyword");
		}

		int itemsInAPage = 5;
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

		request.setAttribute("totalPage", totalPage);
		request.setAttribute("cateItemId", cateItemId);
		request.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, itemsInAPage, cateItemId,
				searchKeywordType, searchKeyword);
		request.setAttribute("articles", articles);

		// extra 추가후
		List<Article> cateNameForArticles = articleService.getCateNameFromCateId();
		request.setAttribute("cateNameForArticles", cateNameForArticles);

		return "article/list.jsp";
	}

}
