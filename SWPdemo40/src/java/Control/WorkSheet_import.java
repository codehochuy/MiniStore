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
import java.util.Date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vuong
 */
public class WorkSheet_import extends HttpServlet {

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
            out.println("<title>Servlet WorkSheet_import</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_import at " + request.getContextPath() + "</h1>");
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

      
      String startdate = request.getParameter("startdate");
String enddate = request.getParameter("enddate");
String startdate_s = request.getParameter("startdate_s");
String enddate_s = request.getParameter("enddate_s");

 if (startdate == "" || enddate == "" || startdate_s == "" || enddate_s == "" ) {
            request.setAttribute("Message", "Không được để trống");
            request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
            
        }
LocalDate startDate = LocalDate.parse(startdate);
LocalDate endDate = LocalDate.parse(enddate);
LocalDate startDate_s = LocalDate.parse(startdate_s);
LocalDate endDate_s = LocalDate.parse(enddate_s);


long checktotal = ChronoUnit.DAYS.between(startDate, endDate);
long checktotal2 = ChronoUnit.DAYS.between(startDate_s, endDate_s);

LocalDate currentDate = LocalDate.now();


if (checktotal !=checktotal2) {
    request.setAttribute("Message", "Thất bại, vui lòng lựa chọn khoảng thời gian tương đương");
    request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
}

else if (startDate_s.isBefore(currentDate) || endDate_s.isBefore(currentDate)) {
    request.setAttribute("Message", "Thất bại, không thể chỉnh sửa lịch trong quá khứ");
    request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
}


else if (endDate.isBefore(startDate) || endDate_s.isBefore(startDate_s)) {
    request.setAttribute("Message", "Thất bại, sai định dạng ngày");
    request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
}

else if (startDate_s.isBefore(startDate) || endDate_s.isBefore(endDate) ) {
        request.setAttribute("Message", "Thất bại, sai định dạng ngày");
    request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
}
else if (startDate.equals(endDate) || startDate_s.equals(endDate_s) || startDate.equals(startDate_s) || endDate.equals(endDate_s)) {
    request.setAttribute("Message", "Thất bại, sai định dạng ngày");
    request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);
}else{


// Tính toán số ngày giữa hai ngày
long total = ChronoUnit.DAYS.between(startDate, endDate) + 1;
long totalID = total * 5;

WorkSheetDAO dao = new WorkSheetDAO();
int idfirst = dao.import_getShiftID(startDate);
int idsecond = dao.import_getShiftID(startDate_s);

int times = 1;

while (times <= totalID) {
    String employeeidfirst = dao.import_getEmployeeIDFirst(idfirst);
    boolean s1 = dao.set_employeeIDfirst(idsecond, employeeidfirst);
    String employeeidsecond = dao.import_getEmployeeIDSecond(idfirst);
    boolean s2 = dao.set_employeeIDsecond(idsecond, employeeidsecond);
    
    times++;
    idfirst++;
    idsecond++;
   
}

 request.setAttribute("Message", "Thành công");
                request.getRequestDispatcher("WorkSheet_import.jsp").forward(request, response);

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
