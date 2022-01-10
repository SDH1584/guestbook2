package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.Dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class Guestbookcontroller extends HttpServlet {

	public Guestbookcontroller() {
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		System.out.println(action);
		
		if( "addList".equals(action)) {
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> getList = guestbookDao.getList();
			System.out.println(getList);
			request.setAttribute("getList", getList);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		}else if ("add".equals(action)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			GuestbookVo guestbookVo = new GuestbookVo(name, content,password);
			System.out.println(guestbookVo);
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookInsert(guestbookVo);
			response.sendRedirect("/guestbook2/gbc?action=addList");
		
		}else if("deleteForm".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no);
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);

		}else if( "delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password=request.getParameter("password");
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookDelete(no, password);			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		else {
			System.out.println("error");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
