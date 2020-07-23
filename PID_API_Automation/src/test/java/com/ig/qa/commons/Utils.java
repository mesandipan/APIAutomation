package com.ig.qa.commons;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static boolean flag;

	// Converting String to Map
	@SuppressWarnings("rawtypes")
	public static Map stringToMap(String params) {
		Map<String, String> map = new HashMap<String, String>();

		if (params == null) {
			System.out.println("");

		} else {
			String[] arr = params.split(",");
			for (int i = 0; i < arr.length; i++) {
				String key = arr[i].substring(0, arr[i].indexOf(":"));
				String value = arr[i].substring(arr[i].indexOf(":") + 1);
				map.put(key, value);
			}
		}
		return map;
	}

}
