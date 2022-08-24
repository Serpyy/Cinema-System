<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session" />
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
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
        <title>Ticket Type Selection</title>
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
            <div class="custom-content">
                <% String selectedSeat = request.getParameter("selectedSeat"); //store the selected seat no that are passed from the previous page into the String object
                session.setAttribute("selectedSeat", selectedSeat); //save the selected seat into session (bcs it will be used by diff pages)
                int seatCount = Integer.parseInt(request.getParameter("seatCount")); //Retrieve the seatCount (the no of seat that user has selected) from the previous page
                double adultUnitPrice = 17.0;
                double childUnitPrice = 12.0; %>
                <h1 class="pageTitle">Ticket Type Selection</h1> <hr/>
                <br/>
                <h2 align="center">Seat Selected: <%= selectedSeat %> </h2> <br/>

                <%--Form that allow user to select and submit the ticket type that he/she wish to purchase (either is child or adult) --%>
                <form action="TicketTypeSelectionServlet" method="post" id="ticketTypeForm">
                    <%--Declare some of the hidden field to be passed to the servlet later (to create the ticket and payment object) --%> 
                    <input type ="hidden" value="<%=seatCount%>" id="seatCountSelected" name="seatCountSelected"/>
                    <input type="hidden" value="<%=adultUnitPrice%>" name="adultUnitPrice" />
                    <input type="hidden" value="<%=childUnitPrice%>" name="childUnitPrice" /> 
                    <input type="hidden" id="ttlPrice" name="ttlPrice" />
                    
                    <table id="ticketTypeTable">
                        <thead>
                            <tr>    
                                <th>Ticket Type</th>
                                <th>Quantity</th>
                                <th>Price(RM)</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>Adult</td>
                                <td><input name="adultQty" type="number" value="0" min="0" id="adultQty" onKeyDown="return false" onchange="subtotal(<%=adultUnitPrice%>, 'adult');"/></td>
                                <td><input type="text" id="adultSubTtl" class="subTtl" value="0.00" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>Child</td>
                                <% if(ssSelectedMovie.getAgeRating().equals("18")){ //if the movie is 18, then disable the child ticket %>
                                <td><input name="childQty" type="number" value="0" min="0" id="childQty" disabled/></td>
                                <% } else { //user is allow to purchase the child ticket %>
                                <td><input name="childQty" type="number" value="0" min="0" id="childQty" onKeyDown="return false" onchange="subtotal(<%=childUnitPrice%>, 'child');"/></td>
                                <% } %>
                                <td><input type="text" id="childSubTtl" class="subTtl" value="0.00" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td style="font-size:20px; text-align: right"><b>Total : </b></td>
                                <td><span id="total"><b><u>RM 0.00</u></b></span></td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>
                    
                    <input type="button" value="Proceed" id="proceedBtn" class="proceedBtn" disabled onclick="adultExistenceChk()" />        
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
            function subtotal(price, type){ //Method to calculate and display the line total (subtotal) to user 
                var qty = document.getElementById(type +"Qty").value;
                var subtotal = price * qty;

                document.getElementById(type + "SubTtl").value = subtotal.toFixed(2);

                limitCheck();
                grandTotal();
            }

            function limitCheck(){ //method that used to check whether the user has hit the limit of selecting the ticket type.
                                              //Exp: if user has selected 3 seat in the previous page, then at here he/she can only select maximum of 3 tickets only
                var childCount = document.getElementById("childQty").value;
                var adultCount = document.getElementById("adultQty").value;
                var limit = parseInt(document.getElementById("seatCountSelected").value);
                var currentCount = parseInt(childCount) + parseInt(adultCount);

                if(currentCount === limit){ //user are not allow to increase the ticket qty and proceed btn will be enable (has reach the limit dy)
                    document.getElementById("childQty").max = childCount;
                    document.getElementById("adultQty").max = adultCount;
                    document.getElementById("proceedBtn").disabled = false;
                }
                else{ //user are allow to increase the ticket qty and proceed btn will be disable (havent reach the limit)
                    document.getElementById("childQty").max = 100;
                    document.getElementById("adultQty").max = 100;
                    document.getElementById("proceedBtn").disabled = true;
                }
            }

            function grandTotal(){ //Method used to calculate the grand total of this transaction (havent undergoes any discount!)
                var adultSub = parseFloat(document.getElementById("adultSubTtl").value);
                var childSub = parseFloat(document.getElementById("childSubTtl").value);
                var total = adultSub + childSub;
                total=total.toFixed(2);
                document.getElementById("total").innerHTML = "<b>" + "<u>" + "RM " + total   + "</u>" + "</b>";
                document.getElementById("ttlPrice").value = total;
            }

            function adultExistenceChk(){ //ensure there is at least 1 adult ticket being purchased for every transaction
                if(document.getElementById("adultQty").value == 0){
                    alert('There must be at least 1 adult ticket being purchased. Please re-select the ticket quantity');
                }else{
                    document.getElementById('ticketTypeForm').submit();
                }
            }
        </script>
        
    </body>

</html>