package com.mission.shorturlservice.global.util;

import org.springframework.stereotype.Component;

@Component
public class Base62 {

	private static final char[] words = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	public String encoding(int index){
		StringBuilder shorts = new StringBuilder();

		while (index > 0){
			shorts.append(words[index%62]);
			index = index / 62;
		}

		return shorts.toString();
	}

}
