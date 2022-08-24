
package controller;
import model.CustomerDA;
import model.MovieDA;
import model.Customer;
import model.Movie;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//           
//            
//            PrintWriter out = response.getWriter();
//            String email = request.getParameter("email"); 
//            String password = request.getParameter("password");
//
//            
//            UserDatabase db =  new UserDatabase(ConnectionPro.getConnection());
//            Customer customer = db.currentCustomer(email, password);
//            
//            if(customer!=null){
//                HttpSession session = request.getSession();
//                session.setAttribute("logUser", customer);
//                response.sendRedirect("Home.html");
//            }else{
//                out.println("Account not found");
//            }
//            
//            
//        }
//    @Override
//        protected void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//            processRequest(request, response);
//        }

    @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            
            try{
                boolean valid=true;
                String email = request.getParameter("email"); 
                String password = request.getParameter("password");
                List list=new LinkedList();
                valid=Customer.chkEmail(email);
                if(valid){             
                    CustomerDA custDA=new CustomerDA();

                    Customer customer = custDA.currentCustomer(email, password);

                    if(customer!=null){

                        session.setAttribute("ssLogUser", customer);
                        response.sendRedirect("Home.jsp");                 
                    }else{
                            //response.sendRedirect("login1.html");
                            list.add("Invalid email or password");
                            request.setAttribute("errorList",list);
                            valid=false;
                    }
                }
                else{
                    list.add("Invalid email");
                    request.setAttribute("errorList",list);
                }

                if(valid==false){ //send error list to jsp page
                    RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                    rd.forward(request,response);
                }
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



//    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        
//        String email = request.getParameter("email"); 
//        String password = request.getParameter("password");
//        
//        
//        try{
//            CustomerDA custDA = new CustomerDA();
//            
//            Customer customer = new Customer(cust_name, ic_no, email, password,phone_no,securityQuestion, answer, membership_no, point);
//
//            custDA.addRecord(customer);
//            
//            out.println("Successfully added to the database. <br />");
//            
//        }catch(SQLException ex){            
//            out.println("ERROR: " + ex.toString() + "<br /><br />");
//            StackTraceElement[] elements = ex.getStackTrace();
//            
//            for(StackTraceElement e: elements){
//                 out.println("File Name: " + e.getFileName() + "<br />");
//                 out.println("Class Name: " + e.getClassName() + "<br />");
//                 out.println("Method Name: " + e.getMethodName() + "<br />");
//                 out.println("Line Number: " + e.getLineNumber() + "<br /><br />");
//            }
//        }
//    }



