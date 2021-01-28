<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    

<%
	//데이터베이스 연결관련 변수 선언
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String id, pwd, nick;

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

	try{
		// JDBC 드라이버 로드
		Class.forName(jdbc_driver);
				
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url, "root", "1234");
		
		// select 문장을 문자열 형태로 구성한다.
		String sql = "select id, nick from stadium";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int i=1;
		

		id = request.getParameter("id");;

		while(rs.next()) {
			if(id != null) {
			if(id.equals(rs.getString("id"))) {
				out.println("nick:" + rs.getString("nick") + "/");
				out.println("id:" + rs.getString("id") + "/");
				}
			}
		}
		rs.close();
		pstmt.close();
		conn.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
%>

