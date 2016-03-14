package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.desserthouse.model.Plan;

public class PlanBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plan plan;
	private List planlist;

	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List getPlanlist() {
		return planlist;
	}
	public void setPlanlist(List list) {
		this.planlist = list;
	}

}
