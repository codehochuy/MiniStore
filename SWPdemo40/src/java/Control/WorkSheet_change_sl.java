/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.WorkSheetDAO;
import DTO.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vuong
 */
public class WorkSheet_change_sl extends HttpServlet {

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
            out.println("<title>Servlet WorkSheet_addmorefirst</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_addmorefirst at " + request.getContextPath() + "</h1>");
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
       String[] selectedShiftIds = request.getParameterValues("selectedShiftIds");
//        System.out.println(selectedShiftIds);
List<String> shiftIdList = new ArrayList<>();

if (selectedShiftIds != null && selectedShiftIds.length > 0) {
    for (String shiftId : selectedShiftIds) {
        shiftIdList.add(shiftId);
    }
}

//        System.out.println(shiftIdList);
        
         WorkSheetDAO dao = new WorkSheetDAO();
          List<Employee> dto = dao.getAllemployee(employeerole);
           
         
           
           
                request.setAttribute("LIST", dto);
                
                 request.setAttribute("shiftIdList", shiftIdList);
//                 System.out.println(shiftIdList);
          request.getRequestDispatcher("WorkSheet_change_sl_first.jsp").forward(request, response);
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
       String[] selectedShiftIds = request.getParameterValues("selectedShiftIds");
       
List<String> shiftIdList = new ArrayList<>();

if (selectedShiftIds != null && selectedShiftIds.length > 0) {
    for (String shiftId : selectedShiftIds) {
        shiftIdList.add(shiftId);
    }
}

//        System.out.println(shiftIdList);
        
         WorkSheetDAO dao = new WorkSheetDAO();
          List<Employee> dto = dao.getAllemployee(employeerole);
           
         
           
           
                request.setAttribute("LIST", dto);
                
                 request.setAttribute("shiftIdList", shiftIdList);
//                 System.out.println(shiftIdList);
          request.getRequestDispatcher("WorkSheet_change_sl_second.jsp").forward(request, response);
      
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
