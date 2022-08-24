<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Movie, java.time.LocalTime, java.time.LocalDate, java.util.*"%>
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
        <title>Date&Time Selection</title>
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

    <body onload="chkSubmitBtnColor('<%=session.getAttribute("ssTicScheduleDateBtnChk")%>')">
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
            <form action="selectedMovie" method="post" id="movieSearchForm" >            
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
                <h1 class="pageTitle">Purchase Ticket</h1> <hr/>
                <br/>
                <div id="mainContent">
                    <%--Get the movie details and the casts of this movie from the session --%>
                    <% Movie movieSelected = (Movie) session.getAttribute("ssSelectedMovie");
                          ArrayList<String> casts = (ArrayList<String>) session.getAttribute("ssTicMovieSelectedCasts"); %>
                    
                    <div id="movieDesc">
                        <table id="movieDescTable">
                            <colgroup>
                                <col class="poster" style="width: 30%"/>
                                <col class="desc" style="width: 70%" />
                            </colgroup>
                            <tr>
                                <td>
                                    <img src="<%=movieSelected.getImgString()%>" alt="Movie Poster" id="moviePoster"/>
                                </td>
                                <td>
                                    <h3 class="movieDescHeader">Movie Title: </h3>
                                    <ul class="movieDescList">
                                        <li><%= movieSelected.getMovieTitle() %></li> 
                                    </ul>
                                    <br/>

                                    <h3 class="movieDescHeader">Age Rating: </h3>
                                    <ul class="movieDescList">
                                        <li><%= movieSelected.getAgeRating()%></li> 
                                    </ul>
                                    <br/>

                                    <h3 class="movieDescHeader">Duration: </h3>
                                    <ul class="movieDescList">
                                        <li><%= movieSelected.getDuration()%> minutes</li> 
                                    </ul>
                                    <br/>
                                    <h3 class="movieDescHeader">Release Date: </h3>
                                    <ul class="movieDescList">
                                        <li><%= movieSelected.getReleaseDate()%></li> 
                                    </ul>
                                    <br/>

                                    <h3 class="movieDescHeader">Director: </h3>
                                    <ul class="movieDescList">
                                        <li><%= movieSelected.getDirector()%></li> 
                                    </ul>
                                    <br/>

                                    <h3 class="movieDescHeader">Casts: </h3>
                                    <ul class="movieDescList">
                                    <% for(int j=0; j<casts.size(); j++){ %>
                                    <% if(j !=0 && j%3==0 ){  //1 line maximum 3 casts %>
                                        <br/>
                                    <% } %>
                                        <li><%=casts.get(j)%></li>                                  
                                    <% } %>
                                    </ul>
                                    <br/>
                                </td>
                            </tr>
                        </table>
                                    
                        <div id="movieSynopsis">
                            <h3 class="movieDescHeader" style="margin-left: 5px">Synopsis: </h3>
                            <ul class="movieDescList" style="padding-left: 20px">
                                <li><%= movieSelected.getSynopsis()%></li>
                            </ul>
                            <br/>
                        </div>
                            
                    </div>
                    <hr/> <br/>
                    
                    <%--Form that allow user to select the schedule time --%>
                    <% 
                        List<LocalDate> scheduleDate =  (List<LocalDate>) session.getAttribute("ssTicScheduleDate");
                        if(scheduleDate.size()!=0) { //means if there is no schedule assigned to this movie yet)   
                     %>
                        <form action="DateTimeSelectionServlet" method="post" name="dateSelection">
                        <input type="hidden" name="clickedBtn" value=""/>
                        <h3 style="margin-bottom: 3px">Select Date: </h3>
                        <table id="dateSelectionTable">
                            <tr>
                                <% 
                                    LocalDate tempDate = null;
                                    for(int i=0; i<scheduleDate.size(); i++){
                                        if(i !=0 && i%5==0){ //1 line (row) can only max display 5 button!
                                           out.println("</tr> <tr>");
                                       }
                                        tempDate = scheduleDate.get(i);
                                %>
                                <td><button id="btn<%= (i+1)%>" name="btn<%= (i+1)%>" class="scheduleDateBtn" value="<%= tempDate %>" type="submit"
                                            onclick="{document.dateSelection.clickedBtn.value=this.id;}" ><span align="center">
                                            <%=tempDate.getDayOfWeek()%></span></br><%=tempDate%></button></td>
                               <% } %>
                           </tr>
                        </table>
                    </form>
                    <% } %>

                    <%
                        if(session.getAttribute("ssTicScheduleDateBtnChk") != ""){ //this part will only be run after the user have select the date
                    %>
                    <br/>
                    <%-- Form that allow user to select the scehdule time based on the selected schedule date --%>
                    <form action="SeatSelectionServlet" method="post">
                        <input type="hidden" name="selectedDate" value="<%=session.getAttribute("ssTicSelectedDate")%>" />
                        <h3 style="margin-bottom: 3px">Select Time: </h3>
                        <table id="timeSelectionTable">
                            <tr>
                                <% 
                                    ArrayList<LocalTime> scheduleTime =  (ArrayList<LocalTime>) session.getAttribute("ssTicScheduleTime");
                                    LocalTime tempTime = null;
                                    for(int i=0; i<scheduleTime.size(); i++){
                                        if(i !=0 && i % 7==0){ //1 line (row) can only max display 7 radio btn!
                                           out.println("</tr> <tr>");
                                       }
                                        tempTime = scheduleTime.get(i);
                                %>
                                <td>
                                    <input type="radio" name="scheduleTime" class="scheduleTimeRadio" id="radio<%=i%>" value="<%=tempTime%>" onclick="chgProceedBtnStatus()"/>
                                    <label for="radio<%=i%>" class="scheduleTimeLabel"><%= String.format("%02d:%02d", tempTime.getHour(), tempTime.getMinute()) %> </label>
                                </td>
                               <% } %>
                           </tr>
                        </table>
                        <br/>
                        <input type="submit" value="Proceed" id="proceedBtn" class="proceedBtn" style="margin: 0 40%" disabled/>
                    </form>
                    <br/> <br/>
                    <%  } %>
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
                                
        <script>
            function chkSubmitBtnColor(btnID){ //Set the btn that user has clicked to grey color 
                if(btnID != ""){
                    document.getElementById(btnID).style.backgroundColor = 'grey';
                    document.getElementById(btnID).style.color = 'white';
                }
            }
            
            function chgProceedBtnStatus(){ //if user has select the date and time, then the submit btn will be enabled dy
                document.getElementById('proceedBtn').disabled = false;
            }
        </script>
        
    </body>

</html>