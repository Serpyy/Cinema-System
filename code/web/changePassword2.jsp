<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<jsp:useBean id="ssLogUser" class="model.Customer" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

        <!-- ===== CSS ===== -->
        <link href="css/loginRegisterStyle.css" rel="stylesheet" type="text/css"/>
        <title>Forgot Password</title>
    </head>
    <body>
        <div class="container">
            <div>
             <img src="img/back.png" usemap="#back" style="width: 30px;height: auto;transform: translate(20px,20px);" class="back" onclick="goBack()">
             <map name="back">
                <area shape="poly" coords="242,93,67,248,241,407,241,306,324,320,448,422,436,330,400,255,389,250,333,205,245,189,241,188,241,93,445,419,448,422">
              </map>
            </div>
        <div class="forms" style="height: 450px" style="width: ">
            <div class="form login">
                <span class="title">Change Password</span>
                <form action="chngePwd1" method="post">
                    <div class="input-field">
                        <input type="password" id="password" name="password"  placeholder="Password" minlength="8" maxlength="16" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="far fa-eye" id="togglePassword" style="margin-left: 340px; cursor: pointer;"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" id="password2" name="confirmPassword"  placeholder="Confirm Password" minlength="8" maxlength="16" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="far fa-eye" id="togglePassword2" style="margin-left: 340px; cursor: pointer;"></i>
                    </div>
                    <font color="red"  padding="10px" style="display: none" id="alertmsg">
                        <li> Password does not match </li>
                    </font>
                    <div class="input-field button">
                            <input type="submit" value="Reset Password" onclick="return match()">
                    </div>
                </form>

                <div class="login-signup">
                    <span class="text">Return back to
                        <a href="login.jsp" class="text signup-link" style="color:yellow;">Login</a>
                    </span>
                </div>
            </div>

            
        </div>
    </div>
    </body>
    
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
    
    function match(){
        var pwd=document.getElementById("password");
        var conPwd=document.getElementById("password2");
        var font=document.getElementById("alertmsg");

        if(pwd.value!=conPwd.value){
            font.style.display="flex";
            return false;
        }else{
            return true;
        }
    }
    
    function goBack() {
            window.history.back();
            }
    </script>
</html>
