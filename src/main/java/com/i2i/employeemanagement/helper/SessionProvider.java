package com.i2i.employeemanagement.helper;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import com.i2i.employeemanagement.exception.EmployeeException;


/**
 * This class will manage the session to the Database.
 * @author Paari
 */
public class SessionProvider {
    private static SessionFactory sessionFactory = null;

    /**
     * This Method is to create the SessionFactory.
     * @throws
     *    EmployeeException if there is any error in config file.
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

}