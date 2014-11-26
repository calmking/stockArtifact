package com.jf.stock.selection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jf.hibernate.HibernateUtils;
import com.jf.stock.model.Dayline2;
import com.jf.stock.model.Macd;
import com.jf.stock.utils.Constant;
import com.jf.stock.utils.TradeDayUtils;
import com.jf.stock.utils.Utils;

/**
 * 收盘价>120日均线 && macd由绿变红
 * @author 曾建峰
 * @date 2014年10月20日
 */
public class Strategy2 {
static Logger logger = Logger.getLogger(Strategy1.class);
	
	public static void main(String[] args) {
		
		int day = 20141105;
		String filePath = "d:/myself/stock/";
		
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.getTransaction();
		FileWriter fileWriter = null;
		try {
			 fileWriter = new FileWriter(new File(filePath+"/strategy2-"+day+".txt"), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			transaction.begin();
			Query query = session.createQuery("from Dayline2 where id.date = "+day);
			List result = query.list();
			
			for (int i = 0 ; i < result.size() ; i ++ )
			{
				
				Dayline2 dayLine = (Dayline2)result.get(i);
				
				/*涨跌幅小于5%*/
				
				if (!StrategyUtils.isBottomDivergence(dayLine, session))
				{
					continue;
				}
			
				logger.info(dayLine.getId().getObj()+" isBottomDivergence true");
				
				String s = dayLine.getId().getObj();
				s=s.replaceAll(".stk", "");
				s=s.replaceAll("SH", "");
				s=s.replaceAll("SZ", "");
				fileWriter.write(s+"\r\n");
			}
			fileWriter.flush();
			fileWriter.close();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		logger.info("completely!!!!!!!!!!!!!!!");
	}
}
