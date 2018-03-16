package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckIntoBorrower extends HttpServlet {



protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String search = request.getParameter("borrower");
	search=search.trim();
	PrintWriter out = response.getWriter();
	ArrayList dataList=new ArrayList();
	Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd");
	response.setContentType("text/html");
	RequestDispatcher dispatcher = null;
	RequestDispatcher DispatcherObj=null;
	try {
		// Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
		Statement stmt = conn.createStatement();
		ServletContext sc = this.getServletContext();
		stmt.execute("USE Library;");
		ResultSet rs = stmt.executeQuery("select bo.bname,b.isbn,b.card_id" + 
				" from book_loans as b inner join borrower as bo on b.card_id=bo.card_id"
				+ " where b.card_id LIKE '"+ search +"%" +"' OR b.isbn LIKE '"+ search +"%" +"' OR bo.bname LIKE '"+ search +"%" +"'");
		
		if(rs.next()!=false) {
			   dataList.add(rs.getString("bname"));
	           dataList.add(rs.getString("isbn"));
	           dataList.add(rs.getString("card_id"));
	           dataList.add(dateFormat.format(calendar.getTime()));
		while (rs.next()){

			           dataList.add(rs.getString("bname"));
			           dataList.add(rs.getString("isbn"));
			           dataList.add(rs.getString("card_id"));
			           dataList.add(dateFormat.format(calendar.getTime()));

			   }
		request.setAttribute("data",dataList);
		dispatcher = request.getRequestDispatcher("/CheckInto.jsp");
			}
			else {
				request.setAttribute("data",null);
		DispatcherObj =request.getRequestDispatcher("/NotFound.jsp");
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  if (dispatcher != null){
	  dispatcher.forward(request, response);
	  } 
	  else if(DispatcherObj != null){
		  DispatcherObj.forward(request, response);
	  }
}
}
