/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DTO.Order;
import DTO.OrderDetail;
import DTO.Voucher;
import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/OrderDetailServlet"})
public class OrderDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final OrderDAO objectDao = new OrderDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        String voucherName = request.getParameter("voucherName");
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        String employeeName = request.getParameter("employeeName");

        List listEmployees = objectDao.getAllEmployee();
        List listVouchers = objectDao.getAllVouchers();

        request.setAttribute("orderID", orderID);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phone", phone);
        request.setAttribute("voucherID", voucherID);
        request.setAttribute("voucherName", voucherName);
        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);
        Voucher voucher = objectDao.getVoucherByID(voucherID); 
        List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
        
        request.setAttribute("listOrderDetails", listOrderDetails);
        
        request.setAttribute("voucher", voucher);
        
        String messUpdateQuantity = (String) request.getAttribute("messUpdateQuantity");
        String messAddProductOrderDetail = (String) request.getAttribute("messAddProductOrderDetail");
        
        request.setAttribute("messUpdateQuantity", messUpdateQuantity);
        request.setAttribute("messAddProductOrderDetail", messAddProductOrderDetail);
        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
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
