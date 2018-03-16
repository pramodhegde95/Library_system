package servlet;

import java.awt.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		search=search.trim();
		PrintWriter out = response.getWriter();
		ArrayList dataList= new ArrayList(); 		
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
			ResultSet rs = stmt.executeQuery("select c.isbn, c.title, c.available, a.name" + 
					" from (authors as a inner join book_author as b on a.author_id=b.author_id)"
					+ " inner join book as c on c.isbn=b.isbn"
					+ " where a.name LIKE '"+ search +"%" +"' OR c.isbn LIKE '"+ search +"%" +"' OR c.title LIKE '"+ search +"%" +"'");
				if(rs.next()!=false) {
			while (rs.next()){
				           dataList.add(rs.getString("isbn"));
				           dataList.add(rs.getString("title"));
				           dataList.add(rs.getString("name"));
				           dataList.add(rs.getString("available"));
				   }
			request.setAttribute("data",dataList);
			dispatcher = request.getRequestDispatcher("/MenuPage.jsp");

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
