<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*, java.util.*"%>
<%
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>

<html>
    <head>
        <title>Movie ID</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        
        <div class="container2" style="margin-bottom: 30%">
            <form class="form1" method="get" action="MovieReport">
                <label class="label1" for="movieID" required>Movie ID</label>
                <input class="input1" type="text" id="movieID" name="movieID" >
                <button class="Submit1" type="submit">Submit </button>
            </form>
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
                            <a href="https://www.facebook.com/GSCinemas/" style="padding-top:10px"><i class="fab fa-facebook-f"></i></a>
                            <a href="https://twitter.com/gsc_movies?lang=en" style="padding-top:10px"><i class="fab fa-twitter"></i></a>
                            <a href="https://www.instagram.com/gscinemas/?hl=en" style="padding-top:10px"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.linkedin.com/company/gscinemas/" style="padding-top:10px"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
             </div>
        </footer>
    </body>
</html>