package com.demo.app.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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

import com.demo.app.entity.User;
import com.demo.app.service.LoginService;
import com.demo.app.util.LocalDateJsonBeanProcessor;

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
	public String  json(@RequestParam int page) {
		System.out.println("正在获取列表！");
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new LocalDateJsonBeanProcessor("yyyy-MM-dd HH:mm:ss"));
			List<User> users = loginService.findAll();
			System.out.println(users.size());
			JSONArray jsonArray = JSONArray.fromObject(loginService.findAll(),jsonConfig);
			String jsonStr = jsonArray.toString();
			jsonArray = null;
			StringBuffer jsonBuf = new StringBuffer("{\"page\":"+page+",");
			jsonBuf.append("\"total\":"+users.size()+",\"rows\":");
			users = null;
			//return jsonBuf.append(jsonStr+"}").toString();
			//response.getWriter().write(jsonBuf.append(jsonStr+"}").toString());
			//response.getWriter().write(jsonStr);
			return "{\"page\":1,\"total\":239,\"rows\":[{\"id\":\"ZW\",\"cell\":{\"name\":\"Zimbabwe \",\"iso\":\"ZW\",\"printable_name\":\"Zimbabwe \",\"iso3\":\"ZWE \",\"numcode\":\"716\"}},{\"id\":\"ZM\",\"cell\":{\"name\":\"Zambia \",\"iso\":\"ZM\",\"printable_name\":\"Zambia \",\"iso3\":\"ZMB \",\"numcode\":\"894\"}},{\"id\":\"YE\",\"cell\":{\"name\":\"Yemen \",\"iso\":\"YE\",\"printable_name\":\"Yemen \",\"iso3\":\"YEM \",\"numcode\":\"887\"}},{\"id\":\"EH\",\"cell\":{\"name\":\"Western Sahara \",\"iso\":\"EH\",\"printable_name\":\"Western Sahara \",\"iso3\":\"ESH \",\"numcode\":\"732\"}},{\"id\":\"WF\",\"cell\":{\"name\":\"Wallis and Futuna \",\"iso\":\"WF\",\"printable_name\":\"Wallis and Futuna \",\"iso3\":\"WLF \",\"numcode\":\"876\"}},{\"id\":\"VI\",\"cell\":{\"name\":\"Virgin Islands, U.s. \",\"iso\":\"VI\",\"printable_name\":\"Virgin Islands, U.s. \",\"iso3\":\"VIR \",\"numcode\":\"850\"}},{\"id\":\"VG\",\"cell\":{\"name\":\"Virgin Islands, British \",\"iso\":\"VG\",\"printable_name\":\"Virgin Islands, British \",\"iso3\":\"VGB \",\"numcode\":\"92\"}},{\"id\":\"VN\",\"cell\":{\"name\":\"Viet Nam \",\"iso\":\"VN\",\"printable_name\":\"Viet Nam \",\"iso3\":\"VNM \",\"numcode\":\"704\"}},{\"id\":\"VE\",\"cell\":{\"name\":\"Venezuela \",\"iso\":\"VE\",\"printable_name\":\"Venezuela \",\"iso3\":\"VEN \",\"numcode\":\"862\"}},{\"id\":\"VU\",\"cell\":{\"name\":\"Vanuatu \",\"iso\":\"VU\",\"printable_name\":\"Vanuatu \",\"iso3\":\"VUT \",\"numcode\":\"548\"}}]}";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

}
