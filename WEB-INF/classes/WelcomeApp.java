import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class WelcomeApp extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException
	{
		try
		{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String username = req.getParameter("username");
		HttpSession session=req.getSession();  
        session.setAttribute("username",username);  
		
		out.println("<html><body bgcolor='cyan'>");
		out.println("<h2><center><font color='green'>Hellow Mr! "+username);
		out.println("</br></br>");
		out.println("<a href='sessionApp'>Continue</a>");  
		out.println("</br></br> <a href='./home.html'> Home </a>");
		out.println("</font></center></h2></body></hthml>");
		
		out.close();
		}catch(Exception e){}
	}
}