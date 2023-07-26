import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class LoadQuestions extends HttpServlet
{
	ServletConfig config;
	Connection con;

	public void init(ServletConfig config)
	{
		this.config=config;
		System.out.println("Load Questions is initialized....!");
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
		System.out.println("Connected to Oracle SQL DB");
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

		String sno = req.getParameter("sno");		
		String question = req.getParameter("question");
		String answer1 = req.getParameter("answer1");
		String answer2 = req.getParameter("answer2");
		String answer3 = req.getParameter("answer3");
		String answer4 = req.getParameter("answer4");
		String answer = req.getParameter("answer");
		String subject = req.getParameter("subject");

		out.println("<p>QNo: "+ sno +"</p>");
		out.println("<p>Question: "+ question +"</p>");
		out.println("<p>QNo: "+ answer1 +"</p>");
		out.println("<p>QNo: "+ answer2 +"</p>");
		out.println("<p>QNo: "+ answer3+"</p>");
		out.println("<p>QNo: "+ answer4 +"</p>");
		out.println("<p>QNo: "+ answer +"</p>");
		out.println("<p>QNo: "+ subject +"</p>");
		
		
		PreparedStatement ps = con.prepareStatement("select * from questions");
		ResultSet rs = ps.executeQuery();

		HttpSession session = req.getSession(true);
		session.setAttribute("sno",sno);
		session.setAttribute("question",question);
		session.setAttribute("answer1",answer1);	
		session.setAttribute("answer2",answer2);
		session.setAttribute("answer3",answer3);	
		session.setAttribute("answer4",answer4);	
		session.setAttribute("answer",answer);
		session.setAttribute("subject","subject");
		
		boolean flag = false;
		if(rs.next())
			flag = true;
		else
			flag = false;		
			
		out.println("<html><body bgcolor='cyan'><h2><center>");
		out.println("<a href='answers.jsp'> Proceed </a>");
		if(flag)
		{
			RequestDispatcher rd = req.getRequestDispatcher("/welcome");
			rd.forward(req,res);
		}else{
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req,res);
			//out.println("<font color='red'>Invalid Credentials");
		}
		out.close();
		
		}catch(Exception e){}
	}
}

/*
HttpSession session = req.getSession(true);
session.setAttribute("question",question)
sesssion.setAttribute("qnser",anser1);
*/