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

import publicpage.test;

/**
 * Servlet implementation class test_t
 */
@WebServlet("/test_t")
public class test_t extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test_t() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		RequestDispatcher rd=request.getRequestDispatcher("/table-test.jsp");  
//		rd.include(request, response);
		
		List<test> students = new ArrayList<test>();
		test student = new test();
		student.setFirstName("Hussein");
		student.setEmail("25");
		        
		test student2 = new test();
		student2.setFirstName("Hussein");
		student2.setEmail("25");
		        
		students.add(student);
		students.add(student2);

		request.setAttribute("students", students);
		RequestDispatcher rd=request.getRequestDispatcher("/table-test.jsp"); 
		rd.include(request, response);
//		request.getRequestDispatcher("/table-test.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
