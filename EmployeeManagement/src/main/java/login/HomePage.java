package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.println("<p>Welcome "+session.getAttribute("email")+"</p>");
		out.println("<a href='Logout'>Logout</a>");
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<title>Forms</title>\r\n"
				+ "<meta charset=\"utf-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "\r\n"
				+ "    <!-- Bootstrap CSS -->\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
				+ "\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\r\n"
				+ "\r\n"
				+ "<link rel=\"stylesheet\" href=\"style.css\" />\r\n"
				+ "\r\n"
				+ "</head>\r\n"
				+ "<body style=\"margin-top : 50px\" class=\"container\">\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col\">\r\n"
				+ "<form action=\"HomePage\" method = \"post\">\r\n"
				+ "<h1>Time Sheet Entry</h1>\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-2\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">Employee No</label> </div>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "<input type =\"text\" name=\"empNo\" class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">No of Working Days</label>\r\n"
				+ "<input type =\"text\" name=\"noOfWorkingDays\"  class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">Month</label>\r\n"
				+ "<input type =\"text\" name=\"month\" size=\"50\"  class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">Year</label>\r\n"
				+ "<input type =\"text\" name=\"year\"  class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<button type =\"submit\" class=\"btn btn-primary\">Submit</button>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</form>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "<div class=\"col\">\r\n"
				+ "<form action =\"PaySlipPage\" method=\"post\">\r\n"
				+ "<h1>Payslip Generation</h1>\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">Month</label>\r\n"
				+ "<input type =\"text\" name=\"paySlipMonth\" class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<label class=\"form-label\">Year</label>\r\n"
				+ "<input type =\"text\" name=\"paySlipYear\" class=\"form-control\"/>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "&nbsp;\r\n"
				+ "\r\n"
				+ "<div class=\"row\">\r\n"
				+ "<div class=\"col-md-4\">\r\n"
				+ "\r\n"
				+ "<button type =\"submit\" class=\"btn btn-primary\">Submit</button>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "</form>\r\n"
				+ "\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int empNo, noOfWorkingDays, month, year;
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		empNo = Integer.parseInt(request.getParameter("empNo"));
		noOfWorkingDays = Integer.parseInt(request.getParameter("noOfWorkingDays"));
		month = Integer.parseInt(request.getParameter("month"));
		year = Integer.parseInt(request.getParameter("year"));
		HttpSession session = request.getSession();
		if(!isValidDate(noOfWorkingDays,month,year))
				{
			out.println("<p>Welcome "+session.getAttribute("email")+"</p>");
			out.print("<html><body><div style = 'color :red;'><h4>Invalid date! please enter valid date</h4></div></body></html>");	
			RequestDispatcher dispatcher = request.getRequestDispatcher("TimeSheet.html");
			dispatcher.include(request, response);	
		}
		
		else {
		try {
			updateTimesheet(empNo, noOfWorkingDays, month, year);
        	//out.print("<p>Welcome "+session.getAttribute("emp")+"</p>");
			out.println("<p>Welcome "+session.getAttribute("email")+"</p>");
			out.print("<html><body><div style = 'color :green;'><h4>Successfully enterted the data into TimeSheet</h4></div></body></html>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("TimeSheet.html");
			dispatcher.include(request, response);


			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	
	public void updateTimesheet(int empNo, int noOfWorkingDays, int month, int year) throws SQLException, ClassNotFoundException 
	{
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("insert into timeSheet values(?,?,?,?)");
		
		p.setInt(1,empNo);
		p.setInt(2,noOfWorkingDays);
		p.setInt(3,month); 
		p.setInt(4,year);
		
	    p.execute();
	    p.close();
        connect.close();
	}
	
	public boolean isValidDate(int days,int month,int year) {
		if((month == 4 || month == 6 || month == 9 ||  
                month == 11) && (days > 30)) {
			return false;
		}
		else if((month == 1 || month == 3 || month == 5 ||   month == 7 ||  month == 8 ||  month == 10 ||  month == 12 ) && (days > 31)) {
			return false;
		}
		else {
			if((year%4==0  || (year%100 == 0 && year%400 == 0 )) || (year%100!=0 && year%4==0)){
				if(days>29) {
					return false;
				}
				else if(days>28){
					return false;
				}
			}
		}
     return true;
	}
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement","root","1234");
		
	}

}
