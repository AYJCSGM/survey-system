package vip.itellyou.dao.impl;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import vip.itellyou.core.format.Md5Class;
import vip.itellyou.dao.UserDao;
import vip.itellyou.entity.User;

public class TestUserDaoImpl {
	@Test
	public void testInsert() throws Exception{
		User user = new User();
		user.setName("ÀöÀö");
		user.setPwd(Md5Class.stringToMd5("123"));
		
		UserDao userDao = new UserDaoImpl();
		int actual = userDao.insert(user);
		int expected=1;
		Assert.assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetAll() throws Exception{
		UserDao userDao = new UserDaoImpl();
		List actual = userDao.getAll();
		int expected=1;
		Assert.assertEquals(expected,actual.size());
	}
}
