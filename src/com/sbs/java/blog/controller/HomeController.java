package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;

public class HomeController extends Controller {
	public HomeController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
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
		case "aboutMe":
			return doActionAboutMe(request, response);
		case "articles":
			return doActionArticles(request, response);
		case "main":
			return doActionMain(request, response);
		case "sns":
			return doActionSns(request, response);

		}
		return "";

	}

	private String doActionSns(HttpServletRequest request, HttpServletResponse response) {
		return "home/sns.jsp";
	}

	private String doActionMain(HttpServletRequest request, HttpServletResponse response) {

		return "home/main.jsp";
	}

	private String doActionArticles(HttpServletRequest request, HttpServletResponse response) {
		List<CateItem> cateItems = articleService.getCateItems(request, response);
		request.setAttribute("cateItems", cateItems);
		return "home/articles.jsp";
	}

	private String doActionAboutMe(HttpServletRequest request, HttpServletResponse response) {

		return "home/aboutMe.jsp";
	}

}
