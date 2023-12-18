import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardSelectedExample {
   public static Connection makeConnection() {
      String url = "jdbc:mysql://localhost:3307/board";
      String id = "root";
      String password = "1234";
      Connection con = null;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("드라이버 적재 성공");
         con = DriverManager.getConnection(url, id, password);
         System.out.println("데이터베이스 연결 성공");
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버를 찾을 수 없습니다.");
      } catch (SQLException e) {
         System.out.println("연결에 실패했습니다.");
      }
      return con;
   }
   


	public static void main(String[] args) {
		Connection con = makeConnection();
		try {
			String sql = "" +"SELECT userid, username, userpassword, userage, useremail " + "FROM users " + "WHERE userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);	
			
			pstmt.setString(1,"winter");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				
				Users user = new Users();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt(4));
				user.setUserEmail(rs.getString(5));
				System.out.println(user);
			}else {
				System.out.println("사용자 아이디 존재 안함");}
				
		
			
			rs.close();
			pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();}
			finally {
				if(con!= null) {
					try {
						con.close();}
					catch(SQLException e) {}					}
				}
			}
						
		

	}

