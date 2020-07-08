package com.sbs.java.blog.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.exception.SQLErrorException;

public class DBUtil {
	public static List<Map<String, Object>> selectRows(Connection connection, String sql) {
		List<Map<String, Object>> rows = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnSize = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();

				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					String columnName = metaData.getColumnName(columnIndex + 1);
					Object value = rs.getObject(columnName);

					if (value instanceof Long) {
						int numValue = (int) (long) value;
						row.put(columnName, numValue);
					} else if (value instanceof Timestamp) {
						String dateValue = value.toString();
						dateValue = dateValue.substring(0, dateValue.length() - 2);
						row.put(columnName, dateValue);
					} else {
						row.put(columnName, value);
					}
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			throw new SQLErrorException("SQL 예외, SQL : " + sql);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, rs 닫기 " + sql);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, stmt 닫기 " + sql);
				}
			}

		}

		return rows;
	}

	public static Map<String, Object> selectRow(Connection connection, String sql) {
		List<Map<String, Object>> rows = selectRows(connection, sql);

		if (rows.size() == 0) {
			return new HashMap<String, Object>();
		}

		return rows.get(0);
	}

	public static void insert(Connection connection, String sql, HttpServletResponse response) throws SQLErrorException {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			int affectedRows = stmt.executeUpdate(sql);
			try {
				response.getWriter().append(affectedRows + "개의 데이터가 추가되었습니다.");
			} catch (IOException e) {
				System.err.printf("[게시물추가 에러] : %s\n", e.getMessage());
			}

		} catch (SQLException e) {
			Util.printEx("SQL 예외, SQL", response, e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					Util.printEx("SQL 예외(커넥션 닫기)", response, e);
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					Util.printEx("SQL 예외, stmt 닫기", response, e);
				}
			}
		}
	}

	public static Article getArticlePrevious(Connection connection, String sql) {

		return null;
	}

	public static int selectRowIntValue(Connection connection, String sql) {
		Map<String, Object> row = selectRow(connection, sql);

		for (String key : row.keySet()) {
			return (int) row.get(key);
		}
		return -1;
	}

	public static String selectRowStringValue(Connection connection, String sql) {
		Map<String, Object> row = selectRow(connection, sql);

		for (String key : row.keySet()) {
			return (String) row.get(key);
		}
		return "";
	}

	public static boolean selectRowBooleanValue(Connection connection, String sql) {
		Map<String, Object> row = selectRow(connection, sql);

		for (String key : row.keySet()) {
			return ((int) row.get(key)) == 1;
		}
		return false;
	}

	public static int getOneId(Connection connection, String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		int oneId = -1;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				oneId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLErrorException("SQL 예외, SQL : " + sql);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, rs 닫기 " + sql);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new SQLErrorException("SQL 예외, stmt 닫기 " + sql);
				}
			}

		}

		return oneId;
	}
}