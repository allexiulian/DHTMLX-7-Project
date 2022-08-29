package com.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class JsonToStringUtil {
	
	
	public static Long formatLong(HttpServletRequest req) throws IOException {
		return Long.parseLong(format(req).replaceAll("\"",""));
		
	}
	
	
	
	public static String format(HttpServletRequest req) throws IOException {
		StringBuffer sb = new StringBuffer();
		String s;
		while ((s = req.getReader().readLine()) != null) {
			sb.append(s);
		}
		return sb.toString();
	}
}
