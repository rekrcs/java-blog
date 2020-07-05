package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.service.ArticleService;

public class MemberController extends Controller {

	public MemberController(Connection dbConnection, String actionMethodName, HttpServletRequest request,
			HttpServletResponse response) {
		super(dbConnection, actionMethodName, request, response);
	}

	@Override
	public String doAction() {
		return "";

	}

}
