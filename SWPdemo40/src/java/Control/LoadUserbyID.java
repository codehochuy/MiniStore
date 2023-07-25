/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.CategoryDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import DAO.RoleDAO;
import DTO.Category;
import DTO.Employee;
import DTO.Product;
import DTO.Role;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author kienb
 */
public class LoadUserbyID extends HttpServlet {

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
            String id = request.getParameter("id");
            EmployeeDAO pdo = new EmployeeDAO();
            Employee e = pdo.getEmployeeByID(id);
            List<String> list = pdo.getUsername();
            String[] array = new String[list.size()];
            array = list.toArray(array);
            String currentusername = e.getUsername();
            

            out.print("<form action=\"updateuser\" method=\"POST\" id=\"updateusers\" onsubmit=\"return validateForm()\">\n"
                    + "  <div class=\"row\">\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">ID Nhân viên </label>\n"
                    + "      <input required readonly=\"true\" class=\"form-control\" type=\"text\" name=\"id\" value=\"" + e.getEmployeeID() + "\" >\n"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Tên nhân viên</label>\n"
                    + "      <input required class=\"form-control\" type=\"text\" required name=\"name\" value=\"" + e.getEmployeename() + "\" >\n"
                                    + "      <span id=\"nameError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Địa chỉ</label>\n"
                    + "      <input required class=\"form-control\" type=\"text\" name=\"address\" value=\"" + e.getEmployeeaddress() + "\" >\n"
                                    + "      <span id=\"addressError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Ngày Sinh </label>\n"
                    + "      <input  required class=\"form-control\" type=\"date\" name=\"birthday\" value=\"" + e.getBirthday() + "\" >\n"
                                    + "      <span id=\"birthdayError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label for=\"exampleSelect1\" class=\"control-label\">Giới tính</label>\n"
                    + "      <select class=\"form-control\" name=\"sex\">\n");
            if (e.getEmployeesex() == 1) {
                out.println("<option value=\"" + 1 + "\">" + "Nam" + "</option>");
                out.println("<option value=\"" + 2 + "\">" + "Nữ" + "</option>");
            } else {
                out.println("<option value=\"" + 2 + "\">" + "Nữ" + "</option>");
                out.println("<option value=\"" + 1 + "\">" + "Nam" + "</option>");
            }

            out.println("      </select>\n");
            out.println("    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">SĐT</label>\n"
                    + "      <input required class=\"form-control\" type=\"number\" name=\"phone\" value=\"" + e.getEmployeephone() + "\" >\n"
                                + "      <span id=\"phoneError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label for=\"exampleSelect1\" class=\"control-label\">Chức vụ</label>\n"
                    + "      <select class=\"form-control\" name=\"role\">\n");
            if (e.getEmprole().getName().equalsIgnoreCase("Manager")) {
                out.println("<option value=\"" + 1 + "\">" + "Manager" + "</option>");
                out.println("<option value=\"" + 2 + "\">" + "Sales" + "</option>");
                out.println("<option value=\"" + 3 + "\">" + "Guard" + "</option>");
            } else if (e.getEmprole().getName().equalsIgnoreCase("Sales")) {
                out.println("<option value=\"" + 2 + "\">" + "Sales" + "</option>");
                out.println("<option value=\"" + 1 + "\">" + "Manager" + "</option>");
                out.println("<option value=\"" + 3 + "\">" + "Guard" + "</option>");
            } else {
                out.println("<option value=\"" + 3 + "\">" + "Guard" + "</option>");
                out.println("<option value=\"" + 1 + "\">" + "Manager" + "</option>");
                out.println("<option value=\"" + 2 + "\">" + "Sales" + "</option>");
            }
            out.println("      </select>\n");
            out.println("    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Tên đăng nhập</label>\n"
                    + "      <input required class=\"form-control\" type=\"text\" name=\"username\" id=\"username\" value=\"" + e.getUsername() + "\" >\n"
                    + "      <span id=\"usernameError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Mật khẩu </label>\n"
                    + "      <input required class=\"form-control\" type=\"text\" name=\"pass\" value=\"" + e.getEmployeepassword() + "\" >\n"
                                    + "      <span id=\"passError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "  </div>\n");

            out.print("<button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n"
                    + "  <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"#\">Hủy bỏ</a>\n"
                    + "  <br>\n"
                    + "</form>\n"
                    + "<script>\n"
                    + "   var currentusername = \"" + currentusername + "\";\n"
                    + "   var array = " + Arrays.toString(array).replace("[", "['").replace(", ", "', '").replace("]", "']") + ";\n"
                    + "   function validateForm() {\n"
                    + "       var username = document.getElementById('username').value;\n"
                    + "       if (username === currentusername) {\n"
                    + "           return true; // No change in username, allow form submission\n"
                    + "       }\n"
                    + "       for (var i = 0; i < array.length; i++) {\n"
                    + "           if (username === array[i]) {\n"
                    + "               document.getElementById('usernameError').innerHTML = 'Tên đăng nhập đã tồn tại.';\n"
                    + "               document.getElementById('usernameError').style.color = 'red'; // Set the color to red\n"
                    + "               return false; // Username is duplicate, prevent form submission\n"
                    + "           }\n"
                    + "       }\n"
                    + "       document.getElementById('usernameError').innerHTML = ''; // Clear the error message if no duplicate username\n"
                    + "       return true; // No duplicate username, allow form submission\n"
                    + "   }\n"
                    + "</script>");

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
