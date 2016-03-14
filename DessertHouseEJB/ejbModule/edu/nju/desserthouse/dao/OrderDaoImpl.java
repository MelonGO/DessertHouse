package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Order;

@Stateless
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(Order order) {
		try {
			em.persist(order);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List findOrderByUserId(int userid) {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Order p where p.userid=:id");
			query.setParameter("id", userid);
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findAll() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Order p");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Order findOrderById(int orderid) {
		try {
			Query query = em.createQuery("select p from Order p where p.orderid=:id");
			query.setParameter("id", orderid);
			Order order = (Order) query.getSingleResult();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Order order) {
		try {
			em.merge(order);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List findOrderByHouseId(int houseid) {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Order p where p.houseid=:id");
			query.setParameter("id", houseid);
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
