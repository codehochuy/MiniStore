/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.OrderDAO;
import DTO.OrderDetail;
import DTO.Voucher;
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
@WebServlet(name = "DeleteOrderDetailServlet", urlPatterns = {"/DeleteOrderDetailServlet"})
public class DeleteOrderDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet DeleteOrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteOrderDetailServlet at " + request.getContextPath() + "</h1>");
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
    private final OrderDAO objectDao = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        String employeeName = request.getParameter("employeeName");

        List listEmployees = objectDao.getAllEmployee();
        List listVouchers = objectDao.getAllVouchers();

        request.setAttribute("orderID", orderID);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phone", phone);

        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);

        request.setAttribute("listEmployees", listEmployees);
        request.setAttribute("listVouchers", listVouchers);

        //Order detail
        Voucher voucher = objectDao.getVoucherByID(voucherID);

        

        String messUpdateQuantity = (String) request.getAttribute("messUpdateQuantity");
        String messAddProductOrderDetail = (String) request.getAttribute("messAddProductOrderDetail");

        request.setAttribute("messUpdateQuantity", messUpdateQuantity);
        request.setAttribute("messAddProductOrderDetail", messAddProductOrderDetail);

        //delete product in order detail
        int productID = Integer.parseInt(request.getParameter("productID"));
        objectDao.deleteOrderDetail(productID, orderID);
        
        List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
        int total = 0;
        for (OrderDetail listOrderDetail : listOrderDetails) {
            total += listOrderDetail.getProductPrice() * listOrderDetail.getQuantity();
        }
        if (total < voucher.getCondition()) {
            objectDao.updateNoVoucherForOrder(orderID);
            voucherID = 1;
            voucher = objectDao.getVoucherByID(voucherID);
            request.setAttribute("messUpdateNoVoucher", "Hóa đơn không đạt yêu cầu sử dụng voucher! Vui lòng kiểm tra lại.");
        }
        request.setAttribute("voucher", voucher);
        request.setAttribute("voucherID", voucherID);
        request.setAttribute("listOrderDetails", listOrderDetails);
        request.getRequestDispatcher("updateOrder.jsp").forward(request, response);

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
