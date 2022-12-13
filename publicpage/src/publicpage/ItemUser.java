package publicpage;

public class ItemUser {
	
	    private String username;
	    private String password;
	    private String email;
	    private String role;
	    private String staffId;
	    private String roleName;
	    private String airportlist;
	    private String remark;
	    
	    public String getStaffId() {
			return staffId;
		}
		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
			this.setRoleName(role);
		}
		private int idUser;
	    
		public int getIdUser() {
			return idUser;
		}
		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String role) {
			if(this.role.equals("admin")) {
				this.roleName = "Admin";
			}else if(this.role.equals("sf")) {
				this.roleName = "Staff";
			}else if(this.role.equals("qa")) {
				this.roleName = "Qatar";
			}
		}
		public String getAirportlist() {
			return airportlist;
		}
		public void setAirportlist(String airportlist) {
			this.airportlist = airportlist;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
}
