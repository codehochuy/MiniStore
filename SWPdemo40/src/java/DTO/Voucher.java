/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class Voucher {
    private int voucherID;
    private String voucherName;
    private Date dateStart;
    private Date dateEnd;
    private boolean voucherStatus;
    private int condition;
    private int quantity;
    private int value;
    private int employeeID;
    private String employeeName;

    public Voucher() {
    }

    public Voucher(int voucherID, String voucherName, Date dateStart, Date dateEnd, boolean voucherStatus, int condition, int quantity, int value, int employeeID, String employeeName) {
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.voucherStatus = voucherStatus;
        this.condition = condition;
        this.quantity = quantity;
        this.value = value;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(boolean voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    

    
}
