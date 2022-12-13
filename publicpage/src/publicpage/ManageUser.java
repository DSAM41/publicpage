package publicpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManageUser {

	public static List<ItemUser> getAllRecords() {
		List<ItemUser> list = new ArrayList<ItemUser>();

		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from publicpage_staff order by role,username");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemUser u = new ItemUser();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getString("role"));
				u.setStaffId(rs.getString("staffid"));
				u.setAirportlist(rs.getString("hopo"));
				u.setRemark(rs.getString("remark"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static ItemUser getRecordById(String staffid) {
		ItemUser u = null;
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from publicpage_staff where staffid=?");
			ps.setString(1, staffid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new ItemUser();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setStaffId(rs.getString("staffid"));
				u.setAirportlist(rs.getString("hopo"));
				u.setRemark(rs.getString("remark"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}

	public static int save(ItemUser u) {
		int status = 0;
		try {
			Connection con = DatabaseConnect.getConnection();
			String sql = "insert into publicpage_staff(staffid,username,password,email,role,hopo,remark) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getRole());
			ps.setString(6, u.getAirportlist());
			ps.setString(7, u.getRemark());
			status = ps.executeUpdate();
			System.out.print(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int update(ItemUser u) {
		int status = 0;
		try {
			Connection con = DatabaseConnect.getConnection();
			String sql = "update publicpage_staff set username=?,password=?,email=?,hopo=?,remark=? where staffid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getAirportlist());
			ps.setString(5, u.getRemark());
			ps.setString(6, u.getStaffId());
			status = ps.executeUpdate();
			System.out.print(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int delete(ItemUser u) {
		int status = 0;
		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from publicpage_staff where staffid=?");
			ps.setString(1, u.getStaffId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
//    public void UserConnect() {
//        DatabaseConnect con = new DatabaseConnect();
//        try {
//            connection = con.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//	   public ArrayList<ItemUser> getPersons() throws SQLException {
//		   Connection conn=DatabaseConnect.getConnection();
//	        String query = "select * from publicpage_staff";
//	        ArrayList<ItemUser> persons = new ArrayList<ItemUser>();
//	        Statement stmt = conn.createStatement();
//	        ResultSet res = stmt.executeQuery(query);
//	        while (res.next()) {
//	            ItemUser person = new ItemUser();
//	            person.setUsername(res.getString("username"));
//	            person.setPassword(res.getString("password"));
//	            person.setEmail(res.getString("email"));
//	            person.setIdUser(res.getInt("id"));
//	            persons.add(person);
//	        }
//	        return persons;
//	    }
}
