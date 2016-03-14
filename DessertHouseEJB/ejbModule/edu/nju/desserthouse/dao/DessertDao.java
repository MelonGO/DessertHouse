package edu.nju.desserthouse.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Dessert;

@Remote
public interface DessertDao {
	
	public Dessert findById(int dessertid);
	
	public Dessert findByName(String name);
	
	public List findDessertList();

}
