/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

/**
 *
 * @author Administrator
 */
public class UpdateProServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProServlet at " + request.getContextPath() + "</h1>");
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
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProductById(id);
            request.setAttribute("product", p);
            request.getRequestDispatcher("updatepro.jsp").forward(request, response);
        }catch(NumberFormatException e){
            System.out.println(e);
        }
        
        String cid_raw = request.getParameter("cid");
        try{
            int cid = Integer.parseInt(cid_raw);
            CategoryDAO cd = new CategoryDAO();
            Category c = cd.getProByCatId(cid);
            request.setAttribute("category", c);
            request.getRequestDispatcher("updatepro.jsp").forward(request, response);
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
        String id_raw = request.getParameter("id");
        String cid_raw = request.getParameter("cid");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String status = request.getParameter("status");
        String quantity_raw = request.getParameter("quantity");
        String price_raw = request.getParameter("price");
        int id,cid,quantity,price;
        try{
            id=Integer.parseInt(id_raw);
            cid=Integer.parseInt(cid_raw);
            quantity=Integer.parseInt(quantity_raw);
            price=Integer.parseInt(price_raw);
            ProductDAO pd = new ProductDAO();
            Product p = new Product(id, cid, name, image, status, quantity, price);
            pd.update(p);
            response.sendRedirect("manage?menu=2");
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
