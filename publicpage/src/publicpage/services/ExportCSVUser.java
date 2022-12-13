package publicpage.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicpage.ItemUser;
import publicpage.ManageUser;

@WebServlet("/ExportCSVUser")
public class ExportCSVUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportCSVUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ItemUser> list = ManageUser.getAllRecords();
		StringBuilder result = new StringBuilder();
		String header = "Username,Password,Email,Role,HOPO,Remark";
		result.append(header);
		result.append("\n");
		for (ItemUser user : list) {
			String LINE = user.getUsername() + "," + user.getPassword() + ","
					+ (user.getEmail() == null ? "" : user.getEmail()) + "," + user.getRoleName() + ","
					+ user.getAirportlist() + "," + (user.getRemark() == null ? "" : user.getRemark());
			result.append(LINE);
			result.append("\n");
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		outByteStream.write(result.toString().getBytes());
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/csv");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=member.csv");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
		outByteStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
