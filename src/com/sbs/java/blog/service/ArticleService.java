package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(Connection dbConnection) {
		this.articleDao = new ArticleDao(dbConnection);
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, HttpServletRequest request, HttpServletResponse response) {
		return articleDao.getForPrintListArticles(page, cateItemId, request, response);
	}

	public Article getForDetailFromArticle(int id, HttpServletRequest request, HttpServletResponse response) {
		return articleDao.getForDetailFromArticle(id, request, response);
	}

	public void doWriteArticle(String title, String body, int cateItemId, HttpServletRequest request, HttpServletResponse response) {
		 articleDao.doWriteArticle(title, body, cateItemId, request, response);
		
		
	}

}
