/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author Administrator
 */
public class UpdatePassServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdatePassServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePassServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        try{
            int id = Integer.parseInt(id_raw);
            CustomerDAO cd = new CustomerDAO();
            Customer c = cd.getCategoryById(id);
            request.setAttribute("customer", c);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);
        }catch(NumberFormatException e){
            System.out.println(e);
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
        HttpSession hs = request.getSession();
        Customer c = (Customer)hs.getAttribute("acc");
        String currentpass= request.getParameter("currentpass"); 
        String newpass1 = request.getParameter("newpass1"); 
        String newpass2 = request.getParameter("newpass2"); 
        String error = "User enters wrong!";
 
        if(!currentpass.equals(c.getPass())){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);            
        }        
        if(currentpass.isEmpty()){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);                        
        }
        if(newpass1.isEmpty()){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);            
        }       
        if(newpass2.isEmpty()){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);            
        }
        
        if(!newpass1.equals(newpass2)){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);
        } 
        if(!currentpass.equals(c.getPass())){
            request.setAttribute("error", error);
            request.getRequestDispatcher("changpass.jsp").forward(request, response);          
        }
        if(newpass1.equals(newpass2) && c.getPass().equals(currentpass)){
            try{
                Customer c1 =new Customer(c.getId(), c.getName(), c.getPhone(), newpass1, c.getAddress());
                CustomerDAO cd = new CustomerDAO();
                cd.updatePass(c1);
                hs.setAttribute("acc", c1);
                response.sendRedirect("home");
            }catch(NumberFormatException e){
                System.out.println(e);
            }
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
