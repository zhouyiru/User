package com.util;

import java.util.UUID;

public  class WebUtil {
	
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
