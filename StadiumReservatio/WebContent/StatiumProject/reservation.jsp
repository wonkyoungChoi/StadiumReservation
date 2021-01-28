<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
    
    <% request.setCharacterEncoding("utf-8"); %>
    
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록</title>
</head>
<body>
<div>
<h2>예약</h2>
<hr>
<form method="post" name="form1">
	<table border="1" cellspacing="1" cellpadding="5">
	
		<tr>
			<td>닉네임</td>
			<td><input type="text" name="nick" size=20></td>
		</tr>
		<tr>
			<td>팀이름</td>
			<td><input type="text" name="teamname" size=20></td>
		</tr>
		<tr>
			<td>예약구장</td>
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
			<td>종료날짜</td>
			<td><input type="text" name="finishdate" size=20></td>
		</tr>
		<tr>
			<td>종료시간</td>
			<td><input type="text" name="finishtime" size=20></td>
		</tr>
		<tr>
			<td>실력</td>
			<td><input type="text" name="ability" size=20></td>
		</tr>
		<tr>
			<td>인원</td>
			<td><input type="text" name="number" size=20></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="Submit" value="등록">
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

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";
	
	try{
		// JDBC 드라이버 로드
		Class.forName(jdbc_driver);
		
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url, "root", "1234");
		
		//Connection 클래스의 인스턴스로부터 SQL문 작성을 위한 Statement 준비
		String sql = "insert into reservation values(?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("nick"));
		pstmt.setString(2, request.getParameter("teamname"));
		pstmt.setString(3, request.getParameter("stadium"));
		pstmt.setString(4, request.getParameter("startdate"));
		pstmt.setString(5, request.getParameter("starttime"));
		pstmt.setString(6, request.getParameter("finishdate"));
		pstmt.setString(7, request.getParameter("finishtime"));
		pstmt.setString(8, request.getParameter("ability"));
		pstmt.setString(9, request.getParameter("number"));
		pstmt.setString(10, request.getParameter("id"));

						
		//id 값을 입력한 경우 sql 문장을 수행.
		if(request.getParameter("nick") != null) {
			pstmt.executeUpdate();
		}


	} catch (Exception e) {
		e.printStackTrace();
	}
%>


<p>
# 등록 목록<p>
<%
	try{
		// select 문장을 문자열 형태로 구성한다.
		String sql = "select nick, teamname, stadium, startdate, starttime, finishdate, finishtime, ability, number from reservation";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int i=1;

		while(rs.next()) {
			out.println("nick"+ " : " + rs.getString("nick")+" , teamname"+ ":"+ rs.getString("teamname")+ " , stadium"+ ":"+ rs.getString("stadium")+
					 " , startdate"+ ":"+ rs.getString("startdate")+ " , starttime"+ ":"+ rs.getString("starttime") +
					 " , finishdate"+ ":"+ rs.getString("finishdate")+ " , finishtime"+ ":"+ rs.getString("finishtime")+
					 " , ability"+ ":"+ rs.getString("ability")+ " , number"+ ":"+ rs.getString("number")+ "<BR>");
		}
		rs.close();
		pstmt.close();
		conn.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
%>
</body>
</html>
    