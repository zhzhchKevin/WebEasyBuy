package com.ithwua.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	IUserService userServiceImpl=new UserServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName=req.getParameter("userName");
        String passWord=req.getParameter("passWord");   
        String sex=req.getParameter("sex");//���0����1
        
        //��ó��������ַ���
        String birthday=req.getParameter("birthday");
		

        String identity=req.getParameter("identity");
        String email=req.getParameter("email");
        String mobile=req.getParameter("mobile");
        String address=req.getParameter("address");
        String veryCode=req.getParameter("veryCode");
        HttpSession session=req.getSession();
        String validateCode=(String) session.getAttribute("validateCode");

        if(!veryCode.equals(validateCode)){
      	resp.getWriter().write("<h1>��֤�����������ע��~~~~</h1>");
      	resp.getWriter().write("<br>");
      	resp.getWriter().write("<a href='register.jsp'>����ע�����</a>");
      }
        
 
        User user=new User();
        user.setName(userName);
        user.setPassword(passWord);
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setIdentityCode(identity);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAddress(address);
        
        System.out.println(user.toString());
        
        
        RequestDispatcher rd=null;
        if(userServiceImpl.register(user)){
        	resp.sendRedirect("reg_result.jsp");
        }else{
        	resp.getWriter().write("ע����Ϣ����������ע��");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='IndexServlet'>������ҳ</a>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='register.jsp'>����ע�����</a>");
        }
	}
}
