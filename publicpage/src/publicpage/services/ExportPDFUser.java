package publicpage.services;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import publicpage.ItemUser;
import publicpage.ManageUser;

@WebServlet("/ExportPDFUser")
public class ExportPDFUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportPDFUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document iText_xls_2_pdf = new Document(PageSize.A4.rotate());
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(iText_xls_2_pdf, buffer);
			iText_xls_2_pdf.open();
			PdfPTable my_table = new PdfPTable(6);
			PdfPCell table_cell;
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
			my_table.setWidthPercentage(100); // table width to 100per
			List<String> memberColumns = Arrays.asList("Username", "Password", "Email", "Role", "HOPO", "Remark");
			List<ItemUser> list = ManageUser.getAllRecords();
			for (int i = 0; i < memberColumns.size(); i++) {
				table_cell = new PdfPCell(new Phrase(memberColumns.get(i), boldFont));
				my_table.addCell(table_cell);
			}
			for (ItemUser user : list) {
				table_cell = new PdfPCell(new Phrase(user.getUsername(), normalFont));
				my_table.addCell(table_cell);
				table_cell = new PdfPCell(new Phrase(user.getPassword(), normalFont));
				my_table.addCell(table_cell);
				table_cell = new PdfPCell(new Phrase(user.getEmail() == null ? "" : user.getEmail(), normalFont));
				my_table.addCell(table_cell);
				table_cell = new PdfPCell(new Phrase(user.getRoleName(), normalFont));
				my_table.addCell(table_cell);
				table_cell = new PdfPCell(new Phrase(user.getAirportlist(), normalFont));
				my_table.addCell(table_cell);
				table_cell = new PdfPCell(new Phrase(user.getRemark() == null ? "" : user.getRemark(), normalFont));
				my_table.addCell(table_cell);
			}
			iText_xls_2_pdf.add(my_table);
			iText_xls_2_pdf.close();

			response.setContentType("application/pdf");
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=member.pdf");

			DataOutput dataOutput = new DataOutputStream(response.getOutputStream());
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			for (int i = 0; i < bytes.length; i++) {
				dataOutput.writeByte(bytes[i]);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
