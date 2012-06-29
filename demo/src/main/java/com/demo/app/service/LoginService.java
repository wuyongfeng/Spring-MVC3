package com.demo.app.service;

import java.util.Date;
import java.util.List;

import com.demo.app.entity.User;

/**
 * 系统登录服务
 * 
 * @author Administrator
 * 
 */

public interface LoginService {
	// 是否可以登录
	boolean canAccess(User user);

	// 返回对应的权限
	// Permission getPermission(User user);
	// 账号是否已被注册
	boolean nameIsUsed(String name);

	// 登录，登出时间
	Date getLogonDate();

	// 获取IP地址
	String getIp();
	
	void regit(User user);
	
	List<User> findAll();
	
	List<User> listPage(int index,int size);

}
