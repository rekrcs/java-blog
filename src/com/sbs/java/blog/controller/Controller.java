package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller {
	protected Connection dbConnection;
	protected String actionMethodName;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public Controller(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		this.dbConnection = dbConnection;
		this.actionMethodName = actionMethodName;
		this.request = request;
		this.response = response;
	}

	public void beforeAction() {
		// 액션 전 실행
		// 이 메서드는 모든 컽트롤러의 모든 액션이 실행되기 전에 실행된다.
	}

	public void afterAction() {
		// 액션 후 실행
	}

	public abstract String doAction();

	public String excutedoAction() {
		beforeAction();
		String rs = doAction();
		afterAction();
		return rs;
	}
}
