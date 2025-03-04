

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String uname = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String query = "update user_details set password = ?,email = ? where username = ?";
		
		try {
			Connection con = Dbcon1.getcon();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.setString(3, uname);
			int i = ps.executeUpdate();
			if(i>0) {
				RequestDispatcher rd = request.getRequestDispatcher("Display");
				rd.forward(request, response);
			}
			else {
				pw.print("<h2>Unable to update</h2>");
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
	

	