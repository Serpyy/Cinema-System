<%@page import="java.time.LocalDate,java.util.ArrayList,java.time.LocalTime,model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Refund> assignedList = (ArrayList<Refund>)session.getAttribute("ssAssignedListList");
    ArrayList<Refund> completeList = (ArrayList<Refund>)session.getAttribute("sscompleteList");
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/style3.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <script
      defer
      src = "https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
      <script>
          function showDetails(id, details) {
        
        if(document.getElementById(id).hidden == true){
             hideDetails(details);
            document.getElementById(id).hidden = false;
        }else{             
            hideDetails(details);
        }
      }

      function hideDetails(details) {
        const collection = document.getElementsByClassName(details);

        for (let i = 0; i < collection.length; i++) {
          collection[i].hidden = true;
        }
      }
      </script>
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
    />
    <title>Staff Refund Management</title>
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
      <h1 id="refundh1">Staff Refund Menu</h1>
      <br /><br />

      <div class="table" style="--s: 'Assigned'">
        <table id="In_processTable">
          <thead>
            <th>Refund Number</th>
            <th>Payment Number</th>
            <th>Refund Date</th>
            <th>Customer Name</th>
          </thead>
          <tbody>
              <% 
                        if(assignedList.isEmpty()){
               %>
               <tr>
                   <td colspan="4">Sorry No refund assigned within a Month</td>
                </tr>
              <%          
                        }else{
                            for (int i = 0; i < assignedList.size(); i++) {
                                String id =  assignedList.get(i).getRefund_no();
              %>
              <tr onclick="showDetails('<%=id%>','refund_Details')">
                    <td><%= assignedList.get(i).getRefund_no() %></td>
                    <td><%= assignedList.get(i).getPayment().getPayment_no() %></td>
                    <td>
                        <%= assignedList.get(i).getRefund_date().getDayOfMonth()%> <%= assignedList.get(i).getRefund_date().getMonth()%> <%= assignedList.get(i).getRefund_date().getYear()%>
                    </td>
                    <td><%= assignedList.get(i).getPayment().getCard().getCustomer().getCust_name() %></td>
            </tr>
            <tr hidden id="<%=id%>" class="refund_Details">
                <td class="details"  colspan="4">
                    <form action="RefundStaffController" method="POST">
                        <div>    
                          Payment No  &emsp;&emsp;&ensp;<%=assignedList.get(i).getPayment().getPayment_no() %><br>
                          Amount         &emsp;&emsp;&emsp;&emsp;&ensp;RM <%= String.format("%.2f", assignedList.get(i).getPayment().getAmount() )%><br>
                          Payment Date&ensp;&emsp;&ensp;<%=assignedList.get(i).getPayment().getPayment_date().getDayOfMonth() %>
                                                  <%=assignedList.get(i).getPayment().getPayment_date().getMonth() %>
                                                  <%=assignedList.get(i).getPayment().getPayment_date().getYear() %><br>
                          Payment Time&ensp;&emsp;&ensp;<%=String.format( "%02d : %02d",assignedList.get(i).getPayment().getPayment_time().getHour(), assignedList.get(i).getPayment().getPayment_time().getMinute() )%><br>
                          Total Ticket &emsp;&emsp;&emsp;<%=(assignedList.get(i).getPayment().getChild_qty() + assignedList.get(i).getPayment().getAdult_qty()) %><br>
                          Refund Reason &emsp;&nbsp; <%=assignedList.get(i).getReason() %>
                            </div>
                          <div class="btn">
                              <input type="text" hidden name="txtRefund"  value="<%= assignedList.get(i).getRefund_no() %>">
                              <input class="approve" type="submit" name="action" value="Approve">&emsp;
                              <input class="unapprove" type="submit" name="action" value="Unapprove">  
                          </div>
                    </form>    
              </td>
            </tr>
            
            <%}
                }%>
          </tbody>
        </table>
      </div>

      <br />
      <br />
      <br />

      <div class="table" style="--s: 'Approved and Unapproved'">
        <table id="approveTable">
          <thead>
            <th>Refund Number</th>
            <th>Payment Number</th>
            <th>Refund Date</th>
            <th>Status</th>
          </thead>
          <% 
                        if(completeList.isEmpty()){
               %>
               <tr>
                   <td colspan="4">Sorry No records within a Month</td>
                </tr>
              <%          
                        }else{
                            for (int i = completeList.size() - 1; i >= 0 ; i--) {
                                if(completeList.get(i).getRefund_date().isAfter(LocalDate.now().minusMonths(1))){
                                String id =  completeList.get(i).getRefund_no();
              %>
              <tr onclick="showDetails('<%=id%>','completeList')">
                    <td><%= completeList.get(i).getRefund_no() %></td>
                    <td><%= completeList.get(i).getPayment().getPayment_no() %></td>
                    <td>
                        <%= completeList.get(i).getRefund_date().getDayOfMonth()%> <%= completeList.get(i).getRefund_date().getMonth()%> <%= completeList.get(i).getRefund_date().getYear()%>
                    </td>
                    <td><%= completeList.get(i).getStatus() %></td>
            </tr>
            <tr hidden id="<%=id%>" class="completeList">
                <td class="details" colspan="4">
                    <div>    
                          Payment No  &emsp;&emsp;&ensp;<%=completeList.get(i).getPayment().getPayment_no() %><br>
                          Amount         &emsp;&emsp;&emsp;&emsp;&ensp;RM <%= String.format("%.2f", completeList.get(i).getPayment().getAmount() )%><br>
                          Payment Date&ensp;&emsp;&ensp;<%=completeList.get(i).getPayment().getPayment_date().getDayOfMonth() %>
                                                  <%=completeList.get(i).getPayment().getPayment_date().getMonth() %>
                                                  <%=completeList.get(i).getPayment().getPayment_date().getYear() %><br>
                          Payment Time&ensp;&emsp;&ensp;<%=String.format( "%02d : %02d",completeList.get(i).getPayment().getPayment_time().getHour(), completeList.get(i).getPayment().getPayment_time().getMinute()) %><br>
                          Total Ticket &emsp;&emsp;&emsp;<%=(completeList.get(i).getPayment().getChild_qty() +completeList.get(i).getPayment().getAdult_qty()) %><br>
                          Refund Reason &emsp;&nbsp; <%=completeList.get(i).getReason() %>
                    </div>
              </td>
            </tr>
            
            <%}}
                }%>
        </table>
      </div>
        <br><br><br>
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

    <script src="app.js"></script>
  </body>
</html>
