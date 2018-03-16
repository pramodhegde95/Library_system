package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckIn extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String checked= request.getParameter("checkbtn");
		response.setContentType("text/html");
		 
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd");
		String result[]=null;
		String isbn;
		String title;
		String name;
		int h=0;
		PrintWriter out = response.getWriter();
		String cardid=request.getParameter("cardid");
		
		if(!cardid.matches(".*\\d+.*")) {
			
			out.println("Invalid card ID format");

		}
		else {
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
			Statement stmt5 = conn5.createStatement();
			ServletContext sc = this.getServletContext();
			stmt5.execute("USE Library;");
			ResultSet rs5 = stmt5.executeQuery("select *" + 
					" from borrower"
					+ " where card_id="+cardid+"");
				if(rs5.next()!=false) {

		if(checked!=null) {
			
			try {
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
				Statement stmt = conn.createStatement();
				stmt.execute("USE Library;");
				ResultSet rs = stmt.executeQuery("Select COUNT(*) from book_loans where card_id="+cardid+" group by card_id");
				if(rs.next()!=false) {
					h=Integer.parseInt(rs.getString("count(*)"));
				}
				if(h>=3) {
					out.println("Cannot borrower more than 3 books");
				}
				else {
					try {
						// Register JDBC driver
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
						Statement stmt1 = conn.createStatement();
						stmt1.execute("USE Library;");
						PreparedStatement ps = conn1
								.prepareStatement("insert into book_loans(isbn,card_id,date_out,due_in) values(?,?,?,?)");
						ps.setString(1, checked);
						ps.setString(2, cardid);
						ps.setString(3, dateFormat.format(calendar.getTime()));
						calendar.add(Calendar.DATE,14);
						ps.setString(4, dateFormat.format(calendar.getTime()));
						ps.executeUpdate();
						int i=0;
						PreparedStatement ps1 =  conn1
								.prepareStatement("update book set available='"+i+"' where isbn='"+checked+"';");
						ps1.executeUpdate();

						conn1.close();
						out.println("Checked out succesfully");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			
				}
				else {
					out.println("This card is not alloted to anyone");
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
}
}
