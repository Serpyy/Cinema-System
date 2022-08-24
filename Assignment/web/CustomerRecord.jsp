<%@page import="model.Customer"%>
<%@page import = "model.Staff,java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/CustomerReport"/>

<!DOCTYPE html>
<% 
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Record</title>
        <link rel="stylesheet" href="css/style6.css">
        <link rel="stylesheet" href="css/style.css">
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap" rel="stylesheet">
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
        
       <div class="container1">
                  <h1 style="color: yellow;text-align: center" >CUSTOMER RECORD</h1>
                         <div class="selection">  
                   
                             <select  id="searchfilter" style="width: 200px; height: 40px;background-color: darkgray; text-align: center;font-size: 20px">
                            <option value="0">Customer</option>
                            <option value="1">Customer ID</option>
                            <option value="2">IC</option>
                            <option value="3">Email</option>
                            <option value="4">Phone No</option>
                        </select>
                        <input class="searchSelection" type="text" placeholder="Search..."  onkeyup="searchRecord()" id="searchbar"> 
                       
                       </div>
     <div class="Scroll"  >
     <table  style="background-color: black" id="CustomerTable">
            <th  onclick="sortTable(0)">Customer</th>
            <th  onclick="sortTable(1)">Customer ID</th>
            <th  onclick="sortTable(2)">IC</th>
            <th  onclick="sortTable(3)">Email</th>
            <th  onclick="sortTable(4)">Phone No</th>
            <%
            Customer[] customerlist = (Customer[]) session.getAttribute("CustomerList");
            for (int i = 0; i < customerlist.length; i++) {

        %>
        <tr>
            <td><%= customerlist[i].getCust_name() %></td>
            <td><%= customerlist[i].getCust_ID() %></td>
            <td><%= customerlist[i].getIc_no()  %></td>
            <td><%= customerlist[i].getEmail() %> </td>
            <td><%= customerlist[i].getPhone_no() %></td>

        </tr>
        
        <% }%>
        </table>
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
function searchRecord(){
            var input, data, table, tr, td, txtValue, filter, column;
            filter = document.getElementById('searchfilter');
            column = filter.options[filter.selectedIndex].value;
            input=document.getElementById("searchbar");
            data= input.value.toUpperCase();
            table = document.getElementById("CustomerTable");
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

function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("CustomerTable");
  switching = true;
  dir = "asc";
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      switchcount ++;
    } else {
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
</script>       
    </body>
</html>
