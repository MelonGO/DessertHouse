package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.User;

@Remote
public interface UserManageService {
	public boolean register(User user);

	public User find(String name, String password);

	public boolean update(User user);
	
	public User findById(int userid);
	
	public List findUserList();
	
}
