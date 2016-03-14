package edu.nju.desserthouse.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.AdminDao;
import edu.nju.desserthouse.model.Admin;
import edu.nju.desserthouse.model.User;

@Stateless
public class AdminManageServiceBean implements AdminManageService {
	@EJB
	AdminDao adminDao;

	@Override
	public Admin find(String name, String password) {
		try {
			Admin admin = adminDao.find(name, password);
			return admin;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
