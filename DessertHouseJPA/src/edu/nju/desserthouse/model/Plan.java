package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int planid;
	private int waiterid;
	private String waitername;
	private int houseid;
	private String housename;
	private int dessertid;
	private String dessertname;
	private Double dessertprice;
	private int amount;
	private String day;
	private String status;
	private String plantime;

	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int id) {
		this.planid = id;
	}

	public int getWaiterid() {
		return waiterid;
	}
	public void setWaiterid(int id) {
		this.waiterid = id;
	}
	
	public String getWaitername() {
		return waitername;
	}
	public void setWaitername(String name) {
		this.waitername = name;
	}
	
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int id) {
		this.houseid = id;
	}
	
	public String getHousename() {
		return housename;
	}
	public void setHousename(String name) {
		this.housename = name;
	}

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
	
	public Double getDessertprice() {
		return dessertprice;
	}
	public void setDessertprice(Double price) {
		this.dessertprice = price;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlantime() {
		return plantime;
	}
	public void setPlantime(String time) {
		this.plantime = time;
	}

}
