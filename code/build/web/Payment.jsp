<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList, model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ssTicPayment" class="model.Payment" scope="session" />
<jsp:useBean id="ssTicTempCard" class="model.Card" scope="session" />
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>
<jsp:useBean id="ssSelectedMovie" class="model.Movie" scope="session"/>
<%    
        List<Movie> movieList = new ArrayList<Movie>();
        if(session.getAttribute("movieList") != null){
            movieList = (List<Movie>)session.getAttribute("movieList");
        }
    
    
    Movie temp=new Movie();

    ArrayList<Movie> movies =(ArrayList<Movie>)session.getAttribute("movieList");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment</title>
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

    <body onload="chkPaymentStatus('<%=session.getAttribute("paymentErrMsg")%>')">
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
                <h1 class="pageTitle">Payment</h1> <hr/>
                <br/>
                <% ArrayList<Card> cards = (ArrayList<Card>) session.getAttribute("ssTicCards"); %>
        
                <h2 style="text-align: center; margin-bottom: 5px">Total Payable Amount: RM <%=String.format("%.2f", ssTicPayment.getAmount()) %></h2>
                <br/>
                <form action="PaymentServlet" method="post" id="paymentForm" name="paymentForm">
                    <table id="paymentTable">
                    <colgroup>
                        <col class="desc" style="width: 40%"/>
                        <col class="value" style="width: 60%"/>
                    </colgroup>
                    <tr>
                        <td>Card Selection: </td>
                        <td>
                            <select name="cardSelection" id='cardSelection' onchange="selectionChged()">
                                <option value="null">Select an option to make payment</option>
                                <option value="register">Register a new Credit Card</option>
                                <% if(cards.size()!=0){ %>
                                <optgroup label="Registered Card(s)">
                                    <% for(int i=0; i<cards.size(); i++){ 
                                            if(cards.get(i).getCard_no().equals(ssTicTempCard.getCard_no())) { %>
                                            <option value="<%= i %>" selected><%=cards.get(i).getCard_no() %></option>
                                      <% }else { %>
                                      <option value="<%= i %>"><%=cards.get(i).getCard_no() %></option>
                                      <% } %>
                                    <% } %>
                                </optgroup>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Card Holder's Name: </td>
                        <td><input type="text" value='<%=ssTicTempCard.getCardholder_name()%>' id="name" name="name" maxlength = "50" disabled oninput="submitBtnStatus()"/></td>
                    </tr>
                    <tr>
                        <td>Card No (without space): </td>
                        <td><input type="text" value='<%=ssTicTempCard.getCard_no()%>' id="cardNo" name="cardNo" disabled maxlength = "16" oninput="submitBtnStatus()"/></td>
                    </tr>
                    <tr>
                        <td>Expiry Date (MM/YYYY): </td>
                        <td><input type="text" value='<%=ssTicTempCard.getExpiry_dateInStr()%>' id='expDate' name="expDate" disabled maxlength = "7"  oninput="submitBtnStatus()"/></td>
                    </tr>
                    <tr>
                        <td>CVV: </td>
                        <td>
                            <% if(ssTicTempCard.getCard_no().equals("")){ %>
                            <input type="password" id="cvv" name="cvv" maxlength = "3" disabled oninput="submitBtnStatus()"/>
                            <% }else { %>
                            <input type="password" id="cvv" name="cvv" oninput="submitBtnStatus()" maxlength = "3" />
                            <% } %>
                        </td>
                    </tr>
                    </table>
                    <br/>
                    <input type="submit" value="Proceed" id="submitBtn" name="submitBtn" class="proceedBtn" disabled onclick="return confirm('Are you sure you want to make payment on this transaction?')" style="margin: 0 47%"/>
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
            function chkPaymentStatus(status){ //Display error message to user if they have entered the wrong info during registration/enter invalid CVV.
                if(status != "Valid"){
                    alert(status);
                }
            }

            function selectionChged(){ //if user chg the option given inside the selection list, then it will trigger this func
                //Retrieve the text that user entered from the text field 
                var name = document.getElementById("name");
                var cardNo = document.getElementById("cardNo");
                var expDate = document.getElementById("expDate");
                var cvv = document.getElementById("cvv");
                
                if(document.getElementById('cardSelection').selectedIndex === 0){ //if user select the first option
                    //Clear all the content inside the text field
                    name.value="";
                    cardNo.value="";
                    expDate.value="";
                    cvv.value="";

                    //Disable all the text fields
                    name.disabled = true;
                    cardNo.disabled = true;
                    expDate.disabled = true;
                    cvv.disabled = true;
                }else if(document.getElementById('cardSelection').selectedIndex === 1){ //if user wan to register new card
                    //Clear all the content inside the text field
                    name.value="";
                    cardNo.value="";
                    expDate.value="";
                    cvv.value="";

                    //Enable all the text fields
                    name.disabled = false;
                    cardNo.disabled = false;
                    expDate.disabled = false;
                    cvv.disabled = false;
                }else{ //the user has select either one of the registered card (will pass it to the servlet for processing)
                    document.getElementById('paymentForm').submit();
                }
            }

            function submitBtnStatus(){ //Decide when should enable the submit btn and when shuld disable it
                //Retrieve the text that user entered from the text field 
                var name = document.getElementById("name").value;
                var cardNo = document.getElementById("cardNo").value;
                var expDate = document.getElementById("expDate").value;
                var cvv = document.getElementById("cvv").value;

                if(name === "" || cardNo === "" || expDate === "" || cvv === ""){ //if either one of the text field is empty (disable the btn)
                    document.getElementById("submitBtn").disabled = true; 
                }else{ //If all field has been filled in (enable the btn)
                    document.getElementById("submitBtn").disabled = false;
                }                 
            }
        </script>
        
    </body>

</html>