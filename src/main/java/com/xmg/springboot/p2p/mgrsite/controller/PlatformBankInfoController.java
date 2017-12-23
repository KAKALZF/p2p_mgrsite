package com.xmg.springboot.p2p.mgrsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.business.domain.PlatformBankInfo;
import com.xmg.springboot.p2p.business.query.PlatformBankInfoQueryObject;
import com.xmg.springboot.p2p.business.service.IPlatformBankInfoService;
import com.xmg.springboot.p2p.util.AjaxResult;

/**
 * 线下充值银行信息控制器
 * @author 1
 *
 */
@Controller
public class PlatformBankInfoController {
	@Autowired
	private IPlatformBankInfoService bankInfoService;

	@RequestMapping("companyBank_list")
	public String bankInfo(Model model,
			@ModelAttribute("qo") PlatformBankInfoQueryObject qo) {
		PageResult pageResult = bankInfoService.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "platformbankinfo/list";

	}

	/**
	 * 添加或修改
	 */
	@RequestMapping("companyBank_update")
	@ResponseBody
	public AjaxResult saveOrUpdate(PlatformBankInfo bankInfo) {
		bankInfoService.saveOrUpdate(bankInfo);
		return new AjaxResult();

	}
}
