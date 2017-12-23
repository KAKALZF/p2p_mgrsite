package com.xmg.springboot.p2p.mgrsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmg.springboot.p2p.base.Query.RealAuthQueryObject;
import com.xmg.springboot.p2p.base.domain.Logininfo;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.ILogininfoService;
import com.xmg.springboot.p2p.base.service.IRealAuthService;
import com.xmg.springboot.p2p.base.service.IUserinfoService;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;

@Controller
public class RealAuthController {
	@Autowired
	private IRealAuthService realAuthService;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private ILogininfoService logininfoService;

	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute("qo") RealAuthQueryObject qo,
			Model model) {
		PageResult pageResult = realAuthService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "realAuth/list";
	}

	@RequestMapping("realAuth_audit")
	public String realAuth_audit(Long id, int state, String remark) {
		realAuthService.audit(id, state, remark, UserContext.getCurrent());
		return "realAuth/list";
	}
}
