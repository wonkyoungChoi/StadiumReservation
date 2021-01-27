
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
<h2>로그인</h2>
<hr>
<form method="post" name="form1">
	<table border="1" cellspacing="1" cellpadding="5">
	
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" size=20></td>
		</tr>
		<tr>
			<td>팀이름</td>
			<td><input type="text" name="teamname" size=20></td>
		</tr>
		<tr>
			<td>경기장이름</td>
			<td><input type="text" name="stadium" size=20></td>
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
			<td>끝나는시간</td>
			<td><input type="text" name="finishdate" size=20></td>
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
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs1 = null;
	
	String id, teamname, stadium, startdate, finishdate, starttime;
	String reserveteam = null;
	String reserveid = null;
	String reservedate = null;
	String reservetime = null;
	String reservestadium = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

	try{
		// JDBC 드라이버 로드
		Class.forName(jdbc_driver);
				
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url, "root", "1234");
		
		// select 문장을 문자열 형태로 구성한다.
		String sql = "select id, teamname, stadium, startdate, starttime, finishdate from reservation";
		pstmt1 = conn.prepareStatement(sql);
		rs1 = pstmt1.executeQuery();
		int i=1;
		
		id = request.getParameter("id");
		teamname = request.getParameter("teamname");
		stadium = request.getParameter("stadium");
		startdate = request.getParameter("startdate");
		starttime = request.getParameter("starttime");
		finishdate = request.getParameter("finishdate");

		
		boolean check = false;

		while(rs1.next()) {
			if(id != null && teamname != null && stadium != null && startdate != null && finishdate != null && starttime != null) {
				if(teamname.equals(rs1.getString("teamname")) && stadium.equals(rs1.getString("stadium")) &&
						startdate.equals(rs1.getString("startdate"))  && starttime.equals(rs1.getString("starttime"))  
						&& finishdate.equals(rs1.getString("finishdate"))) {
				out.println("update1");
				check = true;
				reserveid = rs1.getString("id");
				reserveteam = rs1.getString("teamname");
				reservestadium = rs1.getString("stadium");
				reservedate = rs1.getString("startdate");
				reservetime = rs1.getString("starttime");
				}
			}
		}
		rs1.close();
		pstmt1.close();
		if(check==true){
			out.println(reserveteam);
			out.println(reserveid);
			out.println(id);
			String sql1 = "update reservation set id = ? where teamname = ? and startdate = ? and stadium = ? and starttime = ?";
			pstmt2 = conn.prepareStatement(sql1);
			pstmt2.setString(1, id+"/"+reserveid);
			pstmt2.setString(2, reserveteam);
			pstmt2.setString(3, reservedate);
			pstmt2.setString(4, reservestadium);
			pstmt2.setString(5, reservetime);
			pstmt2.executeUpdate();
		}

		pstmt2.close();
		conn.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
%>

