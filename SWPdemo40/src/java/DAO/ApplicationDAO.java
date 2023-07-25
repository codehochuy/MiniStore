/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Application;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ApplicationDAO {

    public int SendLeaveApplication(String ID, String leaveShift, String leaveReason, String submitTime, String submitDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int re = 0;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement("INSERT INTO Applications (ShiftID, LeaveReason, EmployeeID) VALUES (?, ?, ?)");
////                ptm = conn.prepareStatement("INSERT INTO Applications (LeaveDate, LeaveReason, ApplicationStatus, EmployeeID)\n"
////                        + "SELECT ? AS LeaveDate, ? AS LeaveReason, 'false' AS ApplicationStatus, e.EmployeeID\n"
////                        + "FROM Employee e\n"
////                        + "WHERE e.EmployeeName = N?");
//                ptm.setString(1, leaveShift);
//                ptm.setString(2, leaveReason);
//                ptm.setString(3, ID);
//                rs = ptm.executeQuery();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO Applications(ShiftID, EmployeeID, LeaveReason)\n"
                        + "SELECT ?, ?, ?\n"
                        + "FROM Shifts\n"
                        + "WHERE ShiftID = ? AND CAST(StartDate AS DATETIME) + CAST(StartTime AS DATETIME) >= DATEADD(HOUR, 4, CAST(? AS DATETIME) + CAST(? AS DATETIME));");
                ptm.setString(1, leaveShift);
                ptm.setString(2, ID);
                ptm.setString(3, leaveReason);
                ptm.setString(4, leaveShift);
                ptm.setString(5, submitDate);
                ptm.setString(6, submitTime);
                re = ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    public List<Application> ShowLeaveApplication() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        List<Application> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT A.ShiftID, A.LeaveReason, A.ApplicationStatus, A.EmployeeID, A.IsApproved, B.EmployeeName\n"
                        + "FROM Applications A\n"
                        + "JOIN Employee B ON A.EmployeeID = B.EmployeeID;");
                rs = ptm.executeQuery();
            }
            while (rs.next()) {
                int leaveShift = rs.getInt("ShiftID");
                String leaveReason = rs.getString("LeaveReason");
                boolean applicationStatus = rs.getBoolean("ApplicationStatus");
                int employeeID = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                int isApproved = rs.getInt("IsApproved");
                list.add(new Application(leaveShift, leaveReason, applicationStatus, employeeID, employeeName, isApproved));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            };
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public void ProcessLeaveApplication(String choice, String employeeID, String leaveReason, String leaveShift) {
        String approve = "UPDATE Applications SET IsApproved = 1, ApplicationStatus = 1 WHERE EmployeeID = ? and LeaveReason = ?\n"
                + "                UPDATE Shifts SET EmployeeIDSecond = NULL WHERE ShiftID = ? AND EmployeeIDSecond = ?\n"
                + "                UPDATE Shifts SET EmployeeIDFirst = NULL WHERE ShiftID = ? AND EmployeeIDFirst = ?";
        String reject = "UPDATE Applications SET IsApproved = 0, ApplicationStatus = 1 WHERE EmployeeID = ? and LeaveReason = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (choice.equals("yes")) {
                    ptm = conn.prepareStatement(approve);
                    ptm.setString(1, employeeID);
                    ptm.setString(2, leaveReason);
                    ptm.setString(3, leaveShift);
                    ptm.setString(4, employeeID);
                    ptm.setString(5, leaveShift);
                    ptm.setString(6, employeeID);
                }
                if (choice.equals("no")) {
                    ptm = conn.prepareStatement(reject);
                    ptm.setString(1, employeeID);
                    ptm.setString(2, leaveReason);
                }
//                ptm = conn.prepareStatement(approve);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteLeaveApplication(String leaveShift) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("DELETE FROM Applications WHERE ShiftID = ?");
                ptm.setString(1, leaveShift);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}