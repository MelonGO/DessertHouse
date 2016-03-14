package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.DessertDao;
import edu.nju.desserthouse.model.Dessert;

@Stateless
public class DessertManageServiceBean implements DessertManageService {

	@EJB
	DessertDao dessertDao;

	@Override
	public Dessert findById(int dessertid) {
		// TODO Auto-generated method stub
		return dessertDao.findById(dessertid);
	}

	@Override
	public List findDessertList() {
		// TODO Auto-generated method stub
		return dessertDao.findDessertList();
	}

	@Override
	public Dessert findByName(String name) {
		// TODO Auto-generated method stub
		return dessertDao.findByName(name);
	}

}
