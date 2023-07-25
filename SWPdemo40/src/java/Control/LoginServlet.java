/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.EmployeeDAO;
import DAO.WorkSheetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DTO.Employee;
import DTO.Shift;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        EmployeeDAO dao = new EmployeeDAO();
//        && user.getEmployeePassword().equals(password)
        Employee user = (Employee) dao.detailsEmployee(username, password);
        // id phải có

        //
        if (user != null) {
            if (user.isEmployeestatus() == true) {
                if (user.getEmprole().getName().equalsIgnoreCase("Manager")) {
                    session.setAttribute("USER", user);
                    response.sendRedirect("revunue");

                } else if (user.getEmprole().getName().equalsIgnoreCase("Sales") || user.getEmprole().getName().equalsIgnoreCase("Guard")) {
                    int id = user.getEmployeeID();
           
                    session.setAttribute("USER", user);
                    session.setAttribute("ID", id);
                    WorkSheetDAO wsd = new WorkSheetDAO();
                    Shift shift = wsd.getShift(user.getEmprole().getId());
                    if(shift != null && (id == shift.getEmployeeFirst() || id == shift.getEmployeeSecond())){
                        session.setAttribute("isWork", true);                       
                        if(user.getEmprole().getId() == 3){
                            session.setAttribute("isPay",false);
                        }
                        else {
                            session.setAttribute("isPay",true);
                        }
                    }
                    
//                    response.sendRedirect("WorkSheet_show_sales");
                      request.getRequestDispatcher("WorkSheet_show_sales").forward(request, response);
                    
               
                }
            } else {
                request.setAttribute("mess", "Tài khoản của bạn đã bị vô hiệu hóa");
                request.getRequestDispatcher("Login.jsp").forward(request, response);

            }

        } else {
            request.setAttribute("mess", "Tài khoản hoặc mật khẩu không đúng");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
