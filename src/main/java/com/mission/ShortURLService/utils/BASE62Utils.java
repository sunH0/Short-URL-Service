package com.mission.ShortURLService.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BASE62Utils {

	@Value("${shorten-url-suffix}")
	private static String HEAD;
	@Value("${shorten-algorithm-BASE62}")
	private static String BASE62;

	public static String encode(String value) {
		StringBuilder sb = new StringBuilder(HEAD);
		Integer intVal = Integer.valueOf(value);
		do {
			Integer i = intVal % 62;
			sb.append(BASE62.charAt(i));
			intVal /= 62;
		} while (intVal > 0);
		return sb.toString();
	}
	public static Integer decode(String value) {
		Integer result = 0;
		Integer power = 1;
		for (int i = 0; i < value.length(); i++) {
			int digit = BASE62.indexOf(value.charAt(i));
			result += digit * power;
			power *= 62;
		}
		return result;
	}

}
