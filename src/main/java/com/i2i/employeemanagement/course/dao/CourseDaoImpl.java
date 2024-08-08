package com.i2i.employeemanagement.course.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.helper.SessionProvider;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Employee;

public class CourseDaoImpl implements CourseDao {

    @Override
    public void addCourse(String courseName) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course course = new Course(courseName);
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error in adding the Course: " + courseName, e);
        }
    }

    @Override
    public void updateCourse(Course course) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error updating the course " + course.getCourseName(), e);
        }
    }

    @Override
    public boolean deleteCourse(int courseId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                course.setIsDeleted(true);
                session.update(course);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error in deleting the Course with Id " + courseId, e);
        }
    }

    @Override
    public Map<Integer, Course> getAllCourses() throws EmployeeException {
        Session session = null;
        Map<Integer, Course> courseDetails = new HashMap<>();
        try {
            session = SessionProvider.getSessionFactory().openSession();
            Query<Course> query = session.createQuery("FROM Course WHERE is_deleted = false", Course.class);
            for (Course course : query.list()) {
                courseDetails.put(course.getCourseId(), course);
            }
        } catch (Exception e) {
            throw new EmployeeException("Error in retrieving all Courses. Cause: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return courseDetails;
    }

    @Override
    public Course getCourseById(int courseId) throws EmployeeException {
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            return session.get(Course.class, courseId);
        } catch (Exception e) {
            throw new EmployeeException("Error in retrieving all Courses. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<Integer, Employee> getEmployeesByCourse(int courseId) throws EmployeeException {
        Map<Integer, Employee> employees = new HashMap<>();
        try (Session session = SessionProvider.getSessionFactory().openSession()) {
            Course course = session.get(Course.class, courseId);
            if (course.getEmployees() != null) {
                for (Employee employee : course.getEmployees()) {
                    if (!employee.getIsDeleted()) {
                        employees.put(employee.getId(), employee);
                    }
                }
            }
        } catch (Exception e) {
            throw new EmployeeException("Error in retrieving Employees by Course Id" + courseId , e);
        }
        return employees;
    }
}
