package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

public class ArticleDao {
	private Connection dbConnection;

	public ArticleDao(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public List<Article> getForPrintListArticles() {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM article  ");
		sql += String.format("WHERE displayStatus = 1  ");
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT 0, 5 ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}
		return articles;
	}

}
