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
<html lang="en">
    
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Seat Selection</title>
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
                <h1 class="pageTitle">Seat Selection</h1> <hr/>
                <br/>
                
                <h1 align="center">Screen</h1> <br/>
                <table align="center" id="seatLayoutTable">
                    <% boolean[][] seatLayout = (boolean[][]) session.getAttribute("seatAvaiable"); //retrieve the seat avaiability array from the session
                    int totalRow = seatLayout.length; 
                    int totalCol = seatLayout[0].length;
                    int totalSeatRemaining = totalRow * totalCol; //to be displayed as the number of remaining seat for the first time
                                                                                     //The subsequent count will be updated at the JavaScript.
                    //loop to display the seat layout table to the user (if available, then show in green, else red)
                    for(int i=totalRow-1; i>=0; i--){
                        out.println("<tr class=\"seatRow\">");
                        for(int j=0; j<totalCol; j++){
                            if(seatLayout[i][j] == true){
                                out.println("<td class=\"seatColumn\" style=\"color:rgb(0, 248, 1);\" id=\"" + i + j + "\" onclick=\"seatSelection(" + totalRow + ", " + totalCol + ", " + i + ", " + j +")\" >" + (char)(i+65) + String.format("%02d", (j+1)) + "</td>");
                            }
                            else{
                                out.println("<td class=\"seatColumn\" style=\"color:rgb(226, 31, 39);\"  id=\"" + i + j + "\">" + (char)(i+65) + "0" + (j+1) + "</td>");
                                totalSeatRemaining--;
                            }

                        }
                        out.println("</tr>");
                    }
                    %>
                </table> <br/>
                
                <%--Form to allow user to press the submit button after finish selecting the seat--%>
                <form action="TicketTypeSelection.jsp" method="post">
                    <input type="hidden" name="selectedSeat" id="selectedSeatTextBox" />
                    <input type="hidden" name="seatCount" id="seatCount"/>
                    <% session.setAttribute("remainingSeat", totalSeatRemaining); %>
                    <h3 id="displaySeatRemaining" align="center" style="padding-bottom: 5px"><%= totalSeatRemaining %> seat(s) remaining</h3>
                    <h3 id="displaySeatCount" align="center">Total 0 seat(s) selected</h3> <br/>
                    <input type="submit" value="Proceed" id="proceedBtn" class="proceedBtn" disabled />
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
            function seatSelection(totalRow, totalColumn, row, col){ //function that being tiggered when user select the seat 
                var selectedCell = document.getElementById(row + "" + col); //to know which cell is being clicked
                
                if(selectedCell.style.color === "rgb(0, 248, 1)"){ //if the particular field's font color is green 
                    selectedCell.style.color = 'rgb(244, 230, 24)' ; //chg the font color to yellow (yellow means it has been selected)
                    selectedCell.style.fontWeight = 900;
                }
                else{ //if the particular field's font color is alr yellow (means it has been selected previously) 
                    selectedCell.style.color = 'rgb(0, 248, 1)' ; //chg the font color to green
                    selectedCell.style.fontWeight = 400;
                }
                
                var hallRemainingSeat = totalRow * totalColumn; //initiate it with hall size first, after that only minus it using loop
                var countSeat = 0; //counter to count how many seat is currently unavaiable
                var selectedSeat =0; //counter to count user has selected how many seats (need to use in next page)
                document.getElementById("selectedSeatTextBox").value = "";
                //Loop to calculate the no. of seat remaining and the no. of seat that user has selected
                for(var i=0; i<totalRow; i++){
                    for(var j=0; j<totalColumn; j++){
                        if(document.getElementById(i + "" + j).style.color !== "rgb(0, 248, 1)") { //if the font color is not green (means the particular seat is currently unavailable)
                            countSeat++; 
                            if(document.getElementById(i + "" + j).style.color === "rgb(244, 230, 24)"){ //if the font color is yellow (being selected by user)
                                document.getElementById("selectedSeatTextBox").value += document.getElementById(i + "" + j).innerHTML + " "; //add the selected seatNo into the hidden field
                                selectedSeat++;
                            }
                        }
                    }
                }
                
                var seatRemaining = hallRemainingSeat - countSeat; //To determine the no of avaiable seat inside this hall
                document.getElementById("displaySeatRemaining").textContent = seatRemaining + " seat(s) remaining"
                document.getElementById("displaySeatCount").textContent = "Total " + selectedSeat + " seat(s) selected";
                document.getElementById("seatCount").value = selectedSeat; //store the no of seat that user has selected into the hidden form field

                if(selectedSeat === 0){ //if user havent select any seat, then they are not allowed to proceed
                    document.getElementById('proceedBtn').disabled = true;
                }else{ //User has select at least 1 seat, so the proceed button will be enabled
                    document.getElementById('proceedBtn').disabled = false;
                }
            }
        </script>

    </body>
    
</html>