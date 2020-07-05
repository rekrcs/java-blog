package com.sbs.java.blog.util;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	public static boolean empty(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);

		return empty(paramValue);

	}

	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}
		return false;

	}

	public static boolean isNum(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);

		return isNum(paramValue);
	}

	private static boolean isNum(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof Long) {
			return false;
		}
		if (obj instanceof Integer) {
			return false;
		}
		if (obj instanceof String) {
			try {
				int num = Integer.parseInt((String) obj);
				return false;
			} catch (NumberFormatException e) {
				return true;
			}
		}
		return true;
	}

	public static int getInt(HttpServletRequest request, String paramName) {
		return Integer.parseInt(request.getParameter(paramName));
	}

	public static void printEx(String errName, HttpServletResponse response, Exception e) {
		try {
			response.getWriter()
					.append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");
			response.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
			e.printStackTrace(response.getWriter());
			response.getWriter().append("</pre>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
