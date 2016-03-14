package edu.nju.desserthouse.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.User;

@Remote
public interface UserDao {
	public boolean save(User user);

	public User find(String name, String password);
	
	public boolean update(User user);
	
	public User findById(int userid);
	
	public List findUserList();

}
