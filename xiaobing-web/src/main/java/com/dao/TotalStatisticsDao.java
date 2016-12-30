package com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.DayStatistics;
import com.model.TotalStatistics;

public class TotalStatisticsDao extends BaseDao{

	
	 public TotalStatistics queryStatistics(String uid)
	  {
		 
		 String sql = "select month from NMPayer_total_statistics where uid='"+uid +"'"; 
	        Statement stmt;
	        TotalStatistics totalStatistics = null;
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
			        if(rs.next())  
			        {  
			        	totalStatistics = new TotalStatistics();
			        	totalStatistics.setMonth(rs.getString(1));
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return totalStatistics;
	  }
	 
	 
	 public List<DayStatistics> queryDayStatistics(String uid,int day)
	  {
		 
		 String sql = "select day,weixin,zhifubao,daytotal from NMPayer_day_statistics where uid='"+uid +"' and day>DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"; 
		  Statement stmt;
	        List<DayStatistics> dayStatisticsList = new ArrayList<DayStatistics>();
			try {
				stmt = cnn.createStatement();
				 ResultSet rs = stmt.executeQuery(sql);  
				
			        while(rs.next())  
			        {  
			        	//PayRecord payRecord = new PayRecord();
			        	//payRecordList.add(payRecord);
			        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       
	    	
	    	return dayStatisticsList;
	  }
	
}
