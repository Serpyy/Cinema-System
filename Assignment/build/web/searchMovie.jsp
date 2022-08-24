<%@page import="java.time.LocalTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="model.MovieDA"%>
<%@page import="model.ScheduleDA"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session"/>
<jsp:include page="StartUp" />

<%
    List<Movie> movieList = new ArrayList<Movie>();
    if(session.getAttribute("movieList") != null){
        movieList = (List<Movie>)session.getAttribute("movieList");
    }
    
    
    
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
    LocalDate sdate= LocalDate.parse("2022-1-1", dateFormat);
    
    
    Movie temp=new Movie();

    ArrayList<Movie> movies =(ArrayList<Movie>)session.getAttribute("movieList");
    
%>

<!DOCTYPE html>
<html>
    <head>
        
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
     
    <title>Movies</title>
    
    <script>
        function searchMovie(){
            const searchbox = document.getElementById("search-Bar").value.toUpperCase();
            const storemovies = document.getElementById("flex-box")
            const movie=document.querySelectorAll(".movie-list")
            const movieTitle=document.getElementsByTagName("h2")

            for(var i=0;i<movieTitle.length;i++){
                let match = movie[i].getElementsByTagName('h2')[0];

                if(match){
                    let textvalue=match.textContent || match.innerHTML
                    if(textvalue.toUpperCase().indexOf(searchbox) > -1){
                        movie[i].style.display = "";
                    }else{
                        movie[i].style.display = "none";
                    }
                }
            }
            
        }
        
        function chkLoginStatus(){ 
            
        var email = document.getElementById('logUser').value;
            if(email === "null" || email===""){
                alert('You must login in order to book tickets!');
                return false;
            }
            else
            {
                return true;
            }

        }
        
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
                        <div class="dropdown-menu" style="z-index: 1">
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
                    <div class="content-container">
                        <div class="search-container">
                            <div class="wrap">
                                <div class="search">
                                <form>
                                    <input type="text" class="searchTerm" id="search-Bar" placeholder="Search Movie..." onkeyup="searchMovie()">
                                    
                                </form>
                                </div>
                              </div> 
                            <div class="movie-list-container">
                                <div class="movie-list-wrapper" id="movie-list-wrapper">
                                    
                                        <div class="flex-box">
                                            <% 
                                            //check if release date of movie is before today's date and is within a month after its release date
                                            for(int i=0;i<movies.size();i++){
                                                
                                        %>
                                        <form action="selectedMovie" method="post" onsubmit="return chkLoginStatus()">
                                            <div class="movie-list" id="movie-list">
                                                <div class="search-movie-list-item">
                                                    <img class="search-movie-list-item-img" src="<%=movies.get(i).getImgString()%>" style="width:170px;height:220px;padding:10px;" alt="">
                                                    <h2 class="search-movie-list-item-title" style="width:140px;"><%=movies.get(i).getMovieTitle()%></h2>
                                                    <input name="movie-id" style="display:none;" value="<%=movies.get(i).getMovieID()%>">
                                                    <input id="logUser" style="display:none;" value="<%=ssLogUser.getEmail()%>">
                                                    <button type="submit" class="search-movie-list-item-button" style="color: black;">Book Now</button>
                                                </div>          
                                            </div>
                                            </form>
                                            <%          
                                                }
                                            
                                            %>
                                            </div>
                                        
                                    </div>

                            </div>
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
