package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Check_intoborrower extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String checked= request.getParameter("checkbtn");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd");
        
        	if(checked!=null) {	
					try {
						// Register JDBC driver
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
						Statement stmt1 = conn1.createStatement();
						stmt1.execute("USE Library;");
						PreparedStatement ps = conn1
								.prepareStatement("update book_loans set date_in='"+ dateFormat.format(calendar.getTime()) +"' where isbn="+checked+"");
						ps.executeUpdate();
						int i=1;
						PreparedStatement ps1 = conn1
								.prepareStatement("update book set available='"+i+"' where isbn='"+checked+"'");
						ps1.executeUpdate();
						out.println("Checked in succesfully");
						conn1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
		
	}
	
	}
}
