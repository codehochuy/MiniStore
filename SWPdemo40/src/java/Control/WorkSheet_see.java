/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.EmployeeDAO;
import DAO.WorkSheetDAO;
import DTO.Employee;
import DTO.WorkSheetDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vuong
 */
public class WorkSheet_see extends HttpServlet {

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
            out.println("<title>Servlet WorkSheet_seeworkingtime</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_seeworkingtime at " + request.getContextPath() + "</h1>");
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
        
        
       WorkSheetDAO dao = new WorkSheetDAO();
        List<Employee> employeeDTO = dao.getAll_sales_guard();
        request.setAttribute("LIST", employeeDTO);
        request.getRequestDispatcher("WorkSheet_see.jsp").forward(request, response);
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

        
        

        // lấy input
        String employeeid = request.getParameter("employeeid");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        
        if (startdate == "" || enddate ==""){
                    request.setAttribute("messageerror", "Vui lòng chọn thời gian");
                    request.getRequestDispatcher("WorkSheet_see.jsp").forward(request, response);
        }else{
        
       

        WorkSheetDAO dao = new WorkSheetDAO();
        List<WorkSheetDTO> dto = dao.Seeworkingtime(employeeid, startdate, enddate);
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
