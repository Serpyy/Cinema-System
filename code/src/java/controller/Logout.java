/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
// *
// * @author geremy
// */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            try{   
            HttpSession session=request.getSession();  
            //session.invalidate();  
            session.removeAttribute("ssLogUser");
            
            response.sendRedirect("Home.jsp");
              
            out.close();
            }catch(Exception ex){            
                out.println("ERROR: " + ex.toString() + "<br /><br />");
                StackTraceElement[] elements = ex.getStackTrace();

                for(StackTraceElement e: elements){
                     out.println("File Name: " + e.getFileName() + "<br />");
                     out.println("Class Name: " + e.getClassName() + "<br />");
                     out.println("Method Name: " + e.getMethodName() + "<br />");
                     out.println("Line Number: " + e.getLineNumber() + "<br /><br />");
                }
            }
        }  
}

   


