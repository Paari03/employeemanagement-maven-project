package com.i2i.employeemanagement.employee.controller;


import com.i2i.employeemanagement.employee.service.EmployeeService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Map;

import com.i2i.employeemanagement.course.service.CourseService;
import com.i2i.employeemanagement.course.service.CourseServiceImpl;
import com.i2i.employeemanagement.department.service.DepartmentService;
import com.i2i.employeemanagement.department.service.DepartmentServiceImpl;
import com.i2i.employeemanagement.model.Laptop;
import com.i2i.employeemanagement.employee.service.EmployeeServiceImpl;
import com.i2i.employeemanagement.model.Employee;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Department;
import com.i2i.employeemanagement.util.EmployeeValidator;
import com.i2i.employeemanagement.exception.EmployeeException;

/**
 * This class manages all the input and output functions with CRUD operations.
 * @author Paari
 */
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final EmployeeValidator validation = new EmployeeValidator();
    DepartmentService departmentService = new DepartmentServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);

    public void employeeFunction() {
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Choose a Function - "
                    + "1-Add an Employee\t"
                    + "2-Delete an Employee\t"
                    + "3-Update an Employee\t"
                    + "4-Display one Employee\t"
                    + "5-Display all Employees\t"
                    + "6-Exit");
            int option = scanner.nextInt();
            try {
                switch (option) {
                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        deleteEmployee();
                        break;

                    case 3:
                        updateEmployee();
                        break;


                    case 4:
                        displayOneEmployee();
                        break;

                    case 5:
                        displayAllEmployees();
                        break;

                    case 6:
                        isTrue = false;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (EmployeeException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * This Method is to add employees to the database.
     *  
     */
    public void addEmployee() throws EmployeeException {
        scanner.nextLine();
        String name;
        do {
            System.out.println("Enter Name (Max - 10 Characters): ");
            name = scanner.nextLine();
        } while (validation.stringValidator(name));

        String dob = "";
        boolean correctDob;
        do {
            try {
                System.out.println("Enter Date of Birth (yyyy-MM-dd): ");
                dob = scanner.nextLine();
                correctDob = validation.dobValidator(dob);
            } catch (DateTimeParseException e) {
                logger.error(" Please enter a valid date of birth{}", e.getMessage());
                correctDob = false;
            }
        } while (!correctDob);

        int experience;
        do {
            System.out.println("Enter Experience: ");
            experience = scanner.nextInt();
        } while (validation.experienceValidator(experience));
        scanner.nextLine();
 
        String place;
        do {
            System.out.println("Enter Place: ");
            place = scanner.nextLine();
        } while (validation.stringValidator(place));

        String laptopName;
        do {
            System.out.println("Enter Laptop: ");
            laptopName = scanner.nextLine();
        } while (validation.stringValidator(laptopName));
        Laptop laptop = new Laptop(laptopName);
        Department department = assignDepartment();

        /*
         All the details will be added to the database and will return the id
         generated in the database for assigning the course for the employee
         */
        int employeeId = employeeService.addEmployee(name, dob, 
                         experience, place,laptop, department);
        boolean addCourse = true;
        while (addCourse) {
            int courseId = assignCourse();
            courseService.getCourseById(courseId);
            employeeService.assignCourseToEmployee(employeeId, courseId);
            System.out.println("Do you want to add more courses? 1-Yes \t 0-No");
            if (scanner.nextInt() == 0) {
                addCourse = false;
            }
        }
    }

    /**
     * This Method is to delete employees in the database.
     *  
     */
    public void deleteEmployee() throws EmployeeException {
        if (employeeService.isEmployeeListEmpty()) {
            logger.info("There are no Employee Details");
        } else {
            displayAllEmployees();
            System.out.println("Enter the Employee ID to Delete: ");
            int deleteId = scanner.nextInt();

            if (employeeService.deleteEmployee(deleteId)) {
                logger.info("Employee ID removed successfully");
                System.out.println("=========================================");
            } else {
                logger.info("Employee ID Not Found");
                System.out.println("========================================");
            }
        }
    }
 
    /**
     * This Method is to update employees to the database.
     *  
     */
    public void updateEmployee() throws EmployeeException {
        if (employeeService.isEmployeeListEmpty()) {
            logger.info("There are no Employee Details.");
            System.out.println("=============================================");
        } else {
            displayAllEmployees();
            System.out.println("Enter the ID of the Employee you want to update: ");
            int updateId = scanner.nextInt();
            scanner.nextLine();
            Employee employee = employeeService.getEmployeeById(updateId);

            if (employee != null) {
                System.out.println("Choose the update Field : 1-Name  2-Dob"
                                   + "3-Experience  4-Laptop 5-Department"
                                   + "6-Place  7-Courses");
                int updateField = scanner.nextInt();
                scanner.nextLine();

                switch (updateField) {
                    case 1:
                        String updatedName;
                        do {
                            System.out.println("Enter updated Name (Max - 10 Characters): ");
                            updatedName = scanner.nextLine();
                        } while (validation.stringValidator(updatedName));
                        employee.setName(updatedName);
                        break;

                    case 2:
                        String dob = "";
                        boolean correctDob;
                        do {
                            try {
                                System.out.println("Enter Date of Birth (yyyy-MM-dd): ");
                                dob = scanner.nextLine();
                                correctDob = validation.dobValidator(dob);
                            } catch (DateTimeParseException e) {
                                logger.error("Please enter a valid date of birth{}", e.getMessage() );
                                correctDob = false;
                            }
                        } while (!correctDob);
                        employee.setDob(dob);
                        break;

                    case 3:
                        int updatedExperience;
                        do {
                            System.out.println("Enter Experience: ");
                            updatedExperience = scanner.nextInt();
                        } while (validation.experienceValidator(updatedExperience));
                        employee.setExperience(updatedExperience);
                        break;

                    case 4:
                        String laptopUpdatedName;
                        do {
                            System.out.println("Enter the Updated Laptop: ");
                            laptopUpdatedName = scanner.nextLine();
                        } while (validation.stringValidator(laptopUpdatedName));
                        Laptop laptop = new Laptop(laptopUpdatedName);
                        employeeService.updateLaptop(updateId,laptop);
                        break;

                    case 5:
                        System.out.println("Enter the Updated Department: ");
                        Department updatedDepartment = assignDepartment();
                        employee.setDepartment(updatedDepartment);
                        break;

                    case 6:
                        String updatedPlace;
                        do {
                            System.out.println("Enter Place: ");
                            updatedPlace = scanner.nextLine();
                        } while (validation.stringValidator(updatedPlace));
                        employee.setPlace(updatedPlace);
                        break;

                    case 7:
                        boolean updateCourse = true;
                        while (updateCourse) {
                            int updatedCourseId = assignCourse();
                            employeeService.assignCourseToEmployee(updateId,
                                                              updatedCourseId);
                            System.out.println("Do you want to add more courses?"
                                               + " 1-Yes \t 0-No");
                            if (scanner.nextInt() == 0) {
                                updateCourse = false;
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid Field");
                        System.out.println("=============================================");
                        return;
                }

                employeeService.updateEmployee(updateId, employee);
                logger.info("Employee updated successfully");
                System.out.println("=============================================");
            } else {
                logger.info("Employee ID not found.");
            }
        }
    }

    /**
     * This Method is to assign department to the employees.
     * @return Department
     *     It will contain the id and name of the department.
     */
    public Department assignDepartment() throws EmployeeException {
        displayDepartments();
        System.out.println("Enter your Choice: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        return departmentService.getDepartmentById(departmentId);
    }

    /**
     * This Method is to assign course to the employees.
     * @return Integer
     *     It will contain the id of the course which was chosen by the employee.
     */
    public int assignCourse() throws EmployeeException {
        displayCourses();
        System.out.println("Enter your Choice: ");
        int courseChoice = scanner.nextInt();
        return courseService.getCourseById(courseChoice).getCourseId();
    }

    /**
     * This method will display one employee the Database.
     * According to the id given by the user.
     */
    public void displayOneEmployee() throws EmployeeException {
        try {
            System.out.println("Enter the id of the employee to be displayed");
            int employeeId = scanner.nextInt();
            Employee employee = employeeService.getEmployeeById(employeeId);
            System.out.println("--------------------------------------"
                    + "------------------------------------"
                    + "----------------------------------"
                    + "-------------------------------");
            String employeeFormat = "| %-10s | %-15s | %-15s | %-15s | %-15s | "
                    + "%-15s | %-15s |%-15s |\n";
            System.out.format(employeeFormat, "ID", "Name", "Age", "Experience",
                    "Place", "Laptop", "Department", "Course");
            StringBuilder courseList = new StringBuilder();
            for (Course course : employee.getCourses()) {
                courseList.append(course.getCourseName()).append(", ");
            }
            String course = courseList.toString();
            System.out.format(employeeFormat, employee.getId(),
                    employee.getName(), employee.getAge(),
                    employee.getExperience(), employee.getPlace(),
                    employee.getLaptop().getLaptopName(),
                    employee.getDepartment().getDepartmentName(),
                    course);
            System.out.println("--------------------------------------"
                    + "------------------------------------"
                    + "---------------------------------"
                    + "--------------------------------");
        } catch ( Exception e) {
            logger.info("Enter the correct output");
            scanner.next();
            displayOneEmployee();
        }
        
    }

    /**
     * This Method is to display all the employee details.
     */
    public void displayAllEmployees() throws EmployeeException {
    
        Map<Integer, Employee> employees = employeeService.getAllEmployees();
        System.out.println("--------------------------------------"
                           + "------------------------------------"
                           + "-----------------------------------"
                           + "-------------------------------");
        String employeeFormat = "| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n";
        System.out.format(employeeFormat, "ID", "Name", "Age", "Experience",
                          "Place","Laptop","Department","Courses");
 
        if (employees.isEmpty()) {
            logger.info("No Employee Details to Display");
        } else {
            for (Employee employee : employees.values()) {
                StringBuilder courseList = new StringBuilder();
                for(Course course : employee.getCourses()){
                    courseList.append(course.getCourseName()).append(", ");
                }
                String course = courseList.toString();                   
                System.out.format(employeeFormat, employee.getId(),
                            employee.getName(), employee.getAge(),
                            employee.getExperience(), employee.getPlace(),
                            employee.getLaptop().getLaptopName(),
                            employee.getDepartment().getDepartmentName(),
                            course);                    
                
            }
            System.out.println("--------------------------------------"
                               + "-------------------------------------"
                               + "----------------------------------"
                               + "-------------------------------");
        }
    }

    /**
     * This Method is to display available department for the employee to choose.
     */
    public void displayDepartments() throws EmployeeException {
        Map<Integer, Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            logger.info("No Departments to Display");
        } else {
            System.out.println("List of Departments:");
            System.out.println("-------------------------");
            String format = "| %-10s | %-15s |\n";
            System.out.format(format, "ID", "Department Name");

            for (Department department : departments.values()) {
                System.out.format(format, department.getDepartmentId(), department.getDepartmentName());
            }
            System.out.println("-------------------------");
        }
    }

    /**
     * This Method is to display available course for the employee to choose.
     */
    public void displayCourses() throws EmployeeException {
        CourseService courseService = new CourseServiceImpl();
        Map<Integer, Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            logger.info("No Courses to Display");
        } else {
            System.out.println("List of Courses:");
            System.out.println("--------------------------------------------------------");
            String format = "| %-10s | %-15s |\n";
            System.out.format(format, "ID", "Course Name");

            for (Course course : courses.values()) {
                System.out.format(format, course.getCourseId(), course.getCourseName());
            }
            System.out.println("--------------------------------------------------------");
        }
    }
}
