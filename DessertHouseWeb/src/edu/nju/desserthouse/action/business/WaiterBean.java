package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.desserthouse.model.Waiter;

public class WaiterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Waiter waiter;
	private List waiterlist;

	public Waiter getWaiter() {
		return waiter;
	}
	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public List getWaiterlist() {
		return waiterlist;
	}
	public void setWaiterlist(List list) {
		this.waiterlist = list;
	}

}
