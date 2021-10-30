package musiclist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
//1. 오라클 자동 시작 2.ojdbc6.jar  설치-이클립스 세팅 (jdk경로\jre\lib\ext\ojdbc6.jar)
	public static void main(String[] args) {
		Connection conn = null;
		try { 
			// jdbc driver 메모리  로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// db 연결
			conn = DriverManager.getConnection
			("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			System.out.println("오라클db연결성공");
			conn.close();
			System.out.println("오라클db연결해제성공");
			
			// jdbc driver 메모리  로드
			Class.forName("com.mysql.cj.jdbc.Driver");//오류가 나면 pom.xml 확인
			// db 연결
			conn = DriverManager.getConnection //mysql 설정 부분
			("jdbc:mysql://127.0.0.1:3306/multiproject", "multiuser", "multiuser1234");
			System.out.println("mysql db연결성공");
			conn.close();
			System.out.println("mysql db연결해제성공");	
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 세팅 확인하세요");
		}catch(SQLException e) {
			System.out.println("DB연결정보 확인하세요");
			e.printStackTrace();
		}finally {
			try {
			conn.close();
			}catch(SQLException e) { }
		}
	}

}
