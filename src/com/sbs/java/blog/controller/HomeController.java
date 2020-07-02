package com.sbs.java.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends Controller {

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
		// TODO Auto-generated method stub
		return "home/articles";
	}

	private String doActionAboutMe(HttpServletRequest request, HttpServletResponse response) {
		
		return "home/aboutMe";
	}

}
