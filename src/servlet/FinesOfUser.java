package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinesOfUser extends HttpServlet {
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			ArrayList dataList= new ArrayList(); 		

			response.setContentType("text/html");
			try {
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
				Statement stmt = conn.createStatement();
				stmt.execute("USE Library;");
				ResultSet rs = stmt.executeQuery("select b.card_id,bo.bname,sum(fine_amt)" + 
						" from book_loans as b inner join fines as f on b.loan_id=f.loan_id inner join borrower as bo"
						+ " on bo.card_id=b.card_id"
						+ " where paid="+0+" group by card_id");
				while (rs.next()){
			           dataList.add(rs.getString("card_id"));
			           dataList.add(rs.getString("bname"));
			           dataList.add(rs.getString("sum(fine_amt)"));
			   }
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("data",dataList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayFine.jsp");

			  if (dispatcher != null){
			  dispatcher.forward(request, response);
			  } 

	}
}
