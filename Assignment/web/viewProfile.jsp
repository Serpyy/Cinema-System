<%@page import="java.util.List"%>
<%@page import="model.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session" />
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
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
        <!-- Bootstrap CSS -->
        
        <!-- Font Awesome CSS -->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link
        href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
        rel="stylesheet">
        <script src="https://use.fontawesome.com/f3fb347b11.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <link href="css/style5.css" rel="stylesheet" type="text/css"/>
        <title>View Profile</title>
        
        <script type="text/javascript">
            
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
        
      </div>
    </div>
                
    <div class="view-profile-container">
        <div class="card">
            
            <div class="container" style="background-color:black">
                <br>
                <h3 class="header" style="font-size: 25px;">Profile <a href="editProfile.jsp" class="fa fa-pencil-square-o" aria-hidden="true"></a></h3>
                <br>
            <table class="profile_table" style="width:60%">
            <tr>
              <th width="30%" style="color:grey">Customer ID</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getCust_ID()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Name</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getCust_name()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Mobile Number	</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getPhone_no()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Email</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getEmail()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">IC No</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getIc_no()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Security Question</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getSecurity_question()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Security Answer</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getAnswer()%></td>
            </tr>
            <tr>
              <th width="30%" style="color:grey">Point</th>
              <td width="2%" style="color:grey">:</td>
              <td><%=ssLogUser.getPoint()%></td>
            </tr>
          </table>

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
