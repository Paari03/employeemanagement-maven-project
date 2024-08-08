package com.i2i.employeemanagement.laptop.controller;

import com.i2i.employeemanagement.model.Laptop;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Map;
import java.util.Scanner;

import com.i2i.employeemanagement.exception.EmployeeException;
import com.i2i.employeemanagement.model.Employee;
import com.i2i.employeemanagement.laptop.service.LaptopService;
import com.i2i.employeemanagement.laptop.service.LaptopServiceImpl;

/**
 * This class  manage all the input and output function with CRUD operations.
 * @author paari
 */
public class LaptopController {
    private final LaptopService laptopService = new LaptopServiceImpl();
    private static final Logger logger = LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Display the operation in the Laptop.
     */
    public void laptopFunction() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Choose an option: "
                    + "1 - View All Laptops\t"
                    + "2 - Update a Laptop\t"
                    + "3 - Display Employees by Laptop\t"
                    + "4 - Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {

                    case 1:
                        displayLaptops();
                        break;
                    case 2:
                        updateLaptop();
                        break;

                    case 3:
                        viewEmployeesByLaptop();
                        break;

                    case 4:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid Choice. Please try again.");
                        break;
                }
            } catch (EmployeeException e){
                logger.error(e.getMessage());
            }
        }
    }



    /**
     * Displays all the Laptops .
     */
    public void displayLaptops() throws EmployeeException {
        Map<Integer, Laptop> laptopMap = laptopService.getAllLaptops();
        String format = "| %-15s | %-15s |\n";
        System.out.format(format, "Laptop ID", "Laptop Name");

        if (!laptopMap.isEmpty()) {
            for (Laptop laptop : laptopMap.values()) {
                    System.out.format(format, laptop.getLaptopId(),
                            laptop.getLaptopName());
            }
        } else {
            logger.error("No Laptops available.");
        }
    }

    /**
     * This Method will update the Laptop based on the user input.
     */
    public void updateLaptop() throws EmployeeException {
        displayLaptops();
        System.out.println("Enter the Laptop ID you want to update:");
        int laptopId = scanner.nextInt();
        scanner.nextLine();

        Laptop laptop = laptopService.getLaptopById(laptopId);
        if (laptop != null) {
            System.out.println("Enter the new Laptop Name:");
            String laptopName = scanner.nextLine();
            laptop.setLaptopName(laptopName);
            laptopService.updateLaptop(laptopId, laptop);
            logger.info("Laptop updated successfully.");
        } else {
            logger.error("Laptop ID not found. Please try again.");
        }
    }


    /**
     * Displays employees by Laptop.
     */
    public void viewEmployeesByLaptop() throws EmployeeException {
        displayLaptops();
        System.out.println("Enter the Laptop ID you want to view:");
        int laptopId = scanner.nextInt();
        scanner.nextLine();

        Map<Integer, Employee> employees = laptopService.getEmployeeByLaptop(laptopId);

        String format = "| %-15s | %-20s | %-15s | \n";
        System.out.format(format, "Employee ID", "Employee Name",
                "Place");

        if (!employees.isEmpty()) {
            for (Employee employee : employees.values()) {
                System.out.format(format, employee.getId(),employee.getName(),
                        employee.getPlace());
            }
        } else {
            logger.info("No employees found in this Laptop.");
        }
    }
}
