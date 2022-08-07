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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Administrator
 */
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
    
    boolean check(String name_raw){
        String name = name_raw.trim();
        for (int i = 0; i < name.length(); i++) {
            if(!Character.isDigit(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("nameSearch", name);
            ProductDAO pdb = new ProductDAO();
            if(name.isEmpty()){
              try{
                    List<Product> list1 = pdb.getAll();
                    int page, numperpage=6;
                    int size=list1.size();
                    int num=(size%6==0?(size/6):((size/6))+1);
                    String xpage=request.getParameter("page");
                    if(xpage==null){
                        page=1;
                    }else{
                        page=Integer.parseInt(xpage);
                    }
                    int start, end;
                    start=(page-1)*numperpage;
                    end=Math.min(page*numperpage, size);
                    List<Product> list = pdb.getListProByPage(list1, start, end);
                    request.setAttribute("data", list);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num);  
                    request.setAttribute("listSize", size);
                    request.getRequestDispatcher("search.jsp").forward(request, response); 
                }catch(NumberFormatException e){
                    System.out.println(e);
                }                           
            }
            if(check(name)){    // is Digit
                try{
                    int price = Integer.parseInt(name);
                    List<Product> list1 = pdb.searchByPrice(price);
                    int page, numperpage=6;
                    int size=list1.size();
                    int num=(size%6==0?(size/6):((size/6))+1);
                    String xpage=request.getParameter("page");
                    if(xpage==null){
                        page=1;
                    }else{
                        page=Integer.parseInt(xpage);
                    }
                    int start, end;
                    start=(page-1)*numperpage;
                    end=Math.min(page*numperpage, size);
                    List<Product> list = pdb.getListProByPage(list1, start, end);
                    request.setAttribute("data", list);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num);   
                    request.setAttribute("listSize", size);
                    request.getRequestDispatcher("search.jsp").forward(request, response); 
                }catch(NumberFormatException e){
                    System.out.println(e);
                }            
            }else{ // is Character
                List<Product> list1 = pdb.searchByName(name);
                    int page, numperpage=6;
                    int size=list1.size();
                    int num=(size%6==0?(size/6):((size/6))+1);
                    String xpage=request.getParameter("page");
                    if(xpage==null){
                        page=1;
                    }else{
                        page=Integer.parseInt(xpage);
                    }
                    int start, end;
                    start=(page-1)*numperpage;
                    end=Math.min(page*numperpage, size);
                    List<Product> list = pdb.getListProByPage(list1, start, end);
                    request.setAttribute("data", list);
                    request.setAttribute("page", page);
                    request.setAttribute("num", num); 
                    request.setAttribute("listSize", size);
                    request.getRequestDispatcher("search.jsp").forward(request, response); 
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
