package com.ithwua.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.ICartService;
import com.ithwua.ServiceImpl.CartServiceImpl;
@WebServlet("/DeleteCartServlet")
public class DeleteCartById extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICartService cartService=new CartServiceImpl();
		String cartId=req.getParameter("cartId");
		
		System.out.println("获得的cartid为"+cartId);
		
		Integer result=0;
		
		System.out.println("开始调用 Service层");
		
		if(cartService.deleteCartById(cartId)){
			result=1;
		}
		resp.getWriter().write(result.toString());
	}
}
