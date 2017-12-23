package com.xmg.springboot.p2p.mgrsite.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xmg.springboot.p2p.base.domain.Logininfo;

public class UserContext {
	public static String CURRENT_USER_IN_SESSION = "logininfo";

	public static HttpSession session() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
	}

	/**
	 * 把当前用户放到会话中
	 */
	public static void putCurrent(Logininfo current) {
		session().setAttribute(CURRENT_USER_IN_SESSION, current);
	}

	/**
	 * 获取当前用户
	 * @return
	 */
	public static Logininfo getCurrent() {
		return (Logininfo) session().getAttribute(CURRENT_USER_IN_SESSION);
	}
}
