package edu.nju.desserthouse.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Plan;

@Remote
public interface PlanDao {

	public boolean save(Plan plan);

	public boolean update(Plan plan);

	public Plan findById(int planid);

	public List findByHouseId(int houseid);

	public List findByHouseIdAndDay(int houseid, String day);
	
	public List findByDay(String day);

	public List findPlanList();

	public boolean delete(Plan plan);

}
