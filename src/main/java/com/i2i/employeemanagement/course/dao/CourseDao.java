package com.i2i.employeemanagement.course.dao;

import java.util.HashMap;
import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class is the interface for the database access class.
 * And contains a method to interact with the course database.
 * @author Paari
 */
public interface CourseDao {

    /**
     * Add the course to the database
     *
     * @param course The name of the course. 
     */
    void addCourse(String courseName) throws EmployeeException;

    /**
     * Update the course name  in the database
     *
     * @param courseId
     *     The Id of th eCourse to be changed.
     * @param course
     *     The Updated name of the course. 
     */
    void updateCourse(Course course) throws EmployeeException; 

    /**
     * Delete the course in the database
     *
     * @param courseId 
     *    The Id of the course to be deleted. 
     */
    boolean deleteCourse(int courseId) throws EmployeeException; 

    /**
     * Retrieve all the course in the database
     *
     * @return Map<Integer, Course>
     *     The course Id as the key and the Course object as the value 
     */
    Map<Integer, Course> getAllCourses() throws EmployeeException; 

    /**
     * Retrieves  all Employees in a specific course.
     * @param courseId
     *     The ID of the course to be fetched.
     * @return  Map<Integer, Employee>
     *     The map of employee IDs and Employee objects.
     */
    Map<Integer, Employee> getEmployeesByCourse(int courseId) throws EmployeeException; 
}