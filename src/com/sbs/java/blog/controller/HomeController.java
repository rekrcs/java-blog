package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.service.CateItemService;

public class HomeController extends Controller {
	private CateItemService cateItemService;

	public HomeController(Connection dbConnection) {
		this.cateItemService = new CateItemService(dbConnection);
	}

	@Override
	public String doAction(String actionMethodName, HttpServletRequest request, HttpServletResponse response) {
		switch (actionMethodName) {
		case "aboutMe":
			return doActionAboutMe(request, response);
		case "articles":
			return doActionArticles(request, response);
		case "main":
			return doActionMain(request, response);
		case "sns":
			return doActionSns(request, response);

		}
		return "";

	}

	private String doActionSns(HttpServletRequest request, HttpServletResponse response) {
		return "home/sns";
	}

	private String doActionMain(HttpServletRequest request, HttpServletResponse response) {

		return "home/main";
	}

	private String doActionArticles(HttpServletRequest request, HttpServletResponse response) {
		List<CateItem> cateItems = cateItemService.getCateItems(request, response);
		request.setAttribute("cateItems", cateItems);
		return "home/articles";
	}

	private String doActionAboutMe(HttpServletRequest request, HttpServletResponse response) {

		return "home/aboutMe";
	}

}
