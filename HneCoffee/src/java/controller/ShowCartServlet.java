/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Product;
import model.ViewOrder;

/**
 *
 * @author Administrator
 */
public class ShowCartServlet extends HttpServlet {

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
            out.println("<title>Servlet ShowCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCartServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO pd = new ProductDAO();
        List<Product> list=pd.getAll();
        Cookie[] arr=request.getCookies();
        String txt="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        Cart cart=new Cart(txt, list);
        request.setAttribute("cart", cart);
        int total_raw = cart.getTotalMoney();
        if(total_raw > 1000){
            String total = String.valueOf(total_raw);
            String rs = total.charAt(0) + ".";
            for (int i = 1; i < total.length(); i++) {
                rs += total.charAt(i);
            }
            request.setAttribute("total", rs);
        }
        
        String ship = request.getParameter("ship");
        if(ship!=null){
            if(ship.equals("urban")){
                if(total_raw > 1000){
                    String total = String.valueOf(total_raw+20);
                    String rs = total.charAt(0) + ".";
                    for (int i = 1; i < total.length(); i++) {
                        rs += total.charAt(i);
                    }
                    request.setAttribute("totalShip", rs);
                } else{
                    request.setAttribute("totalShip", total_raw+20);
                }
            }
             if(ship.equals("suburban")){
                if(total_raw> 1000){
                    String total = String.valueOf(total_raw+30);
                    String rs = total.charAt(0) + ".";
                    for (int i = 1; i < total.length(); i++) {
                        rs += total.charAt(i);
                    }
                    request.setAttribute("totalShip", rs);
                } else{
                    request.setAttribute("totalShip", total_raw+30);
                }            
            }        
        }
        request.setAttribute("ship", ship);
        request.getRequestDispatcher("mycart.jsp").forward(request, response);
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
        
        ProductDAO pd = new ProductDAO();
        List<Product> list=pd.getAll();
        Cookie[] arr=request.getCookies();
        String txt="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        Cart cart=new Cart(txt, list);
        request.setAttribute("cart", cart);
        int total_raw = cart.getTotalMoney();
        if(total_raw > 1000){
            String total = String.valueOf(total_raw);
            String rs = total.charAt(0) + ".";
            for (int i = 1; i < total.length(); i++) {
                rs += total.charAt(i);
            }
            request.setAttribute("total", rs);
        }        
        
        String ship = request.getParameter("ship");
        if(ship!=null){
            if(ship.equals("urban")){
                if(total_raw > 1000){
                    String total = String.valueOf(total_raw+20);
                    String rs = total.charAt(0) + ".";
                    for (int i = 1; i < total.length(); i++) {
                        rs += total.charAt(i);
                    }
                    request.setAttribute("totalShip", rs);
                } else{
                    request.setAttribute("totalShip", total_raw+20);
                }
            }
             if(ship.equals("suburban")){
                if(total_raw> 1000){
                    String total = String.valueOf(total_raw+30);
                    String rs = total.charAt(0) + ".";
                    for (int i = 1; i < total.length(); i++) {
                        rs += total.charAt(i);
                    }
                    request.setAttribute("totalShip", rs);
                } else{
                    request.setAttribute("totalShip", total_raw+30);
                }            
            }        
        }
        
        String code = request.getParameter("code");
        
        
        if(!code.equals("hnefreeship")){
            String error = "Wrong codeship!";
            request.setAttribute("ship", ship);
            request.setAttribute("err", error);
            request.getRequestDispatcher("mycart.jsp").forward(request, response);
        } else{
            request.setAttribute("ship", ship);
            request.setAttribute("totalCode", total_raw);
            request.getRequestDispatcher("mycart.jsp").forward(request, response);
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
