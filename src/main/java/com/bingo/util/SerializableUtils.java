package com.bingo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

/**
 * 序列化session
 * 
 * @ClassName: SerializableUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 25865
 * @date 2020年4月26日 上午12:53:07 <br/>
 *       注意：本内容仅限于学习参考，禁止外泄以及用于其他的商业目
 */
public class SerializableUtils {

	public static String serializ(Session session) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(session);
			return Base64.encodeToString(bos.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("serializ session error", e);
		}
	}

	public static Session deserializ(String str) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(str));
			ObjectInputStream ois = new ObjectInputStream(bis);
			return (Session) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("deserializ session error", e);
		}
	}

}
