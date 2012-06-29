package com.demo.app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int index) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("user_name"));
		user.setPassword(rs.getString("pass_word"));
		user.setDate(rs.getDate("create_date"));
		return user;
	}

}
