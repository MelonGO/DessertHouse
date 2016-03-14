package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dessert")
public class Dessert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int dessertid;
	private String dessertname;
	private Double price;
	private int amount;
	
	public int getDessertid() {
		return dessertid;
	}
	public void setDessertid(int id) {
		this.dessertid = id;
	}

	public String getDessertname() {
		return dessertname;
	}
	public void setDessertname(String name) {
		this.dessertname = name;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
