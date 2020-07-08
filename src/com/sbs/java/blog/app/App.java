package com.sbs.java.blog.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.controller.ArticleController;
import com.sbs.java.blog.controller.Controller;
import com.sbs.java.blog.controller.HomeController;
import com.sbs.java.blog.controller.MemberController;
import com.sbs.java.blog.exception.SQLErrorException;
import com.sbs.java.blog.util.Util;

public class App {
	HttpServletRequest request;
	HttpServletResponse response;

	public App(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	private void loadDbDriver() throws IOException {
		// DB 커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}
		// DB 커넥터 로딩 성공
	}

	private String getDbUrl() {
		return "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
	}

	public void start() throws IOException {
		// DB 드라이버 로딩
		loadDbDriver();

		// DB 접속 정보 세팅
		String url = getDbUrl();
		String user = getDbId();
		String password = getDbPassWord();

		Connection dbConnection = null;

		try {
			// DB 접속 성공
			dbConnection = DriverManager.getConnection(url, user, password);

			// 올바른 컨트롤러로 라우팅
			route(request, response, dbConnection);
		} catch (SQLException e) {
			Util.printEx("SQL 예외(커넥션 열기)", response, e);

		} catch (SQLErrorException e) {
			Util.printEx("기타 예외", response, e);
		} catch (Exception e) {

			Util.printEx("기타 예외", response, e);

		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					Util.printEx("SQL 예외(커넥션 닫기)", response, e);
				}
			}
		}

	}

	private void route(HttpServletRequest request, HttpServletResponse response, Connection dbConnection)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");

		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();

		String actionStr = requestURI.replace(contextPath + "/s/", "");
		String[] actionStrBits = actionStr.split("/");

		String controllerName = actionStrBits[0];
		String actionMethodName = actionStrBits[1];

		Controller controller = null;

		switch (controllerName) {
		case "article":
			controller = new ArticleController(dbConnection, actionMethodName, request, response);
			break;
		case "member":
			controller = new MemberController(dbConnection, actionMethodName, request, response);
			break;
		case "home":
			controller = new HomeController(dbConnection, actionMethodName, request, response);
			break;
		}
		if (controllerName != null) {
			String actionResult = controller.excutedoAction();

			if (actionResult.equals("")) {
				response.getWriter().append("액션의 결과가 없습니다.");
			} else if (actionResult.endsWith(".jsp")) {
				String viewPath = "/jsp/" + actionResult;
				request.getRequestDispatcher(viewPath).forward(request, response);
			} else if (actionResult.startsWith("html:")) {
				response.getWriter().append(actionResult.substring(5));

				// doWrite 수정필요.
			} else if (actionResult.equals("doWrite")) {
			} else {
				response.getWriter().append("처리 할 수 없는 액션 결과 입니다.");
			}
		} else {
			response.getWriter().append("존재 하지 않는 게시물 입니다.");
		}

	}

	private String getDbId() {
		return "root";
	}

	private String getDbPassWord() {

		return "";
	}

}
