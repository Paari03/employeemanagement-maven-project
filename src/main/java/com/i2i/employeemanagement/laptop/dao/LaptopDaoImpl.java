package com.i2i.employeemanagement.laptop.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.helper.SessionProvider;
import com.i2i.employeemanagement.model.Laptop;
import com.i2i.employeemanagement.model.Employee;

public class LaptopDaoImpl implements LaptopDao {

    @Override
    public Map<Integer, Laptop> getAllLaptops() throws EmployeeException{
        Session session = null;
        Map<Integer, Laptop> laptops = new HashMap<>();
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Query<Laptop> query = session.createQuery("FROM Laptop ", Laptop.class);
            for (Laptop laptop : query.list()) {
                laptops.put(laptop.getLaptopId(), laptop);
            }
        } catch (Exception e) {
            throw new EmployeeException("Error retrieving all laptops:", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return laptops;
    }

    @Override
    public Laptop getLaptopById(int laptopId) throws EmployeeException {
        Session session = null;
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Laptop laptop1 = session.get(Laptop.class, laptopId);
            return laptop1;
        } catch (Exception e) {
            throw new EmployeeException("Error retrieving a laptops:", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void updateLaptop(int laptopId, Laptop laptop) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(laptop);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error updating the laptopId: " + laptopId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Map<Integer, Employee> getEmployeeByLaptop(int laptopId) throws EmployeeException {
        Session session = null;
        Map<Integer, Employee> employeeDetails = new HashMap<>();
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Laptop laptop = session.get(Laptop.class, laptopId);
            if (laptop != null && laptop.getEmployees() != null) {
                 Employee employee = laptop.getEmployees();
                 employeeDetails.put(employee.getId(), employee);
            }
        } catch (Exception e) {
            throw new EmployeeException("Error in retrieving employees by laptop " + laptopId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeDetails;
    }
}