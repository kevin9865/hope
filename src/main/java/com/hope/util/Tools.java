package com.hope.util;

public class Tools {
	public static String SpaceDisappear(String value){
		if(null==value||value.equals("")){
			return value;
		}else {
			return value.trim();
		}
	}
}
