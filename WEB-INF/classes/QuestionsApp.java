import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class QuestionsApp extends HttpServlet
{
	ServletConfig config;
	Connection con;

	public void init(ServletConfig config)
	{
		this.config=config;
		System.out.println("Adding Questions is initialized....!");		
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
		
		String question = req.getParameter("question");
		String answer1 = req.getParameter("answer1");
		String answer2 = req.getParameter("answer2");
		String answer3 = req.getParameter("answer3");
		String answer4 = req.getParameter("answer4");
		String answer = req.getParameter("answer");
		System.out.println(question);
		System.out.println(answer1);
		System.out.println(answer2);	
		System.out.println(answer3);
		System.out.println(answer4);
		System.out.println(answer);

		if(answer.equals("answer1"))
			answer= answer1;

		if(answer.equals("answer2"))
			answer= answer2;

		if(answer.equals("answer3"))
			answer= answer3;

		if(answer.equals("answer4"))
			answer= answer4;

		PreparedStatement ps = con.prepareStatement("insert into questions values(questions_sequence.NEXTVAL,?,?,?,?,?,?,'java')");
		ps.setString(1,question);
		ps.setString(2,answer1);
		ps.setString(3,answer2);
		ps.setString(4,answer3);
		ps.setString(5,answer4);
		ps.setString(6,answer);
		int rows = ps.executeUpdate();
		out.println("<html><body bgcolor='cyan'><h2><center>");
		
		if(rows>0)
		{
			//out.println("<font color='green'>Hellow Mr! "+username);
			out.println("Question Successfully Added to Database..");
		}else
			out.println("<font color='red'>Question not added to database..!");
		
		out.println("</br></br> <a href='./home.html'> Home </a>");
		//out.println("</br></br> <a href='./register.html'> Register </a>");
		out.println("</br></br> <a href='./add-questions.html'> Add next Question </a>");
		
		out.println("</font></center></h2></body></hthml>");
		out.close();
		}catch(Exception e){}
	}
}