package com.jyothiyanapu.csms;
// model class
public class CivilServant {
    private int id;
    private String name;
    private String department;
    private String designation;
    private double salary;


    // having no-arg constructor is also better
    public CivilServant() {
    }

    public CivilServant(int id, String name, String department, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
  //In order to print the objects this is useful.
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
