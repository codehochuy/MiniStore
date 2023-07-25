/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author kienb
 */
public class Product {
    private int productID;
    private String productLink;
    private String productName;
    private double productPrice;
    private double discount;
    private Category category;
    private int employeeID;
    private int quantity;
    private boolean status;
    private int quatity2;

    public Product() {
    }

    public Product(int productID, String productLink, String productName, double productPrice, double discount, Category category, int employeeID, int quantity, boolean status, int quatity2) {
        this.productID = productID;
        this.productLink = productLink;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discount = discount;
        this.category = category;
        this.employeeID = employeeID;
        this.quantity = quantity;
        this.status = status;
        this.quatity2 = quatity2;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuatity2() {
        return quatity2;
    }

    public void setQuatity2(int quatity2) {
        this.quatity2 = quatity2;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productLink=" + productLink + ", productName=" + productName + ", productPrice=" + productPrice + ", discount=" + discount + ", category=" + category + ", employeeID=" + employeeID + ", quantity=" + quantity + ", status=" + status + ", quatity2=" + quatity2 + '}';
    }

   
    

    
    
}
