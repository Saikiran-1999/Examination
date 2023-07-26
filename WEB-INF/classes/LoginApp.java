import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class LoginApp extends HttpServlet
{
	ServletConfig config;
	Connection con;

	public void init(ServletConfig config)
	{
		this.config=config;
		System.out.println("Login App is initialized....!");
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
		
		PreparedStatement ps = con.prepareStatement("select * from userdetails where username=? and password=?");
		ps.setString(1,username);
		ps.setString(2,password);
		ResultSet rs = ps.executeQuery();
		boolean flag = false;
		if(rs.next())
			flag = true;
		else
			flag = false;		
			
		out.println("<html><body bgcolor='cyan'><h2><center>");
		
		if(flag)
		{
			RequestDispatcher rd = req.getRequestDispatcher("/welcome");
			rd.forward(req,res);
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req,res);
			out.println("<font color='red'>Invalid Credentials");
		}
		out.close();
		
		}catch(Exception e){}
	}
}