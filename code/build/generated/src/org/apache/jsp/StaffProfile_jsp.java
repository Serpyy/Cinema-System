package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.*;
import java.util.*;

public final class StaffProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');

    Staff staff = (Staff)session.getAttribute("ssLogStaff");

      out.write("\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"css/style.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/style4.css\" rel=\"stylesheet\">\n");
      out.write("        <script defer src=\"https://use.fontawesome.com/releases/v5.0.6/js/all.js\"></script>\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css\">\n");
      out.write("        <title>Staff Profile</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("         <div class=\"navbar\">\n");
      out.write("            <div class=\"navbar-container\">\n");
      out.write("                <div class=\"logo-container\">\n");
      out.write("                    <h1 class=\"logo\">GSC</h1>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"menu-container\">\n");
      out.write("\n");
      out.write("                   <ul class=\"menu-list\">\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"StaffProfile.jsp\">Profile</a></li>\n");
      out.write("                        ");

                            if(staff.getRole().equals("Cashier")||staff.getRole().equals("Usher")||staff.getRole().equals("Manager")){
                        
      out.write("\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"SaleRecord.jsp\">Sales</a></li>\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"CustomerRecord.jsp\">Customer</a></li>\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"#\">Report<i class=\"fas fa-caret-down\"></i></a>\n");
      out.write("                        <div class=\"dropdown-menu\" style=\"z-index: 1\">\n");
      out.write("                            <ul>\n");
      out.write("                                <li style=\"width:155px\"><a href=\"SelectDate.jsp\">Sales Report</a></li>\n");
      out.write("                                <li style=\"width:155px\"><a href=\"MovieID.jsp\">Movie Report</a></li>\n");
      out.write("                                <li style=\"width:155px\"><a href=\"RefundReport.jsp\">Refund Report</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                        </li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                        ");

                            if(staff.getRole().equals("Film Projectionist")||staff.getRole().equals("Manager")){
                        
      out.write("\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"MaintainMovie\">Movies</a></li>\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"MaintainSchedule\">Schedules</a></li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                        ");

                            if(staff.getRole().equals("Community Moderator")||staff.getRole().equals("Manager")){
                        
      out.write("\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"StaffReview.jsp\">Review</a></li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                        ");

                            if(staff.getRole().equals("Manager")){
                        
      out.write("\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"MaintainStaff\">Staff</a></li>\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"RefundStaffController\">Refund</a></li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                        <li class=\"menu-list-item\"><a href=\"Home.jsp\">Logout</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class =\"container\">\n");
      out.write("            <div class =\"profile\">\n");
      out.write("                <h1>PROFILE <span style =\"display: inline-block;font-size: 12px;text-indent:5px;letter-spacing:0.5;font-weight:normal;\">(Staff ID: ");
      out.print(staff.getStaffId());
      out.write(")</span></h1>\n");
      out.write("                <div class =\"staffh1Deco\"></div>\n");
      out.write("                <form method =\"post\" action =\"UpdateStaffDetails\">\n");
      out.write("                    <input type=\"hidden\" name=\"forwardTo\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.servletPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" /> \n");
      out.write("                    <div class =\"profileBox\">\n");
      out.write("                        <div class =\"detailsGroup\">\n");
      out.write("                            <label for =\"name\">Full Name</label>\n");
      out.write("                            <input type =\"text\" class =\"name\" name =\"name\" value =\"");
      out.print(staff.getStaffName());
      out.write("\" placeholder =\"Your Full Name\">\n");
      out.write("                            <input type =\"hidden\" class = \"checkCurrentPW\" name =\"checkPW\" value =\"");
      out.print(staff.getStaffPassword());
      out.write("\">\n");
      out.write("                            <label class =\"roleTitle\" for =\"name\">Role</label>\n");
      out.write("                            <input type =\"text\" class =\"role\" name =\"role\" value =\"");
      out.print(staff.getRole());
      out.write("\" placeholder =\"Your Role\" disabled =\"disabled\">\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <div class =\"detailsGroup\">\n");
      out.write("                            <label for =\"email\">Email</label>\n");
      out.write("                            <input type =\"email\" class =\"email\" name =\"email\" value =\"");
      out.print(staff.getStaffEmail());
      out.write("\" placeholder =\"Your Email\">\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <div class =\"pwTopSplit\"></div>\n");
      out.write("                        <h2><b>Change Password</b></h2>\n");
      out.write("                        <div class =\"pwBottomSplit\"></div>\n");
      out.write("                        <div class =\"detailsGroup\">\n");
      out.write("                            <div class =\"changePassword\">\n");
      out.write("                                <label for =\"currentPW\">Current</label>\n");
      out.write("                                <input type =\"password\" class =\"currentPW\" name =\"currentPW\" maxlength=\"16\" minlength=\"8\" placeholder =\"Enter Current Password...\">\n");
      out.write("                                </br>\n");
      out.write("                                <label for =\"newPW\">New</label>\n");
      out.write("                                <input type =\"password\" class =\"newPW\" name =\"newPW\" maxlength=\"16\" minlength=\"8\" placeholder =\"Enter New Password...\">\n");
      out.write("                                </br>\n");
      out.write("                                <label for =\"retypePW\">Re-type new</label>\n");
      out.write("                                <input type =\"password\" class =\"confirmPW\" name =\"confirmPW\" maxlength=\"16\" minlength=\"8\" placeholder =\"Re-type New Password...\">\n");
      out.write("                                </br>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <div class =\"detailsGroup\">\n");
      out.write("                            <label for =\"phone\">Phone Number</label>\n");
      out.write("                            <input type =\"text\" class =\"phoneNo\" name =\"phoneNo\"  pattern=\"^(01)[02-46-9]-*[0-9]{7}$|^(01)[1]-*[0-9]{8}$\" value =\"");
      out.print(staff.getStaffPhoneNo());
      out.write("\" placeholder =\"Your Phone Number\" required>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <div class =\"detailsGroup\">\n");
      out.write("                            <label for =\"questions\">Security Question</label>\n");
      out.write("                            <select name=\"questions\" class=\"questions\" id=\"questions\" onChange=\"enableText()\">\n");
      out.write("                                ");
if (staff.getSecurityQuestion() == null || staff.getSecurityQuestion() == "") {
      out.write("\n");
      out.write("                                    <option selected disabled hidden>Select an Option</option>\n");
      out.write("                                ");
} else {
      out.write("\n");
      out.write("                                <option value=\"");
      out.print(staff.getSecurityQuestion());
      out.write("\" selected disabled hidden>");
      out.print(staff.getSecurityQuestion());
      out.write("</option>\n");
      out.write("                                    <option value=\"In what city were you born?\">In what city were you born?</option>\n");
      out.write("                                    <option value=\"What is the name of your favourite pet?\">What is the name of your favourite pet?</option>\n");
      out.write("                                    <option value=\"What is your father's name?\">What is your father's name?</option>\n");
      out.write("                                    <option value=\"What is your mother's name?\">What is your mother's name?</option>\n");
      out.write("                                    <option value=\"What is your favourite food?\">What is your favourite food?</option>\n");
      out.write("                                    <option value=\"What is your father's middle name?\">What is your father's middle name?</option>\n");
      out.write("                                    <option value=\"What is your mother's middle name?\">What is your mother's middle name?</option>                                    \n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                                \n");
      out.write("                            </select>\n");
      out.write("                            <label class =\"answerTitle\" for =\"answer\">Answer</label>\n");
      out.write("                            <input type =\"text\" class =\"answer\" name =\"answer\" id=\"answer\" value=\"");
      out.print(staff.getAnswer());
      out.write("\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class =\"detailsSplit\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <input class =\"saveStaffProfile\" type =\"submit\" value =\"Save Profile\">\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("       <footer class=\"footer\">\n");
      out.write("             <div class=\"footer-container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"footer-col\">\n");
      out.write("                        <h4>movies</h4>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"footer-col\">\n");
      out.write("                        <h4>help</h4>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"footer-col\">\n");
      out.write("                        <h4>company</h4>\n");
      out.write("                            <ul>\n");
      out.write("                                <li class=\"contact-us-details\">Golden Screen Cinemas<br><br>04-646 1960<br><br>1, Lot 3F-50, Queensbay Mall, No.100, Persiaran Bayan Indah, 11900 Bayan Lepas, Pulau Pinang<br><br>cs@gsc.com.my </li> \n");
      out.write("                            </ul>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"footer-col\">\n");
      out.write("                        <h4>follow us</h4>\n");
      out.write("                        <div class=\"social-links\">\n");
      out.write("                            <a href=\"https://www.facebook.com/GSCinemas/\" style=\"padding-top:10px\"><i class=\"fab fa-facebook-f\"></i></a>\n");
      out.write("                            <a href=\"https://twitter.com/gsc_movies?lang=en\" style=\"padding-top:10px\"><i class=\"fab fa-twitter\"></i></a>\n");
      out.write("                            <a href=\"https://www.instagram.com/gscinemas/?hl=en\" style=\"padding-top:10px\"><i class=\"fab fa-instagram\"></i></a>\n");
      out.write("                            <a href=\"https://www.linkedin.com/company/gscinemas/\" style=\"padding-top:10px\"><i class=\"fab fa-linkedin-in\"></i></a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("             </div>\n");
      out.write("        </footer>\n");
      out.write("        <script>\n");
      out.write("            function enableText() {\n");
      out.write("                document.getElementById('answer').disabled = false;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            document.querySelector('.saveStaffProfile').onclick = function() {\n");
      out.write("                var name = document.querySelector('.name').value;\n");
      out.write("                var email = document.querySelector('.email').value;\n");
      out.write("                var currentPW = document.querySelector('.currentPW').value;\n");
      out.write("                var newPW = document.querySelector('.newPW').value;\n");
      out.write("                var confirmPW = document.querySelector('.confirmPW').value;\n");
      out.write("                var checkCurrentPW = document.querySelector('.checkCurrentPW').value;\n");
      out.write("                var phoneNo = document.querySelector('.phoneNo').value;\n");
      out.write("                const chkAnswer = document.getElementById('answer');\n");
      out.write("                var answer = document.querySelector('.answer').value;\n");
      out.write("                \n");
      out.write("                if (!(chkAnswer.disabled)) {\n");
      out.write("                    if (answer == null || answer == \"\") {\n");
      out.write("                        alert(\"Answer Field cannot be empty.\");\n");
      out.write("                        return false;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("                if (name == null || name == \"\") {\n");
      out.write("                    alert(\"Name Field cannot be empty.\");\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                if (email == null || email == \"\") {\n");
      out.write("                    alert(\"Email Field cannot be empty.\");\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                if (currentPW != \"\") {\n");
      out.write("                    if(currentPW != checkCurrentPW) {\n");
      out.write("                        alert(\"Current Password Invalid.\");\n");
      out.write("                        return false;\n");
      out.write("                    } else {\n");
      out.write("                        if (newPW == \"\") {\n");
      out.write("                            alert(\"New Password cannot be empty.\");\n");
      out.write("                            return false;\n");
      out.write("                        } else if (newPW != confirmPW) {\n");
      out.write("                            alert(\"New Password & Confirm Password do not match.\");\n");
      out.write("                            return false;\n");
      out.write("                        } else if (newPW.length < 8) {\n");
      out.write("                            alert(\"New Password must be of length 8 or more.\");\n");
      out.write("                            return false;\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                } else if (currentPW == \"\") {\n");
      out.write("                    if (newPW != \"\" && confirmPW != \"\") {\n");
      out.write("                        alert(\"Current Password Invalid.\");\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("             \n");
      out.write("            }\n");
      out.write("        </script>\n");
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
