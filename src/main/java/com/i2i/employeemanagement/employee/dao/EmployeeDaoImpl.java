package com.i2i.employeemanagement.employee.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.i2i.employeemanagement.model.Laptop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.helper.SessionProvider;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;

/**
 * This Class is the Implementation class for the EmployeeDao.
 * It will use Hibernate to interact with the Database.
 * Author: Paari
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public int addEmployee(String name, String dob, int experience, String place, Laptop laptop, Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = new Employee(name, dob, experience, place, laptop, department);
            session.save(employee);
            transaction.commit();
            return employee.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding employee: " + name, e);
        }
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() throws EmployeeException {
        Map<Integer, Employee> employees = new HashMap<>();

        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            String hql = "From Employee WHERE isDeleted = false";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            for (Employee employee : query.list()) {
                employees.put(employee.getId(), employee);
            }
        } catch (Exception e) {
            throw new EmployeeException("Error retrieving employees", e);
        }
        return employees;

    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeException {
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            throw new EmployeeException("Error retrieving employees", e);
        }
    }

    @Override
    public boolean deleteEmployee(int deleteId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, deleteId);
            if (employee != null) {
                employee.setIsDeleted(true);
                session.update(employee);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error deleting employee with Id: " + deleteId, e);
        }
    }



    @Override
    public void updateEmployee(int updateId, Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while updating employee: " + employee.getId(), e);
        }
    }

    @Override
    public void assignCourseToEmployee(int employeeId, int courseId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Course course = session.get(Course.class, courseId);
            Set<Course> courses = employee.getCourses();
            if (courses == null) {
                courses = new HashSet<>();
            }
            courses.add(course);
            employee.setCourses(courses);
            session.update(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error assigning course to employee with Id: " + employeeId, e);
        }
    }
}