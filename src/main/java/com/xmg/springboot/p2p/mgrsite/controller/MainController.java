package com.xmg.springboot.p2p.mgrsite.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.domain.Logininfo;
import com.xmg.springboot.p2p.base.service.ILogininfoService;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;
import com.xmg.springboot.p2p.util.AjaxResult;

@Controller
public class MainController {
	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 后台登录控制
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("userLogin")
	@ResponseBody
	public AjaxResult userLogin(String username, String password,
			HttpServletRequest request) {
		AjaxResult result = new AjaxResult();

		Logininfo current = logininfoService.login(username, password,
				request.getRemoteAddr(), Logininfo.TYPE_MANAGER);

		if (current == null) {
			result.setMsg("用户名或密码错误");
		} else {
			//将用户保存到session中去
			UserContext.putCurrent(current);
		}
		return result;
	}

	/**
	 * 后台主页面
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "main";
	}
}
