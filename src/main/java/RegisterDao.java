import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class RegisterDao {

	private String dburl="jdbc:mysql://localhost:3306/userdb";
	private String dbuname="root";
	private String dbpassword = "mysql";
	private String dbdriver= "com.mysql.jdbc.Driver";
	public void loadDriver(String dbDriver) {
	
		try {
			Class.forName(dbDriver);
			} catch (ClassNotFoundException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Connection getConnection() {
		
		Connection con= null;
		try {
		DriverManager.getConnection(dburl,dbuname,dbpassword);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public String insert(Member member) {
		
		loadDriver(dbdriver);
		Connection con=getConnection();
		System.out.println("connection is" +con);
		String result="Data entered succesfully";
		String sql="insert into user.dbmember values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, member.getUname());
			ps.setString(1, member.getPassword());
			ps.setString(1, member.getEmail());
			ps.setString(1, member.getPhone());
			ps.setString(1, member.getDirection());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			result= "Data not entered";
		}
		return result;
	}
}