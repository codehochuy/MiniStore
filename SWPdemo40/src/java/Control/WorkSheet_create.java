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
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vuong
 */
public class WorkSheet_create extends HttpServlet {

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
            out.println("<title>Servlet WorkSheet_create</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_create at " + request.getContextPath() + "</h1>");
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
        if (startdate == "" || enddate == "") {
            request.setAttribute("Message", "Không được để trống");
            request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);
            
        } 
        LocalDate startDate = LocalDate.parse(startdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(enddate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate currentDate = LocalDate.now();
        //kiểm tra điều kiện
        if (startdate == "" || enddate == "") {
            request.setAttribute("Message", "Không được để trống");
            request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);
            
        } 
        
            else if (startDate.isBefore(currentDate)) {
        request.setAttribute("Message", "Không thể tạo lịch làm việc trong quá khứ ");
        request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);
    }
            else  if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
                request.setAttribute("Message", "Sai định dạng thời gian");
                request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);
                //kiểm tra điều kiện
            } else{
            
            LocalDate x = startDate;
            LocalDate y = startDate.plusDays(1);
            //kiểm tra điều kiện
            WorkSheetDAO dao = new WorkSheetDAO();
            boolean checkduplicate = dao.checkduplicatedate(startdate);
            //kiểm tra điều kiện
             if (checkduplicate) {
                request.setAttribute("Message", "Lịch làm việc đã được tạo");
                request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);
            } else {

                while (x.isBefore(endDate.minusDays(1)) || x.equals(endDate.minusDays(1))) {

                    if (x.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        boolean s = dao.Create_sunday(x, y);
                    } else {
                        boolean s = dao.Create(x, y);
                    }
                    x = x.plusDays(1);
                    y = y.plusDays(1);
                }
// Đảm bảo giá trị cuối cùng của x là endDate - 1 và y là endDate
                if (x.equals(endDate.minusDays(1)) && y.equals(endDate)) {

                    if (x.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        boolean s2 = dao.Create_sunday(x, y);
                    } else {
                        boolean s2 = dao.Create(x, y);
                    }
                }
                request.setAttribute("Message", "Tạo thành công");
                request.getRequestDispatcher("WorkSheet_create.jsp").forward(request, response);

            }
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
