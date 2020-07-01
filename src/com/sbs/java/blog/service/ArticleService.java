package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(Connection dbConnection) {
		this.articleDao = new ArticleDao(dbConnection);
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId) {
		return articleDao.getForPrintListArticles(page, cateItemId);
	}

}
