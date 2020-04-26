package com.bingo.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("======realm1======:" + token);
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
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		} else if ("user".equals(name)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
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
		String algorithmName = "MD5";
		Object source = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(result);
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object activeUser = principals.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		if ("admin".equals(activeUser)) {
			roles.add("admin");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}
}
