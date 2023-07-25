/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DTO.Customer;
import DTO.Order7;
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
@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/UpdateOrderServlet"})
public class UpdateOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateOrderServlet at " + request.getContextPath() + "</h1>");
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
        //Order servlet
        request.setCharacterEncoding("UTF-8");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String customerName = request.getParameter("customerName");
        String phone = request.getParameter("phone");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        String employeeName = request.getParameter("employeeName");

        List listEmployees = objectDao.getAllEmployee();
        List listVouchers = objectDao.getAllVouchers();
        List listCustomers = objectDao.getAllCustomer();

        request.setAttribute("orderID", orderID);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phone", phone);
        request.setAttribute("voucherID", voucherID);
        request.setAttribute("orderDate", orderDate);
        request.setAttribute("employeeName", employeeName);

        request.setAttribute("listEmployees", listEmployees);
        request.setAttribute("listVouchers", listVouchers);
        request.setAttribute("listCustomers", listCustomers);

        //Order detail
        Voucher voucher = objectDao.getVoucherByID(voucherID);
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
        request.setAttribute("listOrderDetails", listOrderDetails);
        request.setAttribute("voucher", voucher);

        String messUpdateQuantity = (String) request.getAttribute("messUpdateQuantity");
        String messAddProductOrderDetail = (String) request.getAttribute("messAddProductOrderDetail");

        request.setAttribute("messUpdateQuantity", messUpdateQuantity);
        request.setAttribute("messAddProductOrderDetail", messAddProductOrderDetail);

        request.getRequestDispatcher("updateOrder.jsp").forward(request, response);

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
        request.setCharacterEncoding("UTF-8");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String phone = request.getParameter("phone");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Date orderDate = Date.valueOf(request.getParameter("orderDate"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        int customerID = objectDao.getCustomerIDByPhone(phone);

        Voucher voucher = objectDao.getVoucherByID(voucherID);
        List<OrderDetail> listOrderDetails = objectDao.getOrderDetail(orderID);
        int total = 0;
        for (OrderDetail listOrderDetail : listOrderDetails) {
            total += listOrderDetail.getProductPrice() * listOrderDetail.getQuantity();
        }

        if (customerID == -1) {
            request.setAttribute("mess", "Cập nhật thất bại! Số điện thoại không nằm trong danh sách khách hàng thân thiết.");
            OrderDAO list = new OrderDAO();
            List<Order7> listOrders = list.getAllOrders();
            request.setAttribute("listOrders", listOrders);
            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
        } else if (total < voucher.getCondition()) {
            request.setAttribute("mess", "Cập nhật thất bại! Hóa đơn không đạt yêu cầu sử dụng voucher! Vui lòng kiểm tra lại.");
            OrderDAO list = new OrderDAO();
            List<Order7> listOrders = list.getAllOrders();
            request.setAttribute("listOrders", listOrders);
            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
        } else {
            objectDao.updateOrder(orderID, orderDate, customerID, voucherID, employeeID);
            request.setAttribute("mess", "Cập nhật đơn hàng thành công!");
            OrderDAO list = new OrderDAO();
            List<Order7> listOrders = list.getAllOrders();
            request.setAttribute("listOrders", listOrders);
            //sss
            String customerName = objectDao.getCustomerNameByPhone(phone);
            String employeeName = objectDao.getEmployeeNameByID(employeeID);
            String voucherName = objectDao.getVoucherNameByID(voucherID);
            request.setAttribute("orderID", orderID);
            request.setAttribute("customerName", customerName);
            request.setAttribute("phone", phone);
            request.setAttribute("voucherID", voucherID);
            request.setAttribute("voucherName", voucherName);
            request.setAttribute("orderDate", orderDate);
            request.setAttribute("employeeName", employeeName);
            request.setAttribute("listOrderDetails", listOrderDetails);

            request.setAttribute("voucher", voucher);
            request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
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
