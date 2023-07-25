/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author DELL
 */
public class Payroll {

    private int employeeID;
    private String employeeName;
    private float salary;
    private float finedSalary;

    public Payroll() {
    }

    public Payroll(int employeeID, String employeeName, float salary, float finedSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.salary = salary;
        this.finedSalary = finedSalary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getFinedSalary() {
        return finedSalary;
    }

    public void setFinedSalary(float finedSalary) {
        this.finedSalary = finedSalary;
    }

}