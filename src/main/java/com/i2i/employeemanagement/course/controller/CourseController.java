package com.i2i.employeemanagement.course.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Map;
import java.util.Scanner;

import com.i2i.employeemanagement.course.service.CourseService;
import com.i2i.employeemanagement.course.service.CourseServiceImpl;
import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Course;
import com.i2i.employeemanagement.model.Employee;
import com.i2i.employeemanagement.util.EmployeeValidator;


/**
 * This class manages all the input and output functions with CRUD operations.
 * @author Paari
 */
public class CourseController {
    private CourseService courseService = new CourseServiceImpl();
    private EmployeeValidator validation = new EmployeeValidator();
    private static Logger logger = LogManager.getLogger();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Display the functions in the course.
     */
    public void courseFunction() {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("Choose a Function - "
                + "1-Add a Course\t"
                + "2-Display all Courses\t"
                + "3-Update Course\t"
                + "4-Delete Course\t"
                + "5-View Employees by Course\t"
                + "6-Exit");
            int option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        updateCourse();
                        break;
                    case 4:
                        deleteCourse();
                        break;
                    case 5:
                        viewEmployeeByCourse();
                        break;
                    case 6:
                        isTrue = false;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        System.out.println("=============================================");
                }
            } catch (EmployeeException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * Method to add a new course based on user input.
     */
    public void addCourse() throws EmployeeException {
        String courseName;
        do {
            System.out.println("Enter Course Name: ");
            courseName = scanner.nextLine();
        } while (validation.stringValidator(courseName));
        courseService.createCourse(courseName);
        logger.info("Course added successfully!");
    }

    /**
     * Method to update an existing course based on user input.
     */
    public void updateCourse() throws EmployeeException {
        viewAllCourses();
        System.out.println("Enter the Course ID you want to update:");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            System.out.println("Enter the new Course Name:");
            String courseName = scanner.nextLine();
            course.setCourseName(courseName);
            courseService.updateCourse(course);
            logger.info("Course updated successfully.");
        } else {
            System.out.println("Course ID not found. Please try again.");
        }
    }

    /**
     * Method to delete a course based on user input.
     */
    public void deleteCourse() throws EmployeeException {
        Map<Integer, Course> courseMap = courseService.getAllCourses();
        if (courseMap.isEmpty()) {
            System.out.println("No courses available. Please add a course first.");
        } else {
            this.viewAllCourses();
            System.out.println("Enter the Course ID you want to delete:");
            int courseId = scanner.nextInt();
            scanner.nextLine();
            if (courseService.deleteCourse(courseId)) {
                logger.info("Course deleted successfully.");
            } else {
                System.out.println("Course ID not found. Please try again.");
            }
        }
    }

    /**
     * Method to display all the courses.
     */
    public void viewAllCourses() throws EmployeeException {
        Map<Integer, Course> courses = courseService.getAllCourses();
        if (!courses.isEmpty()) {
            System.out.println("List of Courses:");
            String format = "| %-15s | %-20s |\n";
            System.out.format(format, "ID", "CourseName");
            for (Course course : courses.values()) {
                System.out.format(format, course.getCourseId(), course.getCourseName());
            }
        } else {
            logger.info("No courses found.");
        }
    }

    /**
     * Method to check if the course is empty.
     *
     * @return boolean
     *     true if the courses are empty, else false.
     */
    public boolean isCourseEmpty() throws EmployeeException {
        Map<Integer, Course> courseMap = courseService.getAllCourses();
        return courseMap.isEmpty();
    }

    /**
     * Method to display employees enrolled in a specific course based on user input.
     */
    public void viewEmployeeByCourse() throws EmployeeException {
        viewAllCourses();
        System.out.println("Enter the Course ID you want to view employees for:");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        Map<Integer, Employee> employees = courseService.getEmployeesByCourse(courseId);
        if (employees != null) {
            String format = "| %-15s | %-20s | %-20s |\n";
            System.out.format(format, "EmployeeId", "EmployeeName", "Course");
            for (Employee employee : employees.values()) {
                System.out.format(format, employee.getId(), employee.getName(), courseService.getCourseById(courseId).getCourseName());
            }
        } else {
            logger.info("No employees found for course.");
        }
    }
}
 