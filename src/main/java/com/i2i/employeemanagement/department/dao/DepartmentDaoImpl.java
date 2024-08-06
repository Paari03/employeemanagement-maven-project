package com.i2i.employeemanagement.department.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.helper.SessionProvider;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;

public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public void addDepartment(String departmentName) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Department department = new Department(departmentName);
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error adding the Department Name: " + departmentName, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Map<Integer, Department> getAllDepartments() throws EmployeeException{
        Session session = null;
        Map<Integer, Department> departments = new HashMap<>();
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Query<Department> query = session.createQuery("FROM Department WHERE isDeleted = false", Department.class);
            for (Department department : query.list()) {
                departments.put(department.getDepartmentId(), department);
            }
        } catch (Exception e) {
            throw new EmployeeException("Error retrieving all departments:", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return departments;
    }

    @Override
    public void updateDepartment(int departmentId, Department department) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error updating the departmentId: " + departmentId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteDepartment(int departmentId) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, departmentId);
            if (department != null) {
                department.setIsDeleted(true);
                session.update(department);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error deleting DepartmentId:" + departmentId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Map<Integer, Employee> getEmployeeByDepartment(int departmentId) throws EmployeeException {
        Session session = null;
        Map<Integer, Employee> employeeDetails = new HashMap<>();
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Department department = session.get(Department.class, departmentId);
            if (department != null && department.getEmployees() != null) {
                for (Employee employee : department.getEmployees()) {
                    employeeDetails.put(employee.getId(), employee);
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Error in retrieving employees by department " + departmentId, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeDetails;
    }
}