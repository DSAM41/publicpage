package publicpage;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DatabaseConnect {

	public static  Connection getConnection()  {
	Connection con=null;
	String username= "cedarepdb";
	String pass="CEDA";
//	String url="jdbc:oracle:thin:@//10.121.0.9:1521/ufisaodb";
	//Prod
//	String url="jdbc:oracle:thin:@cedadb.aot.co.th:1521/ufisaodb";
	//UAT
	String url="jdbc:oracle:thin:@10.74.26.128:1521/ufisuat";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                con = DriverManager.getConnection(url, username, pass);
                if(con !=null) {
                	System.out.println("Connection Success");
                }
            } catch (SQLException ex) {
            
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
           System.out.println("Driver not found."); 
        }
        return con;
    }
	public static Connection getConnection2() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/cedarepdb");
			conn = ds.getConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
}
