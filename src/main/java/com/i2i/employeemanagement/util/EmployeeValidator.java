package com.i2i.employeemanagement.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate; 

/**
 * This class  is the validation class which will validate the fields given. 
 * @author paari
 */
public class EmployeeValidator{

    /**
     * Method to validate the input string.
     * @return boolean
     */
    public boolean stringValidator(String input) {
        for (char c : input.toCharArray()) {
            int lengthOfString = input.length();

            if (!Character.isLetter(c) || lengthOfString > 10) {
                System.out.println("Enter Correct Input");
                return true;
            } 
        }
        return false;
    }

    /**
     * Method to validate the Date of birth.
     * @return boolean
     */
    public boolean dobValidator(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(dob,formatter);
        return true;        
    }

    /**
     * Method to validate the experience
     * @return boolean
     */
    public boolean experienceValidator(int experience) {
        if (experience < 50 && experience >= 0) {
            return true; 
        }
        return false;
    }
} 

