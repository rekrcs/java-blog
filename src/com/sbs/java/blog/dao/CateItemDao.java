package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;

public class CateItemDao extends Dao {
	private Connection dbConnection;
	private DBUtil dbUtil;

	public CateItemDao(Connection dbConnection, HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.dbConnection = dbConnection;
		dbUtil = new DBUtil(request, response);
	}

	public List<CateItem> getCateItems(HttpServletRequest request, HttpServletResponse response) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");

		List<Map<String, Object>> rows = dbUtil.selectRows(dbConnection, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

}
