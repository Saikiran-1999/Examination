import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class RegisterApp extends HttpServlet
{
	ServletConfig config;
	Connection con;

	public void init(ServletConfig config)
	{
		this.config=config;
		System.out.println("Register App is initialized....!");		
		ServletContext context = config.getServletContext();
		String driverName = context.getInitParameter("driverName");
		String url = context.getInitParameter("url");
		String username= context.getInitParameter("username");
		String password = context.getInitParameter("password");
		boolean flag=false;
		try
		{
		Class.forName(driverName);
		con = DriverManager.getConnection(url,username,password);
		System.out.println("Connected to MySQL DB");
		}
		catch(Exception e)
		{			
		}	
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException
	{
		try
		{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		
		PreparedStatement ps = con.prepareStatement("insert into userdetails values(?,?,?)");
		ps.setString(1,username);
		ps.setString(2,password);
		ps.setString(3,role);
		int rows = ps.executeUpdate();
		out.println("<html><body bgcolor='cyan'><h2><center>");
		
		if(rows>0)
		{
			out.println("<font color='green'>Hellow Mr! "+username);
			out.println("Registration Successfully Completed..");
		}else
			out.println("<font color='red'>Registration Failed..!");
		
		out.println("</br></br> <a href='./login.html'> Login </a>");
		out.println("</br></br> <a href='./register.html'> Register </a>");
		
		out.println("</font></center></h2></body></hthml>");
		out.close();
		}catch(Exception e){}
	}
}