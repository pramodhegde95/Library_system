package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateFines extends HttpServlet{

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        
        String today=dateFormat.format(calendar.getTime());
		Date tdate=null;
		try {
			tdate = dateFormat.parse(today);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter out = response.getWriter();

        int difference=0;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("select loan_id,date_out,date_in,due_in from book_loans");
			
			while(rs.next()!=false) {
				String datein=rs.getString("date_in");
				String loan=rs.getString("loan_id");
				if(datein==null) {
					String duein=rs.getString("due_in");
					if(duein!=null) {
			        Date ddate=dateFormat.parse(duein);
					Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
					Statement stmt1 = conn1.createStatement();
					stmt1.execute("USE Library;");
					if(tdate.after(ddate)) {
					    difference =daysBetween(ddate, tdate);
						float fineamt = (float) (difference * 0.25);
						//check if id already exists in fines table
						try {
							// Register JDBC driver
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
							Statement stmt3 = conn3.createStatement();
							stmt3.execute("USE Library;");
							ResultSet rs1 = stmt3.executeQuery("select paid" + 
									" from fines"
									+ " where loan_id='"+ loan +"'");
							
							if(rs1.next()==false) {
								PreparedStatement ps1 = conn3
										.prepareStatement("insert into fines(loan_id,fine_amt) values(?,?)");
								ps1.setString(1, rs.getString("loan_id"));
								ps1.setString(2, String.valueOf(fineamt));
								ps1.executeUpdate();
							}
							else {
								int paid=Integer.parseInt(rs1.getString("paid"));
								if(paid==0) {
									PreparedStatement ps3 = conn3
											.prepareStatement("update fines set fine_amt="+fineamt+" where loan_id='"+loan+"'");
									ps3.executeUpdate();
								}
							}
								
						} catch (Exception e) {
							e.printStackTrace();
						}	
					  }
					}
				}
				else {
					String duein1=rs.getString("due_in");
					if(duein1!=null) {
			        Date ddate=dateFormat.parse(duein1);
			        Date Indate=dateFormat.parse(datein);
					Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
					Statement stmt2 = conn2.createStatement();
					stmt2.execute("USE Library;");
					if(Indate.after(ddate)) {
					    difference =daysBetween(ddate, Indate);
					float fineamt = (float) (difference * 0.25);
					try {
						// Register JDBC driver
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
						Statement stmt4 = conn4.createStatement();
						stmt4.execute("USE Library;");
						ResultSet rs2 = stmt4.executeQuery("select paid" + 
								" from fines"
								+ " where loan_id='"+ loan +"'");					
						if(rs2.next()==false) {
							PreparedStatement ps2 = conn4
									.prepareStatement("insert into fines(loan_id,fine_amt) values(?,?)");
							ps2.setString(1, rs.getString("loan_id"));
							ps2.setString(2, String.valueOf(fineamt));
							ps2.executeUpdate();
						}
						else {
							int paid=Integer.parseInt(rs2.getString("paid"));
							if(paid==0) {
								PreparedStatement ps3 = conn4
										.prepareStatement("update fines set fine_amt="+fineamt+" where loan_id='"+loan+"'");
								ps3.executeUpdate();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}	
					}
					}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("/MenuPage.jsp");
		  dispatcher.forward(request, response);

	}
	
	public int daysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

}
