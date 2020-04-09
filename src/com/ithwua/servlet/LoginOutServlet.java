package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ithwua.IService.IUserService;
import com.ithwua.ServiceImpl.UserServiceImpl;

@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);    //get和post通用写法。
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		session.removeAttribute("userBack");
		resp.sendRedirect("IndexServlet");
	}
}
