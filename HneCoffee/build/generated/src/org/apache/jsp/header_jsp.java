package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>HneCoffee</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/index.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <img class=\"header-one\" src=\"Image/logo.png\" style=\"width: 10%\">\n");
      out.write("            <img src=\"Image/logo1.png\" style=\"width: 30%\">\n");
      out.write("            <img src=\"Image/logo2.png\" style=\"width: 10%\">\n");
      out.write("        </div>\n");
      out.write("        <!--Navbar-->       \n");
      out.write("        <div class=\"topnav\" id=\"myTopnav\">\n");
      out.write("            <a href=\"home.jsp\">Home</a>\n");
      out.write("            <a href=\"shop\">All Products</a>\n");
      out.write("            <a href=\"product?cid=1\">Coffee</a>\n");
      out.write("            <a href=\"product?cid=2\">Freeze</a>\n");
      out.write("            <a href=\"product?cid=3\">Milk Tea</a>\n");
      out.write("            <form action=\"search\">\n");
      out.write("                <input class=\"form-search\" type=\"submit\" value=\"Search\">\n");
      out.write("                <input class=\"text-search\" type=\"text\" placeholder=\"Fin,Freeze,35,50,...\" name=\"name\">\n");
      out.write("            </form>      \n");
      out.write("            <a href=\"javascript:void(0);\" class=\"icon\" onclick=\"myFunction()\">\n");
      out.write("                <i class=\"fa fa-bars\"></i>\n");
      out.write("            </a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"icon-bar\">\n");
      out.write("            <!--icon Shopping Cart-->\n");
      out.write("            <a href=\"showcart\" ><img class=\"zoom\" src=\"Image/Cart.png\" style=\"height: 60px; width: 60px; border-radius: 100px;margin-bottom: 1vh;\"></a><br/>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"action\">\n");
      out.write("            <div class=\"profile\" onclick=\"menuToggle();\">\n");
      out.write("                <img class=\"zoom\" src=\"Image/login.png\" alt=\"\" style=\"height: 60px; width: 60px;\">\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"menu\">\n");
      out.write("                <!--login with Admin-->\n");
      out.write("                <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.admin != null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                    <h3>Hello,&nbsp;<span style=\"color: red;\">Admin</span>!</h3>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"manage\">Manage Products</a></li>\n");
      out.write("                        <li><a href=\"updateacc?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.admin.getId()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Edit Account</a></li>\n");
      out.write("                        <li><a href=\"updatepass?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.admin.getId()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Change Password</a></li>\n");
      out.write("                        <li><a href=\"logout\">Logout</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </c:if>\n");
      out.write("                <!--logout-->\n");
      out.write("                <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.admin == null && sessionScope.acc == null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                    <h3>User Account</h3>\n");
      out.write("                </c:if>\n");
      out.write("                <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.admin == null && sessionScope.acc == null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"login\">Login</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </c:if>\n");
      out.write("                <!--login with Customer-->\n");
      out.write("                <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.acc != null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                    <h3>Hello,&nbsp;<span style=\"color: #eaf800;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.acc.getName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>!</h3>\n");
      out.write("                </c:if>\n");
      out.write("                <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.acc != null}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"updateacc?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.acc.getId()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Edit Account</a></li>\n");
      out.write("                        <li><a href=\"updatepass?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.acc.getId()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Change Password</a></li>\n");
      out.write("                        <li><a href=\"logout\">Logout</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </c:if>    \n");
      out.write("            </div>\n");
      out.write("        </div>        \n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
