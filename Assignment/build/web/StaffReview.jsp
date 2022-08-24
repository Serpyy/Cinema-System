<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*, java.util.*"%>
<%
    ReviewDA reviewDA = new ReviewDA();
    
    List<Review> reviewList = new ArrayList<Review>();
    reviewList = reviewDA.getAllReview();
    
    if (session.getAttribute("reviewList") != null) {
        reviewList = (List<Review>) session.getAttribute("reviewList");
    }
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/style4.css" rel="stylesheet">
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <title>Review Feedback</title>
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

        <div class ="container">
            <input type="hidden" name="forwardTo" value="${pageContext.request.servletPath}" /> 
            <div class ="rateRow">
                <div class ="ratingHistory">
                    <h1>Rating History</h1>
                    <form method ="post" action ="AddResponse" id="addResponse">
                        <%
                            Review review = null;
                            for(int i=0; i<reviewList.size(); i++) { 
                                review = reviewList.get(i);
                            %>
                        <div class ="commentBox">
                            <div class ="boxHead">

                            <h3><%=review.getCustomer().getCust_name()%></h3><span class="ratingFormat"><%=review.getRating()%> star(s)</span>
                            </div>
                            <%  if (review.getComment() != null) {%>
                                <p class="commentFormatStaff"><%=review.getComment()%></p>
                            <%  }%>
                        </div>
                            <%if (review.getResponse() == null || review.getResponse() == "") {%>
                            <div id ="replyBox" class ="replyBox">
                            <div class ="boxHead">
                                <h4><i>Reply as GSC Community Moderator</i></h4>
                            </div>
                                <input type ="hidden" name ="actualResponse" id ="actualResponse" value ="">
                                <textarea maxlength ="2000" id ="<%=i%>" name ="response" placeholder ="Your response..."></textarea>
                                <input type ="hidden" name ="reviewNo" id ="reviewNo" value ="">
                                <input class ="submitResponse" id="<%=review.getReviewNo()%>" type ="submit" value ="Post" onclick ="addResponse(this.id, <%=i%>);">
                            
                        </div>
                            <% } else {%>
                            <div id="replyBoxAfter" class ="replyBox">
                                <div class ="boxHead">
                                    <h4><i>Reply from GSC Community Moderator</i></h4>
                                </div>
                                <div>
                                    <p class="responseFormatStaff"><%=review.getResponse()%></p>
                                </div>
                            </div>
                            <%}%>

                            <%}%>
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
                                <li><a href="#">about us</a></li>
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
        <script>
            function addResponse(selectedReview, reviewCount) { 
                
                var revCount = reviewCount;
                var review = selectedReview;
                var actualResponse = document.getElementById(revCount).value;
                    
                if (actualResponse == null || actualResponse == "") {
                    alert("Response cannot be blanked.");
                    return false;
                } else {
                    document.getElementById('actualResponse').value = actualResponse;
                    document.getElementById('reviewNo').value = review;
                    document.getElementById('addResponse').submit();
                    
                    return true;
                }
                
                    return false;
                }
        </script>
    </body>

</html>