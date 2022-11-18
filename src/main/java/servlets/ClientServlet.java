/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import Client.EDSClient;
import com.muzammilnagariya.edsapp.entity.Department;
import com.muzammilnagariya.edsapp.entity.Salary;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author muzz
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    @Inject
    @RestClient
    EDSClient ecl;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClientServlet</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("</br>");
            out.println("</br>");
            Collection<Department> depts = ecl.getAllDepartment();
            Collection<Salary> infos = null;
            //Get By Dept Id and Salary Both
            /*if (request.getParameter("txtDeptId") != null && request.getParameter("BySalary") != null) {
                
                String deptId = request.getParameter("txtDeptId");
                String amount = request.getParameter("BySalary");
                infos = ecl.getAllEmployeeDetailsBySalaryAndDeptId(Integer.valueOf(amount), Integer.valueOf(deptId));
                
            }*/
            //End Get By Dept Id and Salary Both
            if (request.getParameter("txtDeptId") != null) {
                //Getting All Employee Details (Including Department and Salary Details) By Department Id
                String deptId = request.getParameter("txtDeptId");
                infos = ecl.getAllEmployeeSalaryDetailsByDeptId(Integer.valueOf(deptId));
                //End Getting All Employee Details (Including Department and Salary Details) By Department Id
            } else if (request.getParameter("BySalary") != null) {
                //Getting All Employee Details By Salary Amount
                String amount = request.getParameter("BySalary");
                infos = ecl.getAllEmployeeDetailsBySalary(Integer.valueOf(amount));
                //End Getting All Employee Details By Salary Amount
            } else {
                infos = ecl.getAllEmployeeSalaryDetails();
            }

            out.println("<form method='get'>");
            out.println("Select Department: <select name='txtDeptId'>");
            out.println("<option value='' disabled selected required>Select Department</option>");
            for (Department dept : depts) {
                out.println("<option value='" + dept.getDeptId() + "'>" + dept.getDepName() + "</option>");
            }
            out.println("</select>");
            out.println("</br>");
            out.println("Salary: <input type='text' name='BySalary' value='' class='mt-2'>");
            out.println("</br>");
            out.println("<input type='submit' value='Search' class='btn btn-primary mt-2'>");
            out.println("</form>");

            out.println("<hr>");

            out.println("<table class='table table-striped'>");
            out.println("<tr>");
            out.println("<th>Employee Id</th>");
            out.println("<th>Name</th>");
            out.println("<th>Age</th>");
            out.println("<th>Department</th>");
            out.println("<th>Salary</th>");
            out.println("<th>Date of Joining</th>");
            out.println("</tr>");
            if (infos.isEmpty()) {
                out.println("<td colspan='6' class='text-center'>No record</td>");
            } else {
                for (Salary info : infos) {
                    out.println("<tr>");
                    out.println("<td>" + info.getEmpId().getEmpId() + "</td>");
                    out.println("<td>" + info.getEmpId().getEmpName() + "</td>");
                    out.println("<td>" + info.getEmpId().getAge() + "</td>");
                    out.println("<td>" + info.getEmpId().getDeptId().getDepName() + "</td>");
                    out.println("<td>" + info.getAmount() + "</td>");
                    out.println("<td>" + info.getEmpId().getJoiningDate() + "</td>");
                    out.println("</tr>");
                }
            }

            out.println("</table>");
            out.println("</center>");
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
