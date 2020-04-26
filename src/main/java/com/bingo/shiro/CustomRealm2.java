package com.bingo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class CustomRealm2 extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("======realm2======:" + token);
		System.out.println("doGetAuthenticationInfo:" + token);
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String name = upToken.getUsername();
		char[] password = upToken.getPassword();
		System.out.println("用户信息:" + name);
		System.out.println("用户密码:" + password.toString());
		if (name.equals("unknown")) {
			throw new UnknownAccountException("用户不存在");
		}
		if (name.equals("lock")) {
			throw new UnknownAccountException("用户被锁定");
		}
		Object username = name;
		// 数据库密码
		Object credentials = "";
		if ("admin".equals(name)) {
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		} else if ("user".equals(name)) {
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		// 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		String realmName = getName();
		// SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, "123456",
		// this.getName());
		// 盐值加密
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
		return info;
	}

	public static void main(String[] args) {
		String algorithmName = "SHA1";
		Object source = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 1024;
		Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(result);
	}
}
