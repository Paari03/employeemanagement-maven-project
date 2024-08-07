package com.i2i.employeemanagement.laptop.service;

import java.util.Map;

import com.i2i.employeemanagement.laptop.dao.LaptopDao;
import com.i2i.employeemanagement.laptop.dao.LaptopDaoImpl;
import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Laptop;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class provides methods to interact with the Storag class.
 * @author paari
 */
public class LaptopServiceImpl implements LaptopService {
    private LaptopDao laptopDao = new LaptopDaoImpl();


    @Override
    public Map<Integer, Laptop> getAllLaptops() throws EmployeeException {
        return laptopDao.getAllLaptops();
    }

    @Override
    public void updateLaptop(int laptopId, Laptop laptop) throws EmployeeException {
        laptopDao.updateLaptop(laptopId, laptop);
    }

    @Override
    public Laptop getLaptopById(int laptopId) throws EmployeeException {
        return laptopDao.getLaptopById(laptopId);
    }


    @Override
    public Map<Integer,Employee> getEmployeeByLaptop(int laptopId)throws EmployeeException {
        return laptopDao.getEmployeeByLaptop(laptopId);
    }

}
