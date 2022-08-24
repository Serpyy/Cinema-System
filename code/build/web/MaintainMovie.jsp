<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Movie,java.util.ArrayList,model.Cast,model.Schedule,model.Staff"%>

<!DOCTYPE html>
<%
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/style1.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <title>Maintain Movie</title>
</head>

<body>
    <div class="navbar">
            <div class="navbar-container">
                <div class="logo-container">
                    <h1 class="logo">GSC</h1>
                </div>
                <div class="menu-container">

                    <ul class="menu-list">
                        <li class="menu-list-item"><a href="StaffProfile.jsp">Profile</a></li>
                        <%
                            if(staff.getRole().equals("Cashier")||staff.getRole().equals("Usher")||staff.getRole().equals("Manager")){
                        %>
                        <li class="menu-list-item"><a href="SaleRecord.jsp">Sales</a></li>
                        <li class="menu-list-item"><a href="CustomerRecord.jsp">Customer</a></li>
                        <li class="menu-list-item"><a href="#">Report<i class="fas fa-caret-down"></i></a>
                        <div class="dropdown-menu" style="z-index: 1">
                            <ul>
                                <li style="width:155px"><a href="SelectDate.jsp">Sales Report</a></li>
                                <li style="width:155px"><a href="MovieID.jsp">Movie Report</a></li>
                                <li style="width:155px"><a href="RefundReport.jsp">Refund Report</a></li>
                            </ul>
                        </div>
                        </li>
                        <% }%>
                        <%
                            if(staff.getRole().equals("Film Projectionist")||staff.getRole().equals("Manager")){
                        %>
                        <li class="menu-list-item"><a href="MaintainMovie">Movies</a></li>
                        <li class="menu-list-item"><a href="MaintainSchedule">Schedules</a></li>
                        <% }%>
                        <%
                            if(staff.getRole().equals("Community Moderator")||staff.getRole().equals("Manager")){
                        %>
                        <li class="menu-list-item"><a href="StaffReview.jsp">Review</a></li>
                        <% }%>
                        <%
                            if(staff.getRole().equals("Manager")){
                        %>
                        <li class="menu-list-item"><a href="MaintainStaff">Staff</a></li>
                        <li class="menu-list-item"><a href="RefundStaffController">Refund</a></li>
                        <% }%>
                        <li class="menu-list-item"><a href="Home.jsp">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
    <div class="container">
        <div class="custom-content">
            <div class="search-box-container2">  
                <div class="search-box2">
                    <select class="search-filter" id="searchfilter">
                        <option value="0">Movie ID</option>
                        <option value="2">Movie Title</option>
                        <option value="4">Age Rating</option>
                        <option value="5">Release Date</option>
                        <option value="6">Cast</option>
                        <option value="7">Director</option>
                        <option value="8">Distributor</option>
                    </select>
                    <input class="search-txt2" type="text" placeholder="Search..."  onkeyup="searchRecord()" id="searchbar">
                    <a class="search-btn">
                    <i class="fa fa-search"></i>
                    </a>
                </div>   
            </div>
            <form action="AddMovie" method="get">
                <button class="crud" formaction="DeleteMovie" id="delete" onclick="return deleteMovie()" disabled>DELETE</button>
                <button class="crud" formaction="EditMovie" id="edit" disabled>EDIT</button>
                <button class="crud">ADD</button>
                <input type="text" name="movieID" id="movieID" hidden>
                <h1 class="table-caption">MOVIE RECORDS</h1>
                <table class="crud-table" id="movieTable2">
                    <thead class="table-head">
                        <tr class="table-row-header">
                            <th class="table-header" onclick="sortTable(0)" style="cursor: pointer;" id="sort">MOVIE ID</th>
                            <th class="table-header">POSTER</th>
                            <th class="table-header">MOVIE TITLE</th>
                            <th class="table-header">DURATION</th>
                            <th class="table-header" onclick="sortTable(4)" style="cursor: pointer;" id="sort">AGE RATING</th>
                            <th class="table-header" onclick="sortTable(5)" style="cursor: pointer;" id="sort">RELEASE DATE</th>
                            <th class="table-cast">CAST</th>
                            <th class="table-header">DIRECTOR</th>
                            <th class="table-header" style="min-width: 150px;padding-right:15px" id="sort">DISTRIBUTOR</th>
                            <th class="table-synopsis">SYNOPSIS</th>
                        </tr>
                    </thead>
                    <%
                        ArrayList<Movie> movies = (ArrayList)request.getAttribute("ssMovieList");
                        for(int i=0;i<movies.size();i++){
                    %>            
                        <tr class="table-data-header">
                            <td class="table-data"><%=movies.get(i).getMovieID()%></td>
                            <td class="table-data"><img src="<%=movies.get(i).getImgString()%>" style="width:100px;height:148px;padding:10px"></td>
                            <td class="table-data"><%=movies.get(i).getMovieTitle()%></td>
                            <td class="table-data"><%=movies.get(i).getDuration()%></td>
                            <td class="table-data"><%=movies.get(i).getAgeRating()%></td>
                            <td class="table-data"><%=movies.get(i).getReleaseDate()%></td>
                            <td>
                            <% 
                                ArrayList<String> casts = (ArrayList)request.getAttribute("cast"+i);
                                for(int j=0;j<casts.size();j++){%>
                                    <%=casts.get(j)%>
                                    <%if(j!=casts.size()-1){%>
                                        ,
                                    <%}%>
                                <%}%>
                            </td>
                            <td class="table-data"><%=movies.get(i).getDirector()%></td>
                            <td class="table-data"><%=movies.get(i).getDistributor()%></td>
                            <td class="synopsis-text"><%=movies.get(i).getSynopsis()%></td>
                        </tr>
                        <%}%>
                </table>
            </form>
        </div>
    </div>
       <footer class="footer">
             <div class="footer-container">
                <div class="row">
                    <div class="footer-col">
                        <h4>movies</h4>
                    </div>

                    <div class="footer-col">
                        <h4>help</h4>
                    </div>
                    <div class="footer-col">
                        <h4>company</h4>
                            <ul>
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
        //sortTable
        function sortTable(n) {
            var table, rows, x, y;
            table = document.getElementById("movieTable2");
            rows = table.rows;
            var count=0;
  
            for(var i = 1; i < rows.length+1; i++){ 
                for(var j = 1; j < ( rows.length - i ); j++){
                    x = rows[j].getElementsByTagName("TD")[n];
                    y = rows[j + 1].getElementsByTagName("TD")[n];
                    if(x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()){
                        var temp = rows[j].innerHTML
                        rows[j].innerHTML = rows[j + 1].innerHTML
                        rows[j+1].innerHTML = temp;
                        count++;
                    }
                }
            }
 
            if(count==0){
                for(var i = 1; i < rows.length+1; i++){ 
                    for(var j = 1; j < ( rows.length - i ); j++){
                        x = rows[j].getElementsByTagName("TD")[n];
                        y = rows[j + 1].getElementsByTagName("TD")[n];
                        if(x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()){
                            var temp = rows[j].innerHTML
                            rows[j].innerHTML = rows[j + 1].innerHTML
                            rows[j+1].innerHTML = temp;
                            count++;
                        }
                    }
                }    
            }
        }

        //select record
        function selectRecord(){
            var temp, index, table=document.getElementById("movieTable2"),button1, button2, row, input;
            button1 =document.getElementById("edit");
            button2 =document.getElementById("delete");
            input = document.getElementById("movieID");
            for(var i=1; i<table.rows.length;i++){
                table.rows[i].onclick=function(){
                    if(this.style.backgroundColor==="gold"){
                        this.style.color="white";
                        button1.disabled=true;
                        button2.disabled=true;
                        if(this.rowIndex%2==1){
                            this.style.backgroundColor="#151515";
                        }else{
                            this.style.backgroundColor="#262626";
                        }
                    }else{
                        for(var j=1; j<table.rows.length;j++){
                            if(j%2==1){
                                table.rows[j].style.backgroundColor="#151515";
                                table.rows[j].style.color="white";
                            }else{
                                table.rows[j].style.backgroundColor="#262626";
                                table.rows[j].style.color="white";
                            }
                        }  
                    button1.disabled=false;
                    button2.disabled=false;
                    input.value=table.rows[this.rowIndex].cells[0].innerText;
                    this.style.backgroundColor="gold";   
                    this.style.color="black";
                    }
                }
            }
        }
        selectRecord();
    
        //search record
        function searchRecord(){
            var input, data, table, tr, td, txtValue, filter, column;
            filter = document.getElementById('searchfilter');
            column = filter.options[filter.selectedIndex].value;
            input=document.getElementById("searchbar");
            data= input.value.toUpperCase();
            table = document.getElementById("movieTable2");
            tr= table.getElementsByTagName("tr");
        
            for(var i =0; i<tr.length; i++){
                td = tr[i].getElementsByTagName("td")[column];
                if(td){
                    txtValue=td.textContent || td.innerText;
                    if(txtValue.toUpperCase().indexOf(data) > -1){
                        tr[i].style.display=""; 
                    }else{
                        tr[i].style.display="none";
                    }
                }
            }
        }
     
        //check if all 
        function deleteMovie(){
            var txtValue, movieID, scheduleList=[], opt;
            <%  ArrayList<Schedule> schedule= (ArrayList)request.getAttribute("ssScheduleList");
                    for(int i=0;i<schedule.size();i++){%>
                        scheduleList.push("<%=schedule.get(i).getMovie().getMovieID()%>");
                    <%}%>
            movieID=document.getElementById("movieID").value;
            for(let i=0;i<=scheduleList.length;i++){
                if(movieID==scheduleList[i]){
                    alert("Please delete all related schedule.");;
                    return false;
                    }
            }
            var opt = confirm("Do you want to delete this movie?");
            if(opt==true){
                return
            }else{
                return false;
            }
         }
        

    </script>
    <script src="app.js"></script>
    </body>
</html>
