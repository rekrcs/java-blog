package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.CateItemService;

public class HomeController extends Controller {
	private CateItemService cateItemService;

	public HomeController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConnection, actionMethodName, request, response);
		cateItemService = new CateItemService(dbConnection, request, response);
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
		List<CateItem> cateItems = cateItemService.getCateItems(request, response);
		request.setAttribute("cateItems", cateItems);
		return "home/articles.jsp";
	}

	private String doActionAboutMe(HttpServletRequest request, HttpServletResponse response) {

		return "home/aboutMe.jsp";
	}

}
