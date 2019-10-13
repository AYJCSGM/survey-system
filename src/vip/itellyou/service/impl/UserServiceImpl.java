package vip.itellyou.service.impl;

import java.util.List;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.core.format.Md5Class;
import vip.itellyou.dao.UserDao;
import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.entity.User;
import vip.itellyou.entity.UserQueryModel;
import vip.itellyou.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void register(User user) throws Exception {
		// 用户名不能为空验证
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new ReTryException("用户账号不能为空");
		}
		// 登录密码不能为空和长度至少6位验证
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new ReTryException("登录密码不能为空或者密码长度不足6位");
		}
		// 处理密码和确认密码不一致
		if (!user.getPwd().equals(user.getConfirmPwd())) {
			throw new ReTryException("密码和确认密码不一致");
		}
		// 用户账号不能重复
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		List list = userDao.getByCondition(queryModel);
		if (list.size() > 0) {
			throw new ReTryException("用户账号已经被注册");
		}

		// 用户密码必须经过加密
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		// 用户在线状态为不在线
		user.setOnline(1);

		// 保存用户数据到数据库
		userDao.insert(user);
	}

	@Override
	public User login(User user) throws Exception{
		// 用户名不能为空验证
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new ReTryException("用户账号不能为空");
		}
		// 登录密码不能为空和长度至少6位验证
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new ReTryException("登录密码不能为空或者密码长度不足6位");
		}
	
		//按照用户名和密码获取用户对象
		User target = null;
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		queryModel.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.getByCondition(queryModel);
		if(list!=null && list.size()>0){
			//如果集合中存在元素，则获取到用户对象
			target = (User)list.get(0);
			
			//检查用户是否已经在线
			if(target.getOnline()==2){
				throw new ReTryException("用户已经在线");
			}
			else{
				//登录成功，修改用户在线状态
				target.setOnline(2);
				userDao.update(target);
			}
		}
		else{
			//集合中没有用户对象，则登录失败
			throw new ReTryException("用户名不存在或者密码错误");
		}
		
		return target;
	}

	@Override
	public User getUser(int id) throws Exception {
		User user = (User)userDao.getModel(id);
		return user;
	}

	@Override
	public void online(User user,boolean inOrOut) {		
		try {
			if(inOrOut){
				user.setOnline(2);
			}
			else{
			    user.setOnline(1);
			}
			
			userDao.update(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
