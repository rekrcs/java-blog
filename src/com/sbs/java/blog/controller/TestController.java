package com.sbs.java.blog.controller;

import java.sql.Connection;
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
			return doActiondbInsert();
		case "dbSelect":
			return doActionDbSelect();

		}
		return "";

	}

	private String doActionDbSelect() {
		// TODO Auto-generated method stub
		return "html:doActiondbInsert";
	}

	private String doActiondbInsert() {
		// TODO Auto-generated method stub
		return "html:doActionDbSelect";
	}

}
