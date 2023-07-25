/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.WorkSheetDAO;
import DTO.WorkSheetDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vuong
 */
public class WorkSheet_change_sl_add extends HttpServlet {

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
            out.println("<title>Servlet WorkSheet_addmorefirstfinal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkSheet_addmorefirstfinal at " + request.getContextPath() + "</h1>");
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
          
String employeeid = request.getParameter("employeeid");
String employeerolerepo = request.getParameter("employeerole");
//        System.out.println(employeerolerepo);

  List<Integer> listshiftid = (List<Integer>) request.getSession().getAttribute("LISTSHIFTID");

String shiftIdListString = request.getParameter("shiftIdList");
//        System.out.println(shiftIdListString);

String employeerole = "";
if (employeerolerepo.equals("Sales")) {
//    System.out.println(employeerolerepo);
    employeerole = "2";
} else if (employeerolerepo.equals("Guard")) {
    employeerole = "3";
}
WorkSheetDAO dao = new WorkSheetDAO();
if (shiftIdListString != null && !shiftIdListString.isEmpty()) {
    // Loại bỏ kí tự không mong muốn và khoảng trắng
    shiftIdListString = shiftIdListString.replace("[", "").replace("]", "");
    
    // Tách chuỗi thành mảng các số
    String[] shiftIdArray = shiftIdListString.split(",");
    
    List<String> duplicatedShiftIds = new ArrayList<>();
for (String shiftId : shiftIdArray) {
    shiftId = shiftId.trim(); // Remove leading/trailing whitespace
//    System.out.println(shiftId);
 String duplicatedShiftId = dao.check_duplicate_sl_first(shiftId);
if (duplicatedShiftId != null) {
    duplicatedShiftIds.add(duplicatedShiftId);
} 

if (duplicatedShiftIds.contains(employeeid)) {
      request.setAttribute("messageerror", "Trùng lặp với nhân viên thứ 2");
    
}else{
    boolean success = dao.addEmployeeFirst(employeeid, shiftId);
}

}


List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);

    

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
        
                
String employeeid = request.getParameter("employeeid");
String employeerolerepo = request.getParameter("employeerole");
//        System.out.println(employeerolerepo);

  List<Integer> listshiftid = (List<Integer>) request.getSession().getAttribute("LISTSHIFTID");

String shiftIdListString = request.getParameter("shiftIdList");
//        System.out.println(shiftIdListString);

String employeerole = "";
if (employeerolerepo.equals("Sales")) {
//    System.out.println(employeerolerepo);
    employeerole = "2";
} else if (employeerolerepo.equals("Guard")) {
    employeerole = "3";
}
WorkSheetDAO dao = new WorkSheetDAO();
if (shiftIdListString != null && !shiftIdListString.isEmpty()) {
    // Loại bỏ kí tự không mong muốn và khoảng trắng
    shiftIdListString = shiftIdListString.replace("[", "").replace("]", "");
    
    // Tách chuỗi thành mảng các số
    String[] shiftIdArray = shiftIdListString.split(",");
    
    List<String> duplicatedShiftIds = new ArrayList<>();
for (String shiftId : shiftIdArray) {
    shiftId = shiftId.trim(); // Remove leading/trailing whitespace
//    System.out.println(shiftId);
 String duplicatedShiftId = dao.check_duplicate_sl_second(shiftId);
if (duplicatedShiftId != null) {
    duplicatedShiftIds.add(duplicatedShiftId);
} 

if (duplicatedShiftIds.contains(employeeid)) {
      request.setAttribute("messageerror", "Trùng lặp với nhân viên thứ nhất");
    
}else{
    boolean success = dao.addEmployeeSecond(employeeid, shiftId);
}

}


List<WorkSheetDTO> combinedList = new ArrayList<>();
for (Integer shiftId : listshiftid) {
    String shiftIdString = String.valueOf(shiftId);
    List<WorkSheetDTO> dtoList = dao.getallbyshiftid(shiftIdString);
    combinedList.addAll(dtoList);
}
request.setAttribute("LIST", combinedList);
        request.setAttribute("employeerole", employeerole);
request.getRequestDispatcher("WorkSheet_search.jsp").forward(request, response);

    

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
