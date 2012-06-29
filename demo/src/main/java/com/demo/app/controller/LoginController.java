package com.demo.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.app.entity.User;
import com.demo.app.service.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime",formattedDate);

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String test(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value = "/regit", method = RequestMethod.POST)
	public String regit(@ModelAttribute("user") User user, Model model) {
		Assert.notNull(user,"非法访问");
		Assert.hasText(user.getName(),"非法访问");
		loginService.regit(user);
		model.addAttribute("name", user.getName());
		return "success";
	}
	@RequestMapping(value = "/loginto", method = RequestMethod.POST)
	public String success(@ModelAttribute("user") User user, Model model) {
		Assert.notNull(user,"非法访问");
		Assert.hasText(user.getName(),"非法访问");
		Assert.isTrue(loginService.canAccess(user),"密码错误");
		model.addAttribute("name", user.getName());
		return "success";
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( Model model) {
		List<User> userList = loginService.listPage(0, 2);
		model.addAttribute("userList", userList);
		return "list";
	}

}
