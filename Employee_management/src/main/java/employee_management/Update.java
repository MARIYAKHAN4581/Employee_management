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

@WebServlet("/changes")
public class Update extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//	        String action = req.getParameter("action"); // Check if it's form submission or display request
//	        
//	        if (action != null && action.equals("submit")) {
//	            
		int id = Integer.parseInt(req.getParameter("emp_id"));

		// Get updated data from request
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String state = req.getParameter("state");
		String city = req.getParameter("city");
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phone");
		String password = req.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root",
					"root");

			String updateQuery = "UPDATE employee_information SET name=?, address=?, state=?, city=?, email=?, PhoneNum=?, password=? WHERE e_id=?";
			PreparedStatement ps = con.prepareStatement(updateQuery);

			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, state);
			ps.setString(4, city);
			ps.setString(5, email);
			ps.setString(6, phoneNumber);
			ps.setString(7, password);
			ps.setInt(8, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {

				session.setAttribute("name", name);
				session.setAttribute("address", address);
				session.setAttribute("state", state);
				session.setAttribute("city", city);
				session.setAttribute("email", email);
				session.setAttribute("phoneNumber", phoneNumber);
				session.setAttribute("password", password);

				resp.sendRedirect("login.html");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

	}

}
