
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- ===== Iconscout CSS ===== -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

    <!-- ===== CSS ===== -->
    <link href="css/loginRegisterStyle.css" rel="stylesheet" type="text/css"/>
    <title>Login</title>
    
</head>
<body>
    
   <div class="container">
       <div>
             <img src="img/back.png" usemap="#back" style="width: 30px;height: auto;transform: translate(20px,20px);" class="back" onclick="goBack()">
             <map name="back">
                <area shape="poly" coords="242,93,67,248,241,407,241,306,324,320,448,422,436,330,400,255,389,250,333,205,245,189,241,188,241,93,445,419,448,422">
              </map>
        </div>
        <div class="forms" style="height:440px;">
            
            <div class="form login">
                <div class="button2">
                    <div id="btn"></div>
                    <button type="button" class="switch" onclick="login()" style="font-size: 23px;font-weight:600">User Login</button>
                    <button type="button" class="switch" onclick="staffLogin()" style="font-size: 23px;font-weight:600">Staff Login</button>
                </div>

                <form action="Login" method="post" id="login" class="input">
                   
                    <div class="input-field">
                        <input type="text" name="email" placeholder="Email" required>
                        <i class="uil uil-envelope icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" id="password" name="password"  placeholder="Password" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="far fa-eye" id="togglePassword" style="margin-left: 240px; cursor: pointer;"></i>
                    </div>
                    <%
                        List list=(List) request.getAttribute("errorList");
                        if(list!=null){
                            for(Iterator it=list.iterator();it.hasNext();){
                                String errorMsg=(String) it.next();
                                %>
                                <font color="red" padding="5px">
                                    <li> <%=errorMsg%> </li>
                                </font>
                    <%
                            }
                        }
                    %>

                    <div class="checkbox-text">                   
                        <a href="changePassword.jsp" style="color:#f4e618" class="text">Forgot password?</a>
                    </div>
                    
                    <div class="input-field button">
                        <input type="submit"  value="Login Now">
                    </div>
                    
                    <div class="login-signup">
                        <span class="text" style="color:white">Don't have an account?
                            <a href="register.jsp" style="color:#f4e618" class="text signup-link">Signup now</a>
                        </span>
                    </div>
                </form>
                <form action="StaffLogin" method="post" id="staffLogin" class="input">
                    <div class="input-field">
                        <input type="text" name="email" placeholder="Email" required>
                        <i class="uil uil-envelope icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" id="password2" name="password"  placeholder="Password" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="far fa-eye" id="togglePassword2" style="margin-left: 240px; cursor: pointer;"></i>
                    </div>
                    <%
                        List list2=(List) request.getAttribute("errorList2");
                        if(list2!=null){
                            for(Iterator it=list2.iterator();it.hasNext();){
                                String errorMsg=(String) it.next();
                                %>
                                <font color="red" padding="5px">
                                    <li> <%=errorMsg%> </li>
                                </font>
                    <%
                            }
                        }
                    %>
                    <div class="checkbox-text">                   
                        <a href="ChangeStaffPassword.jsp" style="color:#f4e618" class="text">Forgot password?</a>
                    </div>

                    <div class="input-field button">
                        <input type="submit"  value="Login Now">
                    </div>
                 </form>
            </div>
        </div>
    </div>

    <script type="text/javascript">

    //   js code to show/hide password and change icon
    
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });
    
    const togglePassword2 = document.querySelector('#togglePassword2');
    const password2 = document.querySelector('#password2');

    togglePassword2.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password2.getAttribute('type') === 'password' ? 'text' : 'password';
        password2.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });


    var x=document.getElementById("login");
    var y=document.getElementById("staffLogin");
    var z=document.getElementById("btn");
        
        function login(){
            x.style.left="75px";
            y.style.left="450px";
            z.style.left="30px";
        }

        function staffLogin(){       
            x.style.left="-450px";
            y.style.left="75px";
            z.style.left="205px";
        }
        
    function goBack() {
        window.history.back();
        }
        
    </script>
    <!--<script src="script.js"></script>-->

</body>
</html>
