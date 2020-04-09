package com.ithwua.servlet;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ithwua.IService.ICartService;
import com.ithwua.IService.IProductService;
import com.ithwua.ServiceImpl.CartServiceImpl;
import com.ithwua.ServiceImpl.ProductServiceImpl;
import com.ithwua.bean.Cart;
import com.ithwua.bean.Product;
import com.ithwua.bean.User;

@WebServlet("/cartview")
public class CartViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICartService cartService=new CartServiceImpl();
		HttpSession session=req.getSession(false);    //����Ѿ���¼���ͻ��userName
		if(session==null){
			System.out.println("û�л��session");
		}
		User userBack = (User) session.getAttribute("userBack");
		
		
		if(userBack==null){
			System.out.println("session�е�userBack�Ѿ���ʧ����ȡ����");
		}

		List<Cart> carts=cartService.queryCartsByName(userBack.getName());
		
		req.setAttribute("carts", carts);
		req.getRequestDispatcher("shopping.jsp").forward(req, resp);
	}

}
