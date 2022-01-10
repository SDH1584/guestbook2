<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="com.javaex.Dao.GuestbookDao" %>
<%@ page import="java.util.List" %>
<%
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");
	
	GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
	GuestbookDao guestbookDao = new GuestbookDao();
	guestbookDao.guestbookInsert(guestbookVo);
	List<GuestbookVo> guestbookList = guestbookDao.getList();
	
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