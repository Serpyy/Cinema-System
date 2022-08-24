<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Movie,java.util.ArrayList,model.Staff"%>
<%
    Staff staff = (Staff)session.getAttribute("ssLogStaff");
%>
<!DOCTYPE html>
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
    <title>Add Movie</title>
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
        <div class="custom-content2">
            <form action="AddMovie" method="post" enctype="multipart/form-data" id="addform">
                <div class="form">
                    <div class="title">
                        <h1 style="font-size: 50px;padding: 20px 0 20px 50px;color:#ffe229;">Add Movie</h1>
                        <hr style="margin-left:20px" width="1270px">
                    </div>
                    <div class="posterField">
                        <label class="inputPoster">Poster:</label>
                        <div class="image-preview" id="imagePreview">
                            <img alt="Image Preview" class="poster"/>
                            <span class="image-preview-text">Movie Poster<br/>(27 x 40) inches</span>
                        </div>  
                        <br/>
                        <input type="file"  name="poster" accept="image/jpg,image/jpeg,image/png" id="input-image" required>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:2">
                        <label style="margin-left:30px;" ><b>Movie ID:</label>    
                        <input type="text" class="ID" name="movieID" value="<%=request.getAttribute("ssLastMovieID")%>" readonly="readonly"></text>
                        </b>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:3">
                        <label>Movie Title:</label>
                        <br/>
                        <input type="text" class="input-field" name="movieTitle" required>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:3">
                        <label>Duration(Minutes):</label>
                        <br/>
                        <input type="number" min="0" max="500" class="input-field" name="duration" required>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:4">
                        <label>Age Rating:</label>
                        <br/>
                        <select style="width:310px;height:35px" name="ageRating" required>
                            <option hidden disabled selected value> </option>
                            <option>U</option>
                            <option>P13</option>
                            <option>18</option>
                            <option>TBC</option>
                        </select>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:4">
                        <label>Release Date:</label>
                        <br/>
                        <input type="date" id="datefield" class="input-field" name="releaseDate" required>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:5">
                        <label>Director:</label>
                        <br/>
                        <input type="text" class="input-field" name="director" required>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:5">
                        <label>Distributor:</label>
                        <br/>
                        <input type="text" class="input-field" name="distributor" required>
                    </div>
                    <div class="input-cast-wrapper">
                        <label style="margin: 0 0 0 40px;display:block;padding-top:10px">Cast:</label>
                        <input type="text" class="input-field" style="width:670px;margin-left:35px;margin-bottom: 20px" name="cast" id="selected-cast" required>
                        <br/>
                        <select class="cast-select" id="cast-select" oninput="addCast()">
                            <option hidden disabled selected value> </option>
                            <%
                                ArrayList<String> actor=(ArrayList)request.getAttribute("ssActorList");
                                for(int i=0;i<actor.size();i++){            
                            %>
                            <option><%=actor.get(i)%></option>
                            <%}%>
                        </select>
                       <div class="search-box-container2" style="margin:20px 40px 0 0">  
                            <div class="search-box2" style="padding-left: 0">
                                <input class="search-txt2" type="text" placeholder="Search..."  onkeyup="searchRecord()" id="searchbar">
                                    <a class="search-btn">
                                    <i class="fa fa-search"></i>
                                    </a>
                            </div>
                        </div>
                    </div>
                    <div class="input-wrapper"  style="grid-row-start:7; grid-column-start: span 2;">
                        <label>Synopsis:</label>
                        <br/>
                        <textarea name="synopsis" id="textarea"  onkeyup="getWordCount()" class="input-field" style="width:750px;height:150px" maxlength="500" required></textarea>
                        <br/>
                        <text id="word-count" class="word-count">0/500</text>
                    </div>
                    <div style="grid-row-start:8;grid-column-start:span 3;">
                        <button type="submit" class="crud" style="font-size:20px; width:150px;margin:0 150px 0 0;">Add</button>
                        <button type="button" class="crud" style="font-size:20px; width:150px;margin:0 50px 0 0;" onclick="location.reload()">Reset</button>  
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
        //show poster after input file
        const inpFile= document.getElementById("input-image");
        const previewContainer = document.getElementById("imagePreview");
        const previewImage = previewContainer.querySelector(".poster");
        const previewDefaultText = previewContainer.querySelector(".image-preview-text");   
        const fileTypes = ['jpg', 'jpeg', 'png'];

        inpFile.addEventListener("change",function(){
            const file = this.files[0];
            const extension = file.name.split('.').pop().toLowerCase(),isSuccess = fileTypes.indexOf(extension) > -1;
            if(file){
                if(isSuccess){
                    const reader = new FileReader();
                    previewDefaultText.style.display="none";
                    previewImage.style.display="block";
                    reader.addEventListener("load",function(){
                    previewImage.setAttribute("src",this.result);
                    });
                    reader.readAsDataURL(file);
                }else{
                    inpFile.value=null;  
                    previewDefaultText.style.display="block";
                    previewImage.style.display="none";
                    alert("Poster must be in .jpg, .jpeg or .png format.");
                }
                }else{
                    previewDefaultText.style.display="block";
                    previewImage.style.display="none";
                }
        });
        
        //search record
        function searchRecord(){
            var input, data, select, opt;
        
            input=document.getElementById("searchbar");
            data= input.value.toUpperCase();
            select = document.getElementById("cast-select");
        
            for(var i =0; i<select.length; i++){
                opt = select[i].text;
                    if(opt.toUpperCase().indexOf(data) > -1){
                        select[i].style.display=""; 
                }else{
                    select[i].style.display="none";
                }
            }
        }
        
        //add cast when selected
        function addCast(){
            var select, cast;
            
            select = document.getElementById("cast-select");
            cast = document.getElementById("selected-cast");
            if(!cast.value.includes(select.value)){
            cast.value = cast.value + "," + select.value;
            }
            if(cast.value.charAt(0)== ","){
                cast.value=cast.value.slice(1);
                
            }
        }

        //get word coutn of synopsis
        function getWordCount(){
            var word = document.getElementById("textarea");
            var wordCount = document.getElementById("word-count");
            var count = word.value.length;
             wordCount.innerHTML = count + "/500";              
        }
            
    </script>
    <script src="app.js"></script>
    </body>
</html>
