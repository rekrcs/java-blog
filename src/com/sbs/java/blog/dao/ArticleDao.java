package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class ArticleDao extends Dao {
	private Connection dbConnection;

	public ArticleDao(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public List<Article> getForPrintListArticles(int page, int itemsInAPage, int cateItemId, String searchKeywordType,
			String searchKeyword) {
		String sql = "";

		int limitFrom = (page - 1) * itemsInAPage;

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql += String.format("AND title LIKE CONCAT('%%', '%s', '%%')", searchKeyword);
		}
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT %d, %d ", limitFrom, itemsInAPage);

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeyword) {
		String sql = "";

		sql += String.format("SELECT COUNT(*) AS cnt ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");

		if (cateItemId != 0) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeyword.length() > 0) {
			sql += String.format("AND title LIKE CONCAT('%%', '%s', '%%')", searchKeyword);
		}

		int count = DBUtil.selectRowIntValue(dbConnection, sql);
		return count;
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

	public int getFirstIdFromArticle() {
		String sql = "";
		sql += String.format("SELECT id ");
		sql += String.format("FROM article ");
		sql += String.format("ORDER BY id ASC ");
		sql += String.format("LIMIT 1");

		int firstId = DBUtil.selectRowIntValue(dbConnection, sql);

		return firstId;
	}

	public int getListIdFromArticle() {
		String sql = "";
		sql += String.format("SELECT id ");
		sql += String.format("FROM article ");
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT 1");

		int lastId = DBUtil.selectRowIntValue(dbConnection, sql);

		return lastId;
	}

	public Article getNextArticle(int id, Article article) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id > %d", id);
		sql += String.format(" ORDER BY id ASC");
		sql += String.format(" LIMIT 1");

		Article articleNext = null;
		Map<String, Object> row = DBUtil.selectRow(dbConnection, sql);
		if (row.isEmpty()) {
			articleNext = article;
		} else {
			articleNext = new Article(row);
		}
		return articleNext;
	}

	public Article getPreviousArticle(int id, Article article) {
		Article articlePrevious = null;

		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id < %d", id);
		sql += String.format(" ORDER BY id DESC");
		sql += String.format(" LIMIT 1");

		Map<String, Object> row = DBUtil.selectRow(dbConnection, sql);
		if (row.isEmpty()) {
			articlePrevious = article;
		} else {
			articlePrevious = new Article(row);
		}

		return articlePrevious;
	}

	public List<Article> getCateNameFromCateId() {
		String sql = "";

		sql += String.format("SELECT A.*, C.name AS extra__cateItemName ");
		sql += String.format("FROM article AS A ");
		sql += String.format("INNER JOIN cateItem AS C ");
		sql += String.format("ON A.cateItemId = C.id ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND disPlayStatus = 1 ");

//		String sql = "";
//		sql += String.format("SELECT A.*, C.name AS cateItemName ");
//		sql += String.format("FROM article AS A ");
//		sql += String.format("INNER JOIN cateItem AS C ");
//		sql += String.format("ON A.cateItemId = C.id ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Article> cateNameForArticles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateNameForArticles.add(new Article(row));
		}

		return cateNameForArticles;
	}

	public Article getForPrintArticle(int id) {
		String sql = "";

		sql += String.format("SELECT *, 'cateItemName' AS extra__cateItemName ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND id = %d ", id);
		sql += String.format("AND disPlayStatus = 1 ");

		return new Article(DBUtil.selectRow(dbConnection, sql));
	}

	public List<CateItem> getForPrintCateItems() {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");
		sql += String.format("WHERE 1 ");
		sql += String.format("ORDER BY id ASC ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public List<CateItem> getCateItems(HttpServletRequest request, HttpServletResponse response) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND id = %d ", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConnection, sql));
	}

	public int write(int cateItemId, String title, String body) {
		SecSql secSql = new SecSql();

		secSql.append("INSERT INTO article");
		secSql.append("SET regDate = NOW()");
		secSql.append(", updateDate = NOW()");
		secSql.append(", title = ? ", title);
		secSql.append(", body = ? ", body);
		secSql.append(", displayStatus = '1'");
		secSql.append(", cateItemId = ?", cateItemId);
		
		/*
		 * String sql = "";
		 * 
		 * sql += String.format("INSERT INTO article "); sql +=
		 * String.format("SET regDate = NOW() "); sql +=
		 * String.format(", updateDate = NOW() "); sql +=
		 * String.format(", title = '%s' ", title); sql +=
		 * String.format(", body = '%s' ", body); sql +=
		 * String.format(", displayStatus = '1' "); sql +=
		 * String.format(", cateItemId = '%d' ", cateItemId);
		 */
		return DBUtil.insert(dbConnection, secSql);
	}
}
