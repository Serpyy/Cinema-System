<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,java.util.Iterator"%>
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
        <div class="forms" style="height: 350px" style="width: ">
            <div class="form login">
                <span class="title">Change Password</span>

                <form action="ChangeStaffPassword" method="get">
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
                        <a href="login.jsp" class="text signup-link">Login</a>
                    </span>
                </div>
            </div>

            
        </div>
    </div>
    </body>
</html>
