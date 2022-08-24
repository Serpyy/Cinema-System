<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session"/>
<%
       System.out.println("Priting JSP");
       ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("ssPaymentList");
       ArrayList<Ticket> ticketList = (ArrayList<Ticket>) session.getAttribute("ssTicketList");
       
    List<Movie> movieList = new ArrayList<Movie>();
    if(session.getAttribute("movieList") != null){
        movieList = (List<Movie>)session.getAttribute("movieList");
    }
    

    ArrayList<Movie> movies =(ArrayList<Movie>)session.getAttribute("movieList");
%>
<!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/style3.css" />
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
    <title>New Refund Request</title>
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
        <br><br>
        <div id="requestRefund">
                 <table>
                     <thead>
                         <th colspan="3">Payment </th>
                     </thead>
                     <% if(paymentList.isEmpty() ){%>
                     <tr>
                                <td colspan="3"  id="norecord" > 
                                    No Payment refundable<br><br>
                                    <div id="backToRefundPage">
                                        <a  href="RefundCustomer.jsp" >Go back</a>
                                    </div>
                                </td>
                        </tr>
                     <%}else{%>
                     <% for(Payment payment: paymentList) {%>
                        <tr>
                        <form class="newRefundRequest" method="post" action="Refund_Request">
                                <td>
                                    Payment Number <br>
                                    Amount <br>
                                    Payment Date <br>
                                    Total Ticket <br>
                                    Schedule Date <br>
                                    Schedule Time <br>
                                    Movie<br>
                                    Hall<br>
                                    Reason<br>
                                </td>
                                <td>
                                    <input type="text" name="txtRefundPayementNo" size="20"
                                                         value="<%=payment.getPayment_no() %>" readonly=""/><br>
                                    <input type="text" name="txtRefundPayemntAmount" size="20"
                                           value="<%=String.format("RM %.2f",payment.getAmount() )%>" disabled/><br>
                                    <input type="text" name="txtRefundPayemntDate" size="20"
                                           value="<%=String.format("%02d-%02d-%4d", payment.getPayment_date().getDayOfMonth(),
                                                                                    payment.getPayment_date().getMonthValue(),
                                                                                    payment.getPayment_date().getYear())  %>" disabled/><br>
                                    <input type="text" name="txtChildTicket" size="20"
                                                          value="<%=(payment.getChild_qty() + payment.getAdult_qty())%>"  disabled/><br>
                                    
                                    <% for(Ticket ticket: ticketList){ 
                                                if(ticket.getPayment().equals(payment)){%>
                                    <input type="text" name="txtscheduleDate" size="20"
                                           value="<%=String.format("%02d-%02d-%4d", ticket.getSchedule().getSchedule_date().getDayOfMonth(),
                                                                                    ticket.getSchedule().getSchedule_date().getMonthValue(),
                                                                                    ticket.getSchedule().getSchedule_date().getYear())%>" disabled /><br>
                                    <input type="text" name="txtscheduleDate" size="20"
                                           value="<%=ticket.getSchedule().getSchedule_time() %>" disabled /><br>  
                                    <input type="text" name="txtMovietext" size="20"
                                           value="<%=ticket.getSchedule().getMovie().getMovieTitle() %>" disabled /><br>  
                                    <input type="text" name="txtHall" size="20"
                                           value="<%=ticket.getSchedule().getHall().getHall_no() %>" disabled /><br>  
                                    <% break;}}%>
                                    <input type="text" list="reasonList" name="txtreason" class="reason" required /> 
                                    <datalist id="reasonList">
                                        <option value="Infected / Close Contact to Covid-19">Infected / Close Contact to Covid-19</option>
                                        <option value="Change Of Mind">Change Of Mind</option>
                                        <option value="Error in the Payment">Error in the Payment</option>
                                        <option value="Others">Others</option>
                                    </datalist>
                                </td>
                                <td ><input type="submit" value="Refund" class="button"/></td>
                             </form>
                       </tr>
                       <%}}%>
                     </tbody>
                 </table>
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

