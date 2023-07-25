/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class Application {

    private int leaveShift;
    private String leaveReason;
    private boolean applicationStatus;
    private int employeeID;
    private String employeeName;
    private int isApproved;

    public Application() {
    }

    public Application(int leaveShift, String leaveReason, boolean applicationStatus, int employeeID, String employeeName, int isApproved) {
        this.leaveShift = leaveShift;
        this.leaveReason = leaveReason;
        this.applicationStatus = applicationStatus;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.isApproved = isApproved;
    }

    public int getLeaveShift() {
        return leaveShift;
    }

    public void setLeaveShift(int leaveShift) {
        this.leaveShift = leaveShift;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public boolean isApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
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

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

}