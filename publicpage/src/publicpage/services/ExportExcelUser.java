package publicpage.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import publicpage.ItemUser;
import publicpage.ManageUser;

@WebServlet("/ExportExcelUser")
public class ExportExcelUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportExcelUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create a Workbook
		HSSFWorkbook workbook = new HSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
		HSSFSheet sheet = workbook.createSheet("member");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		List<String> memberColumns = Arrays.asList("Username", "Password", "Email", "Role", "HOPO", "Remark");
		List<ItemUser> list = ManageUser.getAllRecords();
		for (int i = 0; i < memberColumns.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(memberColumns.get(i));
		}

		int rowNum = 1;
		for (ItemUser user : list) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getUsername());
			row.createCell(1).setCellValue(user.getPassword());
			row.createCell(2).setCellValue(user.getEmail());
			row.createCell(3).setCellValue(user.getRoleName());
//			String[] airportlist = user.getAirportlist().split(":");
//			String hopo = "";
//			for (int i = 0; i < airportlist.length; i++) {
//				hopo += airportlist[i];
//				if (i < airportlist.length - 1) {
//					hopo += ",";
//				}
//			}
			row.createCell(4).setCellValue(user.getAirportlist());
			row.createCell(5).setCellValue(user.getRemark());
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		OutputStream outStream = response.getOutputStream();
		workbook.write(outByteStream);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=member.xls");
		outStream.write(outArray);
		outStream.flush();
		outByteStream.close();
		outStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
