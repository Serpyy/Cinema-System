<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Staff,java.util.ArrayList,model.Schedule,model.Movie"%>
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
        <title>Maintain Schedule</title>
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
            <h1 class="table-caption" style="padding-bottom: 5px">SCHEDULE RECORDS</h1>
            <table class="crud-table" id="scheduleTable" style="width:100%;overflow: hidden;grid-column-start: 1;height:725px">
                <thead class="table-head">
                    <tr class="table-row-header">
                        <th class="table-header" style="width:200px" onclick="sortTable(0)" style="cursor: pointer;" id="sort">Schedule ID</th>
                        <th class="table-header" style="width:200px" onclick="sortTable(1)" style="cursor: pointer;" id="sort">Movie ID</th>
                        <th class="table-header" style="width:200px" onclick="sortTable(2)" style="cursor: pointer;" id="sort">Hall ID</th>
                        <th class="table-header" style="width:200px">Schedule Time</th>
                        <th class="table-header" style="width:200px" onclick="sortTable(4)" style="cursor: pointer;" id="sort">Schedule Date</th>
                    </tr>
                </thead>   
                <%
                    ArrayList<Schedule> schedule = null;
                    schedule = (ArrayList)request.getAttribute("ssScheduleList");
                    for(int i=0; i<schedule.size();i++){
                 %>
                    <tr class="table-data-header">
                        <td class="table-data" style="height:68px"><%=schedule.get(i).getSchedule_no()%></td>
                        <td class="table-data" style="height:68px"><%=schedule.get(i).getMovie().getMovieID()%></td>
                        <td class="table-data" style="height:68px"><%=schedule.get(i).getHall().getHall_no()%></td>
                        <td class="table-data" style="height:68px"><%=schedule.get(i).getSchedule_time()%></td>
                        <td class="table-data" style="height:68px"><%=schedule.get(i).getSchedule_date()%></td>
                    </tr>
                    <%}%>
            </table>
            <form action="AddSchedule" method="post">
                <div class="form2">
                    <h1 style="margin:auto;color:#ffe229">Maintain Schedule</h1>
                    <div class="search-box-container2" style="width:370px;margin:0 0 30px 20px">  
                        <div class="search-box2">
                            <select class="search-filter" id="searchfilter">
                                <option value="0">Schedule ID</option>
                                <option value="1">Movie ID</option>
                                <option value="2">Hall</option>
                                <option value="4">Schedule Date</option>
                            </select>
                            <input class="search-txt2" type="text" placeholder="Search..."  onkeyup="searchRecord()" id="searchbar">
                            <a class="search-btn">
                            <i class="fa fa-search"></i>  
                            </a>
                        </div>
                    </div>
                    <div style="grid-row-start: 3">
                        <label class="input-wrapper" style="padding-left:40px;">Schedule ID:</label>
                        <input type="text" class="ID" readonly="readonly" value="<%=request.getAttribute("ssLastSchedule")%>" id="scheduleID" name="scheduleID" style="width:150px" required>
                    </div>
                    <div style="grid-row-start: 4">
                        <div class="input-data">
                            <select class="select" id="movieID" name="movieID" required>
                                <option hidden disabled selected value=""> </option>
                                <%
                                    ArrayList<Movie> movies = (ArrayList)request.getAttribute("ssMovieList");
                                    for(int i=0;i<movies.size();i++){
                                %>      
                                <option><%=movies.get(i).getMovieID()%></option>  
                                    <%}%>
                            </select>
                            <label>Movie ID</label>
                        </div>
                    </div>
                    <div style="grid-row-start: 5">
                        <div class="input-data">
                            <select class="select" id="hall" name="hall" required>
                                <option hidden disabled selected value=""> </option> 
                                <%
                                    ArrayList<String> hall = (ArrayList)request.getAttribute("ssHallNo");
                                    for(int i=0;i<hall.size();i++){
                                %>      
                                <option><%=hall.get(i)%></option>  
                                    <%}%>                                                   
                            </select>
                            <label>Hall</label>
                        </div>
                    </div>
                    <div style="grid-row-start: 6">
                        <div class="input-data">
                            <input type="time" placeholder=" " id="time" name="time" required>
                            <label>Schedule Time</label>
                        </div>
                    </div>
                    <div style="grid-row-start: 7">
                        <div class="input-data">
                            <input type="date" placeholder=" " id="date" name="date" required>
                            <label>Schedule Date</label>
                        </div>
                    </div>
                    <div style="grid-row-start: 8" > 
                        <button class="crud" formaction="DeleteSchedule" id="delete" style="margin-right: 45px" onclick="return confirm('Do you want to delete this schedule? All related ticket and payment will be deleted too.');" disabled>DELETE</button>
                        <button type=submit" formaction="EditSchedule" class="crud" id="edit" onclick="return checkSchedule()" disabled>EDIT</button>
                        <button type="submit" class="crud" id="add" onclick="return checkSchedule()">ADD</button>
                    </div>
                </div>
            </form> 
            <ul class="pagination">
                <li class="prev2"><a href="#" id="prev">&#139;</a></li>
                <li class="next2"><a href="#" id="next">&#155;</a></li>
            </ul>
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
        //global variable
        let tbody= document.querySelector('tbody');
        let tr= tbody.getElementsByTagName('tr');
        let array=[];
        let ul=document.querySelector('.pagination');
        //store all schedule into array
        for(let i =0; i<tr.length;i++){
            array.push(tr[i]);
        }

        //search record
        function searchRecord(){
            var input, data, td, txtValue, filter, column, array2;
            filter = document.getElementById('searchfilter');
            column = filter.options[filter.selectedIndex].value;
            input=document.getElementById("searchbar");
            data= input.value.toUpperCase();
            for(var i =0; i<array.length; i++){
                td = array[i].getElementsByTagName("td")[column];
                if(td){
                    txtValue=td.textContent || td.innerText;
                    if(txtValue.toUpperCase().indexOf(data) > -1){
                        array[i].style.display="";
                    }else{
                        array[i].style.display="none";
                    }
                }
            }
        }
    
        //sort table
        function sortTable(n) {
            var table, x, y;
            var count=0;
            for(var i = 1; i < array.length+1; i++){ 
                for(var j = 0; j < ( array.length - i ); j++){
                    x = array[j].getElementsByTagName("TD")[n];
                    y = array[j + 1].getElementsByTagName("TD")[n];
                    if(x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()){
                        var temp = array[j].innerHTML
                        array[j].innerHTML = array[j + 1].innerHTML
                        array[j+1].innerHTML = temp;
                        count++;
                    }
                }
            }
 
            if(count==0){
                for(var i = 1; i < array.length+1; i++){ 
                    for(var j = 0; j < ( array.length - i ); j++){
                        x = array[j].getElementsByTagName("TD")[n];
                        y = array[j + 1].getElementsByTagName("TD")[n];
                        if(x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()){
                            var temp = array[j].innerHTML
                            array[j].innerHTML = array[j + 1].innerHTML;
                            array[j+1].innerHTML = temp;
                            count++;
                        }
                    }
                }    
            }
        }

        //selecte record
        function selectRecord(){
            var index, table=document.getElementById("scheduleTable"),button1, button2, button3, scheduleID, movieID, hall, time ,date;
            scheduleID =document.getElementById("scheduleID");
            movieID =document.getElementById("movieID");
            hall =document.getElementById("hall");
            time =document.getElementById("time");
            date =document.getElementById("date");
            button1 =document.getElementById("edit");
            button2 =document.getElementById("delete");
            button3 =document.getElementById("add");
            for(var i=1; i<table.rows.length;i++){
                table.rows[i].onclick=function(){
                if(this.style.backgroundColor==="gold"){
                    index = this.rowIndex;
                    scheduleID.value="<%=request.getAttribute("ssLastSchedule")%>";
                    movieID.value="";
                    hall.value="";
                    time.value="";
                    date.value=""
                    this.style.color="white";
                    button1.disabled=true;
                    button2.disabled=true;
                    button3.disabled=false;
                    if(this.rowIndex%2==1){
                        this.style.backgroundColor="#151515";
                    }else{
                        this.style.backgroundColor="#262626";
                    }
                }else{
                    index = this.rowIndex;
                    scheduleID.value=table.rows[index].cells[0].innerText;
                    movieID.value=table.rows[index].cells[1].innerText;
                    hall.value=table.rows[index].cells[2].innerText;
                    time.value=table.rows[index].cells[3].innerText;
                    date.value=table.rows[index].cells[4].innerText;
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
                    this.style.backgroundColor="gold";   
                    this.style.color="black";
                }
                }
            }
        } 
    selectRecord();

    //set min date for schedule is tmr
    function setMinDate(){
        var today = new Date();
        var dd = today.getDate()+1;
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }     
        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById("date").setAttribute("min", today);
    }
    setMinDate();

    //display the first page of schedule
    function displayPage(){
        var firstPage = ul.getElementsByTagName('li');
        tbody.innerHTML='';
        for(let i=0; i<10;i++){
            tbody.appendChild(array[i]);
        }
        sessionStorage.setItem("currentPage",1);
        buttonGenerator();
    }
    displayPage();

    //generate page button based of schedule length
    function buttonGenerator(){
        var trNo = array.length;
        if(trNo <=10){
            ul.style.display ="none";
        }else{
            ul.style.display="flex";
            var pageNo = Math.ceil(trNo/10);
            for(i=1; i<= pageNo ;i++){
                let li = document.createElement('li');
                li.className = 'list';
                li.style
                let a = document.createElement('a');
                a.href='#';
                a.setAttribute('data-page', i);
                li.appendChild(a);
                a.innerText=i;
                ul.insertBefore(li,ul.querySelector('.next2'));
                a.onclick = e=>{
                    var otherPage = ul.getElementsByTagName('li');           
                    for(let i =0;i<otherPage.length;i++){
                        otherPage[i].style.backgroundColor='rgb(' + 20 + ',' + 20 + ',' + 20 + ')';
                        otherPage[i].childNodes[0].style.color="white";
                    }
                    var parent = a.parentNode;
                    parent.style.backgroundColor="white";
                    a.style.color="black";
                    let x =e.target.getAttribute("data-page");
                    tbody.innerHTML="";
                    sessionStorage.setItem("currentPage",x);
                    x--;
                    let start= 10*x;
                    let end = start + 10;
                    let page = array.slice(start, end);
                    for(let i =0; i<page.length;i++){
                        let item=page[i];
                        tbody.appendChild(item);
                    }
                };
            }
        }
    }

    //highlight first button
    function firstPage(){
        var firstPage = ul.getElementsByTagName('li');
        firstPage[1].style.backgroundColor="white";
        firstPage[1].childNodes[0].style.color="black";
    }
    firstPage();
    
    //next and prev page button
    let z=0;
    function nextElement(){
        var otherPage = ul.getElementsByTagName('li');  
        var trNo = array.length;
        var pageNo = Math.ceil(trNo/10);
        var z;
        if(this.id == 'next'){
            z = sessionStorage.getItem("currentPage");
            if(z<pageNo){
                for(let i =0;i<otherPage.length;i++){
                    otherPage[i].style.backgroundColor='rgb(' + 20 + ',' + 20 + ',' + 20 + ')';
                    otherPage[i].childNodes[0].style.color="white";
                }
                otherPage[parseFloat(z) + parseFloat(1)].style.backgroundColor="white";
                otherPage[parseFloat(z) + parseFloat(1)].childNodes[0].style.color="black";
                z = parseFloat(z) + parseFloat(1);
                sessionStorage.setItem("currentPage",z);
                z = (parseFloat(z) - parseFloat(1)) * 10;
            }else{
                z = (parseFloat(z) - parseFloat(1)) * 10;
            }
        }
        if (this.id == 'prev'){
            z = sessionStorage.getItem("currentPage");
            z = parseFloat(z) - parseFloat(1);
            if(z!=0){
                for(let i =0;i<otherPage.length;i++){
                    otherPage[i].style.backgroundColor='rgb(' + 20 + ',' + 20 + ',' + 20 + ')';
                    otherPage[i].childNodes[0].style.color="white";
                }
                otherPage[z].style.backgroundColor="white";
                otherPage[z].childNodes[0].style.color="black";
                sessionStorage.setItem("currentPage",z);
                z = (parseFloat(z) - parseFloat(1)) * 10;
            }
        }
        tbody.innerHTML='';
        for(let c=z;c<z+10;c++){
            tbody.appendChild(array[c]);
        }
    }
    document.getElementById('prev').onclick=nextElement;
    document.getElementById('next').onclick=nextElement;
    
    //check if hall is available during schedule time
    function checkSchedule(){
        var hall,time,date, movie, schedule, chosen=[], durationList=[], movieID, duration, sequence, startHour, startMinute, hour, minute, endHour, endMinute, endTime, currentEndHour, currentEndMinute;
        hall =document.getElementById("hall");
        time =document.getElementById("time");
        date =document.getElementById("date");
        schedule =document.getElementById("scheduleID");
        movie=document.getElementById("movieID").value;
        <%for(int i=0;i<movies.size();i++){%>
                 durationList.push(<%=movies.get(i).getDuration()%>);
         <%}%>
        var currentTime = new Date("2022-04-30T"+time.value);
        sequence = movie.split("M");
        duration = durationList[parseFloat(sequence[1])-parseFloat(1)];
        hour = Math.floor(duration/60);
        minute = duration%60;
        currentEndHour = currentTime.getHours()+ hour+1;
        currentEndMinute = currentTime.getMinutes() + minute;
        if(currentEndMinute>=60){
            currentEndHour=currentEndHour+1;
            currentEndMinute = currentEndMinute-60;
        }               
        if(currentEndHour >= 24){
            currentEndHour = 23;
            currentEndMinute =59;            
        }
        for(let i=0; i< array.length ;i++){
            if(array[i].getElementsByTagName("td")[0].innerText != schedule.value){
                if(array[i].getElementsByTagName("td")[2].innerText == hall.value && array[i].getElementsByTagName("td")[4].innerText == date.value){
                chosen.push(array[i]);
                }
            }
        }    
        for(let i=0; i< chosen.length ;i++){
            var startTime = new Date("2022-04-30T"+chosen[i].getElementsByTagName("td")[3].innerText);
            movieID = chosen[i].getElementsByTagName("td")[1].innerText;
            sequence = movieID.split("M");
            duration = durationList[parseFloat(sequence[1])-parseFloat(1)];
            startHour = startTime.getHours();
            startMinute = startTime.getMinutes();
            hour = Math.floor(duration/60);
            minute = duration%60;
            endHour = startHour+ hour+1;
            endMinute = startMinute + minute;
            if(endMinute>=60){
                endHour=endHour+1;
                endMinute = endMinute-60;
            }               
            if(endHour >= 24){
                endHour = 23;
                endMinute =59;            
            }
            if(currentTime.getHours()>= startHour && currentTime.getHours()<=endHour){
                if(currentTime.getHours()==endHour){
                    if(currentTime.getMinutes()<endMinute){
                        alert("Hall " + hall.value + " was being used during this schedule.");
                        return false;
                    }
                }else{
                        alert("Hall " + hall.value + " was being used during this schedule.");
                        return false;
                }
            }
        
            if(currentEndHour>= startHour && currentEndHour<=endHour){
                if(currentEndHour==endHour){
                    if(currentEndMinute<endMinute){
                        alert("Hall " + hall.value + " was being used during this schedule.");
                        return false;
                    }
                }else{

                    alert("Hall " + hall.value + " was being used during this schedule.");
                    return false;
                }
        

            }
        }
    }
    
    </script>
    <script src="app.js"></script>
        
    </body>
</html>
