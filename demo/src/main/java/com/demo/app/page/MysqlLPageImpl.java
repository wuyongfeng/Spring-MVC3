package com.demo.app.page;


public class MysqlLPageImpl implements LimitPage {

	public String getPageList(String sql, int index, int size) {
		StringBuffer buf = new StringBuffer(sql);
		buf.append(" limit ");
		buf.append(index);
		buf.append(",");
		buf.append(size);
		return buf.toString();
	}

}
