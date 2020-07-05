package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dao.CateItemDao;
import com.sbs.java.blog.dto.CateItem;

public class CateItemService extends Service {
	private CateItemDao cateItemDao;

	public CateItemService(Connection dbConnection, HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.cateItemDao = new CateItemDao(dbConnection, request, response);
	}

	public List<CateItem> getCateItems(HttpServletRequest request, HttpServletResponse response) {
		return cateItemDao.getCateItems(request, response);
	}

}
