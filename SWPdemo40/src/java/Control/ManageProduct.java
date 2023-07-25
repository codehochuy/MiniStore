/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DTO.Category;
import DTO.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author kienb
 */
public class ManageProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pdo = new ProductDAO();
        CategoryDAO cdo = new CategoryDAO();
        List<Product> productDTO = pdo.getAllwithstatus(true,null);
        List<Category> listCategory = cdo.getAll();
        request.setAttribute("list", productDTO);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        request.setAttribute("listCate", gson.toJson(listCategory));
        response.setContentType("application/json");
        request.getRequestDispatcher("ManageProduct.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String categoryID = request.getParameter("categoryID");
        ProductDAO pdo = new ProductDAO();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Product> productDTO = pdo.search(id, name, quantity, status, price, categoryID);
        out.println(gson.toJson(productDTO));
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
