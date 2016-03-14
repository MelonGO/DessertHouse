package edu.nju.desserthouse.service;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Admin;

@Remote
public interface AdminManageService {
	public Admin find(String name, String password);

}
