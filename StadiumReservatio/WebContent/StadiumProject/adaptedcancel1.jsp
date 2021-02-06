<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    

<%
	//데이터베이스 연결관련 변수 선언
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String id, stadium, startdate, starttime, finishtime;

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

	try{
		// JDBC 드라이버 로드
		Class.forName(jdbc_driver);
				
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url, "root", "1234");
		
		id = request.getParameter("id");
		stadium = request.getParameter("stadium");
		startdate = request.getParameter("startdate");
		starttime = request.getParameter("starttime");
		finishtime = request.getParameter("finishtime");
		
		
		if(stadium!=null){
		
		// select 문장을 문자열 형태로 구성한다.
		String sql2 = "update reservation set id = ? where stadium = ? and startdate = ? and starttime = ? and finishtime = ?";
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, id);
		pstmt.setString(2, stadium);
		pstmt.setString(3, startdate);
		pstmt.setString(4, starttime);
		pstmt.setString(5, finishtime);
		
		out.println(id + stadium + startdate + starttime + finishtime);
		out.println("delete");
		
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		}		

	}
	catch(Exception e) {
		System.out.println(e);
	}
%>

