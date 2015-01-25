package com.hope.util;

import java.util.List;

public class Tools {
	public static String SpaceDisappear(String value){
		if(null==value||value.equals("")){
			return value;
		}else {
			return value.trim();
		}
	}
	public static String listConvertSqlIn(String field ,String relation,List<String> date){
		String temp=" "+field+" "+relation+" ";
		String dateTemp="";
		for(int i=0;i<date.size();i++){
			if(i==0){
				dateTemp=dateTemp+"('"+date.get(i)+"','";
			}else if(i==date.size()-1){
				dateTemp=dateTemp+date.get(i)+"') ";
			}else {
				dateTemp=dateTemp+date.get(i)+"','";
			}
		}
		temp=temp+dateTemp;
		return temp;
	}
}
