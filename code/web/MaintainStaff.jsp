<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Staff,java.util.ArrayList"%>
<!DOCTYPE html>
<% 
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<html>
    <head><meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/style1.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
        <title>Maintain Staff</title>
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
        <div class="custom-content3">      
            <h1 class="table-caption" style="padding-bottom: 5px">STAFF RECORDS</h1>
            <table class="crud-table" id="staffTable" style="width:100%;overflow-y: scroll;overflow-x:hidden;height:725px;grid-column-start: 1">
                <thead class="table-head">
                    <tr class="table-row-header">
                        <th class="table-header" onclick="sortTable(0)" style="cursor: pointer;" id="sort">STAFF ID</th>
                        <th class="table-header" style="width:250px">NAME</th>
                        <th class="table-header" onclick="sortTable(2)" style="cursor: pointer;" id="sort">ROLE</th>
                        <th class="table-header" style="width:275px">EMAIL</th>
                        <th class="table-header" style="width:150px">PHONE NUMBER</th>
                    </tr>
                </thead>   
                <%
                    ArrayList<Staff> staffList = null;
                    staffList = (ArrayList)request.getAttribute("ssStaffList");
                    for(int i=0; i<staffList.size();i++){
                 %>
                    <tr class="table-data-header">
                        <td class="table-data" style="height:65px"><%=staffList.get(i).getStaffId()%></td>
                        <td class="table-data" style="height:65px"><%=staffList.get(i).getStaffName()%></td>
                        <td class="table-data" style="height:65px"><%=staffList.get(i).getRole()%></td>
                        <td class="table-data" style="height:65px"><%=staffList.get(i).getStaffEmail()%></td>
                        <td class="table-data" style="height:65px"><%=staffList.get(i).getStaffPhoneNo()%></td>
                    </tr>
                    <%}%>
            </table>
            <form action="AddStaff" method="post">
                <div class="form2">
                <h1 style="margin:auto;color:#ffe229">Maintain Staff</h1>
                <div class="search-box-container2" style="width:350px;margin:0 0 30px 30px">  
                    <div class="search-box2">
                        <select class="search-filter" id="searchfilter">
                            <option value="0">Staff ID</option>
                            <option value="1">Name</option>
                            <option value="2">Role</option>
                            <option value="3">Email</option>
                            <option value="4">Phone No</option>
                        </select>
                        <input class="search-txt2" type="text" placeholder="Search..."  onkeyup="searchRecord()" id="searchbar">
                        <a class="search-btn">
                        <i class="fa fa-search"></i>  
                        </a>
                    </div>
                </div>
                <div style="grid-row-start: 3">
                    <label class="input-wrapper" style="padding-left:40px;">Staff ID:</label>
                    <input type="text" class="ID" readonly="readonly" value="<%=request.getAttribute("ssLastStaff")%>" id="staffID" name="staffID" required>
                </div>
                <div style="grid-row-start: 4">
                    <div class="input-data">
                        <input type="text" placeholder=" " id="name" name="name" class="blackInput" required>
                        <label>Name</label>
                    </div>
                </div>
                <div style="grid-row-start: 5">
                    <div class="input-data">
                        <select class="select" id="role" name="role" required>
                            <option hidden disabled selected value=""> </option>
                            <option>Cashier</option>
                            <option>Usher</option>
                            <option>Film Projectionist</option>
                            <option>Community Moderator</option>
                            <option hidden>Manager</option>
                        </select>
                        <label>Role</label>
                    </div>
                </div>
                <div style="grid-row-start: 6">
                    <div class="input-data">
                        <input type="email" placeholder=" " id="email" name="email" class="blackInput" required>
                        <label>Email</label>
                    </div>
                </div>
                <div style="grid-row-start: 7">
                    <div class="input-data">
                        <input type="text" pattern="^(01)[02-46-9]-*[0-9]{7}$|^(01)[1]-*[0-9]{8}$" placeholder=" " id="phoneNo" name="phoneNo" class="blackInput" required>
                        <label>Phone No</label>
                    </div>
                </div>
                <div style="grid-row-start: 8" > 
                    <button class="crud" formaction="DeleteStaff" id="delete" style="margin-right: 45px" onclick="return confirm('Do you want to delete this staff?');" disabled>DELETE</button>
                    <button type=submit" formaction="EditStaff" class="crud" id="edit" disabled>EDIT</button>
                    <button type="submit" class="crud" id="add">ADD</button>
                </div>
            </div>
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
        //search record
        function searchRecord(){
            var input, data, table, tr, td, txtValue, filter, column;
            filter = document.getElementById('searchfilter');
            column = filter.options[filter.selectedIndex].value;
            input=document.getElementById("searchbar");
            data= input.value.toUpperCase();
            table = document.getElementById("staffTable");
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
    
        //sort table
        function sortTable(n) {
            var table, rows, x, y;
            table = document.getElementById("staffTable");
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
            var index, table=document.getElementById("staffTable"),button1, button2, button3, id, name, role, email, phoneNo;
            id =document.getElementById("staffID");
            name =document.getElementById("name");
            role =document.getElementById("role");
            email =document.getElementById("email");
            phoneNo =document.getElementById("phoneNo");
            button1 =document.getElementById("edit");
            button2 =document.getElementById("delete");
            button3 =document.getElementById("add");
            for(var i=1; i<table.rows.length;i++){
                table.rows[i].onclick=function(){
                if(this.style.backgroundColor==="gold"){
                    index = this.rowIndex;
                    id.value="<%=request.getAttribute("ssLastStaff")%>";
                    name.value="";
                    role.value="";
                    email.value="";
                    phoneNo.value=""
                    this.style.color="white";
                    button1.disabled=true;
                    button2.disabled=true;
                    button3.disabled=false;
                    email.readOnly=false;
                    name.readOnly=false;
                    email.readOnly=false;
                    phoneNo.readOnly=false;
                    if(this.rowIndex%2==1){
                        this.style.backgroundColor="#151515";
                    }else{
                        this.style.backgroundColor="#262626";
                    }
                }else{
                    index = this.rowIndex;
                    id.value=table.rows[index].cells[0].innerText;
                    name.value=table.rows[index].cells[1].innerText;
                    role.value=table.rows[index].cells[2].innerText;
                    email.value=table.rows[index].cells[3].innerText;
                    phoneNo.value=table.rows[index].cells[4].innerText;
                    email.readOnly=false;
                    name.readOnly=false;
                    email.readOnly=false;
                    phoneNo.readOnly=false;
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
                    button3.disabled=true;
                    email.readOnly=true;
                    this.style.backgroundColor="gold";   
                    this.style.color="black";
                    if(role.value =="Manager"){
                        button2.disabled=true;
                        button1.disabled=true;
                        name.readOnly=true;
                        email.readOnly=true;
                        phoneNo.readOnly=true;
                    }
                }
            }
        }
    }
    selectRecord();
    
    </script>
    <script src="app.js"></script>
        
    </body>
</html>
