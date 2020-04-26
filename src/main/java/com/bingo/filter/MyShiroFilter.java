package com.bingo.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class MyShiroFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 校验验证码
		// 从session获取正确的验证码
		// HttpSession session = ((HttpServletRequest) request).getSession();
		// 页面输入的验证码
		String randomcode = "123";
//		String randomcode = ((HttpServletRequest) request).getParameter("randomcode");
		// 从session中取出验证码
		String validateCode = "123";
//		String validateCode = (String) session.getAttribute("validateCode");
		if (randomcode != null && validateCode != null) {
			if (!randomcode.equals(validateCode)) {
				request.setAttribute("shiroLoginFailure", "randomCodeError");
				// 拒绝访问，不再校验账号和密码 认证
				return true;
			}
		}
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		// TODO Auto-generated method stub
		return super.onLoginFailure(token, e, request, response);
	}

}
