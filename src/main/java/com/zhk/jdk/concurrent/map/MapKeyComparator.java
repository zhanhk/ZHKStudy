package com.zhk.jdk.concurrent.map;

import java.util.Comparator;

//�Ƚ�����
public class MapKeyComparator implements Comparator<String> {
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}
}
