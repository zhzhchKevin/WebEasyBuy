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
		//修改购物车的购买数量，传递参数String quantity,cart的Id
		String quantity=req.getParameter("quantity");
		String cartId=req.getParameter("cartId");

		System.out.println("获得的cartid为"+cartId);
		
		Integer result=0;
		
		
			
			
		System.out.println("开始调用 Service层");
		
		if(cartService.updateCarts(cartId,quantity)){
			result=1;
		}
		resp.getWriter().write(result.toString());
	}
}
