/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Control;

import DAO.EmployeeDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DTO.Employee;
import DTO.Order;
import DTO.Product;
import DTO.RevunueDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author kienb
 */
public class ManageRevunue extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            EmployeeDAO empDAO = new EmployeeDAO();
            List<Employee> listEmp = empDAO.getListQuitjob();
            int empTotal = empDAO.getTotal();
            ProductDAO pdDAO = new ProductDAO();
            int productTotal = pdDAO.getTotal();
            List<Product> listPro = pdDAO.getListOutStock();
            OrderDAO od = new OrderDAO();
            List<Order> listOrder = od.getAll();
            List<Product> list = od.getProduct();
            RevunueDTO revunueDTO = new RevunueDTO(empTotal, productTotal, listOrder, listPro,listEmp,list);
            double total = listOrder.stream().map(Order::getTotal).reduce(0d,Double::sum);
            request.setAttribute("data", revunueDTO);
            request.setAttribute("totalMoney", total);
            request.getRequestDispatcher("ManageRevunue.jsp").forward(request, response);
        }
  
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
