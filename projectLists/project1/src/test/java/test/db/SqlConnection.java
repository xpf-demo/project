package test.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.SqlUtil;


public class SqlConnection {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement sta  = null;
		try {
			//驱动程序名
			/*String driver = "com.mysql.jdbc.Driver";
			// URL指向要访问的数据库名scutcs
			String url = "jdbc:mysql://127.0.0.1:3306/test";
			// MySQL配置时的用户名
			String user = "root";
			// Java连接MySQL配置时的密码
			String password = "root";
			//1.加载驱动程序
			Class.forName(driver);
			//2.连接数据库
			conn = DriverManager.getConnection(url, user, password);*/
			conn = SqlUtil.getConnection();
			if(!conn.isClosed()){
				//3.创建Statement
				sta = conn.createStatement();//执行静态的sql
				//PreparedStatement pstmt = conn.prepareStatement("") ;//执行动态的sql
				//4.执行sql语句
				ResultSet result = sta.executeQuery("select id as id,building_code as buildingCode,building_name as buildingName from t_crm_building");
				while(result.next()){
					System.out.println("id:"+result.getString(1)+"	buildingCode:"+result.getString(2)+"	buildingName:"+result.getString(3));
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("");
			e.printStackTrace();
		}finally{
			try {
				System.out.println("关闭连接，释放资源..");
				sta.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
