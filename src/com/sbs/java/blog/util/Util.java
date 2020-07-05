package com.sbs.java.blog.util;

import javax.servlet.http.HttpServletRequest;

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
}
