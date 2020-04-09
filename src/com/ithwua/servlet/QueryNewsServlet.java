package com.ithwua.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithwua.IService.INewService;
import com.ithwua.IService.IProductService;
import com.ithwua.ServiceImpl.ProductServiceImpl;
import com.ithwua.ServiceImpl.newServiceImpl;
import com.ithwua.bean.New;
import com.ithwua.bean.Product;
@WebServlet("/QueryNewsServlet")
public class QueryNewsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        INewService newService=new newServiceImpl();

		List<New> news=newService.queryAllNews();
		for(New n:news){
			System.out.println("查询到所有的news"+n.toString());
		}
		

		req.setAttribute("newslist", news);
	}
}
