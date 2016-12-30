package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.MessageDao;
import com.model.Message;



public class MessageServlet extends HttpServlet{
	private MessageDao messageDao ;
	public MessageServlet()
	{
		super();
		messageDao = new MessageDao();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
	     if(request.getRequestURI().endsWith("/message"))
	     {
	    	 String uid = (String)request.getSession().getAttribute("userId");
	 		if(uid==null)
	 		{
	 			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	 		    dispatcher.forward(request, response);
	 			return;
	 		}
	    	List<Message> messageList = messageDao.QueryMessage(uid);
	    	request.setAttribute("messageList", messageList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message.jsp");
	        dispatcher.forward(request, response);
	     }
	}
}
