package com.javaex.Dao;

import java.util.List;

import com.javaex.vo.GuestbookVo;

public class Test {

	public static void main(String[] args) {
		
		//Dao �׽�Ʈ��
		GuestbookDao guestbookDao= new GuestbookDao();
		List<GuestbookVo>gbList= guestbookDao.getList();
		
		
		System.out.println(gbList.toString());
	}

}
