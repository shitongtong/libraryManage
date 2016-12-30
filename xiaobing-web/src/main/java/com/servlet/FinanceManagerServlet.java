package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BalanceRecordDao;
import com.dao.UserDao;
import com.model.BalanceRecord;
import com.model.User;

public class FinanceManagerServlet extends HttpServlet{
	private UserDao userDao ;
	private BalanceRecordDao balanceRecordDao;
	public FinanceManagerServlet()
	{
		super();
		userDao = new UserDao();
		balanceRecordDao = new BalanceRecordDao();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//查询支付记录
		String uid = (String)request.getSession().getAttribute("userId");
		if(uid==null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		    dispatcher.forward(request, response);
			return;
		}
		
		User userinfo = userDao.queryCard(uid);
		request.setAttribute("bankinfo", userinfo);
		List<BalanceRecord>  balanceRecordList =  balanceRecordDao.QueryBalanceRecord(uid);
		request.setAttribute("balanceRecordList", balanceRecordList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/finance.jsp");
        dispatcher.forward(request, response);
	}
}
