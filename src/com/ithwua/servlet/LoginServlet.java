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
		doPost(req,resp);    //get��postͨ��д����
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IUserService userServiceImpl=new UserServiceImpl();
        //��ȡ�û���������
		String name=req.getParameter("userName");
        String pass=req.getParameter("passWord");
        String veryCode=req.getParameter("veryCode");
        
        HttpSession session=req.getSession();    //����session
        
        String validateCode=(String) session.getAttribute("validateCode");
        
        if(!veryCode.equals(validateCode)){
        	
        	resp.getWriter().write("<h1>��֤����������µ�¼~~~~</h1>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='login.jsp'>���ص�¼����</a>");
        }else{
		  
        
        User user=new User();
        user.setName(name);
        user.setPassword(pass);

        RequestDispatcher rd=null;
        //�õ�֮��Ҫ�����жϣ��û��������������ݿ���û��������Ƿ���ڣ�
        
        //�������¼�ɹ� ���������¼ʧ��
        User userBack=userServiceImpl.doLogin(user);
        if(userBack!=null){
            System.out.println("��ʼ���userBack��session");
        	session.setAttribute("userBack", userBack);
        	System.out.println(userBack.toString());
        	resp.sendRedirect("IndexServlet");

        }else{
        	resp.getWriter().write("<h1>�û�������������������µ�¼~~~~</h1>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='login.jsp'>���ص�¼����</a>");
        	resp.getWriter().write("<br>");
        	resp.getWriter().write("<a href='IndexServlet'>������ҳ</a>");
        }
	  }
}
}
