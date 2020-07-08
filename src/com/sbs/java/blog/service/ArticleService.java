package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;

public class ArticleService extends Service {

	private ArticleDao articleDao;

	public ArticleService(Connection dbConnection) {
		this.articleDao = new ArticleDao(dbConnection);
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, int itemsInAPage, HttpServletRequest request,
			HttpServletResponse response, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintListArticles(page, cateItemId, itemsInAPage, request, response, searchKeywordType, searchKeyword);
	}

	public Article getForDetailFromArticle(int id, HttpServletRequest request, HttpServletResponse response) {
		return articleDao.getForDetailFromArticle(id, request, response);
	}

	public void doWriteArticle(String title, String body, int cateItemId, HttpServletRequest request,
			HttpServletResponse response) {
		articleDao.doWriteArticle(title, body, cateItemId, request, response);

	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {

		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
	}

	public int getFirstIdFromArticle() {
		return articleDao.getFirstIdFromArticle();
	}

	public int getLastIdFromArticle() {
		return articleDao.getListIdFromArticle();
	}

	public Article getNextArticle(int id, Article article) {
		return articleDao.getNextArticle(id, article);
	}

	public Article getPreviousArticle(int id, Article article) {
		return articleDao.getPreviousArticle(id, article);
	}

	public List<Article> getCateNameFromCateId() {
		return articleDao.getCateNameFromCateId();
	}

	public Article getForPrintArticle(int id) {

		return articleDao.getForPrintArticle(id);
	}

	public List<CateItem> getForPrintCateItems() {
		return articleDao.getForPrintCateItems();
	}

	public List<CateItem> getCateItems(HttpServletRequest request, HttpServletResponse response) {
		return articleDao.getCateItems(request, response);
	}

}
