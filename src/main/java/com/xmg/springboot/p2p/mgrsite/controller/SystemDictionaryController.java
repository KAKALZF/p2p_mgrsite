package com.xmg.springboot.p2p.mgrsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.Query.SystemDictionaryQueryObject;
import com.xmg.springboot.p2p.base.domain.SystemDictionary;
import com.xmg.springboot.p2p.base.domain.SystemDictionaryItem;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.ISystemDictionaryService;
import com.xmg.springboot.p2p.util.AjaxResult;

@Controller
public class SystemDictionaryController {
	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	@RequestMapping("/systemDictionary_list")
	public String systemDictionary(
			@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		List<SystemDictionary> systemDictionaryGroups = systemDictionaryService
				.selectAll();
		PageResult pageResult = systemDictionaryService.queryDictionay(qo);
		model.addAttribute("systemDictionaryGroups", systemDictionaryGroups);
		model.addAttribute("pageResult", pageResult);
		return "systemdic/systemDictionary_list";
	}

	/**
	 * 数据字典明细
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("/systemDictionaryItem_list")
	public String systemDictionaryItem(
			@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		List<SystemDictionary> systemDictionaryGroups = systemDictionaryService
				.selectAll();
		PageResult pageResult = systemDictionaryService.queryDictionayItem(qo);
		model.addAttribute("systemDictionaryGroups", systemDictionaryGroups);
		model.addAttribute("pageResult", pageResult);
		return "systemdic/systemDictionaryItem_list";
	}

	@RequestMapping("/systemDictionary_update")
	@ResponseBody
	public AjaxResult saveOrUpdateDic(SystemDictionary dic) {

		systemDictionaryService.saveOrUpdate(dic);
		return new AjaxResult();
	}

	@RequestMapping("/systemDictionaryItem_update")
	@ResponseBody
	public AjaxResult saveOrUpdateDicItem(SystemDictionaryItem item) {

		systemDictionaryService.saveOrUpdateUItem(item);
		return new AjaxResult();
	}

}
