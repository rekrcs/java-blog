package com.sbs.java.blog.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Service {
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public Service(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}
