package com.xmg.springboot.p2p.mgrsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.business.domain.PlatformBankInfo;
import com.xmg.springboot.p2p.business.query.RechargeOfflineQueryObject;
import com.xmg.springboot.p2p.business.service.IPlatformBankInfoService;
import com.xmg.springboot.p2p.business.service.IRechargeOfflineService;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;
import com.xmg.springboot.p2p.util.AjaxResult;

/**
 * 线下充值审核控制器
 * @author 1
 *
 */
@Controller
public class RechargeOfflineController {
	@Autowired
	private IRechargeOfflineService rechargeService;
	@Autowired
	private IPlatformBankInfoService platformBankInfoService;

	@RequestMapping("rechargeOffline")
	public String rechargeAudit(
			@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {
		PageResult pageResult = rechargeService.query(qo);
		List<PlatformBankInfo> banks = platformBankInfoService.listAll();
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("banks", banks);
		return "rechargeoffline/list";

	}

	/**
	 * 线下充值审核
	 * @param id
	 * @param state
	 * @param remark
	 * @return
	 */
	@RequestMapping("rechargeOffline_audit")
	@ResponseBody
	public AjaxResult audit(Long id, int state, String remark) {
		rechargeService.audit(id, state, remark, UserContext.getCurrent());
		return new AjaxResult();
	}

}
