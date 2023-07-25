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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class WorkSheet_load extends HttpServlet {

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



                 String id = request.getParameter("id");
                      String employeerole = request.getParameter("employeerole");
                      request.setAttribute("employeerole", employeerole);
                

                   WorkSheetDAO dao = new WorkSheetDAO();
            WorkSheetDTO dto = dao.getbyid(id);


            
                    try (PrintWriter out = response.getWriter()){
                  out.println("<form action=\"WorkSheet_update\" method=\"POST\" id=\"update\">\n");
    out.println("    <input type=\"hidden\" name=\"employeerole\" value=\"" + employeerole + "\" />\n");

                     out.println("                         <div class=\"row\">\n");
                     out.println("                               <div class=\"form-group col-md-6\">\n");
                     out.println("                                   <label class=\"control-label\">ID ca làm việc </label>\n");
                    out.println("                                 <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"shiftid\" value=\"" + dto.getShiftid() + "\" >\n");
                     out.println("                               </div>\n");
                            
                    out.println("                               <div class=\"form-group col-md-6\">\n");
                    out.println("                                    <label class=\"control-label\">Hệ số lương </label>\n");
                     out.println("                                 <input class=\"form-control\" type=\"number\" name=\"coefsalary\" value=\"" + dto.getCoefsalary() + "\" step=\"0.1\" min=\"0\">\n");
                     out.println("                             </div>\n");
                     
 
                     
                     
                       out.println("                               <div class=\"form-group col-sm-2\">\n");
                out.println("                             </div>\n");
              

//                     out.println("      &nbsp;<!-- Khoảng trống -->\n");
//                    out.println("        &nbsp;<!-- Khoảng trống -->\n");
//                   out.println("        &nbsp;<!-- Khoảng trống -->\n");
//                   

                       
                     out.println("                           <button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n");
                   out.println("        &nbsp;<!-- Khoảng trống -->\n");
                    out.println("        &nbsp;<!-- Khoảng trống -->\n");
                     out.println("                           <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"#\">Hủy bỏ</a>\n");
                    out.println("                       <BR>\n");
                            
                out.println("</form>");
             
                 
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
        processRequest(request, response);
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
        processRequest(request, response);
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
