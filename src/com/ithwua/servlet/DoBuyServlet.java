package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ithwua.IService.IBuyService;
import com.ithwua.ServiceImpl.BuyServiceImpl;
import com.ithwua.bean.User;
@WebServlet("/DoBuyServlet")
public class DoBuyServlet extends HttpServlet {
    IBuyService buyService=new BuyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             //���ݸ�ѡ������Ҫ�¶�����cartId����
		     String[] cartIds=req.getParameterValues("items");
		     HttpSession session=req.getSession(false);
		     User userBack=(User) session.getAttribute("userBack");

		     
		     System.out.println("��Servlet��ӡuserBack"+userBack.toString());
		     
		     for(String n:cartIds){
		    	 System.out.println("������Ʒѡ��"+n);
		     }
             
		     boolean result=buyService.doBuy(cartIds,userBack);
		     
		     if(result){
		    	 //�µ��ɹ�
		    	 System.out.println("�µ��ɹ�");
		     }else{
		    	 //�µ�ʧ��
		    	 System.out.println("�µ�ʧ��");
		     }
		     
		     resp.sendRedirect("cartview");

	}
          
}
