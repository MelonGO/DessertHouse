package edu.nju.desserthouse.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.ManagerDao;
import edu.nju.desserthouse.model.Manager;
@Stateless
public class ManagerManageServiceBean implements ManagerManageService {
	@EJB
	ManagerDao managerDao;

	@Override
	public Manager find(String name, String password) {
		// TODO Auto-generated method stub
		return managerDao.find(name, password);
	}

}
