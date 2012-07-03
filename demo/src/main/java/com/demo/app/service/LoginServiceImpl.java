package com.demo.app.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.demo.app.dao.LoginDao;
import com.demo.app.entity.User;
import com.demo.app.entity.UserRowMapper;
import com.demo.app.page.DataDialect;
import com.demo.app.page.LimitPageHepler;
import com.demo.app.util.SupporterUtil;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	public boolean canAccess(User user) {
		String sql = "select * from user where user_name = '"+StringUtils.trim(user.getName())+"'";
		User user1  = loginDao.getJdbcTemplate().queryForObject(sql, new UserRowMapper());
		if (SupporterUtil.getMd5Psw(StringUtils.trim(user.getPassword())).equals(user1.getPassword()))
				return true;
		return false;
	}

	public boolean nameIsUsed(String name) {
		String sql = "select * from user where user_name = '"+StringUtils.trim(name)+"'";
		List<User> user  =  loginDao.getJdbcTemplate().query(sql, new UserRowMapper());
		if (user.size()==0)
				return false;
		return true;
	}

	public Date getLogonDate() {
		return new Date();
	}

	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(value = "txManager")
	public void regit(User user) {
		loginDao.insert(user);
	}

	@Transactional(value = "txManager")
	public List<User> findAll() {
		String sql = "select * from user";
		return loginDao.findAll(sql);
	}

	public List<User> listPage(int index, int size) {
		Assert.isTrue(index>=0, "起始行不能小于0");
		Assert.isTrue(size>0, "每页容量必须大于0");
		return loginDao.getLimipage("select * from user", index-1, size,LimitPageHepler.getLimitPage(DataDialect.MYSQL));
	}

}
