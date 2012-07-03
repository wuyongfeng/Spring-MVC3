package com.demo.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class LocalDateJsonBeanProcessor implements JsonValueProcessor {
	private DateFormat dateFormat;
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	public LocalDateJsonBeanProcessor() {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public LocalDateJsonBeanProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return process(arg0);
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO Auto-generated method stub
		return process(arg1);
	}

	private Object process(Object ob) {
		return dateFormat.format((Date) ob);
	}

}
