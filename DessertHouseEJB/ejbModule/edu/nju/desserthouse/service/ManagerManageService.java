package edu.nju.desserthouse.service;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Manager;

@Remote
public interface ManagerManageService {
	
	public Manager find(String name, String password);

}
