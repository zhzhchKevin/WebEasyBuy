package com.ithwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ithwua.IService.IProductService;
import com.ithwua.ServiceImpl.ProductServiceImpl;
import com.ithwua.bean.Product;

import sun.org.mozilla.javascript.internal.json.JsonParser;

@WebServlet("/queryproduct")
public class QueryProductsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IProductService productService=new ProductServiceImpl();
        int pageCount=productService.getPageCount();
        
		String pageIndex=req.getParameter("nowPage");
		
		
		int nowPage=1;
		if(pageIndex!=null){
			nowPage=Integer.parseInt(pageIndex);
		}
		
		List<Product> products=productService.queryProducts(nowPage);
		
		req.setAttribute("products", products);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pageCount", pageCount);

	}
       
}
