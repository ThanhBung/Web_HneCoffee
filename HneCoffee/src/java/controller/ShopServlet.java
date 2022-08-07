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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author Administrator
 */
public class ShopServlet extends HttpServlet {

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
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO db = new ProductDAO();
        //Cart
        Cookie[] arr=request.getCookies();
        String txt="";
        if(arr!=null){
            for(Cookie o:arr){
                if(o.getName().equals("cart")){
                    txt+=o.getValue();
                }
            }
        }
        List<Product> list = db.getAll();
        Cart cart=new Cart(txt, list);
        List<Item> listItem=cart.getItems();
        int n;
        if(listItem!=null){
            n=listItem.size();
        }else
            n=0;
        request.setAttribute("size", n);        
        
        String[] id_raw = request.getParameterValues("id");
        String order = request.getParameter("order");
        int[] id = null;
        if (id_raw != null) {
            id = new int[id_raw.length];
            for (int i = 0; i < id.length; i++) {
                id[i] = Integer.parseInt(id_raw[i]);
            }
        }
        List<Product> list1 = db.checkProductAndOrderBy(id, order);
        request.setAttribute("listSize", list1.size());
        int page, numperpage = 6;
        int size = list1.size();
        int num = (size % 6 == 0 ? (size / 6) : ((size / 6)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        list = db.getListProByPage(list1, start, end);
        request.setAttribute("data", list);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("order", order);
        CategoryDAO cdb = new CategoryDAO();
        List<Category> clist = cdb.getAllCat();
        boolean[] cid = new boolean[clist.size()];
        for (int i = 0; i < cid.length; i++) {
            if (isCheck(clist.get(i).getId(), id)) {
                cid[i] = true;
            } else {
                cid[i] = false;
            }
        }
        request.setAttribute("cats", clist);
        request.setAttribute("cid", cid);
        request.setAttribute("id", id);
        request.getRequestDispatcher("myshop.jsp").forward(request, response);
    }

    private boolean isCheck(int d, int[] id) {
        if (id == null) {
            return false;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == d) {
                    return true;
                }
            }
        }
        return false;
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
