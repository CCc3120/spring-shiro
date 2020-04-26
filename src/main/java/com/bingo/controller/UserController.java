package com.bingo.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bingo.biz.IUserBiz;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserBiz userBiz;

	@RequestMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws Exception {
		Subject current = SecurityUtils.getSubject();
		if (!current.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// 是否选中设置true
			token.setRememberMe(true);
//			try {
			current.login(token);
//			} catch (AuthenticationException e) {
//				System.out.println("登陆失败" + e.getMessage());
//				return "login";
//			}
		}
		return "list";
		// shiro在认证过程中出现错误后将异常类路径通过request返回
//		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
//		// 错误的名称去判断的相关信息
//		if (exceptionClassName != null) {
//			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
//				throw new MyException("账号不存在");
//			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//				throw new MyException("用户名/密码错误");
//			} else if ("randomCodeError".equals(exceptionClassName)) {
//				throw new MyException("验证码错误");
//			} else {
//				throw new Exception();// 最终在异常处理器生成未知错误
//			}
//		}
		// 控制器里不做 用户登录验证 交给 shiro 认证去完成
//		return "login";
	}

	@RequestMapping("/testAnnotation")
	public String testAnnotation(HttpSession session) {
		session.setAttribute("key", "1234asd");
		userBiz.methodA();
		return "redirect:/list.jsp";
	}
}
