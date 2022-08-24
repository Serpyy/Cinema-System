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
        <title>Set Security Question</title>
    </head>
    <body>
        <div class="container">
        <div class="forms" style="height: 500px" style="width: ">
            <div class="form login">
                <span class="title">Set Security Question</span>

                <form action="SetSecurityQuestion" method="post">
                        <div class="input-field">
                            <select name="question"  style="font-size:14px" required>
                                <option>What is the name of your favourite pet?</option>
                                <option>What is your father's name?</option>
                                <option>What is your mother's name?</option>
                                <option>What is the name of best friend?</option>
                                <option>What is your favourite food?</option>           
                                <option>In what city were you born?</option>
                            </select>
                        </div>
                        <div class="input-field">
                            <input type="text" name="answer" placeholder="Answer" required>
                        </div>

                        <div class="input-field button">
                            <input type="submit" value="Confirm">
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
