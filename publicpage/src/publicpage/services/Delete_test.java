package publicpage.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import publicpage.UserService;
import publicpage.User_test;

/**
 * Servlet implementation class Delete_test
 */
@WebServlet("/Delete_test")
public class Delete_test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int detete_id = Integer.parseInt(request.getParameter("detete_id"));
		User_test e = new User_test();
		e.setId(detete_id);
		List<User_test> list = UserService.delete(e);
		Gson gson = new Gson();
		String userJSON = gson.toJson(list);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(userJSON);
		printWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
