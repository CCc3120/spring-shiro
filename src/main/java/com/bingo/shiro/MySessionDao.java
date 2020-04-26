package com.bingo.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.bingo.util.SerializableUtils;

/**
 * session操作</br>
 * 需要建一张session表
 * 
 * @ClassName: MySessionDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 25865
 * @date 2020年4月26日 上午12:41:41 <br/>
 *       注意：本内容仅限于学习参考，禁止外泄以及用于其他的商业目
 */
public class MySessionDao extends EnterpriseCacheSessionDAO {

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		String sql = "insert into sessions(id,session) values(?,?)";
		// sql第二个参数
		SerializableUtils.serializ(session);
		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sql = "select * from sessions where id = ?";
		return SerializableUtils.deserializ("");
	}

	@Override
	protected void doUpdate(Session session) {
		String sql = "update";
	}

	@Override
	protected void doDelete(Session session) {
		String sql = "delete";
	}
}
