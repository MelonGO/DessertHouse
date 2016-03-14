package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.Plan;

public class DessertBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dessert dessert;
	private List dessertlist;

	public Dessert getDessert() {
		return dessert;
	}
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}

	public List getDessertlist() {
		return dessertlist;
	}
	public void setDessertlist(List list) {
		this.dessertlist = list;
	}

}
