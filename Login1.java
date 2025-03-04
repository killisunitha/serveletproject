

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/Login1")
public class Login1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
	
	try {
		
		 Connection con=Dbcon1.getcon();
	 	String username=request.getParameter("username");
		String password=request.getParameter("password");
		String query="select *from user_details where username=? and password=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,username);
		ps.setString(2,password);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			
			HttpSession hs=request.getSession();
			hs.setAttribute("username",rs.getString(1));
			hs.setAttribute("password",rs.getString(2));
			hs.setAttribute("email",rs.getString(3));
			RequestDispatcher rd=request.getRequestDispatcher("profile1.jsp");
			rd.forward(request, response);
		}
		else {
			response.setContentType("text/html");
			pw.print("<h2>Login not successful</h2>");
			RequestDispatcher rd=request.getRequestDispatcher("/Login1.html");
			rd.include(request, response);
			
		}
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
}

}


