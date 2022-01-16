<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javaex.Dao.GuestbookDao"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
List<GuestbookVo> gList = (List<GuestbookVo>)request.getAttribute("guestList");	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h1>guestbook2</h1>
<body>
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
					<button type="submit">등록</button>
				</td>
			</tr>
			</table>
		</form>	
	<br>


				<c:forEach items="${getList}" var="guestVo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${guestVo.no }</td>
								<td>${guestVo.name }</td>
								<td>${guestVo.regDate }</td>
								<td><a href="/mysite/guest?action=deleteForm&no=${guestVo.no }">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${guestVo.content }</td>
							</tr>
						</table>
					</c:forEach>
</body>
</html>