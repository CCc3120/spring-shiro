package com.bingo.biz.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.bingo.biz.IUserBiz;

@Service
public class UserBizImpl implements IUserBiz {

	@Override
	public String methodA() {
		System.out.println("---->service方法");
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		System.out.println(val);
		return "";
	}

}
