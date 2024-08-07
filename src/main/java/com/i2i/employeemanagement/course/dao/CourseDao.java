package com.i2i.employeemanagement.course.dao;


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
     * @param courseName The name of the course.
     */
    void addCourse(String courseName) throws EmployeeException;

    /**
     * Update the course name  in the database
     *
     * @param course
     *     The Updated name of the course. 
     */
    void updateCourse(Course course) throws EmployeeException; 

    /**
     * Delete the course in the database
     *
     * @param courseId 
     *    The ID of the course to be deleted.
     */
    boolean deleteCourse(int courseId) throws EmployeeException; 

    /**
     * Retrieve all the course in the database
     *
     * @return Map<Integer, Course>
     *     The course ID as the key and the Course object as the value
     */
    Map<Integer, Course> getAllCourses() throws EmployeeException; 
    
    /**
     * Retrieve the single course from the course.
     * @return Course
     *     It contains the course ID and Name.
     */
    Course getCourseById(int courseId) throws EmployeeException;

    /**
     * Retrieves  all Employees in a specific course.
     * @param courseId
     *     The ID of the course to be fetched.
     * @return  Map<Integer, Employee>
     *     The map of employee IDs and Employee objects.
     */
    Map<Integer, Employee> getEmployeesByCourse(int courseId) throws EmployeeException; 
}