/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Attendance;
import DTO.AttendanceReport;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AttendanceDAO {

    public void TakeAttendance(String date, String time, String employeeID, String leaveShift, String button) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null && button.equals("CHECK IN")) {
                ptm = conn.prepareStatement("INSERT INTO Attendance(CheckInDate, CheckInTime, EmployeeID, ShiftID) VALUES(?, ?, ?, ?)");
                ptm.setString(1, date);
                ptm.setString(2, time);
                ptm.setString(3, employeeID);
                ptm.setString(4, leaveShift);
                rs = ptm.executeQuery();
            }
            if (conn != null && button.equals("CHECK OUT")) {
                ptm = conn.prepareStatement("UPDATE Attendance SET CheckOutDate = ?, CheckOutTime = ? WHERE ShiftID = ?");
                ptm.setString(1, date);
                ptm.setString(2, time);
                ptm.setString(3, leaveShift);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TakeAttendancever2(String employeeID, String shiftID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO [dbo].[Attendance]\n"
                        + "           ([CheckInDate]\n"
                        + "           ,[CheckInTime]\n"
                        + "           ,[EmployeeID]\n"
                        + "           ,[ShiftID])\n"
                        + "     VALUES\n"
                        + "           (CONVERT(date,GETDATE())\n"
                        + "           ,CONVERT(time,GETDATE())\n"
                        + "           ,?\n"
                        + "           ,?)");
                ptm.setString(1, employeeID);
                ptm.setString(2, shiftID);
                rs = ptm.executeQuery();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AttendanceReport> ShowAttendanceReport() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        List<AttendanceReport> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE Attendance SET LateTime = DATEDIFF(MINUTE, CONVERT(DATETIME, T2.StartTime), CONVERT(DATETIME, T1.CheckInTime)) FROM Attendance T1\n"
                        + "JOIN Shifts T2 ON T1.ShiftID = T2.ShiftID UPDATE Attendance SET SoonTime = DATEDIFF(MINUTE, CONVERT(DATETIME, T1.CheckOutTime), CONVERT(DATETIME, T2.EndTime))\n"
                        + "FROM Attendance T1 JOIN Shifts T2 ON T1.ShiftID = T2.ShiftID");
                ptm.executeUpdate();

                ptm = conn.prepareStatement("SELECT\n"
                        + "    a.EmployeeID, e.EmployeeName, a.ShiftID, a.CheckInDate, a.CheckInTime, a.CheckOutDate, a.CheckOutTime, a.LateTime,\n"
                        + "	CAST(DATEDIFF(second, CAST(a.CheckInDate AS datetime) + CAST(a.CheckInTime AS datetime), CAST(a.CheckOutDate AS datetime) + CAST(a.CheckOutTime AS datetime)) / 3600 AS varchar) + ':' +\n"
                        + "    RIGHT('0' + CAST((DATEDIFF(second, CAST(a.CheckInDate AS datetime) + CAST(a.CheckInTime AS datetime), CAST(a.CheckOutDate AS datetime) + CAST(a.CheckOutTime AS datetime)) / 60) % 60 AS varchar), 2) + ':' +\n"
                        + "    RIGHT('0' + CAST(DATEDIFF(second, CAST(a.CheckInDate AS datetime) + CAST(a.CheckInTime AS datetime), CAST(a.CheckOutDate AS datetime) + CAST(a.CheckOutTime AS datetime)) % 60 AS varchar), 2) AS TimeDifference\n"
                        + "FROM\n"
                        + "    Attendance a\n"
                        + "    JOIN Employee e ON a.EmployeeID = e.EmployeeID\n"
                        + "ORDER BY\n"
                        + "    a.ShiftID ASC, a.EmployeeID ASC");
                rs = ptm.executeQuery();
            }
            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                int shiftID = rs.getInt("ShiftID");
                String checkInDate = rs.getString("CheckInDate");
                Time checkInTime = rs.getTime("CheckInTime");
                String checkOutDate = rs.getString("CheckOutDate");
                Time checkOutTime = rs.getTime("CheckOutTime");
                String lateTime = rs.getString("LateTime");
                String timeDifference = rs.getString("TimeDifference");
                list.add(new AttendanceReport(employeeID, employeeName, shiftID, checkInDate, checkInTime, checkOutDate, checkOutTime, lateTime, timeDifference));
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

}