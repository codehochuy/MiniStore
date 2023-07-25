/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "UpdateVoucherServlet", urlPatterns = {"/UpdateVoucherServlet"})
public class UpdateVoucherServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateVoucherServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateVoucherServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        String voucherName = request.getParameter("voucherName");
        Date dateStart = Date.valueOf(request.getParameter("dateStart"));
        Date dateEnd = Date.valueOf(request.getParameter("dateEnd"));
        boolean voucherStatus = Boolean.valueOf(request.getParameter("voucherStatus"));
        int condition = Integer.parseInt(request.getParameter("condition"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int value = Integer.parseInt(request.getParameter("value"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        int numberVoucherUsed = objectDao.getNumberVoucherUsed(voucherID);
        Date currentDate = Date.valueOf(LocalDate.now());
        if (dateStart.after(dateEnd)) {
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Ngày bắt đầu' và 'Ngày kết thúc'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (condition < 0 && quantity <= 0 && value < 0){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Điều kiện', 'Số lượng' và 'Giá trị'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (condition < 0 && quantity <= 0){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Điều kiện' và 'Số lượng'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (condition < 0){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Điều kiện'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (quantity <= 0){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Số lượng'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (dateEnd.before(currentDate) && voucherStatus){//Delete this
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Ngày kết thúc' và 'Trạng thái'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (dateStart.after(currentDate) && voucherStatus){//Delete this
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Ngày kết thúc' và 'Trạng thái'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        } else if (value < 0){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Giá trị'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (quantity < numberVoucherUsed){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Số lượng'. Số lượng không được nhỏ hơn số lượng Voucher đã sử dụng.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else if (quantity <= numberVoucherUsed && voucherStatus){
            request.setAttribute("messUpdateVoucher", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Số lượng' và 'Trạng thái'.");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
        }else {
            objectDao.updateVoucher(voucherID, voucherName, dateStart, dateEnd, voucherStatus, condition, quantity, value, employeeID);
            request.setAttribute("messUpdateVoucher", "Cập nhật voucher thành công!");
            request.getRequestDispatcher("VoucherServlet").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        String voucherName = request.getParameter("voucherName");
        Date dateStart = Date.valueOf(request.getParameter("dateStart"));
        Date dateEnd = Date.valueOf(request.getParameter("dateEnd"));
        boolean voucherStatus = Boolean.valueOf(request.getParameter("voucherStatus"));
        int condition = Integer.parseInt(request.getParameter("condition"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int value = Integer.parseInt(request.getParameter("value"));
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        String employeeName = request.getParameter("employeeName");

        List listEmployees = objectDao.getAllEmployee();

        request.setAttribute("voucherID", voucherID);
        request.setAttribute("voucherName", voucherName);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("voucherStatus", voucherStatus);
        request.setAttribute("condition", condition);
        request.setAttribute("quantity", quantity);
        request.setAttribute("value", value);
        request.setAttribute("employeeID", employeeID);
        request.setAttribute("employeeName", employeeName);

        request.setAttribute("listEmployees", listEmployees);

        request.getRequestDispatcher("updateVoucher.jsp").forward(request, response);
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
