package com.demo.app.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.app.page.LimitPage;

public interface JdbcDao<PK, T> {
	/**
	 * 保存
	 * 
	 * @param entiry
	 */
	void insert(T entiry);

	/**
	 * 更新
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 删除
	 * @param pk
	 */
	void delete(PK pk);

	/**
	 * 查找全部
	 * @param sql
	 * @return
	 */
	List<T> findAll(String sql);

	/**
	 * 根据id查找
	 * @param pk
	 * @return
	 */
	T findById(PK pk);

	/**
	 * 分页
	 * @param sql
	 * @param index
	 * @param size
	 * @param page 分页器
	 * @return
	 */
	List<T> getLimipage(String sql, int index, int size, LimitPage page);

	/**
	 * 返回总数
	 * @param sql
	 * @return
	 */
	int getCount(String sql);

	/**
	 * 向业务层返回jdbcTemplate实例
	 * @return
	 */
	JdbcTemplate getJdbcTemplate();
}
