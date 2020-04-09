package com.ithwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.IProductService;
import com.ithwua.ServiceImpl.ProductServiceImpl;
import com.ithwua.bean.Product;
@WebServlet("/productview")
public class ProductviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductService productService=new ProductServiceImpl();
        
		String productId=req.getParameter("id");
		
		//接受到查看的productId时，将Id保存至cookie,如果有重复，则不用保存
		
		
		Cookie[] cookies=req.getCookies();
		//标志位，假设PastProduct不存在
		int flag=0;
		
		if(cookies!=null){
			for(Cookie c:cookies){
				if(c.getName().equals("PastProduct")){
					//默认没有重复，需要添加
					int flag1=0;
					//pastproduct存在，并且最近的5个里面没有同样的商品，那么在其后追加/productId
					String[] all=c.getValue().split("/");
						for(int i=0;i<5&&i<all.length;i++){
							if(productId.equals(all[all.length-i-1])){
								//在近5个之内有重复的id
								flag1=1;
							}
						}
					
					flag=1;
					
					System.out.println(c.getValue());
					
					if(flag1==0){
					Cookie cookie=new Cookie("PastProduct",c.getValue()+"/"+productId);
					resp.addCookie(cookie);
					}
				}
				
			}
		   
			
			if(flag==0){
				System.out.println("浏览记录为空，需要重新创建cookie");
				//浏览记录为空，需要重新创建cookie
				Cookie cookie=new Cookie("PastProduct",productId);
				resp.addCookie(cookie);
			}
		
		}else{
			System.out.println("添加时cookie为空");
			Cookie cookie=new Cookie("PastProduct",productId);
			resp.addCookie(cookie);
		}
		
		
		
		
		
		Product product=productService.queryProductById(productId);
		
		
		req.setAttribute("product", product);
		req.getRequestDispatcher("product_view.jsp").forward(req, resp);
		
		
	}

}
