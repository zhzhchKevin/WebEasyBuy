package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ithwua.IService.IUserService;
import com.ithwua.ServiceImpl.UserServiceImpl;
import com.ithwua.bean.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);    //get和post通用写法。
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IUserService userServiceImpl=new UserServiceImpl();
        //获取用户名和密码
		String name=req.getParameter("userName");
        String pass=req.getParameter("passWord");
        String veryCode=req.getParameter("veryCode");
        
        HttpSession session=req.getSession();    //创建session
        
        String validateCode=(String) session.getAttribute("validateCode");
        
        if(!veryCode.equals(validateCode)){
        	
        	resp.getWriter().write("<h1>验证码错误，请重新登录~~~~</h1>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='login.jsp'>返回登录界面</a>");
        }else{
		  
        
        User user=new User();
        user.setName(name);
        user.setPassword(pass);

        RequestDispatcher rd=null;
        //得到之后要进行判断，用户名和密码在数据库的用户表里面是否存在？
        
        //存在则登录成功 不存在则登录失败
        User userBack=userServiceImpl.doLogin(user);
        if(userBack!=null){
            System.out.println("开始添加userBack至session");
        	session.setAttribute("userBack", userBack);
        	System.out.println(userBack.toString());
        	resp.sendRedirect("IndexServlet");

        }else{
        	resp.getWriter().write("<h1>用户名或者密码错误，请重新登录~~~~</h1>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='login.jsp'>返回登录界面</a>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='IndexServlet'>返回首页</a>");
        }
	  }
}
}
