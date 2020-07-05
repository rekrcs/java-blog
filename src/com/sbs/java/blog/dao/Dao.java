package com.sbs.java.blog.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Dao {
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public Dao(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}
