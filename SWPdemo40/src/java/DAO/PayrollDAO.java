/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Application;
import DTO.Payroll;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class PayrollDAO {

    public List<Payroll> ShowPayroll(String shiftID1, String shiftID2) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Payroll> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE Attendance SET LateTime = DATEDIFF(MINUTE, CONVERT(DATETIME, T2.StartTime), CONVERT(DATETIME, T1.CheckInTime)) FROM Attendance T1\n"
                        + "JOIN Shifts T2 ON T1.ShiftID = T2.ShiftID UPDATE Attendance SET SoonTime = DATEDIFF(MINUTE, CONVERT(DATETIME, T1.CheckOutTime), CONVERT(DATETIME, T2.EndTime))\n"
                        + "FROM Attendance T1 JOIN Shifts T2 ON T1.ShiftID = T2.ShiftID");
                ptm.executeUpdate();

                ptm = conn.prepareStatement("UPDATE Attendance\n"
                        + "SET ShiftComplete = Shifts.CoefSalary\n"
                        + "FROM Attendance\n"
                        + "INNER JOIN Shifts ON Attendance.ShiftID = Shifts.ShiftID\n"
                        + "\n"
                        + "UPDATE Attendance\n"
                        + "SET ShiftComplete = \n"
                        + "CASE\n"
                        + "		WHEN LateTime IS NULL OR SoonTime IS NULL THEN 0\n"
                        + "		WHEN SoonTime > 5 THEN 0\n"
                        + "        WHEN LateTime > 0 AND LateTime <= 10 AND SoonTime <= 5 THEN ShiftComplete * 0.9\n"
                        + "        WHEN LateTime <= 60 AND LateTime > 10 AND SoonTime <= 5 THEN ShiftComplete * 0.5\n"
                        + "		WHEN LateTime > 60 THEN 0\n"
                        + "        ELSE ShiftComplete\n"
                        + "END\n"
                        + "\n"
                        + "UPDATE Attendance\n"
                        + "SET FinedShift = Shifts.CoefSalary - ShiftComplete\n"
                        + "FROM Attendance\n"
                        + "INNER JOIN Shifts ON Attendance.ShiftID = Shifts.ShiftID\n"
                        + "\n"
                        + "UPDATE Payroll SET Salary = (SELECT CAST(SUM(CAST(a.ShiftComplete AS float)) AS float)\n"
                        + "FROM Attendance a\n"
                        + "WHERE a.ShiftComplete > 0 AND a.EmployeeID = Payroll.EmployeeID AND a.ShiftID BETWEEN ? AND ?)\n"
                        + "\n"
                        + "UPDATE Payroll SET FinedSalary = (SELECT CAST(SUM(CAST(a.FinedShift AS float)) AS float)\n"
                        + "FROM Attendance a\n"
                        + "WHERE a.EmployeeID = Payroll.EmployeeID AND a.ShiftID BETWEEN ? AND ?)");
                ptm.setString(1, shiftID1);
                ptm.setString(2, shiftID2);
                ptm.setString(3, shiftID1);
                ptm.setString(4, shiftID2);
                ptm.executeUpdate();

                ptm = conn.prepareStatement("SELECT A.EmployeeID, A.EmployeeName, B.Salary, B.FinedSalary\n"
                        + "FROM Employee A\n"
                        + "JOIN Payroll B ON A.EmployeeID = B.EmployeeID");
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int e = rs.getInt("EmployeeID");
                    String f = rs.getString("EmployeeName");
                    float g = rs.getFloat("Salary");
                    float h = rs.getFloat("FinedSalary");
                    list.add(new Payroll(e, f, g, h));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}