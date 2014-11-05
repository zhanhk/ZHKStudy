package com.zhk.jdk.concurrent.map;

import java.util.Map;
import java.util.TreeMap;

public class MapSortDemo {
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("KFC", "kfc");
		map.put("WNBA", "wnba");
		map.put("NBA", "nba");
		map.put("CBA", "cba");
		Map<String, String> resultMap = sortMapByKey(map); // ��Key��������
		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	/**
	 * ʹ�� Map��key��������
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
}