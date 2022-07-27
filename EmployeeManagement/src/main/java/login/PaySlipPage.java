package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/PaySlipPage")
public class PaySlipPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PaySlipPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String empEmailID = (String) session.getAttribute("email");
		int paySlipMonth = Integer.parseInt(request.getParameter("paySlipMonth"));
		int paySlipYear = Integer.parseInt(request.getParameter("paySlipYear"));
        int empNo = -1;
        PrintWriter out = response.getWriter();
		try {
			empNo = getEmpNo(empEmailID);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
				generatePaySlip(paySlipMonth,paySlipYear,empNo,response,request);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generatePaySlip(int paySlipMonth,int paySlipYear,int empNo, HttpServletResponse response,HttpServletRequest request) throws SQLException, ClassNotFoundException, IOException{
		
		Connection connect = getConnection();
		PrintWriter out = response.getWriter();
		
		PreparedStatement p = connect.prepareStatement("select HireDate,Salary from Employee where empNo = ?");
		p.setInt(1, empNo);
		ResultSet r = p.executeQuery();
		LocalDate hireDate = null;
		Month hireMonth;
		int hireYear;
		int salary = 0;
		while(r.next()) {
			  DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 hireDate=LocalDate.parse(r.getString(1),pattern1);
			 salary = r.getInt(2);
			//r.getString(1);
			 
		}	
		int noOfWorkingDays = 0;
		YearMonth hireYearMonth=YearMonth.of(hireDate.getYear(),hireDate.getMonth());
		YearMonth paySlipYearMonth=YearMonth.of(paySlipYear,paySlipMonth);
		HttpSession session = request.getSession();
		if(hireYearMonth.compareTo(paySlipYearMonth)==-1) {
			//generater payslip
            out.println("<p>Welcome "+session.getAttribute("email")+"</p>");
             noOfWorkingDays = 0;
 			 noOfWorkingDays = getNoOfWorkingDays(empNo,paySlipMonth,paySlipYear,response, request);
     			
     		
		     String emailID = (String) session.getAttribute("email");
             Employee e = getEmployeeDetails(emailID);
             int empCode = e.getEmpNo();
             String empName = e.geteName();
             int deptNo = e.getDeptNo();
             String joinDate = e.getHireDate();
            int Salary = noOfWorkingDays*salary/30;
            float HRA = (float) (Salary*0.12);
            int pf = 1200;
            int tax = 200;
            int deductions = pf+tax;
            int bonus = 500;
            float totalEarnings = Salary+HRA+bonus;
            float netPay = totalEarnings-deductions;
            out.println("<a href='HomePage'>Home Page</a></br>");
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
					+ "<body>\r\n"
					+ "\r\n"
					+ "<div class=\"container mt-5 mb-5\">\r\n"
					+ "    <div class=\"row\">\r\n"
					+ "        <div class=\"col-md-12\">\r\n"
					+" <div class=\"text-center lh-1 mb-2\">\r\n"
					+ "                <h6 class=\"fw-bold\">Payslip</h6>\r\n"
					+ "            </div>"
					+ "            <div class=\"row\">\r\n"
					+ "                <div class=\"col-md-10\">\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"col-md-6\">\r\n"
					+ "                            <div> <span class=\"fw-bolder\">EMP Code</span> <small class=\"ms-3\">"+empCode+"</small> </div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"col-md-6\">\r\n"
					+ "                            <div> <span class=\"fw-bolder\">EMP Name</span> <small class=\"ms-3\">"+empName+"</small> </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"col-md-6\">\r\n"
					+ "                            <div> <span class=\"fw-bolder\">Joining Date</span> <small class=\"ms-3\">"+joinDate+"</small> </div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"col-md-6\">\r\n"
					+ "                            <div> <span class=\"fw-bolder\">Dept Number</span> <small class=\"ms-3\">"+deptNo+"</small> </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <table class=\"mt-4 table table-bordered\">\r\n"
					+ "                    <thead class=\"bg-dark text-white\">\r\n"
					+ "                        <tr>\r\n"
					+ "                            <th scope=\"col\">Earnings</th>\r\n"
					+ "                            <th scope=\"col\">Amount</th>\r\n"
					+ "                            <th scope=\"col\">Deductions</th>\r\n"
					+ "                            <th scope=\"col\">Amount</th>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </thead>\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <th scope=\"row\">Basic</th>\r\n"
					+ "                            <td>"+salary+"</td>\r\n"
					+ "                            <td>PF</td>\r\n"
					+ "                            <td>"+pf+"</td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <th scope=\"row\">HRA</th>\r\n"
					+ "                            <td>"+HRA+"</td>\r\n"
					+ "                            <td>Tax</td>\r\n"
					+ "                            <td>"+tax+"</td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <th scope=\"row\">Bonus</th>\r\n"
					+ "                            <td>"+bonus+"</td>\r\n"
					+ "                            <td colspan=\"2\"></td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr class=\"border-top\">\r\n"
					+ "                            <th scope=\"row\">Total Earnings</th>\r\n"
					+ "                            <td>"+totalEarnings+"</td>\r\n"
					+ "                            <td>Total Deductions</td>\r\n"
					+ "                            <td>"+deductions+"</td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"row\">\r\n"
					+ "                <div class=\"col-md-4\"> <br> <span class=\"fw-bold\">Net Pay : "+ netPay +"</span> </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "</div>\r\n"
					+ "</body>\r\n"
					+ "</html>");	
			 
		}
		else {
			out.println("<p>Welcome "+session.getAttribute("email")+"</p>");
			out.println("<html><body><div style = 'color :red;'><h4>Please enter the valid date to generate the pay slip</h4></div></body></html></br>");
			 out.println("<a href='HomePage'>Home Page</a></br>");
			 out.print("<a href='Logout'>Logout</a>");
		}
           
	}
	
		
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement","root","1234");
		
	}
	public int getEmpNo(String empEmail) throws SQLException, ClassNotFoundException {
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select empNo from LoginCredentials where emailID = ?");
		p.setString(1, empEmail);
		ResultSet r = p.executeQuery();
		
		if(r.next()) {
			return r.getInt(1);
			//r.getString(1);
		}
		return 0;
		
	}
	public int getNoOfWorkingDays(int empNo,int month,int year, HttpServletResponse response,HttpServletRequest request) throws SQLException, ClassNotFoundException, IOException
	{
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select noOFWorkingDays from TimeSheet where empNo = ? and month =? and year =?");
		p.setInt(1, empNo);
		p.setInt(2, month);
		p.setInt(3, year);
		ResultSet r = p.executeQuery();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		 if (r.next() == false) {
		         out.println("<html><body><div style = 'color :red;'><h4>Please enter the details first to timesheet to generate the pay slip</h4></div></body></html></br>");
				 out.println("<a href='HomePage'>Home Page</a></br>");
				 out.print("<a href='Logout'>Logout</a>");
		      } 
		return r.getInt(1);
		          
	}
	
private Employee getEmployeeDetails(String email) throws ClassNotFoundException, SQLException {
		
		Connection connect = getConnection();
		PreparedStatement p = connect.prepareStatement("select * from Employee where EmpNo = (select EmpNo from LoginCredentials where emailID = ?)");
		p.setString(1, email);
		
		
		ResultSet r = p.executeQuery();
		if(r.next())
			return new Employee(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5),r.getInt(6),r.getString(7),r.getInt(8));
		
		return null;
	}	
}

