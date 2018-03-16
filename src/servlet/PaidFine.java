package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaidFine extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String checked= request.getParameter("checkbtn");
		String datein=null;
		PrintWriter out = response.getWriter();

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("select date_in from book_loans where loan_id="+checked+" ");
			
			if(rs.next()!=false) {
				   datein=rs.getString("date_in");
				   
				   if(datein!=null) {
					   
				
					Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
					Statement stmt1 = conn1.createStatement();
					stmt1.execute("USE Library;");
					ResultSet rs1 = stmt1.executeQuery("select paid from fines where loan_id="+checked+" ");

					if(rs1.next()!=false) {
					int paid=Integer.parseInt(rs1.getString("paid"));
					
					if(paid==0) {
						Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
						Statement stmt2 = conn1.createStatement();
						stmt2.execute("USE Library;");
						PreparedStatement ps3 = conn2
								.prepareStatement("update fines set paid='"+1+"' where loan_id='"+checked+"'");
						ps3.executeUpdate();
					}
					else {
						   out.println("Already paid");

					}		
					}
				}
				   else {  
					   out.println("Not yet returned");
				   }
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		}

}
