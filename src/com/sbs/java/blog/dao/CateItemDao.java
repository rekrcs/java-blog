package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;

public class CateItemDao {
	private Connection dbConnection;

	public CateItemDao(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public List<CateItem> getCateItems(HttpServletRequest request, HttpServletResponse response) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM cateItem ");

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<CateItem> cateItems = new ArrayList<>();
		
		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}
		
		return cateItems;
	}

}
