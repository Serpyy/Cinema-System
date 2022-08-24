package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.   time.LocalDate;
import java.util.Collections;
import java.util.List;
import model.MovieDA;
import model.ScheduleDA;
import model.*;
import java.util.ArrayList;

public final class Home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(" ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "StartUp", out, false);
      out.write('\n');
      out.write('\n');
      model.Customer ssLogUser = null;
      synchronized (session) {
        ssLogUser = (model.Customer) _jspx_page_context.getAttribute("ssLogUser", PageContext.SESSION_SCOPE);
        if (ssLogUser == null){
          ssLogUser = new model.Customer();
          _jspx_page_context.setAttribute("ssLogUser", ssLogUser, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("\n");

    List<Movie> movieList = new ArrayList<Movie>();
    if(session.getAttribute("movieList") != null){
        movieList = (List<Movie>)session.getAttribute("movieList");
    }
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
    LocalDate sdate= LocalDate.parse("2022-1-1", dateFormat);

    Movie temp=new Movie();

    ArrayList<Movie> movies =(ArrayList<Movie>)session.getAttribute("movieList");
    

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    \n");
      out.write("    <head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    \n");
      out.write("    <script defer src=\"https://use.fontawesome.com/releases/v5.0.6/js/all.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("    \n");
      out.write("    <link\n");
      out.write("        href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap\"\n");
      out.write("        rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css\">\n");
      out.write("     \n");
      out.write("    <title>Cinema</title>\n");
      out.write("    \n");
      out.write("    <script>\n");
      out.write("        \n");
      out.write("        let slideIndex = [1,1];\n");
      out.write("        /* Class the members of each slideshow group with different CSS classes */\n");
      out.write("        let slideId = [\"movie-slide\", \"promotion-slide\"]\n");
      out.write("        \n");
      out.write("        showSlides(1, 0);\n");
      out.write("        showSlides(1, 1);\n");
      out.write("\n");
      out.write("        // Next/previous controls\n");
      out.write("        function plusSlides(n, no) {\n");
      out.write("            showSlides(slideIndex[no] += n, no);\n");
      out.write("          }\n");
      out.write("\n");
      out.write("        function showSlides(n, no) {\n");
      out.write("            var i;\n");
      out.write("            let x = document.getElementsByClassName(slideId[no]);\n");
      out.write("            if (n > x.length) {slideIndex[no] = 1};    \n");
      out.write("            if (n < 1) {slideIndex[no] = x.length};\n");
      out.write("            for (i = 0; i < x.length; i++) {\n");
      out.write("               x[i].style.display = \"none\";  \n");
      out.write("            }\n");
      out.write("            x[slideIndex[no]-1].style.display = \"block\";  \n");
      out.write("          }\n");
      out.write("          \n");
      out.write("          \n");
      out.write("        function tableSearch() {\n");
      out.write("            let input, filter, table, tr, td, txtValue, x;\n");
      out.write("\n");
      out.write("            //Intialising Variables\n");
      out.write("            input = document.getElementById(\"searchInput\");\n");
      out.write("            filter = input.value.toUpperCase();\n");
      out.write("            table = document.getElementById(\"movieTable\");\n");
      out.write("            tr = table.getElementsByTagName(\"tr\");\n");
      out.write("           \n");
      out.write("\n");
      out.write("            if(input.value.length===0){               \n");
      out.write("                for (let i = 0; i < tr.length; i++){\n");
      out.write("                    tr[i].style.display = \"none\";\n");
      out.write("                }\n");
      out.write("            }else{\n");
      out.write("                for (let i = 0; i < tr.length; i++) {\n");
      out.write("                    td = tr[i].getElementsByTagName(\"td\")[0];\n");
      out.write("                    if (td) {\n");
      out.write("                        txtValue = td.textContent || td.innerText;\n");
      out.write("                        if (txtValue.toUpperCase().indexOf(filter) > -1) {\n");
      out.write("                                            \n");
      out.write("                            tr[i].style.display = \"block\";\n");
      out.write("                        } else {\n");
      out.write("                            \n");
      out.write("                            tr[i].style.display = \"none\";\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("\n");
      out.write("                }   \n");
      out.write("            }\n");
      out.write("           \n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function submitForm(n){\n");
      out.write("            console.log(n);\n");
      out.write("            \n");
      out.write("            var array=[];\n");
      out.write("            ");

                for(int i=0;i<movies.size();i++){
                    
      out.write("\n");
      out.write("                    array.push(\"");
      out.print(movies.get(i).getMovieID());
      out.write("\");\n");
      out.write("                    \n");
      out.write("                    ");

                }
            
      out.write("\n");
      out.write("            let x=document.getElementById(\"movie-search-id\");\n");
      out.write("            x.value=array[n];\n");
      out.write("            document.getElementById('movieSearchForm').submit();\n");
      out.write("        }\n");
      out.write("          \n");
      out.write("        \n");
      out.write("\n");
      out.write("        \n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("    <body>\n");
      out.write("    <div class=\"navbar\">\n");
      out.write("        <div class=\"navbar-container\">\n");
      out.write("            <div class=\"logo-container\">\n");
      out.write("                <h1 class=\"logo\">GSC</h1>\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <div class=\"menu-container\">\n");
      out.write("                \n");
      out.write("                <ul class=\"menu-list\">\n");
      out.write("                    <li class=\"menu-list-item\"><a href=\"Home.jsp\">Home</a></li>\n");
      out.write("                    <li class=\"menu-list-item\"><a href=\"searchMovie.jsp\">Movies</a></li>\n");
      out.write("                    <li class=\"menu-list-item\"><a href=\"Voucher\">Promotion</a></li>\n");
      out.write("                    ");

                        String email=ssLogUser.getEmail();
                        if(email!=null){                 
                    
      out.write("\n");
      out.write("                    <li class=\"menu-list-item\"><a href=\"#\">Profile <i class=\"fas fa-caret-down\"></i></a>\n");
      out.write("                        <div class=\"dropdown-menu\" style=\"z-index: 1\">\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"viewProfile.jsp\">View Profile</a></li> \n");
      out.write("                                <li><a href=\"PurchaseHistoryServlet\">View Purchase History</a></li> \n");
      out.write("                                <li><a href=\"RefundController\">Refund</a></li> \n");
      out.write("                                <li><a href=\"Logout\">Log Out</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                    ");

                        }else{        
                    
      out.write("\n");
      out.write("                    <li class=\"menu-list-item\"><a href=\"login.jsp\">Login</a></li>\n");
      out.write("                    ");

                            }                     
                    
      out.write("\n");
      out.write("                </ul>\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <div class=\"search-box-container\">       \n");
      out.write("               <div class=\"search-box\">\n");
      out.write("                    <input id=\"searchInput\" class=\"search-txt\" onkeyup='tableSearch()' type=\"text\" placeholder=\"Search...\">\n");
      out.write("                    \n");
      out.write("                    <a class=\"search-btn\" href=\"#\">            \n");
      out.write("                        <i class=\"fa fa-search\"></i>\n");
      out.write("                    </a>\n");
      out.write("                </div>         \n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("    </div>\n");
      out.write("                \n");
      out.write("    <div class=\"movie-table-container\">\n");
      out.write("            <form action=\"selectedMovie\" method=\"post\" id=\"movieSearchForm\">            \n");
      out.write("                <table class=\"table\" id=\"movieTable\" data-filter-control=\"true\" data-show-search-clear-button=\"true\">\n");
      out.write("                    ");

                        for(int i=0;i<movies.size();i++){
                    
      out.write("       \n");
      out.write("                    <tr class=\"tableRow\" style=\"display: none; width:200px;\" onclick=\"submitForm(");
      out.print(i);
      out.write(")\">                  \n");
      out.write("                            <td style=\"width: 200px;height:20px; font-size: 16px;\">");
      out.print(movies.get(i).getMovieTitle());
      out.write("</td>\n");
      out.write("                    </tr>      \n");
      out.write("                    ");

                        }
                    
      out.write("          \n");
      out.write("                </table> \n");
      out.write("                <input name=\"movie-id\" id=\"movie-search-id\" value=\"\" hidden> \n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    <div class=\"container\" style=\"top:110px;\">\n");
      out.write("            <div class=\"content-container\">\n");
      out.write("            \n");
      out.write("            <div class=\"featured-content\" style=\"top:10px;\">\n");
      out.write("                 <h1 class=\"movie-list-title\" style=\"top:90px; right: 10%;\">FEATURED</h1>\n");
      out.write("                <div class=\"movie-slide\" style=\"display: block;\">\n");
      out.write("                    <img src=\"img/banner8.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                <div class=\"movie-slide\" style=\"background: linear-gradient(to bottom, rgba(0,0,0,0), #151515);\">\n");
      out.write("                    <img src=\"img/banner9.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                 <div class=\"movie-slide\" style=\"background: linear-gradient(to bottom, rgba(0,0,0,0), #151515);\">\n");
      out.write("                    <img src=\"img/banner10.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                 <div class=\"movie-slide\" style=\"background: linear-gradient(to bottom, rgba(0,0,0,0), #151515);\">\n");
      out.write("                    <img src=\"img/banner11.png\" style=\"width:100%;height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                 <!-- Next and previous buttons -->\n");
      out.write("                <a class=\"prev\" onclick=\"plusSlides(-1, 0)\">&#10094;</a>\n");
      out.write("                <a class=\"next\" onclick=\"plusSlides(1, 0)\">&#10095;</a>\n");
      out.write("            </div>\n");
      out.write("            </div>\n");
      out.write("                 \n");
      out.write("            <div class=\"movie-list-container\" style=\"margin-top:100px;\">\n");
      out.write("                <div id=\"comingSoon\" style=\"margin-left: 30px\">\n");
      out.write("                    <h1 class=\"movie-list-title\">COMING SOON</h1>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"movie-list-wrapper\" style=\"margin-left: 25px\">\n");
      out.write("                    <div class=\"movie-list\">\n");
      out.write("                        ");
 
                            //Sort from earliest to latest date
                            for(int i=0;i<movies.size()-1;i++){
                                for(int j=0;j<movies.size()-1-i;j++){
                                    if(movies.get(j).getReleaseDate().isAfter(movies.get(j+1).getReleaseDate())){
                                        temp=movies.get(j);
                                        movies.set(j, movies.get(j+1));
                                        movies.set(j+1, temp);
                                        
                                    }   
                                }
                            }            
                            
                            int count=0;
                               //display 5 
                            for(int i=0;i<movies.size();i++){
                                sdate=movies.get(i).getReleaseDate();
                                    if(sdate.isAfter(LocalDate.now()) && count<8){
                        
      out.write("              \n");
      out.write("                            <form action=\"selectedMovie\" method=\"post\" onsubmit=\"return chkLoginStatus()\">\n");
      out.write("                                <div class=\"movie-list-item\">\n");
      out.write("                                    <img class=\"movie-list-item-img\" src=\"");
      out.print(movies.get(i).getImgString());
      out.write("\" style=\"width:200px;height:250px;padding:10px;\" alt=\"\">\n");
      out.write("                                    <span class=\"movie-list-item-title\" style=\"width:135px;\">");
      out.print(movies.get(i).getMovieTitle());
      out.write("</span>\n");
      out.write("                                    <input name=\"movie-id\" style=\"display:none;\" value=\"");
      out.print(movies.get(i).getMovieID());
      out.write("\">\n");
      out.write("                                    <input type =\"text\" style=\"display:none;\" id=\"logUser\" value=\"");
      out.print(ssLogUser.getEmail());
      out.write("\">\n");
      out.write("                                    <button type=\"submit\" class=\"movie-list-item-button\" style=\"color: black;\">Book Now</button>\n");
      out.write("                                </div>\n");
      out.write("                            </form>                      \n");
      out.write("                        ");
          count++;
                                    
                                }
                            }
                        
      out.write("        \n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <div class=\"movie-list-container\">\n");
      out.write("                \n");
      out.write("                <div id=\"newReleases\">\n");
      out.write("                    <h1 class=\"movie-list-title\">NEW RELEASES</h1>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"movie-list-wrapper\">\n");
      out.write("                    <div class=\"movie-list\">\n");
      out.write("                        \n");
      out.write("                            ");
 
                            //Sort from latest to earliest date
                            for(int i=0;i<movies.size()-1;i++){
                                for(int j=0;j<movies.size()-1-i;j++){
                                    if(movies.get(j).getReleaseDate().isBefore(movies.get(j+1).getReleaseDate())){
                                        temp=movies.get(j);
                                        movies.set(j, movies.get(j+1));
                                        movies.set(j+1, temp);
                                        
                                    }   
                                }
                            }            
                            
                            count =0;
                            //check if release date of movie is before today's date and is within a month after its release date
                            for(int i=0;i<movies.size();i++){
                                sdate=movies.get(i).getReleaseDate();
                                
                                    if(sdate.equals(LocalDate.now()) || sdate.isBefore(LocalDate.now()) && count<8){
                        
      out.write("\n");
      out.write("                        <form action=\"selectedMovie\" method=\"post\" onsubmit=\"return chkLoginStatus()\">\n");
      out.write("                                <div class=\"movie-list-item\">\n");
      out.write("                                    <img class=\"movie-list-item-img\" src=\"");
      out.print(movies.get(i).getImgString());
      out.write("\" style=\"width:200px;height:250px;padding:10px;\" alt=\"\">\n");
      out.write("                                    <span class=\"movie-list-item-title\" style=\"width:135px;\">");
      out.print(movies.get(i).getMovieTitle());
      out.write("</span>\n");
      out.write("                                    <input name=\"movie-id\" style=\"display:none;\" value=\"");
      out.print(movies.get(i).getMovieID());
      out.write("\">\n");
      out.write("                                    <input type =\"text\" id=\"logUser\" style=\"display:none;\" value=\"");
      out.print(ssLogUser.getEmail());
      out.write("\">\n");
      out.write("                                    <button type=\"submit\" class=\"movie-list-item-button\" style=\"color: black;\">Book Now</button>\n");
      out.write("                                </div>\n");
      out.write("                            </form>\n");
      out.write("                            ");
      count++;    
                                }
                            }
                            
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("            </div>\n");
      out.write("           \n");
      out.write("            <div class=\"content-container\" style=\"margin-top:50px ;\">\n");
      out.write("            \n");
      out.write("                <div class=\"featured-content\" style=\"margin-top:50px ;\">\n");
      out.write("                 \n");
      out.write("                 <div class=\"promotion-slide\" style=\"display: block;\">\n");
      out.write("                    <img src=\"img/cinema6.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                <div class=\"promotion-slide\" style=\"background: linear-gradient(to bottom, rgba(0,0,0,0), #151515);\">\n");
      out.write("                    <img src=\"img/cinema5.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                <div class=\"promotion-slide\" style=\"background: linear-gradient(to bottom, rgba(0,0,0,0), #151515);\">\n");
      out.write("                    <img src=\"img/cinema7.png\" style=\"width:100%; height: 500px; align-items: center;\">\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                 \n");
      out.write("                 <!-- Next and previous buttons -->\n");
      out.write("                <a class=\"prev\" onclick=\"plusSlides(-1, 1)\" style=\"top:65%\">&#10094;</a>\n");
      out.write("                <a class=\"next\" onclick=\"plusSlides(1, 1)\" style=\"top:65%\">&#10095;</a>\n");
      out.write("            </div>\n");
      out.write("                 \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <footer class=\"footer\">\n");
      out.write("  \t <div class=\"footer-container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"footer-col\">\n");
      out.write("                    <h4>movies</h4>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><a href=\"#newReleases\">now showing</a></li>\n");
      out.write("                            <li><a href=\"#comingSoon\">coming soon</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"footer-col\">\n");
      out.write("                    <h4>help</h4>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><a href=\"RefundController\">refund</a></li>\n");
      out.write("                            <li><a href=\"CustomerReview.jsp\">review</a></li>\n");
      out.write("                            \n");
      out.write("                            \n");
      out.write("                        </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"footer-col\">\n");
      out.write("                    <h4>company</h4>\n");
      out.write("                        <ul>\n");
      out.write("                            <li><a href=\"AboutUs.jsp\">about us</a></li>\n");
      out.write("                            <li class=\"contact-us-details\">Golden Screen Cinemas<br><br>04-646 1960<br><br>1, Lot 3F-50, Queensbay Mall, No.100, Persiaran Bayan Indah, 11900 Bayan Lepas, Pulau Pinang<br><br>cs@gsc.com.my </li> \n");
      out.write("                        </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"footer-col\">\n");
      out.write("                    <h4>follow us</h4>\n");
      out.write("                        <div class=\"social-links\">\n");
      out.write("                            <a href=\"https://www.facebook.com/GSCinemas/\"><i class=\"fab fa-facebook-f\"></i></a>\n");
      out.write("                            <a href=\"https://twitter.com/gsc_movies?lang=en\"><i class=\"fab fa-twitter\"></i></a>\n");
      out.write("                            <a href=\"https://www.instagram.com/gscinemas/?hl=en\"><i class=\"fab fa-instagram\"></i></a>\n");
      out.write("                            <a href=\"https://www.linkedin.com/company/gscinemas/\"><i class=\"fab fa-linkedin-in\"></i></a>\n");
      out.write("                        </div>\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("  \t </div>\n");
      out.write("  </footer>\n");
      out.write("    \n");
      out.write("   <script>\n");
      out.write("        function chkLoginStatus(){ \n");
      out.write("\n");
      out.write("        var email = document.getElementById('logUser').value;\n");
      out.write("            if(email === \"null\" || email===\"\"){\n");
      out.write("                alert('You must login in order to book tickets!');\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("            else\n");
      out.write("            {\n");
      out.write("                return true;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("    \n");
      out.write("   \n");
      out.write("</body>\n");
      out.write("\n");
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
