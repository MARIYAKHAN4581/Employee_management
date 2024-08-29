package employee_management;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/smt")
public class Information extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("employeeId"));
		String name = req.getParameter("name");
		String address = req.getParameter("Address");
		String states = req.getParameter("states");
		String city = req.getParameter("city");
		String email = req.getParameter("email");
		String phone = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
			PreparedStatement ps = con
					.prepareStatement("insert into employee_management.employee_information values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(6, email);
			ps.setString(7, phone);
			ps.setString(8, password);
			ps.setString(5, city);
			ps.setString(4, states);
		
			
			
			
			ps.executeUpdate();
		RequestDispatcher rd=req.getRequestDispatcher("submitted.html");
		rd.forward(req, resp);
			System.out.println("sumbitted");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
