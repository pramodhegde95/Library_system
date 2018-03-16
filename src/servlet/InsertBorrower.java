package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertBorrower extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 273142783667193929L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ssn = request.getParameter("ssn");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("SELECT * from borrower WHERE ssn='" + ssn + "'");
			if (rs.next() == false) {
				PreparedStatement ps = conn
						.prepareStatement("insert into borrower(ssn,bname,lname,email,address,city,state,phone) values(?,?,?,?,?,?,?,?)");
				ps.setString(1, ssn);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, email);
				ps.setString(5, address);
				ps.setString(6, state);
				ps.setString(7, city);
				ps.setString(8, phone);
				ps.executeUpdate();
				
				Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
				Statement stmt1 = conn1.createStatement();
				stmt.execute("USE Library;");
				ResultSet rs1 = stmt.executeQuery("SELECT card_id from borrower WHERE ssn='" + ssn + "'");
				
				out.println("Inserted succesfully and The card ID is:" + rs.getString("card_id"));

			}
			
			else {
				out.println("Borrower already exists with same SSN");
			}

			conn.close();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}
}
