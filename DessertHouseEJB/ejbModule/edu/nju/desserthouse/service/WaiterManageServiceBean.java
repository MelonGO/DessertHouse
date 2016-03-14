package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.WaiterDao;
import edu.nju.desserthouse.model.Waiter;

@Stateless
public class WaiterManageServiceBean implements WaiterManageService {

	@EJB
	WaiterDao waiterDao;

	@Override
	public boolean save(Waiter waiter) {
		// TODO Auto-generated method stub
		return waiterDao.save(waiter);
	}

	@Override
	public List findWaiterList() {
		// TODO Auto-generated method stub
		return waiterDao.findWaiterList();
	}

	@Override
	public boolean update(Waiter waiter) {
		// TODO Auto-generated method stub
		return waiterDao.update(waiter);
	}

	@Override
	public boolean delete(Waiter waiter) {
		// TODO Auto-generated method stub
		return waiterDao.delete(waiter);
	}

	@Override
	public Waiter find(String name, String password) {
		// TODO Auto-generated method stub
		return waiterDao.find(name, password);
	}

	@Override
	public Waiter findById(int waiterid) {
		// TODO Auto-generated method stub
		return waiterDao.findById(waiterid);
	}

}
