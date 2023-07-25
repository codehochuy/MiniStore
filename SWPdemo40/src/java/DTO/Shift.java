/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class Shift {
    private int id;
    private String name;
    private int employeeRole;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    private double coef;
    private Integer employeeFirst;
    private Integer employeeSecond;

    public Shift() {
    }

    public Shift(int id, String name, int employeeRole, Time startTime, Time endTime, Date startDate, Date endDate, double coef, Integer employeeFirst, Integer employeeSecond) {
        this.id = id;
        this.name = name;
        this.employeeRole = employeeRole;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coef = coef;
        this.employeeFirst = employeeFirst;
        this.employeeSecond = employeeSecond;
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

    public int getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(int employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public Integer getEmployeeFirst() {
        return employeeFirst;
    }

    public void setEmployeeFirst(Integer employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    public Integer getEmployeeSecond() {
        return employeeSecond;
    }

    public void setEmployeeSecond(Integer employeeSecond) {
        this.employeeSecond = employeeSecond;
    }
    
    
}
