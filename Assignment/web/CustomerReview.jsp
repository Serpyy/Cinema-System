<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*, java.util.*"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>

<%
    CustomerDA custDA = new CustomerDA();    
    
    ReviewDA reviewDA = new ReviewDA();
    
    List<Review> reviewList = new ArrayList<Review>();
    reviewList = reviewDA.getAllReview();
    
    if (session.getAttribute("reviewList") != null) {
        reviewList = (List<Review>) session.getAttribute("reviewList");
    }
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
        <title>Rate & Review</title>
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
                        <input class="search-txt" type="text" placeholder="Search...">
                        <a class="search-btn" href="#">
                            <i class="fa fa-search"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class ="container">
            <div class ="rateRow">
                <div class ="rateColLeft">
                    <h1>Rating History</h1>
                    <%
                        Review review = new Review();
                        for (int i = 0; i < reviewList.size(); i++) {
                            review = reviewList.get(i);
                        %>
                        <div class ="commentBox">
                            <div class ="boxHead">
                                <h3><%=review.getCustomer().getCust_name()%></h3><span class="ratingFormat"><%=review.getRating()%> star(s)</span>
                            </div>
                            <%  if (review.getComment() != null) {%>
                                <input type ="hidden" name ="reviewNo" value ="<%=review.getReviewNo()%>">
                                <p class="commentFormat"><%=review.getComment()%></p>
                            <%  }%>
                        </div>
                            <%if (review.getResponse() != null && review.getResponse() != "") {%>
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
                </div>

                <div class ="rateColRight">
                    <h1 class ="rateHeaderRight">RATE & REVIEW</h1>
                    <form method ="post" action ="AddNewReview">
                        <input type="hidden" name="forwardTo" value="${pageContext.request.servletPath}" /> 
                        <div>
                            <br></br>
                            <div class ="topDeco"></div>
                            <h2>Rate</h2>
                            <div class ="bottomDeco"></div>
                            <div class ="rate">
                                <input type="radio" id="star5" name="rate" class="rate" value="5" />
                                <label for="star5" title="text"></label>
                                <input type="radio" id="star4" name="rate" class="rate" value="4" />
                                <label for="star4" title="text"></label>
                                <input type="radio" id="star3" name="rate" class="rate" value="3" />
                                <label for="star3" title="text"></label>
                                <input type="radio" id="star2" name="rate" class="rate" value="2" />
                                <label for="star2" title="text"></label>
                                <input type="radio" id="star1" name="rate" class="rate" value="1" />
                                <label for="star1" title="text"></label>
                            </div>
                        </div>

                        <div>
                            <br></br><br></br><br></br>
                            <div class ="topDeco"></div>
                            <h2>Comment</h2>
                            <div class ="bottomDeco"></div>

                            <div class ="comment">
                                <textarea maxlength ="250" name ="comment" placeholder ="Comment(Optional)"></textarea>
                            </div>
                        </div>
                        <input class ="submitReview" type ="submit" value ="Post">
                    </form>
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
            document.querySelector('.submitReview').onclick = function() {
                if (document.querySelectorAll('input[type="radio"]:checked').length === 0) {
                    alert("Please Rate before Submit.");
                    return false;
                }
            }   
        </script>
    </body>

</html>