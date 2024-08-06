package com.i2i.employeemanagement.employee.dao;


import java.util.HashMap;
import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class provides methods to interact with the database for employee-related operations.
 * Author: Paari
 */
public interface EmployeeDao {
     
    /**
     * Adds a new employee to the database.
     *
     * @param name The name of the employee to be added.
     * @param dob The date of birth of the employee to be added.
     * @param experience The experience of the employee to be added.
     * @param place The place of the employee to be added.
     * @param department The department of the employee to be added.
     */
    int addEmployee(String name, String dob, int experience, String place, Department department) throws EmployeeException;

    /**
     * Retrieves all employees from the database.
     *
     * @return Map<Integer, Employee> A map with all employee details.
     */
    Map<Integer, Employee> getAllEmployees() throws EmployeeException;

    /**
     * Deletes an employee from the database based on the given ID.
     *
     * @param id The ID of the employee to delete.
     * @return boolean True if the employee was successfully deleted, else false.
     */
    boolean deleteEmployee(int deleteId) throws EmployeeException;

    /**
     * Updates the details of an employee in the database.
     *
     * @param id The ID of the employee to update.
     * @param employee The updated employee object with new details.
     */
    void updateEmployee(int id, Employee employee) throws EmployeeException;

    /**
     * Assigns a course to an employee in the database.
     *
     * @param employeeId The ID of the employee.
     * @param courseId The ID of the course to assign.
     */
    void assignCourseToEmployee(int employeeId, int courseId) throws EmployeeException; 
}