package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AppDao;
import com.dao.TotalStatisticsDao;
import com.model.TotalStatistics;

public class appServlet extends HttpServlet{
	private AppDao appDao ;
	private TotalStatisticsDao totalStatisticsDao;
	public appServlet()
	{
		super();
		appDao = new AppDao();
		totalStatisticsDao = new TotalStatisticsDao();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getRequestURI().endsWith("/balance")){
			dobalance(request,response);
          
        }
		else if(request.getRequestURI().endsWith("/browser"))
		{
			dobalance(request,response);
		}
			
		
		
	}

	
	private void dobalance(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		  //查询财务状况
		String uid = (String)request.getSession().getAttribute("userId");
		if(uid==null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		    dispatcher.forward(request, response);
			return;
		}
		
		TotalStatistics totalStatistics = totalStatisticsDao.queryStatistics(uid);
		String month =  totalStatistics.getMonth();
		if(month==null)
		{
			month = "0";
		}
		request.setAttribute("month",month);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/balance.jsp");
        dispatcher.forward(request, response);
	}
	
}
