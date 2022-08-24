<%@page import="model.Schedule"%>
<%@page import="controller.MovieReport"%>
<%@page import="model.Payment"%>
<%@page import="model.*, java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Report</title>
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
            <h1 style='color: yellow;text-align: center'>Movie Report</h1>
                <input  class="Moviesearch" type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for no..." title="Type in a name">
                <div class="container3">
                    <table class="movietable" border="1" id="MovieTable">
                    <th class="th3">No</th>
                    <th class="th3">Movie</th>
                    <th class="th3">Schedule Time</th>
                    <th class="th3">Schedule Date</th>
                    <th class="th3">Sale(RM)</th>
                    <th class="th3">Adult</th>
                    <th class="th3">Child</th>
        
 
        <%
            Schedule[] paymentlist = (Schedule[]) session.getAttribute("scheduleLists");
            double[] scheduleTotal = (double[]) session.getAttribute("scheduleTotal");
            int[] adult = (int[]) session.getAttribute("adult");
            int[] child = (int[]) session.getAttribute("child");
            int totalAdult = 0;
            double totalSale =0;
            int totalChild = 0;
            for (int i = 0; i < paymentlist.length; i++) {
                    totalAdult +=adult[i];
                    totalChild +=child[i];
                   totalSale += scheduleTotal[i];
                   
        %>
        <tr class="tr2">
            <td><%=( i+1)%></td>
            <td><%= paymentlist[i].getMovie().getMovieTitle() %></td>
            <td><%= paymentlist[i].getSchedule_time() %></td>
            <td><%= paymentlist[i].getSchedule_date() %></td>
            <td><%= scheduleTotal[i] %></td>
            <td><%= adult[i] %></td>
            <td><%= child[i] %></td>
        </tr>
        <% }%>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black"></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black"></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black"></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black"></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black">Total Sale =   <%=totalSale %></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black">Total Adult = <%=totalAdult %></th>
        <th style="bottom: 0px;position: sticky;height: 70p;background-color: white;color: black">Total Child = <%=totalChild %></th>
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
</body>
 <script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("MovieTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>
</html>

