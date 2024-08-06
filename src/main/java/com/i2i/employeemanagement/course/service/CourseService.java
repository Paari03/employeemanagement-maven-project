package com.i2i.employeemanagement.course.service;

import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Employee;


/**
 * This class provides methods to interact with the Storag class. 
 * @author paari
 */
public interface CourseService {

    /**
     * Creates a new course.
     *
     * @param courseName 
     *     The name of the course to be added.
     */
    void createCourse(String courseName) throws EmployeeException; 

    /**
     * Retrieves all courses from the Storage.
     *
     * @return Map<Integer, Course>
     *     A map with course ID as key and Course object as value.
     */
    Map<Integer, Course> getAllCourses() throws EmployeeException;

    /**
     * Retrieves a course by its ID.
     *
     * @param id
     *     The ID of the course to be retrieved.
     * @return Course
     *     Course with the particular ID.
     */
    Course getCourseById(int courseId) throws EmployeeException;

    /**
     * Updates the details of an existing course.
     *
     * @param id 
     *     The ID of the course to be updated.
     * @param course 
     *     The updated Course with new details.
     */
    void updateCourse(Course course) throws EmployeeException;

    /**
     * Deletes a course by its ID.
     *
     * @param id 
     *     The ID of the course to be deleted.
     * @return boolean
     *     True if the course was removed else false.
     */
    boolean deleteCourse(int id) throws EmployeeException;
    
    /**
     * Retrieves a course based on the user's choice.
     *
     * @param courseChoice
     *     The ID of the course to be retrieved.
     * @return Course
     *     Course with the particulaer ID.
     */
    Map<Integer, Employee> getEmployeesByCourse(int couseId) throws EmployeeException;
}
