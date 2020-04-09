package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.IUserService;
import com.ithwua.ServiceImpl.UserServiceImpl;

@WebServlet("/UserNameServlet")
public class UserNameServlet extends HttpServlet {
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("userName");
        IUserService userServiceImpl=new UserServiceImpl();
        
        System.out.println("Servlet��ʼ");
        if(userServiceImpl.verifyName(userName)){
        	//�û��������ڣ�����ʹ��
        	resp.getWriter().write("1");
        	System.out.println("�û��������ڣ�����ʹ��");
        }else{
        	resp.getWriter().write("0");
        	System.out.println("�û������ڣ�������ʹ��");
        }

	}

}
