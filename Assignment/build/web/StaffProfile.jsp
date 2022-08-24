<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*, java.util.*"%>
<%
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
        <title>Staff Profile</title>
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
            <div class ="profile">
                <h1>PROFILE <span style ="display: inline-block;font-size: 12px;text-indent:5px;letter-spacing:0.5;font-weight:normal;">(Staff ID: <%=staff.getStaffId()%>)</span></h1>
                <div class ="staffh1Deco"></div>
                <form method ="post" action ="UpdateStaffDetails">
                    <input type="hidden" name="forwardTo" value="${pageContext.request.servletPath}" /> 
                    <div class ="profileBox">
                        <div class ="detailsGroup">
                            <label for ="name">Full Name</label>
                            <input type ="text" class ="name" name ="name" value ="<%=staff.getStaffName()%>" placeholder ="Your Full Name">
                            <input type ="hidden" class = "checkCurrentPW" name ="checkPW" value ="<%=staff.getStaffPassword()%>">
                            <label class ="roleTitle" for ="name">Role</label>
                            <input type ="text" class ="role" name ="role" value ="<%=staff.getRole()%>" placeholder ="Your Role" disabled ="disabled">
                        </div>
                        
                        <div class ="detailsGroup">
                            <label for ="email">Email</label>
                            <input type ="email" class ="email" name ="email" value ="<%=staff.getStaffEmail()%>" placeholder ="Your Email">
                        </div>
                        
                        <div class ="pwTopSplit"></div>
                        <h2><b>Change Password</b></h2>
                        <div class ="pwBottomSplit"></div>
                        <div class ="detailsGroup">
                            <div class ="changePassword">
                                <label for ="currentPW">Current</label>
                                <input type ="password" class ="currentPW" name ="currentPW" maxlength="16" minlength="8" placeholder ="Enter Current Password...">
                                </br>
                                <label for ="newPW">New</label>
                                <input type ="password" class ="newPW" name ="newPW" maxlength="16" minlength="8" placeholder ="Enter New Password...">
                                </br>
                                <label for ="retypePW">Re-type new</label>
                                <input type ="password" class ="confirmPW" name ="confirmPW" maxlength="16" minlength="8" placeholder ="Re-type New Password...">
                                </br>
                            </div>
                        </div>
                        
                        <div class ="detailsGroup">
                            <label for ="phone">Phone Number</label>
                            <input type ="text" class ="phoneNo" name ="phoneNo"  pattern="^(01)[02-46-9]-*[0-9]{7}$|^(01)[1]-*[0-9]{8}$" value ="<%=staff.getStaffPhoneNo()%>" placeholder ="Your Phone Number" required>
                        </div>
                        
                        <div class ="detailsGroup">
                            <label for ="questions">Security Question</label>
                            <select name="questions" class="questions" id="questions" onChange="enableText()">
                                <%if (staff.getSecurityQuestion() == null || staff.getSecurityQuestion() == "") {%>
                                    <option selected disabled hidden>Select an Option</option>
                                <%} else {%>
                                <option value="<%=staff.getSecurityQuestion()%>" selected disabled hidden><%=staff.getSecurityQuestion()%></option>
                                    <option value="In what city were you born?">In what city were you born?</option>
                                    <option value="What is the name of your favourite pet?">What is the name of your favourite pet?</option>
                                    <option value="What is your father's name?">What is your father's name?</option>
                                    <option value="What is your mother's name?">What is your mother's name?</option>
                                    <option value="What is your favourite food?">What is your favourite food?</option>
                                    <option value="What is your father's middle name?">What is your father's middle name?</option>
                                    <option value="What is your mother's middle name?">What is your mother's middle name?</option>                                    
                                <%}%>
                                
                            </select>
                            <label class ="answerTitle" for ="answer">Answer</label>
                            <input type ="text" class ="answer" name ="answer" id="answer" value="<%=staff.getAnswer()%>">
                        </div>
                        <div class ="detailsSplit"></div>
                    </div>
                    <input class ="saveStaffProfile" type ="submit" value ="Save Profile">
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
            function enableText() {
                document.getElementById('answer').disabled = false;
            }
            
            document.querySelector('.saveStaffProfile').onclick = function() {
                var name = document.querySelector('.name').value;
                var email = document.querySelector('.email').value;
                var currentPW = document.querySelector('.currentPW').value;
                var newPW = document.querySelector('.newPW').value;
                var confirmPW = document.querySelector('.confirmPW').value;
                var checkCurrentPW = document.querySelector('.checkCurrentPW').value;
                var phoneNo = document.querySelector('.phoneNo').value;
                const chkAnswer = document.getElementById('answer');
                var answer = document.querySelector('.answer').value;
                
                if (!(chkAnswer.disabled)) {
                    if (answer == null || answer == "") {
                        alert("Answer Field cannot be empty.");
                        return false;
                    }
                }
                
                if (name == null || name == "") {
                    alert("Name Field cannot be empty.");
                    return false;
                }
                if (email == null || email == "") {
                    alert("Email Field cannot be empty.");
                    return false;
                }
                if (currentPW != "") {
                    if(currentPW != checkCurrentPW) {
                        alert("Current Password Invalid.");
                        return false;
                    } else {
                        if (newPW == "") {
                            alert("New Password cannot be empty.");
                            return false;
                        } else if (newPW != confirmPW) {
                            alert("New Password & Confirm Password do not match.");
                            return false;
                        } else if (newPW.length < 8) {
                            alert("New Password must be of length 8 or more.");
                            return false;
                        }
                    }
                } else if (currentPW == "") {
                    if (newPW != "" && confirmPW != "") {
                        alert("Current Password Invalid.");
                    }
                }
                
             
            }
        </script>
    </body>
</html>
