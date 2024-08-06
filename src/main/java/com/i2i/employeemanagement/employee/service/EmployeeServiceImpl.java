package com.i2i.employeemanagement.employee.service;

import java.util.Map;

import com.i2i.employeemanagement.course.service.CourseService;
import com.i2i.employeemanagement.course.service.CourseServiceImpl;
import com.i2i.employeemanagement.department.service.DepartmentService;
import com.i2i.employeemanagement.department.service.DepartmentServiceImpl;
import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.employee.dao.EmployeeDaoImpl;
import com.i2i.employeemanagement.employee.dao.EmployeeDao;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.model.Employee;


/**
 * This class provides methods to interact with the Storage class.
 * @author Paari
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private CourseService courseService = new CourseServiceImpl();

    @Override
    public int addEmployee(String name, String dob, int experience, String place, Department department) throws EmployeeException {
        return employeeDao.addEmployee(name, dob, experience, place, department);
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
    public Employee getEmployee(int id) throws EmployeeException {
        return employeeDao.getAllEmployees().get(id);
    }

    @Override
    public Map<Integer, Department> getAllDepartments() throws EmployeeException {
        return departmentService.getAllDepartments();
    }

    @Override
    public Map<Integer, Course> getAllCourses() throws EmployeeException {
        return courseService.getAllCourses();
    }

    @Override
    public Department getDepartmentById(int departmentChoice) throws EmployeeException {
        return getAllDepartments().get(departmentChoice);
    }

    @Override
    public Course getCourseById(int courseChoice) throws EmployeeException {
        return getAllCourses().get(courseChoice);
    }

    @Override
    public void assignCourseToEmployee(int employeeId, int courseId) throws EmployeeException {
        employeeDao.assignCourseToEmployee(employeeId, courseId);
    }
}
