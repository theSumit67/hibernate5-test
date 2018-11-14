package com.simple_crud.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import com.simple_crud.entity.Person;
import com.simple_crud.util.HibernateUtil;

public class PersonDao {
	public void savePerson( Person person ) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(person);
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
	}
	
	public void deletePerson( Person person ) {
		Session session = HibernateUtil.getSesstionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(person);
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
	}
}
