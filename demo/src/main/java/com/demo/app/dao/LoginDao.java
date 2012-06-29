package com.demo.app.dao;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.demo.app.entity.User;
import com.demo.app.entity.UserRowMapper;
import com.demo.app.page.LimitPage;
import com.demo.app.util.SupporterUtil;

@Repository
public class LoginDao implements JdbcDao<String, User> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * save the new user entity to database
	 * 
	 * @param user
	 */
	public void insert(User user) {
		String sql = "INSERT INTO user values(?,?,?,?)";
		Object[] params = new Object[] { SupporterUtil.getId(), user.getName(),
				SupporterUtil.getMd5Psw(user.getPassword()), new Date() };
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.TIMESTAMP };
		jdbcTemplate.update(sql, params, types);

	}

	/**
	 * update the user
	 * 
	 * @param user
	 */
	public void update(User user) {
		String sql = "update user set pass_word=? where user_name=?";
		jdbcTemplate.update(sql,
				new Object[] { SupporterUtil.getMd5Psw(user.getPassword()) },
				user.getName(), new int[] { Types.VARCHAR, Types.VARCHAR });

	}

	/**
	 * delete user
	 * 
	 * @param userName
	 */
	public void delete(String userName) {
		jdbcTemplate.update("delete from user where user_name = '" + userName
				+ "'");
	}

	/**
	 * load all user infomation
	 * 
	 * @return
	 */

	public List<User> findAll(String sql) {
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	/**
	 * 返回分页结果
	 * 
	 * @param sql
	 * @param index
	 * @param end
	 * @return
	 */
	public List<User> getLimipage(String sql, int index, int size,
			LimitPage page) {
		return jdbcTemplate.query(page.getPageList(sql, index, size),
				new UserRowMapper());
	}

	/**
	 * 根据主键返回User对象
	 * 
	 * @param pk
	 */
	public User findById(String pk) {
		String sql = "select * from user where id = '" + pk + "'";
		return jdbcTemplate.queryForObject(sql, new UserRowMapper());
	}

	/**
	 * 返回总数
	 * 
	 * @param sql
	 */
	public int getCount(String sql) {
		if (!StringUtils.hasLength(sql))
			sql = "select count(*) from user";
		return jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 返回jdbcTemplate实例
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
}
