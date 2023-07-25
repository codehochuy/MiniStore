/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.WorkSheetDAO;
import DTO.WorkSheetDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vuong
 */
public class WorkSheet_delete_search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WorkSheet_delete_search</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_delete_search at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String employeerole = request.getParameter("employeerole");
         
        List<Integer> listshiftid = (List<Integer>) request.getSession().getAttribute("LISTSHIFTID");

String shiftid = request.getParameter("shiftid");
String employeeidfirst = request.getParameter("employeeidfirst");

   // lấy ngày hiện tại
         Date currentDate = new Date();
       // Chuyển đổi sang định dạng ngày tháng mong muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String formattedDate = dateFormat.format(currentDate);
         
         // Lấy thời gian hiện tại
LocalTime currentLocalTime = LocalTime.now();

         
WorkSheetDAO dao = new WorkSheetDAO();
Time checktime = dao.check_time_inthepast(shiftid);
// Chuyển đổi checktime sang LocalTime
LocalTime checkTimeLocal = checktime.toLocalTime();
String checkDateStr = dao.check_date_inthepast(shiftid);


// So sánh ngày của hai chuỗi
int compareResult = formattedDate.compareTo(checkDateStr);

if (compareResult < 0) {
  
boolean result;
        try {
             result = dao.Deleteemployeefirst(shiftid,employeeidfirst);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           
List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);

        }

    } else if  (compareResult == 0  && (currentLocalTime.isBefore(checkTimeLocal)) ){
        
boolean result;
        try {
             result = dao.Deleteemployeefirst(shiftid,employeeidfirst);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           
List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);

    }
    }
    else{
          request.setAttribute("messageerror","Không thể chỉnh sửa ca làm việc đã qua");
          List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);
        
    }
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         String employeerole = request.getParameter("employeerole");
    
        List<Integer> listshiftid = (List<Integer>) request.getSession().getAttribute("LISTSHIFTID");

String shiftid = request.getParameter("shiftid");
String employeeidsecond = request.getParameter("employeeidsecond");



  // lấy ngày hiện tại
         Date currentDate = new Date();
       // Chuyển đổi sang định dạng ngày tháng mong muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String formattedDate = dateFormat.format(currentDate);
         
         // Lấy thời gian hiện tại
LocalTime currentLocalTime = LocalTime.now();

         
WorkSheetDAO dao = new WorkSheetDAO();
Time checktime = dao.check_time_inthepast(shiftid);
// Chuyển đổi checktime sang LocalTime
LocalTime checkTimeLocal = checktime.toLocalTime();
String checkDateStr = dao.check_date_inthepast(shiftid);


// So sánh ngày của hai chuỗi
int compareResult = formattedDate.compareTo(checkDateStr);

if (compareResult < 0) {
   boolean result;
        try {
            
            result = dao.Deleteemployeesecond(shiftid,employeeidsecond);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           
List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);

        }

    
  } else if  (compareResult == 0  && (currentLocalTime.isBefore(checkTimeLocal)) ){
       boolean result;
        try {
            
            result = dao.Deleteemployeesecond(shiftid,employeeidsecond);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           
List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);
      
  }
  }
  else{
         request.setAttribute("messageerror","Không thể chỉnh sửa ca làm việc đã qua");
         List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);
  }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
