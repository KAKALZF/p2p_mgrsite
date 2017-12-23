package com.xmg.springboot.p2p.mgrsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.Query.UserfileQueryObject;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.IUserFileService;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;
import com.xmg.springboot.p2p.util.AjaxResult;

@Controller
public class UserfileAuditController {
	@Autowired
	private IUserFileService userFileService;

	@RequestMapping("/userFileAuth")
	public String userfileAudit(@ModelAttribute("qo") UserfileQueryObject qo,
			Model model) {
		PageResult pageResult = userFileService.query(qo);
		System.out.println(pageResult);
		model.addAttribute("pageResult", pageResult);
		return "userFileAuth/list";
	}

	/**
	 * 风控材料审核
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("/userFile_audit")
	@ResponseBody
	public AjaxResult userFile_audit(Long id, int state, String remark,
			int score) {
		userFileService.audit(id, state, remark, score,
				UserContext.getCurrent());
		return new AjaxResult();
	}
}
