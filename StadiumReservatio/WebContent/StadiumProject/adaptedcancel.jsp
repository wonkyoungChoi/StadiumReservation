<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("UTF-8"); %>
    
    <html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록</title>
</head>
<body>
<div>
<h2>닉네임 변경</h2>
<hr>
<form method="post" name="form1">
	<table border="1" cellspacing="1" cellpadding="5">
	
		<tr>
			<td>경기장</td>
			<td><input type="text" name=stadium size=20></td>
		</tr>
		<tr>
			<td>시작날짜</td>
			<td><input type="text" name="startdate" size=20></td>
		</tr>
		<tr>
			<td>시작시간</td>
			<td><input type="text" name="starttime" size=20></td>
		</tr>
		<tr>
			<td>종료시간</td>
			<td><input type="text" name="finishtime" size=20></td>
		</tr>

		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="Submit" value="로그인">
				<input type="reset" name="Cancel" value="취소"></td>
		</tr>
	</table>
</form>
<hr>
</div>
    
    

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
		
		
		stadium = request.getParameter("stadium");
		startdate = request.getParameter("startdate");
		starttime = request.getParameter("starttime");
		finishtime = request.getParameter("finishtime");
		
		String sql = "select id, stadium, startdate, starttime, finishtime from reservation";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			if(stadium.equals(rs.getString("stadium")) && startdate.equals(rs.getString("startdate")) && starttime.equals(rs.getString("starttime"))
					&& finishtime.equals(rs.getString("finishtime"))) {
				out.println("id:" + rs.getString("id"));
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

