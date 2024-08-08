package com.i2i.employeemanagement.employee.service;

import java.util.Map;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.employee.dao.EmployeeDaoImpl;
import com.i2i.employeemanagement.employee.dao.EmployeeDao;
import com.i2i.employeemanagement.laptop.service.LaptopService;
import com.i2i.employeemanagement.laptop.service.LaptopServiceImpl;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;
import com.i2i.employeemanagement.model.Laptop;


/**
 * This class provides methods to interact with the Storage class.
 * @author Paari
 */
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final LaptopService laptopService = new LaptopServiceImpl();

    @Override
    public int addEmployee(String name, String dob, int experience, String place, Laptop laptop, Department department) throws EmployeeException {
        return employeeDao.addEmployee(name, dob, experience, place, laptop,department);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() throws EmployeeException {
        return employeeDao.getAllEmployees();
    }

    @Override
    public boolean deleteEmployee(int deleteId) throws EmployeeException {
        return employeeDao.deleteEmployee(deleteId);
    }

    @Override
    public void updateEmployee(int updateId, Employee employee) throws EmployeeException {
        employeeDao.updateEmployee(updateId, employee);
    }

    @Override
    public boolean isEmployeeListEmpty() throws EmployeeException {
        return employeeDao.getAllEmployees().isEmpty();
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeException {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void assignCourseToEmployee(int employeeId, int courseId) throws EmployeeException {
        employeeDao.assignCourseToEmployee(employeeId, courseId);
    }

    @Override
    public void updateLaptop(int updateId,Laptop laptop) throws EmployeeException {
         laptopService.updateLaptop(updateId,laptop);
    }
}
