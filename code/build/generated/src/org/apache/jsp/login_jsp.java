package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import java.util.List;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    \r\n");
      out.write("    <!-- ===== Iconscout CSS ===== -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://unicons.iconscout.com/release/v4.0.0/css/line.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css\">\r\n");
      out.write("\r\n");
      out.write("    <!-- ===== CSS ===== -->\r\n");
      out.write("    <link href=\"css/loginRegisterStyle.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <title>Login</title>\r\n");
      out.write("    \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    \r\n");
      out.write("   <div class=\"container\">\r\n");
      out.write("       <div>\r\n");
      out.write("             <img src=\"img/back.png\" usemap=\"#back\" style=\"width: 30px;height: auto;transform: translate(20px,20px);\" class=\"back\" onclick=\"goBack()\">\r\n");
      out.write("             <map name=\"back\">\r\n");
      out.write("                <area shape=\"poly\" coords=\"242,93,67,248,241,407,241,306,324,320,448,422,436,330,400,255,389,250,333,205,245,189,241,188,241,93,445,419,448,422\">\r\n");
      out.write("              </map>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"forms\" style=\"height:440px;\">\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"form login\">\r\n");
      out.write("                <div class=\"button2\">\r\n");
      out.write("                    <div id=\"btn\"></div>\r\n");
      out.write("                    <button type=\"button\" class=\"switch\" onclick=\"login()\" style=\"font-size: 23px;font-weight:600\">User Login</button>\r\n");
      out.write("                    <button type=\"button\" class=\"switch\" onclick=\"staffLogin()\" style=\"font-size: 23px;font-weight:600\">Staff Login</button>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <form action=\"Login\" method=\"post\" id=\"login\" class=\"input\">\r\n");
      out.write("                   \r\n");
      out.write("                    <div class=\"input-field\">\r\n");
      out.write("                        <input type=\"text\" name=\"email\" placeholder=\"Email\" required>\r\n");
      out.write("                        <i class=\"uil uil-envelope icon\"></i>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field\">\r\n");
      out.write("                        <input type=\"password\" id=\"password\" name=\"password\"  placeholder=\"Password\" required>\r\n");
      out.write("                        <i class=\"uil uil-lock icon\"></i>\r\n");
      out.write("                        <i class=\"far fa-eye\" id=\"togglePassword\" style=\"margin-left: 240px; cursor: pointer;\"></i>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");

                        List list=(List) request.getAttribute("errorList");
                        if(list!=null){
                            for(Iterator it=list.iterator();it.hasNext();){
                                String errorMsg=(String) it.next();
                                
      out.write("\r\n");
      out.write("                                <font color=\"red\" padding=\"5px\">\r\n");
      out.write("                                    <li> ");
      out.print(errorMsg);
      out.write(" </li>\r\n");
      out.write("                                </font>\r\n");
      out.write("                    ");

                            }
                        }
                    
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"checkbox-text\">                   \r\n");
      out.write("                        <a href=\"changePassword.jsp\" style=\"color:#f4e618\" class=\"text\">Forgot password?</a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"input-field button\">\r\n");
      out.write("                        <input type=\"submit\"  value=\"Login Now\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"login-signup\">\r\n");
      out.write("                        <span class=\"text\" style=\"color:white\">Don't have an account?\r\n");
      out.write("                            <a href=\"register.jsp\" style=\"color:#f4e618\" class=\"text signup-link\">Signup now</a>\r\n");
      out.write("                        </span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </form>\r\n");
      out.write("                <form action=\"StaffLogin\" method=\"post\" id=\"staffLogin\" class=\"input\">\r\n");
      out.write("                    <div class=\"input-field\">\r\n");
      out.write("                        <input type=\"text\" name=\"email\" placeholder=\"Email\" required>\r\n");
      out.write("                        <i class=\"uil uil-envelope icon\"></i>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field\">\r\n");
      out.write("                        <input type=\"password\" id=\"password2\" name=\"password\"  placeholder=\"Password\" required>\r\n");
      out.write("                        <i class=\"uil uil-lock icon\"></i>\r\n");
      out.write("                        <i class=\"far fa-eye\" id=\"togglePassword2\" style=\"margin-left: 240px; cursor: pointer;\"></i>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    ");

                        List list2=(List) request.getAttribute("errorList2");
                        if(list2!=null){
                            for(Iterator it=list2.iterator();it.hasNext();){
                                String errorMsg=(String) it.next();
                                
      out.write("\r\n");
      out.write("                                <font color=\"red\" padding=\"5px\">\r\n");
      out.write("                                    <li> ");
      out.print(errorMsg);
      out.write(" </li>\r\n");
      out.write("                                </font>\r\n");
      out.write("                    ");

                            }
                        }
                    
      out.write("\r\n");
      out.write("                    <div class=\"checkbox-text\">                   \r\n");
      out.write("                        <a href=\"ChangeStaffPassword.jsp\" style=\"color:#f4e618\" class=\"text\">Forgot password?</a>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"input-field button\">\r\n");
      out.write("                        <input type=\"submit\"  value=\"Login Now\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                 </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("    //   js code to show/hide password and change icon\r\n");
      out.write("    \r\n");
      out.write("    const togglePassword = document.querySelector('#togglePassword');\r\n");
      out.write("    const password = document.querySelector('#password');\r\n");
      out.write("\r\n");
      out.write("    togglePassword.addEventListener('click', function (e) {\r\n");
      out.write("        // toggle the type attribute\r\n");
      out.write("        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';\r\n");
      out.write("        password.setAttribute('type', type);\r\n");
      out.write("        // toggle the eye slash icon\r\n");
      out.write("        this.classList.toggle('fa-eye-slash');\r\n");
      out.write("    });\r\n");
      out.write("    \r\n");
      out.write("    const togglePassword2 = document.querySelector('#togglePassword2');\r\n");
      out.write("    const password2 = document.querySelector('#password2');\r\n");
      out.write("\r\n");
      out.write("    togglePassword2.addEventListener('click', function (e) {\r\n");
      out.write("        // toggle the type attribute\r\n");
      out.write("        const type = password2.getAttribute('type') === 'password' ? 'text' : 'password';\r\n");
      out.write("        password2.setAttribute('type', type);\r\n");
      out.write("        // toggle the eye slash icon\r\n");
      out.write("        this.classList.toggle('fa-eye-slash');\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    var x=document.getElementById(\"login\");\r\n");
      out.write("    var y=document.getElementById(\"staffLogin\");\r\n");
      out.write("    var z=document.getElementById(\"btn\");\r\n");
      out.write("        \r\n");
      out.write("        function login(){\r\n");
      out.write("            x.style.left=\"75px\";\r\n");
      out.write("            y.style.left=\"450px\";\r\n");
      out.write("            z.style.left=\"30px\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function staffLogin(){       \r\n");
      out.write("            x.style.left=\"-450px\";\r\n");
      out.write("            y.style.left=\"75px\";\r\n");
      out.write("            z.style.left=\"205px\";\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("    function goBack() {\r\n");
      out.write("        window.history.back();\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("    </script>\r\n");
      out.write("    <!--<script src=\"script.js\"></script>-->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
