package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.controller.ArticleController;
import com.sbs.java.blog.controller.Controller;
import com.sbs.java.blog.controller.HomeController;
import com.sbs.java.blog.controller.MemberController;
import com.sbs.java.blog.util.Util;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
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

		// DB 접속 시작
		String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		String user = "root";
		String password = "";

		Connection dbConnection = null;

		try {
			dbConnection = DriverManager.getConnection(url, user, password);
			String contextPath = request.getContextPath();
			String requestURI = request.getRequestURI();

			String actionStr = requestURI.replace(contextPath + "/s/", "");
			String[] actionStrBits = actionStr.split("/");

			String controllerName = actionStrBits[0];
			String actionMethodName = actionStrBits[1];

			Controller controller = null;

			switch (controllerName) {
			case "article":
				controller = new ArticleController(dbConnection);
				break;
			case "member":
				controller = new MemberController();
				break;
			case "home":
				controller = new HomeController(dbConnection);
				break;
			}
			if (controllerName != null) {
				String actionResult = controller.doAction(actionMethodName, request, response);

				if (actionResult.equals("")) {
					response.getWriter().append("ERROR, 응답없음");
				} else if (actionResult.endsWith(".jsp")) {
					String viewPath = "/jsp/" + actionResult;
					request.getRequestDispatcher(viewPath).forward(request, response);
				} else if (actionResult.startsWith("plain:")) {
					response.getWriter().append(actionResult.substring(6));

					// doWrite 수정필요.
				} else if (actionResult.equals("doWrite")) {
				}
			} else {
				response.getWriter().append("존재 하지 않는 게시물 입니다.");
			}
		} catch (SQLException e) {
			Util.printEx("SQL 예외(커넥션 열기)", response, e);
			
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
