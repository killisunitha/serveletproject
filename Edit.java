

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		PrintWriter pw=response.getWriter();
		String username=request.getParameter("uusername");
		String password=request.getParameter("upassword");
		String email=request.getParameter("uemail");
		String query="select *from user_details where username=?";
		
		try {
			Connection con=Dbcon1.getcon();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			response.setContentType("text/html");
			if(rs.next()) {
				pw.print("<form action='Update'>");
				pw.print("<input type = 'text' name = 'name' value= "+rs.getString(1)+">");
				pw.print("<input type = 'text' name = 'password' value = "+rs.getString(2)+">");
				pw.print("<input type = 'text' name = 'email' value = "+rs.getString(3)+">");
				pw.print("<input type = 'submit' value = 'submit'>");
				pw.print("</form>");
				
			}
			else {
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}

		