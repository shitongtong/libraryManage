package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//是否登录过
		Cookie cookies[] = request.getCookies();
		String userId = null;
		String name = null;
		 if(cookies!=null&&cookies.length>0){ 
			 for(int i=0;i<cookies.length;i++){  
				 Cookie cookie=cookies[i];  
				 if("userId".equals(cookie.getName())){    
					 userId=cookie.getValue();    
					 request.setAttribute("userId", userId);
				 }
				 else if("name".equals(cookie.getName()))
				 {
					 request.setAttribute("name", name);
				 }
			 }
			 
		 }
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
		
		
	}
}
