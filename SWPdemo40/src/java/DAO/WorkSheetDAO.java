/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Vuong
 */
import DTO.Employee;
import DTO.Role;
import DTO.Shift;
import DTO.WorkSheetDTO;
import DTO.WorkSheetEmployeeFirstDTO;
import DTO.WorkSheetEmployeeSecondDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vuong
 */
public class WorkSheetDAO {

    //lấy tất cả, ko dùng nữa // đang sài lấy tất cả theo ngày hiện tại
    public List<WorkSheetDTO> getAll() {
        List<WorkSheetDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
// Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<WorkSheetDTO> getallbyshiftid(String shiftid) {
        List<WorkSheetDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                + "WHERE p.ShiftID = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftid);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
// Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<WorkSheetDTO> Seeworkingtime(String employeeid, String startdate, String enddate) {
        List<WorkSheetDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                + "WHERE (EmployeeIDFirst = ? OR EmployeeIDSecond = ? ) AND StartDate >= ? AND EndDate <= ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, employeeid);
            ps.setString(2, employeeid);
            ps.setString(3, startdate);
            ps.setString(4, enddate);

            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
                // Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    // lấy tất cả theo ngày hiện tại
    public List<WorkSheetDTO> getAllbycurrentdate(String formattedDate) {
        List<WorkSheetDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                + "WHERE p.StartDate = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, formattedDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
// Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<WorkSheetDTO> searchbydate(String startdate, String enddate, String shiftname, String employeerole) {
        List<WorkSheetDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                + "WHERE StartDate >= ? AND EndDate <= ? AND ShiftName = ? AND EmployeeRole = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, enddate);
            ps.setString(3, shiftname);
            ps.setString(4, employeerole);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
                // Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
//       
    
    public List<Integer> searchbydate_return_listshiftid(String startdate, String enddate, String shiftname, String employeerole) {
List<Integer> shiftIDs = new ArrayList<>();
Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null;
String sql = "SELECT p.ShiftID FROM Shifts p " +
"WHERE StartDate >= ? AND EndDate <= ? AND ShiftName = ? AND EmployeeRole = ?";
try {
conn = DBUtils.getConnection();
ps = conn.prepareStatement(sql);
ps.setString(1, startdate);
ps.setString(2, enddate);
ps.setString(3, shiftname);
ps.setString(4, employeerole);
rs = ps.executeQuery();
while (rs.next()) {
int shiftID = rs.getInt("ShiftID");
shiftIDs.add(shiftID);
}
} catch (SQLException e) {
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
return shiftIDs;
}
    
    

    public WorkSheetDTO getbyid(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ShiftID, CoefSalary FROM Shifts Where ShiftID = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setCoefsalary(rs.getFloat(2));

                return p;
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void update(String shiftid, String coefsalary) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE Shifts SET CoefSalary = ?  WHERE ShiftID = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, coefsalary);

            ps.setString(2, shiftid);
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public List<Employee> getAllemployee(String employeerole) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone, r.RoleName, e.EmployeeStatus FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId WHERE r.RoleId = ? AND e.EmployeeStatus = 1  ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, employeerole);
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
            Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public boolean addEmployeeFirst(String employeeid, String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Shifts SET EmployeeIDFirst = ?  WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);

            // nhân viên
            ps.setString(1, employeeid);
            ps.setString(2, shiftid);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;

    }

    public boolean addEmployeeSecond(String employeeid, String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE Shifts SET EmployeeIDSecond = ?  WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);

            // nhân viên
            ps.setString(1, employeeid);
            ps.setString(2, shiftid);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    // lấy tất cả theo ngày hiện tại
    public List<WorkSheetDTO> getAll_forsalse(int id, String formattedDate) {
        List<WorkSheetDTO> list = new ArrayList<>();
//        String sql =  "Select p.*,c.EmployeeName from Shifts p left join Employee c on p.EmployeeID = c.EmployeeID WHERE P.EmployeeID = ?  ";
//        String sql = "Select ShiftID, ShiftName, StartTime, EndTime, StartDate, EndDate, CoefSalary  FROM Shifts  WHERE (EmployeeIDFirst = ?  OR EmployeeIDSecond = ? ) AND StartDate = ? ";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
            String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                    + "WHERE (p.EmployeeIDFirst = ?  OR p.EmployeeIDSecond = ? ) AND p.StartDate = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setString(3, formattedDate);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
// Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);

            } 
            return list;
           
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<WorkSheetDTO> searchbydate_forsales(String startdate, String enddate, int id) {
        List<WorkSheetDTO> list = new ArrayList<>();

//        String sql =  "Select p.*,c.EmployeeName from Shifts p left join Employee c on p.EmployeeID = c.EmployeeID WHERE  StartDate >= ? AND EndDate <= ? AND p.EmployeeID = ?";
//        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
//        String sql = "SELECT ShiftID, ShiftName, StartTime, EndTime, StartDate, EndDate, CoefSalary FROM Shifts WHERE StartDate >= ? AND EndDate <= ? AND (EmployeeIDFirst = ? OR EmployeeIDSecond = ? ) ";
  String sql = "SELECT p.ShiftID, p.ShiftName, p.EmployeeRole, p.StartTime, p.EndTime, p.StartDate, p.EndDate, p.CoefSalary, p.EmployeeIDFirst, p.EmployeeIDSecond, "
                + "c1.EmployeeName AS EmployeeNameFirst, c1.EmployeeAvatar AS EmployeeAvatarFirst, c2.EmployeeName AS EmployeeNameSecond, c2.EmployeeAvatar AS EmployeeAvatarSecond "
                + "FROM Shifts p LEFT JOIN Employee c1 ON p.EmployeeIDFirst = c1.EmployeeID LEFT JOIN Employee c2 ON p.EmployeeIDSecond = c2.EmployeeID "
                    + " WHERE StartDate >= ? AND EndDate <= ? AND (EmployeeIDFirst = ? OR EmployeeIDSecond = ? ) ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, startdate);
            ps.setString(2, enddate);
            ps.setInt(3, id);
            ps.setInt(4, id);
                    
            
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkSheetDTO p = new WorkSheetDTO();
                p.setShiftid(rs.getInt(1));
                p.setShiftname(rs.getString(2));
                p.setEmployeerole(rs.getInt(3));
                p.setStarttime(rs.getTime(4));
                p.setEndtime(rs.getTime(5));
                p.setStartdate(rs.getDate(6));
// Lấy ngày của tuần từ trường startdate
                Date startDate = p.getStartdate();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startDate);
                int dayOfWeekStart = calendarStart.get(Calendar.DAY_OF_WEEK);
                p.setDayofweekfirst(dayOfWeekStart);

                p.setEnddate(rs.getDate(7));
// Lấy ngày của tuần từ trường enddate
                Date endDate = p.getEnddate();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(endDate);
                int dayOfWeekEnd = calendarEnd.get(Calendar.DAY_OF_WEEK);
                p.setDayofweeksecond(dayOfWeekEnd);

                p.setCoefsalary(rs.getFloat(8));

                p.setWorksheetemployeefirstdto(new WorkSheetEmployeeFirstDTO(rs.getInt(9), rs.getString(11), rs.getString(12)));
                if (p.worksheetemployeefirstdto.getAvatar() != null && !p.worksheetemployeefirstdto.getAvatar().contains("http")) {
                    p.worksheetemployeefirstdto.setAvatar("./img/" + p.worksheetemployeefirstdto.getAvatar());
                }
                p.setWorksheetemployeeseconddto(new WorkSheetEmployeeSecondDTO(rs.getInt(10), rs.getString(13), rs.getString(14)));
                if (p.worksheetemployeeseconddto.getAvatar() != null && !p.worksheetemployeeseconddto.getAvatar().contains("http ")) {
                    p.worksheetemployeeseconddto.setAvatar("./img/" + p.worksheetemployeeseconddto.getAvatar());
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    // show information employee
    public List<Employee> getEmployeeinfo(String id) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone, r.RoleName, e.EmployeeStatus FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId WHERE EmployeeID = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
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
            Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public boolean Deleteemployeefirst(String shiftid, String employeeid)
            throws SQLException, SQLException {
        Connection con = null;
        PreparedStatement stm = null; // phải = null
        boolean result = false;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {

                //2. Write SQL command
//                String sql = "UPDATE Shifts SET EmployeeID = NULL,  WHERE ShiftID = ?";
                String sql = "UPDATE Shifts SET EmployeeIDFirst = NULL WHERE ShiftID = ? AND EmployeeIDFirst = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql); //Nạp tham số 1 lần cho Statement
                stm.setString(1, shiftid);
                stm.setString(2, employeeid);

                //4. Execute Statement Object to get result
                int effectRow = stm.executeUpdate();
                //Nạp tham số 1 lần cho Statement
                //5. Process result
                if (effectRow > 0) {
                    result = true;
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

    public boolean Deleteemployeesecond(String shiftid, String employeeid)
            throws SQLException, SQLException {
        Connection con = null;
        PreparedStatement stm = null; // phải = null
        boolean result = false;
        try {
            //1. Connect DB
            con = DBUtils.getConnection();
            if (con != null) {

                //2. Write SQL command
//                String sql = "UPDATE Shifts SET EmployeeID = NULL,  WHERE ShiftID = ?";
                String sql = "UPDATE Shifts SET EmployeeIDSecond = NULL WHERE ShiftID = ? AND EmployeeIDSecond = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql); //Nạp tham số 1 lần cho Statement
                stm.setString(1, shiftid);
                stm.setString(2, employeeid);

                //4. Execute Statement Object to get result
                int effectRow = stm.executeUpdate();
                //Nạp tham số 1 lần cho Statement
                //5. Process result
                if (effectRow > 0) {
                    result = true;
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

    public boolean Create(LocalDate x, LocalDate y) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            String sql1 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 1', 2, '06:00:00', '12:00:00', ?, ?, 1.5, NULL, NULL)";
            String sql2 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 2', 2, '12:00:00', '18:00:00', ?, ?, 1.5, NULL, NULL)";
            String sql3 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 3', 2, '18:00:00', '06:00:00', ?, ?, 2, NULL, NULL)";
            String sql4 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 1', 3, '06:00:00', '18:00:00', ?, ?, 1.5, NULL, NULL)";
            String sql5 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 2', 3, '18:00:00', '06:00:00', ?, ?, 2, NULL, NULL)";

            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql2);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql3);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(y));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql4);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql5);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(y));
            ps.executeUpdate();

            result = true; // Đánh dấu thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public boolean Create_sunday(LocalDate x, LocalDate y) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            String sql1 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 1', 2, '06:00:00', '12:00:00', ?, ?, 3, NULL, NULL)";
            String sql2 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 2', 2, '12:00:00', '18:00:00', ?, ?, 3, NULL, NULL)";
            String sql3 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 3', 2, '18:00:00', '06:00:00', ?, ?, 4, NULL, NULL)";
            String sql4 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 1', 3, '06:00:00', '18:00:00', ?, ?, 3, NULL, NULL)";
            String sql5 = "INSERT INTO Shifts(ShiftName, EmployeeRole, StartTime, EndTime, StartDate, EndDate, CoefSalary, EmployeeIDFirst, EmployeeIDSecond ) "
                    + "VALUES ('Slot 2', 3, '18:00:00', '06:00:00', ?, ?, 4, NULL, NULL)";

            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql2);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql3);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(y));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql4);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(x));
            ps.executeUpdate();

            ps = conn.prepareStatement(sql5);
            ps.setDate(1, Date.valueOf(x));
            ps.setDate(2, Date.valueOf(y));
            ps.executeUpdate();

            result = true; // Đánh dấu thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public boolean checkduplicatedate(String startdate) {
        boolean isDuplicate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM Shifts WHERE StartDate = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, startdate);
           

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
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return isDuplicate;
        }
    }

    public String check_duplicate_sl_first(String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT EmployeeIDSecond FROM Shifts WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftid);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("EmployeeIDSecond");
            }
        } catch (SQLException e) {
            // Handle the exception
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
        return result;
    }
    
      public String check_duplicate_sl_second(String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT EmployeeIDFirst FROM Shifts WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftid);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("EmployeeIDFirst");
            }
        } catch (SQLException e) {
            // Handle the exception
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
        return result;
    }
      
        
    public String check_duplicate_emp_first(String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT EmployeeIDSecond FROM Shifts WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftid);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("EmployeeIDSecond");
            }
        } catch (SQLException e) {
            // Handle the exception
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
        return result;
    }
    
    

    public String check_duplicate_emp_second(String shiftid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT EmployeeIDFirst FROM Shifts WHERE ShiftID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, shiftid);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("EmployeeIDFirst");
            }
        } catch (SQLException e) {
            // Handle the exception
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
        return result;
    }
    
  
    
    
    
    public List<Employee> getAll_sales_guard() {
        List<Employee> list = new ArrayList<>();
String sql = "SELECT e.EmployeeID, e.EmployeeName, e.EmployeeAvatar, e.EmployeeAddress, e.Birthday, e.EmployeeSex, e.EmployeePhone, r.RoleName, e.EmployeeStatus FROM Employee e JOIN Roles r ON e.RoleId = r.RoleId WHERE e.RoleId = 2 OR e.RoleId = 3";

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
    
    
    public Shift getShift(int role) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT *\n"
                    + "  FROM [SWPProject2].[dbo].[Shifts] where StartDate = Convert(date,GETDATE()) and StartTime <= Convert(time,GETDATE()) and (EndTime >= Convert(time,GETDATE()) \n"
                    + "  or EndDate > Convert(date,GETDATE())) and EmployeeRole = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    Shift s = new Shift();
                    s.setId(rs.getInt(1));
                    s.setName(rs.getString(2));
                    s.setEmployeeRole(rs.getInt(3));
                    s.setStartTime(rs.getTime(4));
                    s.setEndTime(rs.getTime(5));
                    s.setStartDate(rs.getDate(6));
                    s.setEndDate(rs.getDate(7));
                    s.setCoef(rs.getDouble(8));
                    s.setEmployeeFirst(rs.getInt(9));
                    s.setEmployeeSecond(rs.getInt(10));
                    return s;
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
        return null;
    }
    
     public void insertAttendance(int shiftID, int employeeID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO [dbo].[Attendance]\n"
                + "           ([CheckInDate]\n"
                + "           ,[CheckInTime]\n"
                + "           ,[LateTime]\n"
                + "           ,[EmployeeID]\n"
                + "           ,[ShiftID])\n"
                + "     VALUES\n"
                + "           (CONVERT(date,GETDATE())\n"
                + "           ,CONVERT(time,GETDATE())\n"
                + "           ,DATEDIFF(second,convert(time,GETDATE()),(Select StartTime from Shifts where ShiftID = ?))\n"
                + "           ,?\n"
                + "           ,?)";
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shiftID);
            ps.setInt(2, employeeID);
            ps.setInt(3, shiftID);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAtt(int shiftID, int employeeID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE [dbo].[Attendance]\n"
                + "   SET \n"
                + "      [CheckOutDate] = CONVERT(date,GETDATE())\n"
                + "      ,[CheckOutTime] = CONVERT(time,GETDATE())\n"
                + "      ,[SoonTime] = DATEDIFF(second,convert(time,GETDATE()),(Select StartTime from Shifts where ShiftID = ?))\n"
                + " WHERE EmployeeID = ? and ShiftID = ?";
        conn = DBUtils.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shiftID);
            ps.setInt(2, employeeID);
            ps.setInt(3, shiftID);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(WorkSheetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Time check_time_inthepast(String shiftid) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Time result = null;
    try {
        conn = DBUtils.getConnection();
        String sql = "SELECT StartTime FROM Shifts WHERE ShiftID = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, shiftid);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getTime("StartTime");
        }
    } catch (SQLException e) {
        // Handle the exception
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
    return result;
}
    
     public String check_date_inthepast (String shiftid) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String result = null;
    try {
        conn = DBUtils.getConnection();
        String sql = "SELECT StartDate FROM Shifts WHERE ShiftID = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, shiftid);
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString("StartDate");
        }
    } catch (SQLException e) {
        // Handle the exception
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
    return result;
}
   
        
 public int import_getShiftID(LocalDate startdate) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int result = 0; // Giá trị mặc định nếu không có kết quả

    try {
        conn = DBUtils.getConnection();
        String sql = "SELECT ShiftID FROM Shifts WHERE StartDate = ? ";
        ps = conn.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(startdate));

        rs = ps.executeQuery();
        if (rs.next()) {
            result = Integer.parseInt(rs.getString("ShiftID"));
        }
    } catch (SQLException e) {
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

    return result;
}

    
     public String import_getEmployeeIDFirst(int  shiftid){
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String result = null;
    try {
        conn = DBUtils.getConnection();
        String sql = "SELECT EmployeeIDFirst FROM Shifts WHERE ShiftID = ? ";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, shiftid);
     
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString("EmployeeIDFirst");
        }
    } catch (SQLException e) {
   
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
    return result;
}
     
       public String import_getEmployeeIDSecond(int  shiftid){
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String result = null;
    try {
        conn = DBUtils.getConnection();
        String sql = "SELECT EmployeeIDSecond FROM Shifts WHERE ShiftID = ? ";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, shiftid);
     
        rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getString("EmployeeIDSecond");
        }
    } catch (SQLException e) {
   
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
    return result;
}

    public boolean set_employeeIDfirst(int shiftid, String employeeidfirst) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "UPDATE Shifts SET EmployeeIDFirst = ?  WHERE ShiftID = ? ";
    try {
        conn = DBUtils.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, employeeidfirst);
        ps.setInt(2, shiftid);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Handle the exception
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
    return false;
}
    
      public boolean set_employeeIDsecond(int shiftid, String employeeidsecond) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "UPDATE Shifts SET EmployeeIDSecond = ?  WHERE ShiftID = ? ";
    try {
        conn = DBUtils.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, employeeidsecond);
        ps.setInt(2, shiftid);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        // Handle the exception
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
    return false;
}
    

}
