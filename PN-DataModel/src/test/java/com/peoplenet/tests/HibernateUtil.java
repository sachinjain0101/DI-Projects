package com.peoplenet.tests;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	private static ServiceRegistry serviceRegistry;

	static {
		try {
		
			File file=new File("./cfg/hibernate.cfg.xml");
			
		StandardServiceRegistry standardRegistry = 
		new StandardServiceRegistryBuilder().configure(file).build();
		Metadata metaData = 
		new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		} catch (Throwable th) {

			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);

		}
	}
	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}
}