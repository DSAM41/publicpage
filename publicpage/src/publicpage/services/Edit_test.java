package publicpage.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import publicpage.UserService;
import publicpage.User_test;

/**
 * Servlet implementation class Edit_test
 */
@WebServlet("/Edit_test")
public class Edit_test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit_test() {
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
		int update_id = Integer.parseInt(request.getParameter("update_id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String mail = request.getParameter("mail");

		User_test e = new User_test();
		e.setId(update_id);
		e.setFirstName(fname);
		e.setLastName(lname);
		e.setEmail(mail);

		List<User_test> users = (ArrayList<User_test>)request.getSession().getAttribute("users");
		List<User_test> list = UserService.update(e, users);
		HttpSession session = request.getSession();
		session.setAttribute("users",list);
		
//		List<User_test> users = (ArrayList<User_test>)request.getSession().getAttribute("users");
//		for(User_test user : users)
//		{
//			System.out.println(user.getFirstName());
//		}
		Gson gson = new Gson();
		String userJSON = gson.toJson(users);

		PrintWriter printWriter = response.getWriter();
		printWriter.write(userJSON);
		printWriter.close();
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
