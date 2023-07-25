/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DTO.Category;
import DTO.Product;
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
public class loadProductByID extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            ProductDAO pdo = new ProductDAO();
             Product p = pdo.getProductByID(id);
            CategoryDAO cdo = new CategoryDAO();
            List<Category> categories = cdo.getAll();
          
            /* TODO output your page here. You may use following sample code. */
            out.println("<form action=\"update\" method=\"POST\" id=\"updatesp\">\n"
                    + "                            <div class=\"row\">\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Mã sản phẩm </label>\n"
                    + "                                    <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"masp\" value=\"" + p.getProductID() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Tên sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"tensp\" value=\"" + p.getProductName() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Giá bán</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"giasp\" value=\"" + p.getProductPrice() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Số lượng</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"quantity\" value=\"" + p.getQuantity() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Discount</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"discountsp\" value=\"" + p.getProductPrice() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Danh mục</label>\n"
                    + "                                    <select class=\"form-control\" name=\"categorysp\">\n");
                                                            for (Category i : categories) {
                                                                if (i.getId() == p.getCategory().getId()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                    + "                            </div>\n"
                    + "                            <BR>\n"
                    + "                            <BR>\n"
                    + "                            <BR>\n"
                    + "                            <button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n"
                    + "                            <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"#\">Hủy bỏ</a>\n"
                    + "                            <BR>\n"
                    + "                        </form>");
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
