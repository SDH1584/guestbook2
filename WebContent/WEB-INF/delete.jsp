<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.Dao.GuestbookDao" %>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");
	GuestbookDao guestbookDao = new GuestbookDao();
	guestbookDao.guestbookDelete(no, password);
	response.sendRedirect("./addList.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>