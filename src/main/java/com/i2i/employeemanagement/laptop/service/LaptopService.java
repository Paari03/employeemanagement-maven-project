package com.i2i.employeemanagement.laptop.service;

import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Laptop;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class provides methods to interact with the Storage class.
 * @author paari
 */
public interface LaptopService {



    /**
     * Retrieves all Laptops.
     *
     * @return Map<Integer, Laptop>
     *     A map of Laptop IDs to Laptop objects.
     */
    Map<Integer, Laptop> getAllLaptops() throws EmployeeException;

    /**
     * Updates the details of a Laptop.
     *
     * @param laptopId
     *     The ID of the Laptop to be updated.
     * @param laptop
     *     The Laptop object containing updated details.
     */
    void updateLaptop(int laptopId, Laptop laptop) throws EmployeeException;


    /**
     * Retrieves a Laptop by ID.
     *
     * @param laptopId
     *     The ID of the laptop to retrieve.
     * @return Laptop
     *     The object with the laptop ID and Name.
     */
    Laptop getLaptopById(int laptopId) throws EmployeeException;



    /**
     * Retrieves an employee by Laptop based on user choice.
     *
     * @param laptopId
     *     The ID of the laptop chosen by the user.
     * @return Map<Integer,Employee>
     *      with the employee details.
     */
    Map<Integer,Employee> getEmployeeByLaptop(int laptopId)throws EmployeeException;
}
