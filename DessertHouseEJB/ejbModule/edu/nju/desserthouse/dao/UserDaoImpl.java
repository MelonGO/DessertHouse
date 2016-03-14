package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.User;

@Stateless
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(User user) {
		try {
			em.persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User find(String name, String password) {
		try {
			Query query = em.createQuery("select u from User u where u.username=:name and u.userpassword=:password");
			query.setParameter("name", name);
			query.setParameter("password", password);
			User user = (User) query.getSingleResult();
			em.clear();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(User user) {
		try {
			em.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public User findById(int userid) {
		try {
			Query query = em.createQuery("select u from User u where u.userid=:id");
			query.setParameter("id", userid);
			User user = (User) query.getSingleResult();
			em.clear();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findUserList() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select u from User u");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
