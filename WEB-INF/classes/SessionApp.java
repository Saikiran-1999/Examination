import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class SessionApp extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException
	{
		try
		{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
				
		HttpSession session = req.getSession(false);  
        String username = (String)session.getAttribute("username");  
		
		out.println("<html><body bgcolor='cyan'><h2><center>");
		out.println("<font color='green'>Hellow Mr! "+username);
		out.println("</br></br> <a href='./home.html'> Home </a>");
		out.println("</font></center></h2></body></hthml>");
		
		out.close();
		}catch(Exception e){}
	}
}