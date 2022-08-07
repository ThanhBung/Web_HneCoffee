/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDAO;
import dal.CustomerDAO;
import dal.OrderDAO;
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
import model.Customer;
import model.Item;
import model.Product;

/**
 *
 * @author Administrator
 */
public class ManageServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageServlet at " + request.getContextPath() + "</h1>");
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
        OrderDAO od = new OrderDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Category> cat = cd.getAllCat();
        request.setAttribute("listCat", cat);
            
        List<Integer> listQuantity = od.getQuanPurchaseOrderByCatId();
        request.setAttribute("listQuantity", listQuantity);    
        request.setAttribute("sizeInt", listQuantity.size());
        List<Integer> listIncome = od.getIncomeOrderByCatId();
        request.setAttribute("listIncome", listIncome);  
        
        String menu_raw = request.getParameter("menu");
        int menu = Integer.parseInt(menu_raw);
        request.setAttribute("menu", menu);
        //Cart
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        List<Product> list = db.getAll();
        Cart cart = new Cart(txt, list);
        List<Item> listItem = cart.getItems();
        int n;
        if (listItem != null) {
            n = listItem.size();
        } else {
            n = 0;
        }
        request.setAttribute("size", n);
        if (menu == 2) {
            //Product
            //Add new (gui id Cat)
            List<Category> c = cd.getAllCat();
            request.setAttribute("category", c);
            ProductDAO pd = new ProductDAO();
            int page, numperpage = 6;
            int size = list.size();
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
            list = pd.getListProByPage(list, start, end);
            request.setAttribute("data", list);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.getRequestDispatcher("manage.jsp").forward(request, response);
        }

        if (menu == 1) {
            List<Category> listCat = cd.getAllCat();
            request.setAttribute("dataCat", listCat);
            request.getRequestDispatcher("manage.jsp").forward(request, response);
        }
        
        if (menu == 3) {
            ProductDAO pd = new ProductDAO();
            CustomerDAO cusd = new CustomerDAO();
            List<Customer> listCus = cusd.getAllCus();
            int page, numperpage = 6;
            int size = listCus.size();
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
            listCus = pd.getListCusByPage(listCus, start, end);
            request.setAttribute("page", page);
            request.setAttribute("num", num);            
            request.setAttribute("dataCus", listCus);
            request.getRequestDispatcher("manage.jsp").forward(request, response);
        }
        if(menu==4){
            int income = od.getIncome();
            if(income > 1000){
                String total = String.valueOf(income);
                String rs = total.charAt(0) + ".";
                for (int i = 1; i < total.length(); i++) {
                    rs += total.charAt(i);
                }
                request.setAttribute("income", rs); 
            } else{
                request.setAttribute("income", income);
            }
            int totalQuantity = od.getTotalQuantity();
            request.setAttribute("totalQuantity", totalQuantity); 
            int numberOfPurchase = od.getNumberOfPurchases();
            request.setAttribute("numberOfPurchase", numberOfPurchase); 
            request.getRequestDispatcher("manage.jsp").forward(request, response);
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
