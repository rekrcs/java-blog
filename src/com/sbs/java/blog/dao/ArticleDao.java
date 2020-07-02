package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

public class ArticleDao {
	private Connection dbConnection;

	public ArticleDao(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, HttpServletRequest request,
			HttpServletResponse response) {
		String sql = "";

		int itemsInAPage = 5;
		int limitFrom = (page - 1) * itemsInAPage;
		int totalPage;
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT %d, %d ", limitFrom, itemsInAPage);

		String sql2 = "";

		sql2 += String.format("SELECT COUNT(*)");
		sql2 += String.format(" FROM article");
		if (cateItemId != 0) {
			sql2 += String.format(" WHERE cateItemId = %d", cateItemId);
		}
		int totalCount = DBUtil.getTotalCount(dbConnection, sql2);
		totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("cateItemId", cateItemId);
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}
		return articles;

	}

	public Article getForDetailFromArticle(int id, HttpServletRequest request, HttpServletResponse response) {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id = %d", id);
		
		Map<String, Object> row = DBUtil.selectRow(dbConnection, sql);
		
		Article article = new Article(row);
		return article;
	}

	public void doWriteArticle(String title, String body, int cateItemId, HttpServletRequest request, HttpServletResponse response) {
		String sql = "";
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW()");
		sql += String.format(", updateDate = NOW()");
		sql += String.format(", disPlayStatus = 1");
		sql += String.format(", cateItemId = %d", cateItemId);
		sql += String.format(", title = '%s'", title);
		sql += String.format(", body = '%s'", body);

		DBUtil.insert(dbConnection, sql, response);
		
	}

}
