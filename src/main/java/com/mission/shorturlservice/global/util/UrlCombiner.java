package com.mission.shorturlservice.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlCombiner {

	@Value("${server.host}")
	private String host;

	@Value("${server.port}")
	private String port;

	public String combineShortUrl(String path){
		return "http://" +host +":" +port +"/" +"shortner/" +path;
	}

}
