<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate, java.time.LocalTime"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssTicPayment" class="model.Payment" scope="session" />
<jsp:useBean id="ssTicSelectedSchedule" class="model.Schedule" scope="session" />
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Transaction Successful</title>
        <script type="text/javascript">
            //To block user from pressing the 'previous' button at the browser
            function preback(){window.history.forward();}
            setTimeout("preback()",0);
            window.onunload=function(){null};
            
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
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/style2.css">
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    </head>

    <body onload="showTicketMsg()">
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
            <div class="custom-content">
                <h1 class="pageTitle">Payment Successful</h1> <hr/>
                <br/>
                
                <%--Table used to display some of the transaction details to the user --%>
                <table id="transactionSuccessTable">
                    <colgroup>
                        <col class="desc" style="width: 40%;"/>
                        <col class="value" style="width: 60%;"/>
                    </colgroup>
                    
                    <tr>
                        <td colspan="2">
                            <h3 style="text-align: left; text-decoration: underline">Payment details</h3> 
                        </td>
                    </tr>
                    <tr>
                        <td>Payment No.: </td>
                        <td><b><%=ssTicPayment.getPayment_no()%></b></td>
                    </tr>
                    <tr>
                        <td>Payment Date: </td>
                        <% LocalDate payDate = ssTicPayment.getPayment_date(); %>
                        <td><b><%=String.format("%02d/%02d/%d", payDate.getDayOfMonth(), payDate.getMonthValue(), payDate.getYear())%></b></td>
                    </tr>
                    <tr>
                        <td>Payment Time: </td>
                        <% LocalTime payTime = ssTicPayment.getPayment_time(); %>
                        <td><b><%=String.format("%02d:%02d:%02d", payTime.getHour(), payTime.getMinute(), payTime.getSecond())%></b></td>
                    </tr>
                    <tr>
                        <td>Total Amount: </td>
                        <td><b><%=String.format("RM%.2f", ssTicPayment.getAmount()) %></b></td>
                    </tr>
                    <tr>
                        <td>Point Gained: </td>
                        <td><b><%=request.getAttribute("ttlPointGained")%> points</b></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2">
                            <br/><h3 style="text-align: left; text-decoration: underline">Movie details</h3>
                        </td>
                    </tr>
                    <tr>
                        <td>Movie Title: </td>
                        <td><b>${ssTicSelectedSchedule.getMovie().getMovieTitle()}</b></td>
                    </tr>
                    <tr>
                        <td>Showing Date: </td>
                        <td><b>${ssTicSelectedSchedule.getSchedule_date()}</b></td>
                    </tr>
                    <tr>
                        <td>Showing Time: </td>
                        <td><b>${ssTicSelectedSchedule.getSchedule_time()}</b></td>
                    </tr>
                    <tr>
                        <td>Hall No: </td>
                        <td><b>${ssTicSelectedSchedule.getHall().getHall_no()}</b></td>
                    </tr>
                    <tr>
                        <td>Seat Selected: </td>
                        <td><b>${selectedSeat}</b></td>
                    </tr>
                </table>
                <br/>
                
                
                <form action="Home.jsp" method="get">
                    <input type="submit" value="Done" class="proceedBtn" />
                </form>
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
                    
        <script>
            function showTicketMsg(){
                alert('Your ticket(s) has been sent to your registered email.');
            }
        </script>
        
    </body>

</html>