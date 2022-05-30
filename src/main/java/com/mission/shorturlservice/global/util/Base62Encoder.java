package com.mission.shorturlservice.global.util;


public class Base62Encoder {

	private static final int BASE = 62;
	private static final char[] words =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static String encoding(int index){
		StringBuilder shorts = new StringBuilder();

		while (index > 0){
			shorts.append(words[index%BASE]);
			index /= 62;
		}

		return shorts.toString();
	}

}
