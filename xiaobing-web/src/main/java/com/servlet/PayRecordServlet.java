package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.PayRecordDao;
import com.model.PayRecord;

public class PayRecordServlet extends HttpServlet{
    private PayRecordDao payRecordDao;
	public PayRecordServlet()
	{
		super();
		payRecordDao = new PayRecordDao();
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
		List<PayRecord> payRecordList = payRecordDao.QueryRayRecord(uid,null,null,null);
		request.setAttribute("payRecordList", payRecordList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/payRecord.jsp");
        dispatcher.forward(request, response);
	}
}
