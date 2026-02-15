package com.jyothiyanapu.csms.model;

import com.jyothiyanapu.csms.annotations.Column;
import com.jyothiyanapu.csms.annotations.Entity;
import com.jyothiyanapu.csms.annotations.Table;

@Entity
@Table(name = "civil_servant")
public class CivilServant {

    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary")
    private double salary;

    public CivilServant() {
    }

    public CivilServant(int id, String name, String department, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CivilServant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                '}';
    }
}
