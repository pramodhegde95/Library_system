package servlet;

import java.io.IOException;
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

public class PaymentOfFine extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String card_id= request.getParameter("fines");
		card_id=card_id.trim();
		ArrayList dataList= new ArrayList(); 		
		RequestDispatcher dispatcher = null;
		RequestDispatcher DispatcherObj=null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "pramod");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("select b.loan_id,b.date_in,f.fine_amt,f.paid from book_loans as b inner join fines as f"
					+ " on b.loan_id=f.loan_id where card_id="+card_id+" ");
			if(rs.next()!=false) {
			while(rs.next()!=false) {
				   dataList.add(rs.getString("loan_id"));
		           dataList.add(rs.getString("date_in"));
		           dataList.add(rs.getString("fine_amt"));
		           dataList.add(rs.getString("paid"));
			}
			request.setAttribute("data",dataList);
			dispatcher = request.getRequestDispatcher("/FinePayment.jsp");
				}
				else {
			request.setAttribute("data",null);
			DispatcherObj =request.getRequestDispatcher("/NotFound.jsp");
				}
			rs.close();
			conn.close();
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
