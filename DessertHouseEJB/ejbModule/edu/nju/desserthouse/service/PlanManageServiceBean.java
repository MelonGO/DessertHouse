package edu.nju.desserthouse.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.PlanDao;
import edu.nju.desserthouse.model.Plan;

@Stateless
public class PlanManageServiceBean implements PlanManageService {

	@EJB
	PlanDao planDao;
	
	@Override
	public boolean save(Plan plan) {
		// TODO Auto-generated method stub
		return planDao.save(plan);
	}

	@Override
	public boolean update(Plan plan) {
		// TODO Auto-generated method stub
		return planDao.update(plan);
	}

	@Override
	public List findByHouseId(int houseid) {
		// TODO Auto-generated method stub
		return planDao.findByHouseId(houseid);
	}

	@Override
	public List findPlanList() {
		// TODO Auto-generated method stub
		return planDao.findPlanList();
	}

	@Override
	public boolean delete(Plan plan) {
		// TODO Auto-generated method stub
		return planDao.delete(plan);
	}

	@Override
	public Plan findById(int planid) {
		// TODO Auto-generated method stub
		return planDao.findById(planid);
	}

	@Override
	public List findByHouseIdAndDay(int houseid, String day) {
		// TODO Auto-generated method stub
		return planDao.findByHouseIdAndDay(houseid, day);
	}

	@Override
	public List findByDay(String day) {
		// TODO Auto-generated method stub
		return planDao.findByDay(day);
	}

}
