package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.desserthouse.model.Order;

public class OrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;
	private List orderlist;

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public List getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List list) {
		this.orderlist = list;
	}
}
