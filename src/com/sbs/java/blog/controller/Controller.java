package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.MemberService;

public abstract class Controller {
	protected Connection dbConnection;
	protected String actionMethodName;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	protected ArticleService articleService;
	protected MemberService memberService;

	public Controller(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		this.dbConnection = dbConnection;
		this.actionMethodName = actionMethodName;
		this.request = request;
		this.response = response;
		articleService = new ArticleService(dbConnection);
		memberService = new MemberService(dbConnection);
	}

	public void beforeAction() {
		// 액션 전 실행
		// 이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다.
		List<CateItem> cateItems = articleService.getForPrintCateItems();
		
		request.setAttribute("cateItems", cateItems);
	}

	public void afterAction() {
		// 액션 후 실행
	}

	public abstract String doAction();


	public String executeAction() {
		beforeAction();
		String rs = doAction();
		afterAction();

		return rs;
	}

}
