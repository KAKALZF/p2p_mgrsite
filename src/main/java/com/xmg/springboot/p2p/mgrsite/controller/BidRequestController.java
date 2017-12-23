package com.xmg.springboot.p2p.mgrsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.springboot.p2p.base.Query.UserfileQueryObject;
import com.xmg.springboot.p2p.base.domain.Userfile;
import com.xmg.springboot.p2p.base.domain.Userinfo;
import com.xmg.springboot.p2p.base.page.PageResult;
import com.xmg.springboot.p2p.base.service.IRealAuthService;
import com.xmg.springboot.p2p.base.service.IUserFileService;
import com.xmg.springboot.p2p.base.service.IUserinfoService;
import com.xmg.springboot.p2p.business.domain.BidRequest;
import com.xmg.springboot.p2p.business.domain.BidRequestAuditDomain;
import com.xmg.springboot.p2p.business.query.BidRequestQueryObject;
import com.xmg.springboot.p2p.business.service.IBidRequestSevice;
import com.xmg.springboot.p2p.mgrsite.util.UserContext;
import com.xmg.springboot.p2p.util.AjaxResult;
import com.xmg.springboot.p2p.util.Consts;

/**
 * 后台借款相关控制器
 * @author 1
 *
 */
@Controller
public class BidRequestController {
	@Autowired
	private IBidRequestSevice bidRequestSevice;
	@Autowired
	private IUserinfoService userinfoService;
	@Autowired
	private IRealAuthService realAuthService;
	@Autowired
	private IUserFileService userFileService;
	

	/**
	 * 发标前审核列表
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit_list")
	public String publisList(Model model,
			@ModelAttribute("qo") BidRequestQueryObject qo) {
		qo.setBidRequestState(Consts.BIDREQUEST_STATE_PUBLISH_PENDING);
		PageResult pageResult = bidRequestSevice.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "bidrequest/publish_audit";
	}

	/**
	 * 借款审核
	 */
	@RequestMapping("bidrequest_publishaudit")
	@ResponseBody
	public AjaxResult publishAudit(Long id, int state, String remark) {
		bidRequestSevice.audit(id, state, remark, UserContext.getCurrent());
		return new AjaxResult();
	}

	/**
	 * 后台借款详情
	 */
	@RequestMapping("borrow_info")
	public String borrowDetail(Long id, Model model) {
		BidRequest br = bidRequestSevice.get(id);
		//借款的基本信息
		model.addAttribute("bidRequest", br);
		//借款的用户基本信息
		Userinfo userinfo = userinfoService.get(br.getCreateUser().getId());
		model.addAttribute("userInfo", userinfo);
		//借款的审核对象列表
		List<BidRequestAuditDomain> audits = bidRequestSevice
				.listAuditsByBidRequestId(id);
		model.addAttribute("audits", audits);
		//借款人的实名认证信息
		model.addAttribute("realAuth",
				realAuthService.get(userinfo.getRealAuthId()));
		//借款人风控资料
		UserfileQueryObject qo = new UserfileQueryObject();
		qo.setState(Userfile.STATE_PASS);
		qo.setUserId(userinfo.getId());
		//qo.setPageSize(UserfileQueryObject.PAGE_SIZE_UMLIMIT);
		List<Userfile> userFiles = userFileService.queryForList(qo);
		model.addAttribute("userFiles", userFiles);
		return "bidrequest/borrow_info";
	}

	/**
	 * 满标一审列表
	 */
	@RequestMapping("bidrequest_audit1_list")
	public String fullAuditList(Model model,
			@ModelAttribute("qo") BidRequestQueryObject qo) {
		qo.setBidRequestState(Consts.BIDREQUEST_STATE_APPROVE_PENDING_1);
		PageResult pageResult = bidRequestSevice.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "bidrequest/audit1";
	}

	/**
	 * 满标一审审核
	 */
	@RequestMapping("bidrequest_audit1")
	@ResponseBody
	public AjaxResult fullAudit1(Long id, int state, String remark) {
		bidRequestSevice
				.fullAudit1(id, state, remark, UserContext.getCurrent());
		return new AjaxResult();
	}

	/**
	 * 满标二审列表
	 */
	@RequestMapping("bidrequest_audit2_list")
	public String fullAudit2List(Model model,
			@ModelAttribute("qo") BidRequestQueryObject qo) {
		qo.setBidRequestState(Consts.BIDREQUEST_STATE_APPROVE_PENDING_2);
		PageResult pageResult = bidRequestSevice.query(qo);
		model.addAttribute("pageResult", pageResult);
		return "bidrequest/audit2";
	}

	/**
	 * 满标二审审核
	 */
	@RequestMapping("bidrequest_audit2")
	@ResponseBody
	public AjaxResult fullAudit2(Long id, int state, String remark) {
		bidRequestSevice
				.fullAudit2(id, state, remark, UserContext.getCurrent());
		return new AjaxResult();
	}
}
