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
public class Employee7 {
    private int EmployeeID;
    private String EmployeeName;
    private String EmployeeAvatar;
    private String Username;
    private Date Birthday;
    private String EmployeeSex;
    private String EmployeePassword;
    private String EmployeeAddress;
    private String EmployeePhone;
    private int RoleId;

    public Employee7() {
    }

    public Employee7(int EmployeeID, String EmployeeName, String EmployeeAvatar, String Username, Date Birthday, String EmployeeSex, String EmployeePassword, String EmployeeAddress, String EmployeePhone, int RoleId) {
        this.EmployeeID = EmployeeID;
        this.EmployeeName = EmployeeName;
        this.EmployeeAvatar = EmployeeAvatar;
        this.Username = Username;
        this.Birthday = Birthday;
        this.EmployeeSex = EmployeeSex;
        this.EmployeePassword = EmployeePassword;
        this.EmployeeAddress = EmployeeAddress;
        this.EmployeePhone = EmployeePhone;
        this.RoleId = RoleId;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getEmployeeAvatar() {
        return EmployeeAvatar;
    }

    public void setEmployeeAvatar(String EmployeeAvatar) {
        this.EmployeeAvatar = EmployeeAvatar;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getEmployeeSex() {
        return EmployeeSex;
    }

    public void setEmployeeSex(String EmployeeSex) {
        this.EmployeeSex = EmployeeSex;
    }

    public String getEmployeePassword() {
        return EmployeePassword;
    }

    public void setEmployeePassword(String EmployeePassword) {
        this.EmployeePassword = EmployeePassword;
    }

    public String getEmployeeAddress() {
        return EmployeeAddress;
    }

    public void setEmployeeAddress(String EmployeeAddress) {
        this.EmployeeAddress = EmployeeAddress;
    }

    public String getEmployeePhone() {
        return EmployeePhone;
    }

    public void setEmployeePhone(String EmployeePhone) {
        this.EmployeePhone = EmployeePhone;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    
    
}
