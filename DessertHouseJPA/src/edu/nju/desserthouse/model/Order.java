package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int orderid;
	private int userid;
	private String username;
	private int houseid;
	private String housename;
	private String content;
	private Double price;
	private String status;
	private String ordertime;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int id) {
		this.orderid = id;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

}
