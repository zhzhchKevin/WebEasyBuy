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
		doPost(req,resp);    //get��postͨ��д����
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IProductService productService=new ProductServiceImpl();
		String[] productIds=null;
		
		//��cookie�л�õ���5��productId
		Cookie[] cookies=req.getCookies();
		
		//���ñ�־λ���ж�COOKIES���Ƿ����PastProduct
		boolean flag=false;
		
		if(cookies!=null){
			for(Cookie c:cookies){
				if(c.getName().equals("PastProduct")){
					
					//�ҵ���PastProduct
					flag=true;

					System.out.println("��ѯ����õ�PastProduct"+c.getValue());
					String[] all=c.getValue().split("/");
					if(all.length<=5){
						productIds=all;
					}else{
						//ȡ�õ�����5����¼
						productIds=new String[5];
						for(int i=0;i<5;i++){
							productIds[i]=all[all.length-i-1];
							System.out.println("��õ�productId"+productIds[i]);
						}
					}
					
				}
			}
			
			//���ø���productIds����ѯproduct�ķ���
			if(flag){
			List<Product> pastProducts=productService.queryProductsByIds(productIds);
			req.setAttribute("pastProducts", pastProducts);
			}else{
				System.out.println("cookies�л�û�д洢pastProducts");
			}
			
		}else{
			System.out.println("cookiesΪ��");
		}
		
	}
}
