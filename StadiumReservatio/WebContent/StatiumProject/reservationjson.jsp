<%@page import= "java.net.URLEncoder" %>
<%@page import= "java.net.URLDecoder" %>
<%@ page import="org.json.simple.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>


<%
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=Asia/Seoul";
	String sql = "select nick, teamname, stadium, startdate, starttime, finishdate, finishtime, ability, number, id from reservation";
	Class.forName(jdbc_driver);
	Connection conn = DriverManager.getConnection(DB_URL, "root", "1234");
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
	JSONArray arr = new JSONArray();
	while(rs.next())
		{
			String nick = URLEncoder.encode(rs.getString("nick"), "UTF-8");
			String teamname = URLEncoder.encode(rs.getString("teamname"), "UTF-8");
			String stadium = URLEncoder.encode(rs.getString("stadium"), "UTF-8");
			String startdate = URLEncoder.encode(rs.getString("startdate"), "UTF-8");
			String starttime = URLEncoder.encode(rs.getString("starttime"), "UTF-8");
			String finishdate = URLEncoder.encode(rs.getString("finishdate"), "UTF-8");
			String finishtime = URLEncoder.encode(rs.getString("finishtime"), "UTF-8");
			String ability = URLEncoder.encode(rs.getString("ability"), "UTF-8");
			String number = URLEncoder.encode(rs.getString("number"), "UTF-8");
			String id = URLEncoder.encode(rs.getString("id"), "UTF-8");
			
			String nick1 = URLDecoder.decode(nick, "UTF-8");
			String teamname1 = URLDecoder.decode(teamname, "UTF-8");
			String stadium1 = URLDecoder.decode(stadium, "UTF-8");
			String startdate1 = URLDecoder.decode(startdate, "UTF-8");
			String starttime1 = URLDecoder.decode(starttime, "UTF-8");
			String finishdate1 = URLDecoder.decode(finishdate, "UTF-8");
			String finishtime1 = URLDecoder.decode(finishtime, "UTF-8");
			String ability1 = URLDecoder.decode(ability, "UTF-8");
			String number1 = URLDecoder.decode(number, "UTF-8");
			String id1 = URLDecoder.decode(id, "UTF-8");
			 
			JSONObject obj = new JSONObject();
			obj.put("nick", nick1);
			obj.put("teamname", teamname1);
			obj.put("stadium", stadium1);
			obj.put("startdate", startdate1);
			obj.put("starttime", starttime1);
			obj.put("finishdate", finishdate1);
			obj.put("finishtime", finishtime1);
			obj.put("ability", ability1);
			obj.put("number", number1);
			obj.put("id", id1);
			if(obj != null)
				arr.add(obj);
		}
	out.print(arr);
	

%>