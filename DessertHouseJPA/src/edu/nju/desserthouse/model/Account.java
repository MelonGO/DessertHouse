package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int accountid;
	private int userid;
	private Double payment;

	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int id) {
		this.accountid = id;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int id) {
		this.userid = id;
	}

	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}

}
