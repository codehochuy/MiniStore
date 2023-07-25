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
import java.text.SimpleDateFormat;
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
public class WorkSheet_delete extends HttpServlet {

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
// Lấy ngày hiện tại
        Date currentDate = new Date();
        // Chuyển đổi sang định dạng ngày tháng mong muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        
        

String shiftid = request.getParameter("shiftid");
String employeeidfirst = request.getParameter("employeeidfirst");
     

        
   WorkSheetDAO dao = new WorkSheetDAO();
   boolean result;
        try {
            
            result = dao.Deleteemployeefirst(shiftid,employeeidfirst);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           List<WorkSheetDTO> dto = dao.getAllbycurrentdate(formattedDate);
            request.setAttribute("LIST", dto);
             request.getRequestDispatcher("WorkSheet.jsp").forward(request, response);
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
      
 Date currentDate = new Date();
        // Chuyển đổi sang định dạng ngày tháng mong muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        
        

String shiftid = request.getParameter("shiftid");
String employeeid = request.getParameter("employeeid");

        
   WorkSheetDAO dao = new WorkSheetDAO();
   boolean result;
        try {
            
            result = dao.Deleteemployeesecond(shiftid,employeeid);
        } catch (SQLException ex) {
            Logger.getLogger(WorkSheet_delete.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result = true) {
           List<WorkSheetDTO> dto = dao.getAllbycurrentdate(formattedDate);
            request.setAttribute("LIST", dto);
             request.getRequestDispatcher("WorkSheet.jsp").forward(request, response);
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
