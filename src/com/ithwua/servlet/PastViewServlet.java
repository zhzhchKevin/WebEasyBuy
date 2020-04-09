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


@WebServlet("/PastViewServlet")
public class PastViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);    //get和post通用写法。
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductService productService=new ProductServiceImpl();
		String[] productIds=null;
		
		//从cookie中获得倒数5条productId
		Cookie[] cookies=req.getCookies();
		
		//设置标志位，判断COOKIES中是否存在PastProduct
		boolean flag=false;
		
		if(cookies!=null){
			for(Cookie c:cookies){
				if(c.getName().equals("PastProduct")){
					
					//找到了PastProduct
					flag=true;

					System.out.println("查询方获得的PastProduct"+c.getValue());
					String[] all=c.getValue().split("/");
					if(all.length<=5){
						productIds=all;
					}else{
						//取得倒数的5条记录
						productIds=new String[5];
						for(int i=0;i<5;i++){
							productIds[i]=all[all.length-i-1];
							System.out.println("获得的productId"+productIds[i]);
						}
					}
					
				}
			}
			
			//调用根据productIds来查询product的方法
			if(flag){
			List<Product> pastProducts=productService.queryProductsByIds(productIds);
			req.setAttribute("pastProducts", pastProducts);
			}else{
				System.out.println("cookies中还没有存储pastProducts");
			}
			
		}else{
			System.out.println("cookies为空");
		}
		
	}
}
