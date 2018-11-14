package com.simple_crud.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
//			Configuration configuration = new Configuration()
//					.addAnnotatedClass(Person.class)
//					.configure("hibernate.simple_crud.cfg.xml");
//			
//			SessionFactory sessionFactory = configuration.buildSessionFactory();
//			System.out.println(" Hibernate configured ");
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.simple_crud.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();

//			return sessionFactory;
		} catch ( Throwable throwable ) {
			throw new ExceptionInInitializerError( throwable );
		}
		
	}

	public static SessionFactory getSesstionFactory() { return sessionFactory; }
	
}
