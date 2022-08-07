package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.Item;
import model.Product;

public class ProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
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
        List<Product> listt = db.getAll();
        Cart cart=new Cart(txt, listt);
        List<Item> listItem=cart.getItems();
        int n;
        if(listItem!=null){
            n=listItem.size();
        }else
            n=0;
        request.setAttribute("size", n);           
        String cid_raw = request.getParameter("cid");
        ProductDAO pod = new ProductDAO();
        try{
            int cid = Integer.parseInt(cid_raw);
            request.setAttribute("cid", cid);
            List<Product> list1 = new ArrayList<>();
            if(cid==1){
                list1 = (List<Product>)pod.getByCatID(cid);
            }
            if(cid==2){
                list1 = (List<Product>)pod.getByCatID(cid);
            }
            if(cid==3){
                list1 = (List<Product>)pod.getByCatID(cid);
            }            
            
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
            List<Product> list = pod.getListProByPage(list1, start, end);
            request.setAttribute("listSize", list1.size());
            request.setAttribute("data", list);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }catch(NumberFormatException e){
            
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
