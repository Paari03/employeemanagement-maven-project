package com.i2i.employeemanagement.department.service;

import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class provides methods to interact with the Storag class. 
 * @author paari
 */
public interface DepartmentService {

    /**
     * Creates a new department.
     *
     * @param departmentName 
     *     The name of the department to be added.
     */
    void createDepartment(String departmentName) throws EmployeeException;

    /**
     * Retrieves all departments.
     *
     * @return Map<Integer, Department>
     *     A map of department IDs to Department objects.
     */
    Map<Integer, Department> getAllDepartments() throws EmployeeException;

    /**
     * Updates the details of an department.
     *
     * @param id 
     *     The ID of the department to be updated.
     * @param department
     *     The Department object containing updated details.
     */
    void updateDepartment(int departmentId, Department department) throws EmployeeException;

    /**
     * Deletes a department by ID.
     *
     * @param id 
     *     The ID of the department to be deleted.
     * @return boolean
     *     true if the department was marked as removed,else false.
     */
    boolean deleteDepartment(int departmentId) throws EmployeeException;

    /**
     * Retrieves a department by ID.
     *
     * @param id 
     *     The ID of the department to retrieve.
     * @return Department 
     *     The object with the Department ID and Name.
     */
    Department getDepartmentById(int departmentId) throws EmployeeException;

    /**
     * Checks if there is departments in the storage.
     *
     * @return boolean
     *     true if no departments are present,else false.
     */
    boolean checkDepartment() throws EmployeeException;

    /**
     * Retrieves a employee by department based on user choice.
     *
     * @param departmentId
     *     The ID of the department chosen by the user.
     * @return Map<Integer,Employee>
     *      with the employee details.
     */    
    Map<Integer,Employee> getEmployeeByDepartment(int departmentId)throws EmployeeException;
}
