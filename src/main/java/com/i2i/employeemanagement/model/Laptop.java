package com.i2i.employeemanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int laptopId;

    @Column(name = "laptop_name")
    private String laptopName;

    @OneToOne (mappedBy = "laptop",cascade = CascadeType.ALL)
    private Employee employees;

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employee) {
        this.employees = employee;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public int getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(int laptopId) {
        this.laptopId = laptopId;
    }

    public Laptop() {
    }

    public Laptop(String laptopName) {
        this.laptopName = laptopName;
        this.employees = employees;
    }

}
