package com.i2i.employeemanagement.department.dao;

import java.util.HashMap;
import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;


/**
 * This class provides methods to interact with the department storage using PostSQL database.
 * @author Paari
 */
public interface DepartmentDao {

    /**
     * This Method will add the Department to the database;
     * @param departmentName
     *     It is the name of the department to be added.
     */
    void addDepartment(String departmentName) throws EmployeeException;  
   
    /**
     * This Method will update the Department to the database;
     * @param id
     *     It is the department id.
     * @param department
     *     It contains both the id and name of the department.
     */
    void updateDepartment(int departmentId, Department department) throws EmployeeException; 
    
    /**
     * This Method will delete the Department to the database;
     * @return boolean
     *     if deleted return true else false
     */
    boolean deleteDepartment(int departmentId) throws EmployeeException;

    /**
     * This Method will get all the Department in the database;
     * @return Map<Integer, Department>
     *     id as a integer and both id and name as department
     */
    Map<Integer, Department> getAllDepartments() throws EmployeeException; 

    /**
     * This Method will get employee by Department in the database;
     * @return Map<Integer, Employee>
     *     id as a integer and the employee contails all the detaails
     */
    Map<Integer, Employee> getEmployeeByDepartment(int departmentId)throws EmployeeException; 
}
