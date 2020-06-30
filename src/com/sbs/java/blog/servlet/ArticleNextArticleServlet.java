package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;

@WebServlet("/s/article/nextArticle")
public class ArticleNextArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "root";
		String password = "";
		String driverName = "com.mysql.cj.jdbc.Driver";

		String sql2 = "";
		int id = Integer.parseInt(request.getParameter("id"));
		
		Article articleNext = new Article();
		
		System.out.println("id : " + id);
		sql2 += String.format("SELECT * ");
		sql2 += String.format("FROM article ");
		sql2 += String.format("WHERE id > %d", id);
		sql2 += String.format(" ORDER BY id ASC");
		sql2 += String.format(" LIMIT 1");
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql2);

			if (rs.next()) {
				articleNext.setId(rs.getInt("id"));
				articleNext.setRegDate(rs.getString("regDate"));
				articleNext.setUpdateDate(rs.getString("updateDate"));
				articleNext.setTitle(rs.getString("title"));
				articleNext.setBody(rs.getString("body"));
			}
			
			request.setAttribute("articleNext", articleNext);
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);

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
