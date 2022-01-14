package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.Dao.GuestbookDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class Guestbookcontroller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("add".equals(action)) {

			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");

			GuestbookDao dao = new GuestbookDao();
			GuestbookVo vo = new GuestbookVo(name, password, content);
			dao.guestbookInsert(vo);
			System.out.println(vo.toString());

			//�����̷�Ʈ
			Webutill.redirect(request, response, "/guestbook2/gbc");
			
		} else if ("deleteform".equals(action)) {

			//������
			Webutill.forward(request, response, "/WEB-INF/deleteForm.jsp");

		} else if ("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("pass");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);

			GuestbookDao dao = new GuestbookDao();
			dao.delete(vo);

			
			//�����̷�Ʈ
			Webutill.redirect(request, response, "/guestbook2/gbc");
			
		} else {//����Ʈ �� �⺻������
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> gList = dao.getList();

			request.setAttribute("guestList", gList);
			
			//������
			Webutill.forward(request, response, "/WEB-INF/addList.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}