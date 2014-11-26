package com.jf.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateUtils {
	
	private static Configuration configuration = new Configuration();
	
	private static SessionFactory sessionFactory = null;
	
	static{
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		StandardServiceRegistry standardServiceRegistry = builder.build();
		
		sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
		
	}
	
	public static Session getSession()
	{
		Session session = sessionFactory.openSession();
		return session;
	}
	
	;


	
}