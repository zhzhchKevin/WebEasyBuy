package com.ithwua.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ithwua.IService.ICartService;
import com.ithwua.ServiceImpl.CartServiceImpl;
import com.ithwua.bean.User;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICartService cartService=new CartServiceImpl();
		String productId=req.getParameter("productId");
		
		HttpSession session=req.getSession(false);
		
		User userBack=(User) session.getAttribute("userBack");
		if(userBack!=null){
			String result="1";
			if(cartService.addCart(productId,userBack.getName())){
				result="2";
			}
			resp.getWriter().write(result);
		}else{
			resp.getWriter().write("0");
		}
		
	}
}
