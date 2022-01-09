<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javaex.Dao.GuestbookDao"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>

<%
List<GuestbookVo> getList = (List<GuestbookVo>)request.getAttribute("getList");	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h1>guestbook2</h1>
<body>
	<!-- 등록폼영역 -->
		<form action="/guestbook2/gbc" method="get">
			<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" values=""></td>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password" values="">
					<input type="hidden" name="action" value="add">
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<textarea name="content" cols="64" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="submit">확인</button>
				</td>
			</tr>
			</table>
		</form>	
	<br>

	<%
	for(int i = 0; i < getList.size(); i++) {
	%>
		<table border="1">
			<tr>
				<td><%= getList.get(i).getNo()%></td>
				<td><%= getList.get(i).getName() %></td>
				<td><%= getList.get(i).getRegDate() %></td>
				<td><a href="/guestbook2/gbc?action=deleteForm&no=<%= getList.get(i).getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4"><%= getList.get(i).getContent() %></td>
			</tr>
		</table>
		</br>
	<%
	}
	%>
</body>
</html>