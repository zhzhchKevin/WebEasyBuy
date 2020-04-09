package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.ICartService;
import com.ithwua.ServiceImpl.CartServiceImpl;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICartService cartService=new CartServiceImpl();
		//�޸Ĺ��ﳵ�Ĺ������������ݲ���String quantity,cart��Id
		String quantity=req.getParameter("quantity");
		String cartId=req.getParameter("cartId");

		System.out.println("��õ�cartidΪ"+cartId);
		
		Integer result=0;
		
		
			
			
		System.out.println("��ʼ���� Service��");
		
		if(cartService.updateCarts(cartId,quantity)){
			result=1;
		}
		resp.getWriter().write(result.toString());
	}
}
