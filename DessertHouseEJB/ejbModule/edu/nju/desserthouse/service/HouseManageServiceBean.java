package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.HouseDao;
import edu.nju.desserthouse.model.House;

@Stateless
public class HouseManageServiceBean implements HouseManageService {

	@EJB
	HouseDao houseDao;

	@Override
	public boolean save(House house) {
		return houseDao.save(house);
	}

	@Override
	public List findHouseList() {
		return houseDao.findHouseList();
	}

	@Override
	public boolean update(House house) {
		return houseDao.update(house);
	}

	@Override
	public boolean delete(House house) {
		return houseDao.delete(house);
	}

	@Override
	public House findHouseById(int houseid) {
		// TODO Auto-generated method stub
		return houseDao.findHouseById(houseid);
	}

	@Override
	public House findHouseByName(String housename) {
		// TODO Auto-generated method stub
		return houseDao.findHouseByName(housename);
	}

}
