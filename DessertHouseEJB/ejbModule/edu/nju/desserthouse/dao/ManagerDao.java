package edu.nju.desserthouse.dao;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Manager;

@Remote
public interface ManagerDao {
	
	public Manager find(String name, String password);

}
