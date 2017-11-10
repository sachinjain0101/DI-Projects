package com.peoplenet.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.peoplenet.model.*;


public class CreateData {
	public static void main(String[] args) throws Exception {

	SessionFactory sessFact = HibernateUtil.getSessionFactory();
	Session session = sessFact.getCurrentSession();
	org.hibernate.Transaction tr = session.beginTransaction();
	TestSJ test = new TestSJ();
	test.setRecordID(1);
	test.setFullName("SJ");
	
	session.save(test);
	tr.commit();
	System.out.println("Successfully inserted");
	sessFact.close();
	}

}