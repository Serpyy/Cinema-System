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

                <form action="chngePwd" method="get">
                    <%
                        String email=ssLogUser.getEmail();
                                      
                    %>
                        <div class="input-field">
                            <input type="text" name="email" placeholder="Enter your email" required>
                            <i class="uil uil-envelope icon"></i>

                        </div>
                    
                    <%
                        List list=(List) request.getAttribute("errorList");
                        if(list!=null){
                            for(Iterator it=list.iterator();it.hasNext();){
                                String errorMsg=(String) it.next();
                                %>
                                <font color="red"  padding="10px">
                                    <li> <%=errorMsg%> </li>
                                </font>
                    <%
                            }
                        }
                    %>

                        <div class="input-field button">
                            <input type="submit" value="Proceed">
                        </div>
                    
                </form>

                <div class="login-signup">
                    <span class="text">Return back to
                        <a href="#" class="text signup-link" style="color:yellow;">Login</a>
                    </span>
                </div>
            </div>

            
        </div>
    </div>
    </body>
    
    <script>
        function goBack() {
            window.history.back();
            }
    </script>
</html>

