package com.xmg.springboot.p2p.mgrsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmg.springboot.p2p.base.Query.IpLogQueryObject;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.IIpLogService;

/**
 * 后台登录日志控制器
 * @author 1
 *
 */
@Controller
public class IpLogController {
	@Autowired
	private IIpLogService ipLogService;

	@RequestMapping("ipLog")
	public String logView(@ModelAttribute("qo") IpLogQueryObject qo, Model model) {
		//qo.setUsername(UserContext.getCurrent().getUsername());
		//qo.setUserType(Logininfo.TYPE_MANAGER);
		PageResult pageResult = ipLogService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "ipLog/list";
	}
}
