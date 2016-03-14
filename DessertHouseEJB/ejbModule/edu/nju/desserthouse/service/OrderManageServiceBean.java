package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.OrderDao;
import edu.nju.desserthouse.model.Order;

@Stateless
public class OrderManageServiceBean implements OrderManageService {

	@EJB
	OrderDao orderDao;
	
	@Override
	public boolean save(Order order) {
		// TODO Auto-generated method stub
		return orderDao.save(order);
	}

	@Override
	public List findOrderByUserId(int userid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByUserId(userid);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return orderDao.findAll();
	}

	@Override
	public Order findOrderById(int orderid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderById(orderid);
	}

	@Override
	public boolean update(Order order) {
		// TODO Auto-generated method stub
		return orderDao.update(order);
	}

	@Override
	public List findOrderByHouseId(int houseid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByHouseId(houseid);
	}

}
