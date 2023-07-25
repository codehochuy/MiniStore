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
public class Employee {

    private int employeeID;
    private String employeename;
    private String employeeavatar;
    private String username;
    private Date birthday;
    private int employeesex;
    private String employeepassword;
    private String employeeaddress;
    private String employeephone;
    private Role emprole;
    private boolean employeestatus;

    public Employee() {
    }

    public Employee(int employeeID, String employeename, String employeeavatar, String username, Date birthday, int employeesex, String employeepassword, String employeeaddress, String employeephone, Role emprole, boolean employeestatus) {
        this.employeeID = employeeID;
        this.employeename = employeename;
        this.employeeavatar = employeeavatar;
        this.username = username;
        this.birthday = birthday;
        this.employeesex = employeesex;
        this.employeepassword = employeepassword;
        this.employeeaddress = employeeaddress;
        this.employeephone = employeephone;
        this.emprole = emprole;
        this.employeestatus = employeestatus;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeeavatar() {
        return employeeavatar;
    }

    public void setEmployeeavatar(String employeeavatar) {
        this.employeeavatar = employeeavatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getEmployeesex() {
        return employeesex;
    }

    public void setEmployeesex(int employeesex) {
        this.employeesex = employeesex;
    }

    public String getEmployeepassword() {
        return employeepassword;
    }

    public void setEmployeepassword(String employeepassword) {
        this.employeepassword = employeepassword;
    }

    public String getEmployeeaddress() {
        return employeeaddress;
    }

    public void setEmployeeaddress(String employeeaddress) {
        this.employeeaddress = employeeaddress;
    }

    public String getEmployeephone() {
        return employeephone;
    }

    public void setEmployeephone(String employeephone) {
        this.employeephone = employeephone;
    }

    public Role getEmprole() {
        return emprole;
    }

    public void setEmprole(Role emprole) {
        this.emprole = emprole;
    }

    public boolean isEmployeestatus() {
        return employeestatus;
    }

    public void setEmployeestatus(boolean employeestatus) {
        this.employeestatus = employeestatus;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeID=" + employeeID + ", employeename=" + employeename + ", employeeavatar=" + employeeavatar + ", username=" + username + ", birthday=" + birthday + ", employeesex=" + employeesex + ", employeepassword=" + employeepassword + ", employeeaddress=" + employeeaddress + ", employeephone=" + employeephone + ", emprole=" + emprole + ", employeestatus=" + employeestatus + '}';
    }
    
    

    
   
    
    
    
    

  
    
    

    
}
