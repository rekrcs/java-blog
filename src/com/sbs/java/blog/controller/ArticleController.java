package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController(Connection dbConnection) {
		articleService = new ArticleService(dbConnection);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		switch (actionMethodName) {
		case "list":
			return doActionList(request, response);

		}
		return "";

	}

	private String doActionList(HttpServletRequest request, HttpServletResponse response) {
		int cateItemId = 0;
		if (request.getParameter("cateItemId") != null) {
			cateItemId = Integer.parseInt(request.getParameter("cateItemId"));
		}

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId);
		request.setAttribute("articles", articles);
		return "article/list";
	}

}
