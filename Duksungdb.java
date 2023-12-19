import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Duksungdb {
	

	   public static Connection makeConnection() {
	      String url = "jdbc:mysql://localhost:3306/Duksung";
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
	   

	public static void main(String[] args) throws SQLException {
		Connection con = makeConnection();
		try {
			String sql = new StringBuilder()
					.append("UPDATE Students SET ")
					.append("stuId=?,")
					.append("name=?,")
					.append("tel=?,")
					.append("dept=?")
					.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,2000001);
			pstmt.setString(2,"minji Kim");
			pstmt.setString(3,"000-0000-0001");
			pstmt.setString(4,"Cyber Security");
			pstmt.close();
			
			
			
			
			
			
			}catch(SQLException e) {
				e.printStackTrace();
				}
			finally {
				if(con!= null) {
					try {
						
						con.close();}
					catch(SQLException e) {}					}
				}
			}
						
		}

	
	
