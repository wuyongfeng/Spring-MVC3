package com.demo.app.util;

import java.util.UUID;

import org.springframework.util.DigestUtils;

/**
 * 工具包类
 * 
 * @author Administrator
 * 
 */
public final class SupporterUtil {
	private SupporterUtil() {

	}

	/**
	 * 返回32为UUID，用于主键策略
	 * 
	 * @return
	 */
	public static String getId() {
		StringBuffer id = new StringBuffer("");
		UUID uuid = UUID.randomUUID();
		String uuid_str = uuid.toString();
		String[] uuid_array = uuid_str.split("-");
		for (String string : uuid_array) {
			id.append(string);
		}
		return id.toString();
	}

	/**
	 * 密码密文加密
	 * 
	 * @param psw
	 * @return
	 */
	public static String getMd5Psw(String psw) {
		if (psw == null || psw.length() == 0)
			return "";
		return DigestUtils.md5DigestAsHex(psw.getBytes());
	}
}
