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
		case "detail":
			return doActionDetail(request, response);
		case "doWrite":
			return doActionDoWrite(request, response);
		}
		return "";

	}

	private String doActionDoWrite(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String cateItemId = request.getParameter("cateItemId");

		articleService.doWriteArticle(title, body, cateItemId, request, response);
		return "article/doWrite";
	}

	private String doActionDetail(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = articleService.getForDetailFromArticle(id, request, response);
		request.setAttribute("article", article);
		return "article/detail";
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
		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId, request, response);
		request.setAttribute("articles", articles);
		return "article/list";
	}

}
