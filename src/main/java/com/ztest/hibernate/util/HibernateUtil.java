package com.ztest.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.ztest.hibernate.model.Employee1;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static SessionFactory sessionAnnotationFactory;
	private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // XML based SessionFactory from hibernate.cfg.xml
        	// Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        	// ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        	
        	// SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            // return sessionFactory;
        }
        catch (Throwable exc) {
            System.err.println("SessionFactory creation failed." + exc);
            throw new ExceptionInInitializerError(exc);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
    	try {
            // Create the SessionFactory from hibernate.cfg.xml
        	// Configuration configuration = new Configuration().configure("hibernate-annotation.cfg.xml");
        	// System.out.println("Hibernate Annotation Configuration loaded");
        	// ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	// System.out.println("Hibernate Annotation serviceRegistry created");
        	// SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            // return sessionFactory;
    		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate-annotation.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

    private static SessionFactory buildSessionJavaConfigFactory() {
    	try {
    	Configuration configuration = new Configuration();
		
		//Create Properties, can be read from property files too
		Properties props = new Properties();
		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.url", "jdbc:mysql://localhost/dbtest");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.connection.password", "toor");
		props.put("hibernate.current_session_context_class", "thread");
		
		configuration.setProperties(props);
		
		//we can set mapping file or class with annotation
		//addClass(Employee1.class) will look for resource
		// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
		configuration.addAnnotatedClass(Employee1.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	System.out.println("Hibernate Java Config serviceRegistry created");
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
        return sessionFactory;
    	}
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
    
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
	
	public static SessionFactory getSessionAnnotationFactory() {
		if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
	
	public static SessionFactory getSessionJavaConfigFactory() {
		if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
	
}
