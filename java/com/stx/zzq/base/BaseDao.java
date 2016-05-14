package com.stx.zzq.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;


public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected static Session session;
	
	/*static {
		session = sessionFactory.getCurrentSession();
	}*/
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
