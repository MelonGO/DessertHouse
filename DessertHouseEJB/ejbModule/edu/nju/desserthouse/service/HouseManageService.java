package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.House;

@Remote
public interface HouseManageService {

	public boolean save(House house);

	public List findHouseList();
	
	public House findHouseById(int houseid);
	
	public House findHouseByName(String housename);

	public boolean update(House house);

	public boolean delete(House house);

}
