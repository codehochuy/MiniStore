/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Account;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

    public ArrayList<Account> AccountList(String accID, String searchKey) throws SQLException {
        ArrayList<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            if (accID != null) {
                sql = "SELECT * FROM Account where accountID = " + accID;;
            }
            if (searchKey != null) {
                sql = "SELECT * FROM Account WHERE accountID LIKE " + "'%" + searchKey + "%'";
            }

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String accountID = rs.getString("accountID");
                String accountName = rs.getString("accountName");
                String acpassword = rs.getString("acpassword");
                int acSession = rs.getInt("acSession");
                Account account = new Account(accountID, accountName, acpassword, acSession);
                accountList.add(account);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return accountList;
    }

    public Account checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT AccountID,AccountName,Acpassword,AcSession FROM Account  WHERE AccountName = ? and Acpassword = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                String accountID = rs.getString("accountID");
                String accountName = rs.getString("accountName");
                String acpassword = rs.getString("acpassword");
                int acSession = rs.getInt("acSession");
                Account account = new Account(accountID, accountName, acpassword, acSession);
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;

    }

//    public Account checkAccount(String username) throws SQLException {
//        String sql = "SELECT AccountID,AccountName,Acpassword,AcSession FROM Account WHERE AccountName = ?";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            conn = new DBUtils().getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//               String accountID = rs.getString("accountID");
//                String accountName = rs.getString("accountName");
//                String acpassword = rs.getString("acpassword");
//                int acSession = rs.getInt("acSession");
//                Account account = new Account(accountID, accountName, acpassword, acSession);
//                return account;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return null;
//
//    }
    public static void main(String[] args) {
        try {
            DAO dao = new DAO();
            ArrayList<Account> accList = new ArrayList<>();
            accList = dao.AccountList("", "");
            Account acc = dao.checkLogin("Tuand", "123");
            if( acc == null){
                System.out.println("false");
            } else {
                System.out.println(acc);
            }
            for (Account account : accList) {
                System.out.println(account.toString());
            }
        } catch (Exception e) {
        }

    }
}
