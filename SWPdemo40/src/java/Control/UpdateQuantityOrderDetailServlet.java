/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DTO.Voucher;
import DAO.OrderDAO;
import DTO.OrderDetail;
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
@WebServlet(name = "UpdateQuantityOrderDetailServlet", urlPatterns = {"/UpdateQuantityOrderDetailServlet"})
public class UpdateQuantityOrderDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final OrderDAO objectDao = new OrderDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateQuantityOrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateQuantityOrderDetailServlet at " + request.getContextPath() + "</h1>");
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

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));

        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        String employeeName = request.getParameter("employeeName");

        List listEmployees = objectDao.getAllEmployee();
        List listVouchers = objectDao.getAllVouchers();
        int availableQuantity = objectDao.getRemainQuantityByProductID(productID,orderID);
        
        request.setAttribute("orderID", orderID);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phone", phone);
//        request.setAttribute("voucherID", voucherID);
        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);

        request.setAttribute("listEmployees", listEmployees);
        request.setAttribute("listVouchers", listVouchers);

        Voucher voucher = objectDao.getVoucherByID(voucherID);

        

        String messAddProductOrderDetail = (String) request.getAttribute("messAddProductOrderDetail");

        request.setAttribute("messAddProductOrderDetail", messAddProductOrderDetail);
        //update quantity
        if (quantity > 0 && quantity <= availableQuantity) {
            objectDao.updateQuantityOrderDetail(orderID, productID, quantity);
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
            request.setAttribute("messUpdateQuantity", "Cập nhật số lượng sản phẩm thành công!");

            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        } else if(quantity < 0){
            request.setAttribute("messUpdateQuantity", "Cập nhật số lượng sản phẩm thất bại! Vui lòng nhập số lượng hợp lệ (Lớn hơn 0).");
            List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
            request.setAttribute("voucher", voucher);
            request.setAttribute("voucherID", voucherID);
            request.setAttribute("listOrderDetails", listOrderDetails);
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        }  else if(quantity > availableQuantity){
            request.setAttribute("messUpdateQuantity", "Cập nhật số lượng sản phẩm thất bại! Số lượng sản phẩm trong kho không đủ.");
            List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
            request.setAttribute("voucher", voucher);
            request.setAttribute("voucherID", voucherID);
            request.setAttribute("listOrderDetails", listOrderDetails);
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        }
        //order servlet

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
