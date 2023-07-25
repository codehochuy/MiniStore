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
public class Order7 {
    private int orderID;
    private String customerName;
    private String phone;
    private int voucherID;
    private String voucherName;
    private Date orderDate;
    private String employeeName;
    

    public Order7() {
    }

    public Order7(int orderID, String customerName, String phone, int voucherID, String voucherName, Date orderDate, String employeeName) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.phone = phone;
        this.voucherID = voucherID;
        this.voucherName = voucherName;
        this.orderDate = orderDate;
        this.employeeName = employeeName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    

    
    
}
