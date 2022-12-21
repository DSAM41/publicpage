package publicpage.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicpage.UserService;
import publicpage.User_test;

/**
 * Servlet implementation class Log_test
 */
@WebServlet("/Log_test")
public class Log_test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Log_test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password = request.getParameter("password");
		response.setContentType("text/html");// setting the content type

		if (password.equals("123")) {
			List<User_test> users = (ArrayList<User_test>)request.getSession().getAttribute("users");
			if (users == null) 
			{
				List<User_test> list = UserService.getUsers();
				request.setAttribute("users", list);
				
				HttpSession session = request.getSession();
				session.setAttribute("users",list);
			}
				RequestDispatcher rd = request.getRequestDispatcher("/table-test.jsp");
				rd.forward(request, response);
			
		} else {
			response.getWriter().write("False");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
