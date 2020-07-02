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
import com.sbs.java.blog.controller.MemberController;

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
		String url = "jdbc:mysql://site33.iu.gy:3306/site33?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site33";
		String password = "sbs123414";

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
			}
			if (controllerName != null) {
				String viewPath = controller.doAction(actionMethodName, request, response);
				
				if(viewPath.equals("")) {
					response.getWriter().append("ERROR, 응답없음");
				}
				viewPath = "/jsp/" + viewPath + ".jsp";
				request.getRequestDispatcher(viewPath).forward(request, response);
			} else {
				response.getWriter().append("존재 하지 않는 게시물 입니다.");
			}
		} catch (SQLException e) {
			System.err.printf("[SQLException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB연결 실패");
			return;
		} catch (Exception e) {
			System.err.printf("[기타Exception 예외, %s]\n", e.getMessage());
			response.getWriter().append("기타 실패");
			return;
		} finally {
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					System.err.printf("[SQLException 예외, %s]\n", e.getMessage());
					response.getWriter().append("DB연결닫기 실패");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
