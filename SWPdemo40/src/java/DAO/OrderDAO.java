/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Category;
import DTO.Customer;
import DTO.Employee;
import DTO.Order;
import DTO.Employee7;
import DTO.Order7;
import DTO.OrderDetail;
import DTO.Product;
import DTO.Voucher;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kienb
 */
public class OrderDAO extends DAO {

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "Select o.OrderID,o.OrderDate,c.CustomerName,count(p.ProductID) as total,ISNULL(sum(od.Quantity * p.ProductPrice * (100 - p.Disscount) / 100),0) as [revunue] from Orders o left join Customer c on\n"
                + "o.CustomerID = c.CustomerID left join Orderdetail od on o.OrderID = od.OrderID left join Product p on od.ProdcutID = p.ProductID group by o.OrderID,o.OrderDate,c.CustomerName";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt(1));
                o.setOrderDate(rs.getTimestamp(2).toLocalDateTime());
                o.setCustomerName(rs.getString(3));
                o.setTotal(rs.getDouble(5));
                o.setTotalProduct(rs.getInt(4));
                list.add(o);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    private Utils.DBContext db;

    public OrderDAO() {
        db = new Utils.DBContext();
    }

    public OrderDAO(Utils.DBContext db) {
        this.db = db;
    }

    public Utils.DBContext getDb() {
        return db;
    }

    public void setDb(Utils.DBContext db) {
        this.db = db;
    }

    public List<Order7> getAllOrders() {
        List<Order7> listOrders = new ArrayList<>();

        try {
            String sql = "select OrderID, CustomerName, Phone, v.VoucherID, VoucherName, OrderDate, EmployeeName\n"
                    + "  from Orders o join Customer c on o.CustomerID = c.CustomerID\n"
                    + "  join Voucher v on v.VoucherID = o.VoucherID\n"
                    + "  join Employee e on e.EmployeeID = o.EmployeeID";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerName = rs.getString("CustomerName");
                String Phone = rs.getString("Phone");
                int VoucherID = rs.getInt("VoucherID");
                String VoucherName = rs.getString("VoucherName");
                Date OrderDate = rs.getDate("OrderDate");
                String EmployeeName = rs.getString("EmployeeName");

                Order7 order = new Order7(OrderID, CustomerName, Phone, VoucherID, VoucherName, OrderDate, EmployeeName);
                listOrders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listOrders;
    }

    public List<OrderDetail> getOrderDetail(int orderID) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<OrderDetail> orderDetails = new ArrayList<>();

        try {
            String sql = "select ProdcutID, ProductLink, ProductName, ProductPrice, od.Quantity\n"
                    + "from Orderdetail od join Orders o on od.OrderID = o.OrderID\n"
                    + "join Product pro on pro.ProductID = od.ProdcutID\n"
                    + "where o.OrderID = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ProdcutID = rs.getInt("ProdcutID");
                String ProductLink = rs.getString("ProductLink");
                String ProductName = rs.getString("ProductName");
                Double ProductPrice = rs.getDouble("ProductPrice");
                int Quantity = rs.getInt("Quantity");
                OrderDetail order = new OrderDetail(ProdcutID, ProductLink, ProductName, ProductPrice, Quantity);
                orderDetails.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderDetails;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> listCustomers = new ArrayList<>();

        try {
            String sql = "select * from Customer";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                String phone = rs.getString("Phone");
                Customer customer = new Customer(customerID, customerName, phone);
                listCustomers.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listCustomers;
    }

    public List<Voucher> getAllVouchers() {
        OrderDAO dao = new OrderDAO();
        List<Voucher> listVouchers = new ArrayList<>();
        Date currentDate = Date.valueOf(LocalDate.now());
        try {
            String sql = "select v.VoucherID, v.VoucherName, v.DateStart, v.DateEnd, v.VoucherStatus, v.Condition, v.Quatity, v.Value, v.EmployeeID, e.EmployeeName \n"
                    + "  from Voucher v left join Employee e on v.EmployeeID = e.EmployeeID";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int voucherID = rs.getInt("VoucherID");
                String voucherName = rs.getString("VoucherName");
                Date dateStart = rs.getDate("DateStart");
                Date dateEnd = rs.getDate("DateEnd");

                boolean voucherStatus = rs.getBoolean("VoucherStatus");
                if (dateEnd.before(currentDate)) {
                    voucherStatus = false;
                }
                int condition = rs.getInt("Condition");
                int value = rs.getInt("Value");
                int quatity = rs.getInt("Quatity");
                int numberVoucherUsed = dao.getNumberVoucherUsed(voucherID);

                if (numberVoucherUsed >= quatity) {
                    voucherStatus = false;
                } else if (dateEnd.before(currentDate) || dateStart.after(currentDate)) {
                    voucherStatus = false;
                }

                int employeeID = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                Voucher voucher = new Voucher(voucherID, voucherName, dateStart, dateEnd, voucherStatus, condition, quatity, value, employeeID, employeeName);
                listVouchers.add(voucher);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listVouchers;
    }

    public int getNumberVoucherUsed(int voucherID) {
        int numberVoucherUsed = 0;
        try {

            String sql;
            sql = "select count(*) as Number from Orders where VoucherID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, voucherID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return numberVoucherUsed = rs.getInt("Number");
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberVoucherUsed;
    }

    public List<Employee7> getAllEmployee() {
        List<Employee7> listEmployees = new ArrayList<>();

        try {
            String sql = "select * from Employee";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String EmployeeName = rs.getString("EmployeeName");
                String EmployeeAvatar = rs.getString("EmployeeAvatar");
                String Username = rs.getString("Username");
                Date Birthday = rs.getDate("Birthday");
                String EmployeeSex = rs.getString("EmployeeSex");
                String EmployeePassword = rs.getString("EmployeePassword");
                String EmployeeAddress = rs.getString("EmployeeAddress");
                String EmployeePhone = rs.getString("EmployeePhone");
                int RoleId = rs.getInt("RoleId");

                Employee7 employee = new Employee7(EmployeeID, EmployeeName, EmployeeAvatar, Username, Birthday, EmployeeSex, EmployeePassword, EmployeeAddress, EmployeePhone, RoleId);
                listEmployees.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmployees;
    }

    public Voucher getVoucherByID(int voucherID) {
        Voucher voucher = new Voucher();
        try {
            String sql = "select v.VoucherID, v.VoucherName, v.DateStart, v.DateEnd, v.VoucherStatus, v.Condition, v.Quatity, v.Value, v.EmployeeID, e.EmployeeName\n"
                    + "from Voucher v left join Employee e on v.EmployeeID = e.EmployeeID\n"
                    + "where VoucherID = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, voucherID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String voucherName = rs.getString("VoucherName");
                Date dateStart = rs.getDate("DateStart");
                Date dateEnd = rs.getDate("DateEnd");

                boolean voucherStatus = rs.getBoolean("VoucherStatus");

                int condition = rs.getInt("Condition");
                int value = rs.getInt("Value");
                int quatity = rs.getInt("Quatity");

                int employeeID = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                Voucher v = new Voucher(voucherID, voucherName, dateStart, dateEnd, voucherStatus, condition, quatity, value, employeeID, employeeName);
                voucher = v;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voucher;
    }

    public void deleteOrder(int orderID) {
        try {

            String sql;
            sql = "delete from Orders where OrderID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, orderID);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAllProductInOrder(int orderID) {
        try {

            String sql;
            sql = "delete from Orderdetail where OrderID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, orderID);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteOrderDetail(int productID, int orderID) {
        try {

            String sql;
            sql = "delete from Orderdetail where ProdcutID = ? and OrderID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, productID);
            stmt.setInt(2, orderID);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean deleteVoucher(int voucherID) {
        boolean check = false;
        try {

            String sql;
            sql = "delete from Voucher where VoucherID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, voucherID);
            stmt.executeUpdate();
            check = true;

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public int getCustomerIDByPhone(String phone) {
        OrderDAO dao = new OrderDAO();
        List<Customer> listCustomers = dao.getAllCustomer();
        for (Customer listCustomer : listCustomers) {
            if (listCustomer.getPhone().equals(phone)) {
                return listCustomer.getCustomerID();
            }
        }
        return -1;
    }

    public String getCustomerNameByPhone(String phone) {
        OrderDAO dao = new OrderDAO();
        List<Customer> listCustomers = dao.getAllCustomer();
        for (Customer listCustomer : listCustomers) {
            if (listCustomer.getPhone().equals(phone)) {
                return listCustomer.getCustomerName();
            }
        }
        return "-1";
    }

    public int getVoucherIDByName(String voucherName) {
        OrderDAO dao = new OrderDAO();
        List<Voucher> listVouchers = dao.getAllVouchers();
        for (Voucher listVoucher : listVouchers) {
            if (listVoucher.getVoucherName().equals(voucherName)) {
                return listVoucher.getVoucherID();
            }
        }
        return -1;
    }

    public int getEmployeeIDByName(String employeeName) {
        OrderDAO dao = new OrderDAO();
        List<Employee7> listEmployees = dao.getAllEmployee();
        for (Employee7 listEmployee : listEmployees) {
            if (listEmployee.getEmployeeName().equals(employeeName)) {
                return listEmployee.getEmployeeID();
            }
        }
        return -1;
    }

    public void updateOrder(int orderID, Date orderDate, int customerID, int voucherID, int employeeID) {
        try {
            String sql;
            sql = "update Orders set OrderDate = ?, CustomerID = ?, VoucherID = ?, EmployeeID = ?\n"
                    + "  where OrderID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setDate(1, orderDate);
            stmt.setInt(2, customerID);
            stmt.setInt(3, voucherID);
            stmt.setInt(4, employeeID);
            stmt.setInt(5, orderID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuantityOrderDetail(int orderID, int productID, int quantity) {
        try {
            String sql;
            sql = "update Orderdetail set Quantity = ?\n"
                    + "  where OrderID = ? and ProdcutID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);

            stmt.setInt(1, quantity);
            stmt.setInt(2, orderID);
            stmt.setInt(3, productID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateVoucher(int voucherID, String voucherName, Date dateStart, Date dateEnd, boolean voucherStatus, int condition, int quantity, int value, int employeeID) {
        try {
            String sql;
            sql = "update Voucher set VoucherName = ?, DateStart =?, DateEnd = ?, VoucherStatus = ?, Condition = ?, Quatity = ?, Value = ?, EmployeeID = ?\n"
                    + "  where VoucherID = ?";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1, voucherName);
            stmt.setDate(2, dateStart);
            stmt.setDate(3, dateEnd);
            stmt.setBoolean(4, voucherStatus);
            stmt.setInt(5, condition);
            stmt.setInt(6, quantity);
            stmt.setInt(7, value);
            stmt.setInt(8, employeeID);
            stmt.setInt(9, voucherID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateNoVoucherForOrder(int orderID) {
        try {
            String sql;
            sql = "update Orders set VoucherID = 1\n"
                    + "where OrderID = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkProductID(int productID) {
        boolean check = false;
        try {
            String sql = "SELECT * FROM Product WHERE ProductID = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                check = true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }

    public void addProductOrderDetail(int orderID, int productID, int quantity) {
        try {
            String sql;
            sql = "insert into Orderdetail (ProdcutID, Quantity, OrderID) values(?,?,?)";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);

            stmt.setInt(1, productID);
            stmt.setInt(2, quantity);
            stmt.setInt(3, orderID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addVoucher(String voucherName, Date dateStart, Date dateEnd, boolean voucherStatus, int condition, int quantity, int value, int employeeID) {
        try {
            String sql;
            sql = "insert into Voucher (VoucherName, DateStart, DateEnd, VoucherStatus, Condition, Quatity, Value, EmployeeID) values(?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);

            stmt.setString(1, voucherName);
            stmt.setDate(2, dateStart);
            stmt.setDate(3, dateEnd);
            stmt.setBoolean(4, voucherStatus);
            stmt.setInt(5, condition);
            stmt.setInt(6, quantity);
            stmt.setInt(7, value);
            stmt.setInt(8, employeeID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertOrder(String voucherID, int employeeId, int customerID) {
        try {
            String sql;
            if (voucherID != null) {
                sql = "INSERT INTO [dbo].[Orders]\n"
                        + "           ([OrderDate]\n"
                        + "           ,[CustomerID]\n"
                        + "           ,[EmployeeID]\n"
                        + "		   ,[VoucherID])\n"
                        + "     VALUES\n"
                        + "           (GETDATE()\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";
            } else {
                sql = "INSERT INTO [dbo].[Orders]\n"
                        + "           ([OrderDate]\n"
                        + "           ,[CustomerID]\n"
                        + "           ,[EmployeeID])\n"
                        + "     VALUES\n"
                        + "           (GETDATE()\n"
                        + "           ,?\n"
                        + "           ,?)";
            }

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, customerID);
            stmt.setInt(2, employeeId);
            if (voucherID != null) {
                stmt.setInt(3, Integer.parseInt(voucherID));
            }
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getLastId() {
        Connection con = null;
        PreparedStatement stm = null;
        con = DBUtils.getConnection();
        String sql = "Select top 1 OrderID from Orders order by OrderID desc";
        try {
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void insertOrderDetail(HashMap<Product, Integer> map, int orderId) {
        try {
            String sql = "INSERT INTO [dbo].[Orderdetail]\n"
                    + "           ([ProdcutID]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[OrderID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";

            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            for (Product i : map.keySet()) {
                stmt.setInt(1, i.getProductID());
                stmt.setInt(2, map.get(i));
                stmt.setInt(3, orderId);
                stmt.executeUpdate();
            }
            stmt.close();
            String xSQL = "UPDATE [dbo].[Product]\n"
                    + "   SET Quatity = ?\n"
                    + " WHERE ProductID = ? ";
            PreparedStatement ptm = db.getConn().prepareStatement(xSQL);
            for (Product i : map.keySet()) {
                ptm.setInt(1, i.getQuantity() - map.get(i));
                ptm.setInt(2, i.getProductID());
                ptm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmployeeNameByID(int employeeID) {
        OrderDAO dao = new OrderDAO();
        List<Employee7> listEmployees = dao.getAllEmployee();
        for (Employee7 listEmployee : listEmployees) {
            if (listEmployee.getEmployeeID() == employeeID) {
                return listEmployee.getEmployeeName();
            }
        }
        return "-1";
    }

    public String getVoucherNameByID(int voucherID) {
        OrderDAO dao = new OrderDAO();
        List<Voucher> listVouchers = dao.getAllVouchers();
        for (Voucher listVoucher : listVouchers) {
            if (listVoucher.getVoucherID() == (voucherID)) {
                return listVoucher.getVoucherName();
            }
        }
        return "-1";
    }

    public int getSoldQuantityProductByID(int ProductID) {
        int soldQuantiy = 0;;
        try {
            String sql = "select * from [Orderdetail]\n"
                    + "  where [ProdcutID] = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, ProductID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("Quantity");
                soldQuantiy += quantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soldQuantiy;
    }

    public int getSoldQuantityProductByID(int ProductID, int OrderID) {
        int soldQuantiy = 0;;
        try {
            String sql = "select * from [Orderdetail]\n"
                    + "  where [ProdcutID] = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, ProductID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("Quantity");
                int orderID = rs.getInt("OrderID");
                if (orderID != OrderID) {
                    soldQuantiy += quantity;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soldQuantiy;
    }

    public int getInventoryQuantityProductByID(int ProductID) {
        int inventoryQuantiy = 0;;
        try {
            String sql = "select * from Product where ProductID = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1, ProductID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                inventoryQuantiy = rs.getInt("Quatity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventoryQuantiy;
    }

    public int getRemainQuantityByProductID(int ProductID) {
        OrderDAO dao = new OrderDAO();
        int soldQuantiy = dao.getSoldQuantityProductByID(ProductID);
        int inventoryQuantiy = dao.getInventoryQuantityProductByID(ProductID);
        return (inventoryQuantiy - soldQuantiy);
    }

    public int getRemainQuantityByProductID(int ProductID, int OrderID) {
        OrderDAO dao = new OrderDAO();
        int soldQuantiy = dao.getSoldQuantityProductByID(ProductID, OrderID);
        int inventoryQuantiy = dao.getInventoryQuantityProductByID(ProductID);
        return (inventoryQuantiy - soldQuantiy);
    }

    public List<Integer> getTop3() {
        List<Integer> topProducts = new ArrayList<>();
        try {
            String sql = "SELECT TOP (3) [ProdcutID], SUM([Quantity]) AS TotalQuantity\n"
                    + "FROM [SWPProject2].[dbo].[Orderdetail]\n"
                    + "GROUP BY [ProdcutID]\n"
                    + "ORDER BY TotalQuantity DESC;";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProdcutID");
                topProducts.add(productID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topProducts;
    }

    public List<Product> getTop5() {
        List<Integer> topProductIDs = getTop3();
        List<Product> list = new ArrayList<>();
//        String sql = "SELECT p.*, c.CategoryName FROM Product p LEFT JOIN Categorie c ON p.CategoryID = c.CategoryID WHERE p.ProductID IN (?, ?, ?)";
        String sql = "SELECT p.*, c.CategoryName, od.TotalQuantity\n"
                + "FROM Product p\n"
                + "LEFT JOIN Categorie c ON p.CategoryID = c.CategoryID\n"
                + "LEFT JOIN (\n"
                + "    SELECT TOP (3) [ProdcutID], SUM([Quantity]) AS TotalQuantity\n"
                + "    FROM [SWPProject2].[dbo].[Orderdetail]\n"
                + "    GROUP BY [ProdcutID]\n"
                + "    ORDER BY TotalQuantity DESC\n"
                + ") od ON p.ProductID = od.ProdcutID\n"
                + "WHERE p.ProductID IN (?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, topProductIDs.get(0));
            ps.setInt(2, topProductIDs.get(1));
            ps.setInt(3, topProductIDs.get(2));
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                p.setProductID(rs.getInt(1));
                p.setProductLink(rs.getString(2));
                p.setProductName(rs.getString(3));
                p.setProductPrice(rs.getDouble(4));
                p.setDiscount(rs.getDouble(5));
                p.setCategory(new Category(rs.getInt(8), rs.getString(10)));
                p.setQuantity(rs.getInt(7));
                p.setStatus(rs.getBoolean(6));
                p.setQuatity2(rs.getInt(11));

                if (!p.getProductLink().contains("http")) {
                    p.setProductLink("./img/" + p.getProductLink());
                }
                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<Integer> getList() {
        List<Integer> topProducts = new ArrayList<>();
        try {
            String sql = "SELECT [ProdcutID], SUM([Quantity]) AS TotalQuantity\n"
                    + "FROM [SWPProject2].[dbo].[Orderdetail]\n"
                    + "GROUP BY [ProdcutID]\n"
                    + "ORDER BY TotalQuantity DESC;";
            Connection conn = DBUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProdcutID");
                topProducts.add(productID);
            }
            // Close the resources properly
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topProducts;
    }

    public List<Product> getProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, c.CategoryName, od.TotalQuantity\n"
                + "FROM Product p\n"
                + " LEFT JOIN Categorie c ON p.CategoryID = c.CategoryID\n"
                + "INNER JOIN (\n"
                + "    SELECT [ProdcutID], SUM([Quantity]) AS TotalQuantity\n"
                + "    FROM [SWPProject2].[dbo].[Orderdetail]\n"
                + "    GROUP BY [ProdcutID]\n"
                + ") od ON p.ProductID = od.ProdcutID\n"
                + "ORDER BY od.TotalQuantity DESC;\n"
                + "";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setProductLink(rs.getString(2));
                p.setProductName(rs.getString(3));
                p.setProductPrice(rs.getDouble(4));
                p.setDiscount(rs.getDouble(5));
                p.setCategory(new Category(rs.getInt(8), rs.getString(10)));
                p.setQuantity(rs.getInt(7));
                p.setStatus(rs.getBoolean(6));
                p.setQuatity2(rs.getInt(11));

                if (!p.getProductLink().contains("http")) {
                    p.setProductLink("./img/" + p.getProductLink());
                }
                list.add(p);
            }
        } catch (SQLException e) {
            // Log or handle the exception appropriately.
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<Product> x = dao.getProduct();
        System.out.println(x);
    }
}
