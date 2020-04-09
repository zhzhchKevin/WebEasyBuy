package com.ithwua.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/cartview"})
public class DoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		HttpSession session=req.getSession(false);
		
		if(session.getAttribute("userBack")!=null){
		    chain.doFilter(req,resp);
		}else {
			resp.getWriter().write("����δ��¼���޷�ִ�иò���             ");
			resp.getWriter().write("<a href='login.jsp'>�����¼</a>      ");
			resp.getWriter().write("<a href='IndexServlet'>���������ҳ</a>");
		}
	}

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
