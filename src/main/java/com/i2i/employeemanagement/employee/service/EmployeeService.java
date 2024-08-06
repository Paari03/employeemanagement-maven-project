package com.i2i.employeemanagement.employee.service;

import java.util.Map;

import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;
import com.i2i.employeemanagement.exception.EmployeeException;

/**
 * This class provides methods to interact with the Storage class.
 */
public interface EmployeeService {

    /**
     * Creates a new employee with the given details and returns the generated employee ID.
     *
     * @param name
     *     The name of the employee to be added.
     * @param dob
     *     The dob of the employee to be added.
     * @param experience
     *     The experience of the employee to be added.
     * @param place
     *     The place of the employee to be added.
     * @param department
     *     The department of the employee to be added.
     *
     * @return int
     *     The generated ID of the newly added employee.
     */
    int addEmployee(String name, String dob, int experience, String place, Department department) throws EmployeeException;

    /**
     * Retrieves all employees from the storage.
     *
     * @return Map<Integer, Employee>
     *     A map with all employee Details.
     *
     * @throws EmployeeException
     *     when Employee is not found
     */
    Map<Integer, Employee> getAllEmployees() throws EmployeeException;

    /**
     * Deletes an employee from the storage based on the given id.
     *
     * @param id
     *     The id of the employee to delete.
     *
     * @return boolean
     *     True if the employee was successfully deleted, else false.
     */
    boolean deleteEmployee(int deleteId) throws EmployeeException;

    /**
     * Updates the details of an employee in the storage.
     *
     * @param id
     *     The id of the employee to update.
     * @param employee
     *     The updated employee object with new details.
     */
    void updateEmployee(int updateId, Employee employee) throws EmployeeException;

    /**
     * Checks if the employees is empty.
     *
     * @return boolean
     *     True if there are no employees in the storage, else false.
     */
    boolean isEmployeeListEmpty() throws EmployeeException;

    /**
     * Retrieves an employee by their id.
     *
     * @param id
     *     The id of the employee to retrieve.
     *
     * @return Employee
     *     The employee object to the given id.
     */
    Employee getEmployee(int id) throws EmployeeException;

    /**
     * Retrieves all departments available in the storage.
     *
     * @return Map<Integer, Department>
     *     A map containing all departments keyed by their ids.
     */
    Map<Integer, Department> getAllDepartments() throws EmployeeException;

    /**
     * Retrieves all courses available in the storage.
     *
     * @return Map<Integer, Course>
     *     A map containing all courses by their ids.
     */
    Map<Integer, Course> getAllCourses() throws EmployeeException;

    /**
     * Assigns a department based on user choice.
     *
     * @param departmentChoice
     *     The choice of department chosen by the user.
     *
     * @return Department
     *     The department object to the user's choice.
     */
    Department getDepartmentById(int departmentChoice) throws EmployeeException;

    /**
     * Assigns a course based on user choice.
     *
     * @param courseChoice
     *     The choice of course chosen by the user.
     *
     * @return Course
     *     The course object to the user's choice.
     */
    Course getCourseById(int courseChoice) throws EmployeeException;

    /**
     * Assigns a course to an employee.
     *
     * @param employeeId
     *     The ID of the employee.
     * @param courseId
     *     The ID of the course to assign.
     */
    void assignCourseToEmployee(int employeeId, int courseId) throws EmployeeException;
}
