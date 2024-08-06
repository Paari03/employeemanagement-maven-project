package com.i2i.employeemanagement.department.service;

import java.util.Map;

import com.i2i.employeemanagement.department.dao.DepartmentDao;
import com.i2i.employeemanagement.department.dao.DepartmentDaoImpl;
import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;

/**
 * This class provides methods to interact with the Storag class. 
 * @author paari
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void createDepartment(String departmentName) throws EmployeeException {
        departmentDao.addDepartment(departmentName);
    }

    @Override
    public Map<Integer, Department> getAllDepartments() throws EmployeeException {
        return departmentDao.getAllDepartments();
    }

    @Override
    public void updateDepartment(int departmentId, Department department) throws EmployeeException {
        departmentDao.updateDepartment(departmentId, department);
    }

    @Override
    public boolean deleteDepartment(int departmentId) throws EmployeeException {
        return departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public Department getDepartmentById(int departmentId) throws EmployeeException {
        return getAllDepartments().get(departmentId);
    }

    @Override
    public boolean checkDepartment() throws EmployeeException {
        return getAllDepartments().isEmpty();
    }

    @Override
    public Map<Integer,Employee> getEmployeeByDepartment(int departmentId)throws EmployeeException {
        return departmentDao.getEmployeeByDepartment(departmentId);
    }

}
