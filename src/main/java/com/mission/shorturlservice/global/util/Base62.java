package com.mission.shorturlservice.global.util;

import org.springframework.stereotype.Component;

@Component
public class Base62 {

	private static final int BASE = 62;
	private static final char[] words =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public String encoding(int index){
		StringBuilder shorts = new StringBuilder();

		while (index > 0){
			shorts.append(words[index%BASE]);
			index /= 62;
		}

		return shorts.toString();
	}

}
