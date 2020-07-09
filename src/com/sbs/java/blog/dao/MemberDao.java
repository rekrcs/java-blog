package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.DBUtil;

public class MemberDao extends Dao {
	private Connection dbConnection;

	public MemberDao(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public int join(String loginId, String name, String nickName, String loginPw) {
		String sql = "";

		sql += String.format("INSERT INTO `member` ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", loginId = '%s' ", loginId);
		sql += String.format(", name = '%s' ", name);
		sql += String.format(", nickName = '%s' ", nickName);
		sql += String.format(", loginPw = '%s' ", loginPw);


		return DBUtil.insert(dbConnection, sql);
	}

	public List<Member> getForPrintMembers() {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM `member` ");
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConnection, sql);
		List<Member> members = new ArrayList<>();
		
		for (Map<String, Object> row : rows) {
			members.add(new Member(row));
		}
		
		return members;
	}

}
