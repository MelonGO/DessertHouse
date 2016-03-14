package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Waiter;

@Remote
public interface WaiterManageService {

	public boolean save(Waiter waiter);
	
	public Waiter find(String name, String password);

	public List findWaiterList();
	
	public Waiter findById(int waiterid);

	public boolean update(Waiter waiter);

	public boolean delete(Waiter waiter);

}
