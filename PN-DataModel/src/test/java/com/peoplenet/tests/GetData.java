package com.peoplenet.tests;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.peoplenet.model.TestSJ;


public class GetData {
	public static void main(String[] args) throws Exception {

	SessionFactory sessFact = HibernateUtil.getSessionFactory();
	Session session = sessFact.getCurrentSession();
	org.hibernate.Transaction tr = session.beginTransaction();

	CriteriaBuilder builder = session.getCriteriaBuilder();
	CriteriaQuery<TestSJ> cq = builder.createQuery(TestSJ.class);
	Root<TestSJ> root = cq.from(TestSJ.class);
	
	cq.where(builder.lessThan(root.get("recordID"),1));
	
	List<TestSJ> reList = session.createQuery(cq).getResultList();

	for (TestSJ re : reList) {
		System.out.println("GroupCode: " + re.getFullName());
		System.out.println("Client: " + re.getRecordID());
	}  
	

	tr.commit();
	System.out.println("Data printed");
	sessFact.close();
	}
}