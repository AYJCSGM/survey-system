package vip.itellyou.service;

import vip.itellyou.entity.User;

public interface UserService {
	public void register(User user) throws Exception;

	public User login(User user) throws Exception;

	public User getUser(int id) throws Exception;

	public void online(User user,boolean inOrOut);
}
