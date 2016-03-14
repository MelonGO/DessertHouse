package edu.nju.desserthouse.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Order;

@Remote
public interface OrderDao {
	
	public boolean save(Order order);
	
	public Order findOrderById(int orderid);
	
	public List findOrderByHouseId(int houseid);

	public List findOrderByUserId(int userid);
	
	public List findAll();
	
	public boolean update(Order order);
	
}
