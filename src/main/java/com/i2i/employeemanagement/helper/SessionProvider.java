package com.i2i.employeemanagement.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.i2i.employeemanagement.exception.EmployeeException;


/**
 * This class will manage the session to the Database.
 * @author Paari
 */
public class SessionProvider {
    private static SessionFactory sessionFactory = null;

    /**
     * This Methos is to create the SesionFactory.
     * @throws 
     *    -EmployeeException if there is any error in config file.
     */ 
    public static SessionFactory getSessionFactory() throws EmployeeException {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                 System.out.println("Error in building session " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    /**
     * This methos is to close the session in the Session Factory.
     */
    public static void closeSession(Session session) {
        try {
            session.close();
        } catch (HibernateException e) {
            System.out.println("Failed to close the Hibernate session: " + e.getMessage());
        }
        
    }
}