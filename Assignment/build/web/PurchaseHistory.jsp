<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList, model.*"%>
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Purchase History</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/style2.css"/>
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
            rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
        <script>
        function tableSearch2() {
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

    <body onload="validatePaymentExistance('<%=session.getAttribute("ssPurHisRecordExistance") %>', '<%=session.getAttribute("ssPurHisSelectedIndex") %>')">
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
                    <input id="searchInput" class="search-txt" onkeyup='tableSearch2()' type="text" placeholder="Search...">
                    
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
                <% if(session.getAttribute("ssPurHisRecordExistance").equals("Valid")){  //Which means this part will only be run if the user has made at least 1 payment before %>
                <%--Retrieve the filtered array list from the session --%>
                <% 
                    ArrayList<Payment> selectedPayments = (ArrayList<Payment>) session.getAttribute("ssPurHisPaymentLink"); 
                    ArrayList<Schedule> selectedSchedules = (ArrayList<Schedule>)session.getAttribute("ssPurHisScheduleLink"); 
                %>
                      
                <%--Table used to display the page title and search bar --%>
                <table style="width: 100%">
                    <tr>
                        <td style="border-collapse: collapse"><h1 class="pageTitle">Payment History</h1> </td>
                        <td style="text-align: right" >
                            <div class="search-box-container">
                                <div class="search-box">
                                    <input class="search-txt" id="searchBox" type="text" placeholder="Search Payment ID..." onkeyup='tableSearch()' />
                                    <a class="search-btn" href="#">
                                        <i class="fa fa-search"></i>
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
                <hr/>

                <%--Form that display a selection list to user in order to allow them to select the time frame that he/she wish to view --%>
                <form name="historyForm" action="PurchaseHistoryServlet" method="post">
                    <select id="historySelector" name="historySelector" onchange="javascript:document.historyForm.submit();">
                            <option value="0">Select payment's time frame: </option>
                         <optgroup label="Upcoming">
                             <option value="1">Upcoming showing date</option>
                         </optgroup>

                         <optgroup label="Payment Date">
                             <option value="2">Pass 1 month</option>
                             <option value="3">Pass 2-3 months</option>
                             <option value="4">Pass 4-6 months</option>
                         </optgroup>
                     </select>
                </form>

                <%--Table that is used to display the purchase history details to the user based on his/her selection on the selection list --%>
                <table id="historyTable">
                    <colgroup>
                        <col class="indexNo" style="width: 200px"/>
                        <col class="paymentNo" style="width: 11%"/>
                        <col class="paymentDateTime" style="width: 15%"/>
                        <col class="movieTitle" style="width: 20%"/>
                        <col class="showingDateTime" style="width: 15%"/>
                        <col class="hallNo" style="width: 8%"/>
                        <col class="adultQty" style="width: 8%"/>
                        <col class="childQty" style="width: 8%"/>
                        <col class="amt" style="width: 10%"/>
                    </colgroup>
                    
                    <thead>
                        <th>No. </th>
                        <th>Payment No. </th>
                        <th>Payment Date&Time </th>
                        <th>Movie Title </th>
                        <th>Showing Date&Time </th>
                        <th>Hall No. </th>
                        <th>Adult Qty </th>
                        <th>Child Qty </th>
                        <th>Amount (RM) </th>
                    </thead>

                    <tbody>
                        <% if(selectedPayments.isEmpty()){ //If the payment's array list is empty (show no record found msg to user--%>
                        <tr>
                            <td colspan="9">No Record Found!</td>
                        </tr>
                        <% }else{  //if there is record(s) found after filtering the array list based on the user selection
                            for(int i=0; i<selectedPayments.size(); i++){ //loop to display the payment history's details to the user%>
                            <tr>
                                <td><%=i+1%></td>
                                <td><%=selectedPayments.get(i).getPayment_no() %></td>
                                <td><%=selectedPayments.get(i).getPayment_date() + ", " + selectedPayments.get(i).getPayment_time()%></td>
                                <td><%=selectedSchedules.get(i).getMovie().getMovieTitle() %></td>
                                <td><%=selectedSchedules.get(i).getSchedule_date() + ", " + selectedSchedules.get(i).getSchedule_time() %></td>
                                <td><%=selectedSchedules.get(i).getHall().getHall_no() %></td>
                                <td><%=selectedPayments.get(i).getAdult_qty() %></td>
                                <td><%=selectedPayments.get(i).getChild_qty() %></td>
                                <td><%=String.format("%.2f", selectedPayments.get(i).getAmount()) %></td>
                            </tr>
                     <% } %>
                        <tr>
                            <td colspan="8" style="text-align: right; font-weight: bold">Total: </td>
                            <td>
                                <% double ttlPayment = 0.0; 
                                for (int i=0; i<selectedPayments.size(); i++){ 
                                    ttlPayment += selectedPayments.get(i).getAmount();
                                }
                                //Display the total payment that he/she has spent by adding up the amount displayed inside each of the table records
                                out.println("<b><u>RM" + String.format("%.2f", ttlPayment) + "</u> </b>"); %>
                            </td>                    
                        </tr>
                    <% } %>
                    </tbody>
                </table>
                <br/> <br/>
                <%} %>
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
            function validatePaymentExistance(status, selectedIndex){ //Will be triggered if this user has not yet done any payment(display errormsg to him and bring him back to the home page)
                if(status === "Invalid"){
                    alert('No transaction made by this account. \nPlease purchase at least one ticket before entering this page!');
                    window.location = 'Home.jsp';
                }else{
                    selectIndex(selectedIndex);
                }
            }
            
            function selectIndex(index){ //To determine which option should be selected when this page has been loaded up/being refreshed
                document.getElementById("historySelector").value = index;
            }

            function tableSearch(){ //method that makes the search function on the top nav bar workable 
                var input, table, tr, td, txtValue, filter;

                input = document.getElementById('searchBox');
                filter = input.value.toUpperCase();
                table = document.getElementById("historyTable");
                tr= table.getElementsByTagName("tr");

                for(var i =1; i<tr.length; i++){
                    td = tr[i].getElementsByTagName("td")[1];
                    if(td){
                        txtValue=td.textContent || td.innerText;
                        if(txtValue.toUpperCase().indexOf(filter) > -1){
                            tr[i].style.display=""; 
                        }
                        else{
                            tr[i].style.display="none";
                        }
                    }
                }
            }
        </script>

    </body>

</html>
