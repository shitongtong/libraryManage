package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.UserDao;
import com.model.User;

public class UserServlet extends HttpServlet{
	private UserDao userDao ;
	public UserServlet()
	{
		super();
		userDao = new UserDao();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getRequestURI().endsWith("/login")){

			doLogin(request,response);
          
        }
		else if(request.getRequestURI().endsWith("/regist"))
		{
			doRegist(request,response);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
	     if(request.getRequestURI().endsWith("/logout"))
	     {
		    doLogOut(request,response);
	     }
		 else if(request.getRequestURI().endsWith("/qualification"))
		 {
			doQualification(request,response);
		 }
		 else if(request.getRequestURI().endsWith("/appManager"))
		 {
			 doAppManager(request,response);
		 }
		 else if(request.getRequestURI().endsWith("/urlManager"))
		 {
			 doUrlManager(request,response);
		 }
	}
	
	private void doUrlManager(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  String uid = (String)request.getSession().getAttribute("userId");
			if(uid==null)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			    dispatcher.forward(request, response);
				return;
			}
			
			 User user = userDao.querySyncUrl(uid);
			 request.setAttribute("notify_url", user.getNotify_url());
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/urlManager.jsp");
	         dispatcher.forward(request, response);
	}
	
	private void doAppManager(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  String uid = (String)request.getSession().getAttribute("userId");
			if(uid==null)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			    dispatcher.forward(request, response);
				return;
			}
			
			 User user = userDao.queryAppInfo(uid);
			 request.setAttribute("app_id", user.getApp_id());
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/appManager.jsp");
	         dispatcher.forward(request, response);
	}
	
	private void doQualification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		 //查询认证状态
		  String uid = (String)request.getSession().getAttribute("userId");
			if(uid==null)
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			    dispatcher.forward(request, response);
				return;
			}
		  User user = userDao.queryQualification(uid);
		  String qualificate = user.getQualificate();
		  if(qualificate==null)
		  {
			  qualificate = "未认证";  
		  }
		  request.setAttribute("bQualificate", qualificate);
		  request.setAttribute("Qualificate_Name", user.getQualificate_Name());
		  request.setAttribute("Qualificate_Number", user.getQualificate_Number());
		  request.setAttribute("Qualificat_pic", user.getQualificat_pic());
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/qualification.jsp");
          dispatcher.forward(request, response);
	}
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  String username=request.getParameter("username");
		  String password = request.getParameter("password");
          User user = userDao.login(username,password);
          if(user!=null)
          {
        	  request.getSession().setAttribute("userId", user.getUid());
        	  request.getSession().setAttribute("name", user.getUser_name());
        	  response.sendRedirect("/app/balance");
          }
	}
	
	
	private void doRegist(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  String username=request.getParameter("username");
		  String password = request.getParameter("password");
          User user = userDao.regist(username,password);
          if(user!=null)
          {
        	  request.getSession().setAttribute("userId", user.getUid());
        	  request.getSession().setAttribute("name", user.getUser_name());
      	      response.sendRedirect("/app/balance");
          }
	}

	private void doLogOut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  request.getSession().setAttribute("userId", null);
    	  request.getSession().setAttribute("name", null);
  		  RequestDispatcher dispatcher = request.getRequestDispatcher("/index");
          dispatcher.forward(request, response);
	}
}
