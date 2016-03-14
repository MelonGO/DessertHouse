package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Dessert;

@Remote
public interface DessertManageService {

	public Dessert findById(int dessertid);
	
	public Dessert findByName(String name);

	public List findDessertList();

}
