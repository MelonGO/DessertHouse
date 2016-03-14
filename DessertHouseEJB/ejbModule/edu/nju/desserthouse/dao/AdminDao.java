package edu.nju.desserthouse.dao;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Admin;

@Remote
public interface AdminDao {
	
	public Admin find(String name, String password);

}
