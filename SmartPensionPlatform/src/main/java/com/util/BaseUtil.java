package com.util;

import java.util.UUID;

import com.util.CreateId;

public class BaseUtil {
	/**
	 * 获取id
	 * @return
	 */
	public static String getUUID() {
		try {
			return CreateId.getid();
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
	}
	
	public static boolean isSuccess(int result) {
		boolean isSuccess = false;
		if(result>0) {
			isSuccess = true;
		}
		return isSuccess;
	}
}
