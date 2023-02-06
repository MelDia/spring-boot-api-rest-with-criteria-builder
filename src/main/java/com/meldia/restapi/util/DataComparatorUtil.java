package com.meldia.restapi.util;

import org.apache.commons.lang3.StringUtils;

public class DataComparatorUtil {
	
	public static String comparator(String newValue, String originalValue) {
		return StringUtils.isNotBlank(newValue) ? newValue : originalValue;
	}
}
