
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

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String name = request.getParameter("uusername");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String query="delete from user_details where username=?";
		
		try {
			Connection con=Dbcon1.getcon();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			int i = ps.executeUpdate();
			if(i>0) {
				RequestDispatcher rd = request.getRequestDispatcher("Display");
				rd.forward(request, response);
			}
			else {
				pw.print("<h2>Deletion is not done</h2>");
			}
							
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	


		
		
		
		
		
	}


