package com.demo.app.page;

public class LimitPageHepler {
	private LimitPageHepler() {
	}

	private static  LimitPage limitPage;

	public static LimitPage getLimitPage(String db) {
		if(db.equals(DataDialect.ORACLE))
			limitPage = new OracleLpageImpl();
		if(db.equals(DataDialect.MYSQL))
			limitPage = new MysqlLPageImpl();
		if(db.equals(DataDialect.SQLSERVER))
			limitPage = new MSLpageImpl();
		return limitPage;
	}
}
