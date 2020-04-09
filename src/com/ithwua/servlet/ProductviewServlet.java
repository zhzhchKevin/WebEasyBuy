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
		
		//���ܵ��鿴��productIdʱ����Id������cookie,������ظ������ñ���
		
		
		Cookie[] cookies=req.getCookies();
		//��־λ������PastProduct������
		int flag=0;
		
		if(cookies!=null){
			for(Cookie c:cookies){
				if(c.getName().equals("PastProduct")){
					//Ĭ��û���ظ�����Ҫ���
					int flag1=0;
					//pastproduct���ڣ����������5������û��ͬ������Ʒ����ô�����׷��/productId
					String[] all=c.getValue().split("/");
						for(int i=0;i<5&&i<all.length;i++){
							if(productId.equals(all[all.length-i-1])){
								//�ڽ�5��֮�����ظ���id
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
				System.out.println("�����¼Ϊ�գ���Ҫ���´���cookie");
				//�����¼Ϊ�գ���Ҫ���´���cookie
				Cookie cookie=new Cookie("PastProduct",productId);
				resp.addCookie(cookie);
			}
		
		}else{
			System.out.println("���ʱcookieΪ��");
			Cookie cookie=new Cookie("PastProduct",productId);
			resp.addCookie(cookie);
		}
		
		
		
		
		
		Product product=productService.queryProductById(productId);
		
		
		req.setAttribute("product", product);
		req.getRequestDispatcher("product_view.jsp").forward(req, resp);
		
		
	}

}
