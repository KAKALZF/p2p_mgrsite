package com.xmg.springboot.p2p.mgrsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.Query.VedioAuthQueryObject;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.IVedioAuthService;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;
import com.xmg.springboot.p2p.util.AjaxResult;

@Controller
public class VedioAuthController {
	@Autowired
	private IVedioAuthService vedioAuthService;

	@RequestMapping("vedioAuth")
	public String vedioAuth(Model model,
			@ModelAttribute("qo") VedioAuthQueryObject qo) {
		PageResult pageResult = vedioAuthService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "vedioAuth/list";
	}

	@RequestMapping("vedioAuth_audit")
	@ResponseBody
	public AjaxResult vedioAuth_audit(String remark, int state,
			Long loginInfoValue) {
		vedioAuthService.audit(remark, state, loginInfoValue,
				UserContext.getCurrent());
		return new AjaxResult();
	}
}
