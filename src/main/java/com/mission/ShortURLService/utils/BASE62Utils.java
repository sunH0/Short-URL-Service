package com.mission.ShortURLService.utils;

import org.springframework.stereotype.Component;

@Component
public class BASE62Utils {

	private static final String HEAD = "http://localhost:8080/";
	private static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	public static String encode(Integer value) {
		StringBuilder sb = new StringBuilder(HEAD);
		do {
			Integer i = value % 62;
			sb.append(BASE62[i]);
			value /= 62;
		} while (value > 0);
		return sb.toString();
	}
	public static Integer decode(String value) {
		Integer result = 0;
		Integer power = 1;
		for (int i = 0; i < value.length(); i++) {
			int digit = new String(BASE62).indexOf(value.charAt(i));
			result += digit * power;
			power *= 62;
		}
		return result;
	}


}
