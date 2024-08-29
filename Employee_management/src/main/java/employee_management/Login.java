package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("Id"));
		String password = req.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root",
					"root");
			PreparedStatement ps = con
					.prepareStatement("Select * from employee_information where e_id=? and Password=?");
			ps.setInt(1, id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				HttpSession session = req.getSession();

				session.setAttribute("id", rs.getInt("e_id"));
				session.setAttribute("name", rs.getString("Name"));
				session.setAttribute("address", rs.getString("Address"));
				session.setAttribute("email", rs.getString("Email"));
				session.setAttribute("phoneNumber", rs.getString("PhoneNum"));

				session.setAttribute("password", rs.getString("Password"));
				session.setAttribute("city", rs.getString("City"));
				session.setAttribute("state", rs.getString("State"));

				PrintWriter pw = resp.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html lang='en'>");
				pw.println("<head>");
				pw.println("    <meta charset='UTF-8'>");
				pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
				pw.println("    <title>User Profile</title>");
				pw.println("    <link rel='stylesheet' href='./details.css'>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("    <div class='userp'>");
				pw.println("        <h2>User Profile</h2>");
				pw.println("    </div>");
				pw.println("    <div class='ig'>");
				pw.println("        <img src='Images/emp_image.jpg' alt='Profile Image'>");
				pw.println("    </div>");
				pw.println("    <div class='All'>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>Employee ID:</label>");
				pw.println("            <h1><span id='userId'>" + rs.getInt("e_id") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>Name:</label>");
				pw.println("            <h1><span id='userName'>" + rs.getString("Name") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>Address:</label>");
				pw.println("            <h1><span id='userAddress'>" + rs.getString("Address") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>State:</label>");
				pw.println("            <h1><span id='userState'>" + rs.getString("State") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>City:</label>");
				pw.println("            <h1><span id='userCity'>" + rs.getString("City") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>Email:</label>");
				pw.println("            <h1><span id='userEmail'>" + rs.getString("Email") + "</span></h1>");
				pw.println("        </div>");
				pw.println("        <div class='info-item'>");
				pw.println("            <label>Phone Number:</label>");
				pw.println("            <h1><span id='userPhone'>" + rs.getString("PhoneNum") + "</span></h1>");
				pw.println("        </div>");
				pw.println("    </div>");
				pw.println("    <div class='button-group'>");
				pw.println("<form action='update' method='post'>");
				pw.println("        <button>Update</button> ");
				pw.println("</form>");
				pw.println("<form action='delete' method='post'>");
				pw.println("        <button>Delete</button>");
				pw.println("</form>");
				pw.println("    </div>");
				pw.println("</body>");
				pw.println("</html>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				resp.getWriter().println("invalid user name or password");

				rd.forward(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}
}
