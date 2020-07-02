package com.sbs.java.blog.util;

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

import com.sbs.java.blog.dto.Article;

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
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}
		}

		return rows;
	}

	public static int getTotalCount(Connection connection, String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		int totalCount = -1;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("msg : " + e.getMessage());
				}
			}
		}

		return totalCount;
	}

	public static Map<String, Object> selectRow(Connection connection, String sql) {
		List<Map<String, Object>> rows = selectRows(connection, sql);

		if (rows.size() == 0) {
			return new HashMap<String, Object>();
		}

		return rows.get(0);
	}

	public static void insert(Connection connection, String sql) {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, connection 닫기] : %s\n", e.getMessage());
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, stmt 닫기] : %s\n", e.getMessage());
				}
			}
		}
	}
}