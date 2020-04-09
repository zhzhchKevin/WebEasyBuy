package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.IUserService;
import com.ithwua.ServiceImpl.UserServiceImpl;

@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        IUserService userServiceImpl=new UserServiceImpl();
        
        System.out.println("Servlet开始");
        if(userServiceImpl.verifyEmail(email)){
        	//邮箱不存在，可以使用
        	resp.getWriter().write("1");
        }else{
        	resp.getWriter().write("0");
        }

	}

}
