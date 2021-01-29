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
<h2>비밀번호 찾기</h2>
<hr>
<form method="post" name="form1">
	<table border="1" cellspacing="1" cellpadding="5">
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" size=20></td>
		</tr>
	
		<tr>
			<td>name</td>
			<td><input type="text" name="name" size=20></td>
		</tr>
		<tr>
			<td>email</td>
			<td><input type="text" name="email" size=20></td>
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
	
	String id, name, email;

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";

	try{
		// JDBC 드라이버 로드
		Class.forName(jdbc_driver);
				
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url, "root", "1234");
		
		// select 문장을 문자열 형태로 구성한다.
		String sql = "select id, name, email, pwd  from stadium";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int i=1;
		

		id = request.getParameter("id");
		name = request.getParameter("name");
		email = request.getParameter("email");

		while(rs.next()) {
			if(id != null && name != null && email != null) {
			if(id.equals(rs.getString("id")) && name.equals(rs.getString("name")) && email.equals(rs.getString("email"))) {
				out.println("true");
				out.println("pwd:" + rs.getString("pwd") + "/");
			} else {
				out.println("false");
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

