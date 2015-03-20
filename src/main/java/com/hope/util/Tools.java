package com.hope.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class Tools {
	public static String SpaceDisappear(String value) {
		if (null == value || value.equals("")) {
			return value;
		} else {
			return value.trim();
		}
	}

	public static String listConvertSqlIn(String field, String relation,
			List<String> date) {
		String temp = " " + field + " " + relation + " ";
		String dateTemp = "";
		for (int i = 0; i < date.size(); i++) {
			if (i == 0) {
				dateTemp = dateTemp + "('" + date.get(i) + "','";
			} else if (i == date.size() - 1) {
				dateTemp = dateTemp + date.get(i) + "') ";
			} else {
				dateTemp = dateTemp + date.get(i) + "','";
			}
		}
		temp = temp + dateTemp;
		return temp;
	}

	/**
	 * 读取JSON
	 * 
	 * @param value
	 * @return
	 */
	public static Map<String, Object> readJson(String value) {
		Map<String, Object> opeAuthMap = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			opeAuthMap = mapper.readValue(value, Map.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return opeAuthMap;
	}

	/**
	 * 写入JSON
	 * 
	 * @param map
	 * @return
	 */
	public static String writeJson(Map<String, Object> map) {
		String result = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> opeAuthMap = new HashMap<String, String>();

			for (String key : map.keySet()) {
				opeAuthMap.put(key, (String) map.get(key));
			}
			result = mapper.writeValueAsString(opeAuthMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
