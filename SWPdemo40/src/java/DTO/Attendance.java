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
public class Attendance {
    private int id;
    private Date checkinDate;
    private Time checkinTime;
    private Date checkoutDate;
    private Time checkoutTime;
    private int latetime;
    private int soontime;
    private int employeeID;
    private int shiftID;
    private boolean shiftComplete;

    public Attendance() {
    }

    public Attendance(int id, Date checkinDate, Time checkinTime, Date checkoutDate, Time checkoutTime, int latetime, int soontime, int employeeID, int shiftID, boolean shiftComplete) {
        this.id = id;
        this.checkinDate = checkinDate;
        this.checkinTime = checkinTime;
        this.checkoutDate = checkoutDate;
        this.checkoutTime = checkoutTime;
        this.latetime = latetime;
        this.soontime = soontime;
        this.employeeID = employeeID;
        this.shiftID = shiftID;
        this.shiftComplete = shiftComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Time getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Time checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Time getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Time checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public int getLatetime() {
        return latetime;
    }

    public void setLatetime(int latetime) {
        this.latetime = latetime;
    }

    public int getSoontime() {
        return soontime;
    }

    public void setSoontime(int soontime) {
        this.soontime = soontime;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public boolean isShiftComplete() {
        return shiftComplete;
    }

    public void setShiftComplete(boolean shiftComplete) {
        this.shiftComplete = shiftComplete;
    }
    
    
}
