
<%@page import="java.util.List"%>
<%@page import="model.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session"/>

<%
    List<Movie> movieList = new ArrayList<Movie>();
    if(session.getAttribute("movieList") != null){
        movieList = (List<Movie>)session.getAttribute("movieList");
    }
    

    ArrayList<Movie> movies =(ArrayList<Movie>)session.getAttribute("movieList");
    
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/style3.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <script
      defer
      src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"
    ></script>
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
    />
    <title>About Us</title>
    <script>
        function tableSearch() {
            let input, filter, table, tr, td, txtValue, x;

            //Intialising Variables
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("movieTable");
            tr = table.getElementsByTagName("tr");
           

            if(input.value.length===0){               
                for (let i = 0; i < tr.length; i++){
                    tr[i].style.display = "none";
                }
            }else{
                for (let i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                            
                            tr[i].style.display = "block";
                        } else {
                            
                            tr[i].style.display = "none";
                        }
                    }

                }   
            }
           
        }
        
        function submitForm(n){
            console.log(n);
            
            var array=[];
            <%
                for(int i=0;i<movies.size();i++){
                    %>
                    array.push("<%=movies.get(i).getMovieID()%>");
                    
                    <%
                }
            %>
            let x=document.getElementById("movie-search-id");
            x.value=array[n];
            document.getElementById('movieSearchForm').submit();
        }
    </script>
  </head>
  <body>
    <div class="navbar">
        <div class="navbar-container">
            <div class="logo-container">
                <h1 class="logo">GSC</h1>
                
            </div>
            <div class="menu-container">
                
                <ul class="menu-list">
                    <li class="menu-list-item"><a href="Home.jsp">Home</a></li>
                    <li class="menu-list-item"><a href="searchMovie.jsp">Movies</a></li>
                    <li class="menu-list-item"><a href="Voucher">Promotion</a></li>
                    <%
                        String email=ssLogUser.getEmail();
                        if(email!=null){                 
                    %>
                    <li class="menu-list-item"><a href="#">Profile <i class="fas fa-caret-down"></i></a>
                        <div class="dropdown-menu">
                            <ul>
                                <li><a href="viewProfile.jsp">View Profile</a></li> 
                                <li><a href="PurchaseHistoryServlet">View Purchase History</a></li> 
                                <li><a href="RefundController">Refund</a></li> 
                                <li><a href="Logout">Log Out</a></li>
                            </ul>
                        </div>
                    </li>
                    <%
                        }else{        
                    %>
                    <li class="menu-list-item"><a href="login.jsp">Login</a></li>
                    <%
                            }                     
                    %>
                </ul>
                
            </div>
            <div class="search-box-container">       
               <div class="search-box">
                    <input id="searchInput" class="search-txt" onkeyup='tableSearch()' type="text" placeholder="Search...">
                    
                    <a class="search-btn" href="#">            
                        <i class="fa fa-search"></i>
                    </a>
                </div>         
        </div>
        
    </div>
                
    <div class="movie-table-container" style="margin-right:200px;">
            <form action="selectedMovie" method="post" id="movieSearchForm">            
                <table style="position: absolute;" class="table" id="movieTable" data-filter-control="true" data-show-search-clear-button="true">
                    <%
                        for(int i=0;i<movies.size();i++){
                    %>       
                    <tr class="tableRow" style="display: none; width:200px; " onclick="submitForm(<%=i%>)">                  
                            <td style="width: 200px;height:20px; font-size: 16px;"><%=movies.get(i).getMovieTitle()%></td>
                    </tr>      
                    <%
                        }
                    %>          
                </table> 
                <input name="movie-id" id="movie-search-id" value="" hidden> 
            </form>
        </div>

    <div class="container">
      <br />
      <br />

      <table id="aboutus">
        <tr>
          <td>
            <img
              id="cinemaImage"
              src="img/cinemaView.png"
              title="Front view of Our Cinema"
              alt="Front view of Our Cinema"
            /><br>
            <p>
              <b style="font-size: 30px; padding-left: 5px;">History </b><br><br>
              Golden Screen was founded in 1987 under the name of Golden
              Communications Circuit, a joint venture between Hong Kong's Golden
              Harvest and the Malaysian conglomerate PPB Group, the latter of
              whom operated a small set of Malaysian cinemas leased from Shaw
              Brothers Studio. After January 1988 merger between Golden
              Communications (GC) Circuit and the Malaysian branch of Cathay
              Organisation, the company was renamed Golden Screen Cinemas.
            </p>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h3 style="font-size: 25px;">About Our Cinema</h3><br>
            <p>
              <b>Gold Screen Cinema</b>, Malaysia's largest cinema exhibitor,
              that specializes in entertainment and film distribution in the
              media industry. Gold Screen Cinema was found in 1987 as Golden
              Communications Circuit, a joint venture between the Kuok family's
              conglomerate PPB and Hong Kong's once-powerful Golden Harvest. The
              business of Gold Screen Cinema mainly operates based on ticket
              sales. Besides that, the customer screening of newly released
              films makes up the majority of the income of the company. Gold
              Screen Cinema aims to entice a different segment who normally
              doesn't go to the cinema. Gold Screen Cinema is also committed to
              providing the community with world-class entertainment while
              supporting local filmmakers along the process.
            </p>
          </td>
        </tr>
      </table>
      <br />
      <br />

      <div id="info">
          <div class="row">
            <div class="col" >
              <h3>Operating Hour</h3><br />
              <b>Every Day :</b> <br />
                8:00AM- 3:00AM<br />
                (including public holiday)<br><br>
              </div>
              <div class="col" >
                <h3>Contact Us</h3><br />
                <b>Tel no. (mobile) :</b>
                <a href="tel:+6019-4872358" style="text-decoration: none;color: #ffe229" title="Call Us" class="contactUs">
                  019-4872358</a>
                <br />
                <b>Tel no. (hotline) :</b
                ><a href="tel:+6046461960" style="text-decoration: none;color: #ffe229" title="Call Our Hotline" 
                  class="contactUs">
                  04-646 1960</a>
                <br />
                <b>Email :</b>
                <a href="mailto:Cs@gsc.com.my"
                  style="text-decoration: none;color: #ffe229" title="Contact Us via Email" class="contactUs">
                  Cs@gsc.com.my</a><br><br>
              </div>
              <div class="col" >
              <h3>Address </h3><br>
                1, Lot 3F-50, Queensbay Mall, No.100,
                Persiaran Bayan Indah, 11900 Bayang Lepas, Pulau Pinang <br /><br />
            </div>
          </div>
      </div>

    <footer class="footer">
  	 <div class="footer-container">
            <div class="row">
                <div class="footer-col">
                    <h4>movies</h4>
                        <ul>
                            <li><a href="searchMovie.jsp">Search Movies</a></li>                        
                        </ul>
                </div>

                <div class="footer-col">
                    <h4>help</h4>
                        <ul>
                            <li><a href="RefundController">refund</a></li>
                            <li><a href="CustomerReview.jsp">review</a></li>                
                        </ul>
                </div>
                <div class="footer-col">
                    <h4>company</h4>
                        <ul>
                            <li><a href="AboutUs.jsp">about us</a></li>
                            <li class="contact-us-details">Golden Screen Cinemas<br><br>04-646 1960<br><br>1, Lot 3F-50, Queensbay Mall, No.100, Persiaran Bayan Indah, 11900 Bayan Lepas, Pulau Pinang<br><br>cs@gsc.com.my </li> 
                        </ul>
                </div>
                <div class="footer-col">
                    <h4>follow us</h4>
                        <div class="social-links">
                            <a href="https://www.facebook.com/GSCinemas/"><i class="fab fa-facebook-f"></i></a>
                            <a href="https://twitter.com/gsc_movies?lang=en"><i class="fab fa-twitter"></i></a>
                            <a href="https://www.instagram.com/gscinemas/?hl=en"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.linkedin.com/company/gscinemas/"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    
                </div>
            </div>
  	 </div>
  </footer>
  </body>
</html>
