package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

@WebServlet("/s/article/list")
public class ArticleListServlet extends HttpServlet {
	private List<Article> getArticles(HttpServletRequest request) {
		String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "root";
		String password = "";
		String driverName = "com.mysql.cj.jdbc.Driver";

		String sql = "";

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format(" WHERE displayStatus = 1");
		if (request.getParameter("cateItemId") != null) {
			sql += String.format(" AND cateItemId = %d", Integer.parseInt(request.getParameter("cateItemId")));
		}
		sql += String.format(" ORDER BY id DESC ");
		sql += String.format(" LIMIT %d, %d", 5 * (Integer.parseInt(request.getParameter("page")) - 1), 5);

		List<Article> articles = new ArrayList<>();

		Connection connection = null;

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);

			List<Map<String, Object>> rows = DBUtil.selectRows(connection, sql);

			for (Map<String, Object> row : rows) {
				articles.add(new Article(row));
			}

		} catch (ClassNotFoundException e) {
			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n", e.getMessage());
		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, connection 닫기] : %s\n", e.getMessage());

				}
			}
		}
		return articles;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		List<Article> articles = getArticles(request);

		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}
}
