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
             //根据复选框获得需要下订单的cartId数组
		     String[] cartIds=req.getParameterValues("items");
		     HttpSession session=req.getSession(false);
		     User userBack=(User) session.getAttribute("userBack");

		     
		     System.out.println("再Servlet打印userBack"+userBack.toString());
		     
		     for(String n:cartIds){
		    	 System.out.println("购买商品选项"+n);
		     }
             
		     boolean result=buyService.doBuy(cartIds,userBack);
		     
		     if(result){
		    	 //下单成功
		    	 System.out.println("下单成功");
		     }else{
		    	 //下单失败
		    	 System.out.println("下单失败");
		     }
		     
		     resp.sendRedirect("cartview");

	}
          
}
