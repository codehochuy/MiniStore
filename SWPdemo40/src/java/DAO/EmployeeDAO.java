/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.DBContext;
import DTO.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.Employee;
import DTO.Product;
import DTO.Role;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class EmployeeDAO {

    private DBContext db;

    public EmployeeDAO() {
        db = new DBContext();
    }

    public EmployeeDAO(DBContext db) {
        this.db = db;
    }

    public DBContext getDb() {
        return db;
    }

    public void setDb(DBContext db) {
        this.db = db;
    }

    public int getTotal() {
        String sql = "Select count(*) as total from Employee";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
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
        return -1;
    }

    public Employee detailsEmployee(String username, String password) {
        try {
            String sql = "SELECT e.* ,r.RoleName FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId WHERE Username = ? and EmployeePassword = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String dbUsername = rs.getString("Username");
                String dbPassword = rs.getString("EmployeePassword");

                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    int employeeID = rs.getInt("EmployeeID");
                    String employeeName = rs.getString("EmployeeName");
                    String employeeAvatar = rs.getString("EmployeeAvatar");
                    Date birthday = rs.getDate("Birthday");
                    int employeeSex = rs.getInt("EmployeeSex");
                    String employeeAddress = rs.getString("EmployeeAddress");
                    String employeePhone = rs.getString("EmployeePhone");
                    String roleName = rs.getString("RoleName");
                    boolean employeeStatus = rs.getBoolean("EmployeeStatus");

                    Role role = new Role();
                    role.setName(roleName);
                    role.setId(rs.getInt(11));
                    Employee emp = new Employee(employeeID, employeeName, employeeAvatar, dbUsername, birthday, employeeSex, dbPassword, employeeAddress, employeePhone, role, employeeStatus);
                    return emp;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone, r.RoleName, e.EmployeeStatus FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee p = new Employee();
                p.setEmployeeID(rs.getInt(1));
                p.setEmployeename(rs.getString(2));
                p.setEmployeeavatar(rs.getString(3));
                p.setEmployeeaddress(rs.getString(4));
                p.setBirthday(rs.getDate(5));
                p.setEmployeesex(rs.getInt(6));
                p.setEmployeephone(rs.getString(7));
                p.setEmprole(new Role(-1, rs.getString(8))); // Assuming the Role class constructor takes (id, name) as parameters
                p.setEmployeestatus(rs.getBoolean(9));
                if (!p.getEmployeeavatar().contains("http")) {
                    p.setEmployeeavatar("./img/" + p.getEmployeeavatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public Employee getEmployeeByID(String id) {
        String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone, r.RoleName, e.EmployeeStatus ,e.Username,e.EmployeePassword "
                + "FROM Employee e left JOIN Roles r ON e.RoleId = r.RoleId "
                + "WHERE EmployeeID = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Employee p = new Employee();
                p.setEmployeeID(rs.getInt(1));
                p.setEmployeename(rs.getString(2));
                p.setEmployeeavatar(rs.getString(3));
                p.setEmployeeaddress(rs.getString(4));
                p.setBirthday(rs.getDate(5));
                p.setEmployeesex(rs.getInt(6));
                p.setEmployeephone(rs.getString(7));
                p.setEmprole(new Role(-1, rs.getString(8)));
                p.setEmployeestatus(rs.getBoolean(9));
                p.setUsername(rs.getString(10));
                p.setEmployeepassword(rs.getString(11));
                return p;
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public void updateEmployee(String id, String name, String address, String birthday, String sex, String phone, String role, String username, String pass) {
        String sql = "UPDATE [dbo].[Employee]\n"
                + "   SET \n"
                + "      [EmployeeName] = ?\n"
                + "      ,[EmployeeAddress] = ?\n"
                + "      ,[Birthday] = ?\n"
                + "      ,[EmployeeSex] = ?\n"
                + "      ,[EmployeePhone] = ?\n"
                + "      ,[RoleId] = ?\n"
                + "      ,[Username] = ?\n"
                + "      ,[EmployeePassword] = ?\n"
                + " WHERE EmployeeID = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setDate(3, Date.valueOf(birthday));
            ps.setString(4, sex);
            ps.setString(5, phone);
            ps.setInt(6, Integer.parseInt(role));
            ps.setString(7, username);
            ps.setString(8, pass);
            ps.setInt(9, Integer.parseInt(id));
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean isDuplicate(String username) {
        boolean isDuplicate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM Employee WHERE Username = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isDuplicate = true;
                }
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return isDuplicate;
        }
    }

    public void insertuser(String name, String avatar, String address, String birthday, String sex, String phone, String role, String username, String pass) {
        String sql = "INSERT INTO Employee"
                + "(EmployeeName, Username, EmployeePassword, EmployeeSex, Birthday, RoleId, EmployeePhone, EmployeeAddress, EmployeeStatus, EmployeeAvatar)\n"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, 'true', ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, pass);
            ps.setInt(4, Integer.parseInt(sex));
            ps.setDate(5, Date.valueOf(birthday));
            ps.setInt(6, Integer.parseInt(role));
            ps.setString(7, phone);
            ps.setString(8, address);
            ps.setString(9, avatar);
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean getEmployeeStatus(String employeeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean status = false;

        try {
            // Connect to the database
            con = DBUtils.getConnection();

            if (con != null) {
                // Prepare the SQL statement
                String sql = "SELECT EmployeeStatus FROM Employee WHERE EmployeeID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, employeeID);

                // Execute the query
                rs = stm.executeQuery();

                if (rs.next()) {
                    // Retrieve the employee status
                    status = rs.getBoolean("EmployeeStatus");
                }
            }
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the resources
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return status;
    }

    public boolean deleteuser(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {

                //2. Write SQL command
                String sql = "UPDATE Employee SET EmployeeStatus = 'false'  WHERE EmployeeID = ? and EmployeeStatus ='true'";
                //3. Create Statement Object
                stm = con.prepareStatement(sql); //Nạp tham số 1 lần cho Statement
                stm.setString(1, id);

                //4. Execute Statement Object to get result
                int effectRow = stm.executeUpdate();
                //Nạp tham số 1 lần cho Statement
                //5. Process result
                if (effectRow > 0) {
                    result = true;
                } else {
                    result = false;

                }
                System.out.println(result);

            } //end connection has existed
        } catch (SQLException e) {
        } finally {

            if (stm != null) {
                stm.close();  // tạo sau nên đóng trước
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    public boolean adduser(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {

                //       2. Write SQL command
//                String sql = "UPDATE Employee SET EmployeeStatus = 'true'  WHERE EmployeeID = ? ";
                String sql = "UPDATE Employee\n"
                        + "SET EmployeeStatus = 'true'\n"
                        + "WHERE EmployeeID = ? AND EmployeeStatus = 'false';";
                //3. Create Statement Object
                stm = con.prepareStatement(sql); //Nạp tham số 1 lần cho Statement
                stm.setString(1, id);

                //4. Execute Statement Object to get result
                int effectRow = stm.executeUpdate();
                //Nạp tham số 1 lần cho Statement
                //5. Process result
                if (effectRow > 0) {
                    result = true;
                } else {
                    result = false;
                }

            } //end connection has existed
        } catch (SQLException e) {
        } finally {

            if (stm != null) {
                stm.close();  // tạo sau nên đóng trước
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    public List<Employee> getListQuitjob() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, "
                + "e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone,"
                + " r.RoleName, e.EmployeeStatus "
                + "FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId "
                + "WHERE e.EmployeeStatus = 'false'";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee p = new Employee();
                p.setEmployeeID(rs.getInt(1));
                p.setEmployeename(rs.getString(2));
                p.setEmployeeavatar(rs.getString(3));
                p.setEmployeeaddress(rs.getString(4));
                p.setBirthday(rs.getDate(5));
                p.setEmployeesex(rs.getInt(6));
                p.setEmployeephone(rs.getString(7));
                p.setEmprole(new Role(-1, rs.getString(8))); // Assuming the Role class constructor takes (id, name) as parameters
                p.setEmployeestatus(rs.getBoolean(9));
                if (!p.getEmployeeavatar().contains("http")) {
                    p.setEmployeeavatar("./img/" + p.getEmployeeavatar());
                }
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getUsername() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT Username FROM Employee";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("Username");
                list.add(username);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            EmployeeDAO dao = new EmployeeDAO();
            List<Employee> empList = dao.getListQuitjob();
            for (Employee employee : empList) {
                System.out.println(employee);

            }

            System.out.println(empList);
        } catch (Exception e) {
            e.printStackTrace(); // Printing the exception trace for debugging purposes.
        }

//        EmployeeDAO dao = new EmployeeDAO();
//        Employee e = dao.detailsEmployee("tuan", "123");
//        System.out.println(e.toString());
    }

}
