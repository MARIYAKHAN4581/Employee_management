package employee_management;

import java.io.IOException;
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

@WebServlet("/delete")
public class Delete extends HttpServlet {

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


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "root");

            String deleteQuery = "DELETE FROM employee_information WHERE e_id=?";
            PreparedStatement ps = con.prepareStatement(deleteQuery);
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
               
              
                resp.sendRedirect("Delete.html");
            } else {
               
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
           
        }
    }
}
