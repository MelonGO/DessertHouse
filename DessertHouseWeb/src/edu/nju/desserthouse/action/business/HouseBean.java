package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.desserthouse.model.House;

public class HouseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private House house;
	private List houselist;

	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}

	public List getHouselist() {
		return houselist;
	}
	public void setHouselist(List list) {
		this.houselist = list;
	}

}
