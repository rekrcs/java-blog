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

@WebServlet("/s/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String url = "jdbc:mysql://site33.iu.gy:3306/site33?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site33";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";

		String sql = "";

		List<Article> articles = new ArrayList<>();

		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format(" WHERE displayStatus = 1");
		if (request.getParameter("cateItemId") != null) {
			sql += String.format(" AND cateItemId = %d", Integer.parseInt(request.getParameter("cateItemId")));
		}
		sql += String.format(" ORDER BY id DESC ");
		sql += String.format(" LIMIT %d, %d", 5 * (Integer.parseInt(request.getParameter("page")) - 1), 5);
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setRegDate(rs.getString("regDate"));
				article.setUpdateDate(rs.getString("updateDate"));
				article.setTitle(rs.getString("title"));
				article.setBody(rs.getString("body"));

				articles.add(article);
			}
			request.setAttribute("articles", articles);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

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

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, stmt 닫기] : %s\n", e.getMessage());
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, rs 닫기] : %s\n", e.getMessage());
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
