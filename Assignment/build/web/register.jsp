<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
        <link href="css/loginRegisterStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="register-container">
            <div>
             <img src="img/back.png" usemap="#back" style="width: 30px;height: auto;transform: translate(20px,20px);" class="back" onclick="goBack()">
             <map name="back">
                <area shape="poly" coords="242,93,67,248,241,407,241,306,324,320,448,422,436,330,400,255,389,250,333,205,245,189,241,188,241,93,445,419,448,422">
              </map>
            </div>
            <div class="forms">
                
                    <div class="form signup">
                        <span class="title">Registration</span>

                        <form action="Register" method="post">
                            <div class="input-field">
                                <input type="text" placeholder="Name" name="name" required>
                                
                            </div>
                            <div class="input-field">
                                <input type="text" placeholder="Mobile Number" name="mobileNumber" required>
                                
                            </div>
                            <div class="input-field">
                                <input type="email" placeholder="Email" name="email" required>
                                
                            </div>
                            <div class="input-field">
                                <input type="text" placeholder="IC No" name="icNo" required>
                                
                            </div>
                            <div class="input-field">
                                <input type="password" id="password" placeholder="Password" name="password" required>
                                
                                <i class="far fa-eye" id="togglePassword" style="margin-left: 240px; cursor: pointer;"></i>
                            </div>
                            <div class="input-field">
                                <input type="password" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword" required>
                                
                                <i class="far fa-eye" id="togglePassword2" style="margin-left: 240px; cursor: pointer;"></i>
                            </div>
                            <label for="securityQuestion">Select a security question</label></br>
                            <select name="securityQuestion" style="background: transparent; color: white; border: none; border-bottom: solid 1px;">
                                <option value="What is your father's name?" style="color: black;">What is your father's name?</option>
                                <option value="What is your mother's name?" style="color: black;">What is your mother's name?</option>
                                <option value="What is your favourite food?" style="color: black;">What is your favourite food?</option>
                                <option value="What is the name of your favourite pet?" style="color: black;">What is the name of your favourite pet?</option>
                                <option value="In what city were you born?" style="color: black;">In what city were you born?</option>
                            </select>
                            <div class="input-field">
                                <input type="answer" placeholder="Answer" name="answer" required>
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
                            <div class="input-field button">
                                <input type="submit" value="Register">
                            </div>
                        </form>
                        <div class="login-signup">
                            <span class="text" style="color:white">Already have an account?
                                <a href="login.jsp" class="text login-link" style="color:#f4e618">Login</a>
                            </span>
                        </div>
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
    const password2 = document.querySelector('#comfirmPassword');

    togglePassword2.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password2.getAttribute('type') === 'password' ? 'text' : 'password';
        password2.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });

            function goBack() {
                    window.history.back();
                }

            
        </script>
</body>
</html>