/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author Administrator
 */
public class AddCatServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCatServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCatServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("newcat.jsp").forward(request, response);
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
        String id_raw= request.getParameter("id");
        String name= request.getParameter("name");
        String description = request.getParameter("description");

        if(id_raw.isEmpty()){
            String erNul = "Has a null value!!!";
            request.setAttribute("errorNull", erNul);
            request.getRequestDispatcher("newcat.jsp").forward(request, response);
        }
        if(name.isEmpty()){
            String erNul = "Has a null value!!!";
            request.setAttribute("errorNull", erNul);
            request.getRequestDispatcher("newcat.jsp").forward(request, response);            
        }
        if(description.isEmpty()){
            String erNul = "Has a null value!!!";
            request.setAttribute("errorNull", erNul);
            request.getRequestDispatcher("newcat.jsp").forward(request, response);             
        } 
        
        try{
            int id = Integer.parseInt(id_raw);
            CategoryDAO cd = new CategoryDAO();
            Category c1 = cd.getProByCatId(id);
            List<Category> list = cd.getAllCat();
            if(c1 != null){
                String er1 = id+" exsited!!";
                request.setAttribute("error1", er1);
                String er2 = "Enter id > " + list.get(list.size()-1).getId()+"!!!";
                request.setAttribute("error2", er2);
                request.getRequestDispatcher("newcat.jsp").forward(request, response);
            } else {
                Category c = new Category(id, name, description);
                cd.insert(c);
                response.sendRedirect("manage?menu=1");
            }
        }catch(NumberFormatException e){
            System.out.println(e);
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
