package com.demo.app.controller;

import java.text.DateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.app.entity.JsonObject;
import com.demo.app.entity.User;
import com.demo.app.json.WebJSONJquery;
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
		logger.debug("Welcome home! the client locale is ");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

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
		Assert.notNull(user, "非法访问");
		Assert.hasText(user.getName(), "非法访问");
		loginService.regit(user);
		model.addAttribute("name", user.getName());
		return "success";
	}

	@RequestMapping(value = "/loginto", method = RequestMethod.POST)
	public String success(@ModelAttribute("user") User user, Model model) {
		Assert.notNull(user, "非法访问");
		Assert.hasText(user.getName(), "非法访问");
		Assert.isTrue(loginService.canAccess(user), "密码错误");
		model.addAttribute("name", user.getName());
		return "success";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<User> userList = loginService.listPage(1, 2);
		model.addAttribute("userList", userList);
		return "list";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public @ResponseBody
	String validate(@RequestParam String userName) {
		System.out.println("验证开始");
		System.out.println(userName);
		if (loginService.nameIsUsed(userName))
			return "该用户名已被注册！";
		return "";
	}

	@RequestMapping(value = "/jsonList", method = RequestMethod.GET)
	@ResponseBody
	public String json(@RequestParam int page) {
		System.out.println("正在获取列表！");
		page = 1;
		try {
			List<User> users = loginService.findAll();
			Assert.isTrue(users.size()>0,"获取列表为空！");
			final List  jsonList = new ArrayList();
			for(User user:users){
				Assert.notNull(user,"存在空的实体对象！");
				jsonList.add(new JsonObject<String, User>(user.getId(), user));
			}
			users = null;
			String json = new WebJSONJquery(jsonList, true,"yyyy-MM-dd HH:mm:ss").getJsonString();
			StringBuffer jsonBuf = new StringBuffer("{\"page\":" + page + ",");
			jsonBuf.append("\"total\":" + jsonList.size() + ",\"rows\":");
			String str = jsonBuf.append(json+'}').toString();
			System.out.println(str);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
	


}
