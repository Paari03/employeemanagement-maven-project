package com.i2i.employeemanagement.course.service;

import java.util.Map;

import com.i2i.employeemanagement.course.dao.CourseDao;
import com.i2i.employeemanagement.course.dao.CourseDaoImpl;
import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Employee;


/**
 * This class provides methods to interact with the Storage class.
 * @author paari
 */
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public void createCourse(String courseName) throws EmployeeException {
        courseDao.addCourse(courseName);
    }

    @Override
    public Map<Integer, Course> getAllCourses() throws EmployeeException {
        return courseDao.getAllCourses();
    }

    @Override
    public Course getCourseById(int courseId) throws EmployeeException {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public void updateCourse(Course course) throws EmployeeException  {
        courseDao.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int courseId) throws EmployeeException  {
        return courseDao.deleteCourse(courseId);
    }

    @Override
    public Map<Integer, Employee> getEmployeesByCourse(int courseId) throws EmployeeException {
       return courseDao.getEmployeesByCourse(courseId);
    }
}
