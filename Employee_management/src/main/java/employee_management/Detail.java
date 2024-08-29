package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update")
public class Detail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 HttpSession session = req.getSession();
	        

            int id = (int) session.getAttribute("id");
            String name = (String) session.getAttribute("name");
            String address = (String) session.getAttribute("address");
            String state = (String) session.getAttribute("state");
            String city = (String) session.getAttribute("city");
            String email = (String) session.getAttribute("email");
            String phoneNumber = (String) session.getAttribute("phoneNumber");
            String password = (String) session.getAttribute("password");
            PrintWriter pw = resp.getWriter();
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang='en'>");
            pw.println("<head>");
            pw.println("    <meta charset='UTF-8'>");
            pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.println("    <title>Edit Profile</title>");
            pw.println("    <link rel='stylesheet' href='./Update.css'>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("    <header>");
            pw.println("        <h1>Edit Profile</h1>");
            pw.println("    </header>");
            pw.println("    <main>");
            pw.println("        <section class='edit-profile'>");
            pw.println("            <form action='changes' method='POST'>");
            pw.println("                <input type='hidden' name='action' value='submit'>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='emp_id'>Employee ID:</label>");
            pw.println("                    <input type='text' id='emp_id' name='emp_id' value='" + id + "' readonly>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='name'>Name:</label>");
            pw.println("                    <input type='text' id='name' name='name' value='" + (name != null ? name : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='address'>Address:</label>");
            pw.println("                    <input type='text' id='address' name='address' value='" + (address != null ? address : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='email'>Email:</label>");
            pw.println("                    <input type='email' id='email' name='email' value='" + (email != null ? email : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='phone'>Phone Number:</label>");
            pw.println("                    <input type='tel' id='phone' name='phone' value='" + (phoneNumber != null ? phoneNumber : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='password'>Password:</label>");
            pw.println("                    <input type='password' id='password' name='password' value='" + (password != null ? password : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='city'>City:</label>");
            pw.println("                    <input type='text' id='city' name='city' value='" + (city != null ? city : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <div class='form-group'>");
            pw.println("                    <label for='state'>State:</label>");
            pw.println("                    <input type='text' id='state' name='state' value='" + (state != null ? state : "") + "' required>");
            pw.println("                </div>");
            pw.println("                <button type='submit'>Save Changes</button>");
            pw.println("            </form>");
            pw.println("        </section>");
            pw.println("    </main>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }
