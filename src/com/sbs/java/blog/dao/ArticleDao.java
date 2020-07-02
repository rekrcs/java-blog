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
		int totalCount = DBUtil.getOneId(dbConnection, sql2);
		totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("cateItemId", cateItemId);
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}
		
		String sql5 = "";
		sql5 += String.format("SELECT A.*, C.name AS cateItemName ");
		sql5 += String.format("FROM article AS A ");
		sql5 += String.format("INNER JOIN cateItem AS C ");
		sql5 += String.format("ON A.cateItemId = C.id ");
		
		List<Map<String, Object>> rows2 = DBUtil.selectRows(dbConnection, sql5);
		List<Article> articles2 = new ArrayList<>();
		
		for (Map<String, Object> row1 : rows2) {
			articles2.add(new Article(row1));
		}
		
		request.setAttribute("articles2", articles2);
		return articles;

	}

	public Article getForDetailFromArticle(int id, HttpServletRequest request, HttpServletResponse response) {
		String strForFirstId = "";
		strForFirstId += String.format("SELECT * ");
		strForFirstId += String.format("FROM article ");
		strForFirstId += String.format("ORDER BY id ASC ");
		strForFirstId += String.format("LIMIT 1");

		int firstId = DBUtil.getOneId(dbConnection, strForFirstId);

		String strForLastId = "";
		strForLastId += String.format("SELECT * ");
		strForLastId += String.format("FROM article ");
		strForLastId += String.format("ORDER BY id DESC ");
		strForLastId += String.format("LIMIT 1");

		int lastId = DBUtil.getOneId(dbConnection, strForLastId);
		
		request.setAttribute("firstId", firstId);
		request.setAttribute("lastId", lastId);

		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id = %d", id);

		Map<String, Object> row = DBUtil.selectRow(dbConnection, sql);

		Article article = new Article(row);
		Article articleNext = null;
		Article articlePrevious = null;

		String sql2 = "";

		sql2 += String.format("SELECT * ");
		sql2 += String.format("FROM article ");
		sql2 += String.format("WHERE id < %d", id);
		sql2 += String.format(" ORDER BY id DESC");
		sql2 += String.format(" LIMIT 1");

		Map<String, Object> row2 = DBUtil.selectRow(dbConnection, sql2);
		if (row2.isEmpty()) {
			articlePrevious = new Article(row);
		} else {
			articlePrevious = new Article(row2);
		}
		String sql3 = "";
		sql3 += String.format("SELECT * ");
		sql3 += String.format("FROM article ");
		sql3 += String.format("WHERE id > %d", id);
		sql3 += String.format(" ORDER BY id ASC");
		sql3 += String.format(" LIMIT 1");

		Map<String, Object> row3 = DBUtil.selectRow(dbConnection, sql3);
		if (row3.isEmpty()) {
			articleNext = new Article(row);
		} else {
			articleNext = new Article(row3);
		}

		request.setAttribute("articleNext", articleNext);
		request.setAttribute("articlePrevious", articlePrevious);
		
		
		
		String sql5 = "";
		sql5 += String.format("SELECT A.*, C.name AS cateItemName ");
		sql5 += String.format("FROM article AS A ");
		sql5 += String.format("INNER JOIN cateItem AS C ");
		sql5 += String.format("ON A.cateItemId = C.id ");
		
		List<Map<String, Object>> rows2 = DBUtil.selectRows(dbConnection, sql5);
		List<Article> articles2 = new ArrayList<>();
		
		for (Map<String, Object> row1 : rows2) {
			articles2.add(new Article(row1));
		}
		
		request.setAttribute("articles2", articles2);
		return article;
	}

	public void doWriteArticle(String title, String body, int cateItemId, HttpServletRequest request,
			HttpServletResponse response) {
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
