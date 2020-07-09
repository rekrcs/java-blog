package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;

public class TestController extends Controller {
	public TestController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConnection, actionMethodName, request, response);

	}

	public void beforeAction() {
		super.beforeAction();
		// 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행 된다.
		// 필요 없다면 지워도 된다.
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "dbInsert":
			return doActionDbInsert();
		case "dbSelect":
			return doActionDbSelect();

		}
		return "";

	}

	private String doActionDbInsert() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = -1;
		try {
			stmt = dbConnection.prepareStatement(
					"INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, body = ?, displayStatus = ?, cateItemId = ?",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "제목");
			stmt.setString(2, "내용");
			stmt.setInt(3, 1);
			stmt.setInt(4, 1);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return "html:" + id;
	}

	private String doActionDbSelect() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String title = null;
		try {
			stmt = dbConnection.prepareStatement(
					"SELECT title FROM article WHERE title LIKE CONCAT('%', ?, '%') ORDER BY id DESC LIMIT 1");
			stmt.setString(1, "제목");
			rs = stmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return "html:" + title;
	}

}
