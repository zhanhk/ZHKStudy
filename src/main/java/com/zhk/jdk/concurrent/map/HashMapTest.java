package com.zhk.jdk.concurrent.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	
	public static void main(String[] args) {
		
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("����", "woca");
		
		map.put("����", "woca11s");
		
		System.out.println(map.toString());
	}
}
