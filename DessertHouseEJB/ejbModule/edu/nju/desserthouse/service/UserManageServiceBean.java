package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;

@Stateless
public class UserManageServiceBean implements UserManageService {

	@EJB
	UserDao userDao;

	@Override
	public boolean register(User user) {
		return userDao.save(user);

	}

	@Override
	public User find(String name, String password) {
		try {
			User user = userDao.find(name, password);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public User findById(int userid) {
		// TODO Auto-generated method stub
		return userDao.findById(userid);
	}

	@Override
	public List findUserList() {
		return userDao.findUserList();
	}

}
