package com.i2i.employeemanagement.exception;

public class EmployeeException extends Exception {
  
    public EmployeeException(String errorMessage, Throwable error) {  
        super(errorMessage,error);  
    }  

}