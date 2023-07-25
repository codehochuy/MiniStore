/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DTO.OrderDetail;
import DAO.OrderDAO;
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
@WebServlet(name = "AddProductOrderDetailServlet", urlPatterns = {"/AddProductOrderDetailServlet"})
public class AddProductOrderDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductOrderDetailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductOrderDetailServlet at " + request.getContextPath() + "</h1>");
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
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        String employeeName = request.getParameter("employeeName");
        
        request.setAttribute("orderID", orderID);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phone", phone);
        request.setAttribute("voucherID", voucherID);
        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);
        
        request.getRequestDispatcher("addProductOrderDetail.jsp").forward(request, response);
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
        request.setAttribute("voucherID", voucherID);
        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);

        request.setAttribute("listEmployees", listEmployees);
        request.setAttribute("listVouchers", listVouchers);
        
        //Order detail
        Voucher voucher = objectDao.getVoucherByID(voucherID); 
        
        
        request.setAttribute("voucher", voucher);
        
        String messUpdateQuantity = (String) request.getAttribute("messUpdateQuantity");
        String messAddProductOrderDetail = (String) request.getAttribute("messAddProductOrderDetail");
        
        request.setAttribute("messUpdateQuantity", messUpdateQuantity);
        request.setAttribute("messAddProductOrderDetail", messAddProductOrderDetail);

        
        // add product order detail servlet
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int availableQuantity = objectDao.getRemainQuantityByProductID(productID);
        boolean checkProductID = objectDao.checkProductID(productID);
        boolean checkDuplication = false;
        if (quantity > 0 && checkProductID && quantity <= availableQuantity) {
            List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
            for (OrderDetail OrderDetail : listOrderDetails) {
                if (OrderDetail.getProductID() == productID){
                    objectDao.updateQuantityOrderDetail(orderID, productID, quantity + OrderDetail.getQuantity());
                    checkDuplication = true;
                }
            }
            if (!checkDuplication){
                objectDao.addProductOrderDetail(orderID, productID, quantity);
            }
            List<OrderDetail> listOrderDetails1 = objectDao.getOrderDetail(orderID);
            request.setAttribute("listOrderDetails", listOrderDetails1);
            request.setAttribute("messAddProductOrderDetail", "Thêm sản phẩm vào đơn hàng thành công!");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        } else if (quantity <= 0 && !checkProductID){
            List<OrderDetail> listOrderDetails1 = objectDao.getOrderDetail(orderID);
            request.setAttribute("listOrderDetails", listOrderDetails1);
            request.setAttribute("messAddProductOrderDetail", "Thêm sản phẩm vào đơn hàng thất bại! Vui lòng nhập số lượng hợp lệ (Lớn hơn 0) và ID sản phẩm không có trong danh sách.");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        } else if (quantity <= 0){
            List<OrderDetail> listOrderDetails1 = objectDao.getOrderDetail(orderID);
            request.setAttribute("listOrderDetails", listOrderDetails1);
            request.setAttribute("messAddProductOrderDetail", "Thêm sản phẩm vào đơn hàng thất bại! Vui lòng nhập số lượng hợp lệ (Lớn hơn 0).");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        } else if (!checkProductID){
            List<OrderDetail> listOrderDetails1 = objectDao.getOrderDetail(orderID);
            request.setAttribute("listOrderDetails", listOrderDetails1);
            request.setAttribute("messAddProductOrderDetail", "Thêm sản phẩm vào đơn hàng thất bại! ID sản phẩm không có trong danh sách.");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
        } else if (quantity > availableQuantity){
            List<OrderDetail> listOrderDetails1 = objectDao.getOrderDetail(orderID);
            request.setAttribute("listOrderDetails", listOrderDetails1);
            request.setAttribute("messAddProductOrderDetail", "Thêm sản phẩm vào đơn hàng thất bại! Số lượng sản phẩm không có đủ.");
            request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
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
