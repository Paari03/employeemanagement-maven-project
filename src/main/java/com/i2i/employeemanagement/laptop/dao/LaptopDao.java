package com.i2i.employeemanagement.laptop.dao;

import java.util.HashMap;
import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Laptop;
import com.i2i.employeemanagement.model.Employee;


/**
 * This class provides methods to interact with the Laptop storage using PostSQL database.
 * @author Paari
 */
public interface LaptopDao {

    /**
     * This Method will update the Laptop to the database;
     * @param laptopId
     *     It is the laptop id.
     * @param laptop
     *     It contains both the id and name of the laptop.
     */
    void updateLaptop(int laptopId, Laptop laptop) throws EmployeeException;

    /**
     * This Method will get all the Laptop in the database;
     * @return Map<Integer, Laptop>
     *     id as a integer and both id and name as laptop
     */
    Map<Integer, Laptop> getAllLaptops() throws EmployeeException;

    /**
     * This Method will get the Laptop by Id.
     * @return Laptop
     *     It contains the id and name of the laptop.
     */
    Laptop getLaptopById(int laptopId) throws EmployeeException;

    /**
     * This Method will get employee by Laptop in the database;
     * @return Map<Integer, Employee>
     *     id as a integer and the employee contails all the detaails
     */
    Map<Integer, Employee> getEmployeeByLaptop(int laptopId)throws EmployeeException;
}
