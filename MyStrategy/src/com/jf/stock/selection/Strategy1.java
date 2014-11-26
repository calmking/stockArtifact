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
import com.jf.stock.utils.SendMailUtil;
import com.jf.stock.utils.TradeDayUtils;
import com.jf.stock.utils.Utils;

/**
 * 收盘价>120日均线 && macd由绿变红
 * 
 * @author 曾建峰
 * @date 2014年10月20日
 */
public class Strategy1 {
	static Logger logger = Logger.getLogger(Strategy1.class);

	public static void main(String[] args) {

		int day = Utils.getNowDay();
		String filePath = args[0];

		Session session = HibernateUtils.getSession();
		Transaction transaction = session.getTransaction();
		FileWriter fileWriter = null;
		StringBuffer sb = new StringBuffer();
		File file = new File(filePath + "/strategy1-" + day + ".txt");
		try {
			fileWriter = new FileWriter(file, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			transaction.begin();
			Query query = session.createQuery("from Dayline2 where id.date = "
					+ day);
			List result = query.list();
			transaction.commit();
			for (int i = 0; i < result.size(); i++) {
				try {
					logger.info("begin process " + (i + 1));
					Dayline2 dayLine = (Dayline2) result.get(i);

					/* 涨跌幅小于5% */

					if (StrategyUtils.ZDFGreater(dayLine, 0.05)) {
						logger.info(dayLine.getId().getObj()
								+ " zdf less then 5% false");
						continue;
					}
					logger.info(dayLine.getId().getObj()
							+ " zdf less then 5% true");
					/* 当日价格大于120日均线 */
					if (!StrategyUtils.isGreaterThen120(dayLine, session)) {
						logger.info(dayLine.getId().getObj()
								+ " isGreaterThen120 false");
						continue;
					}
					logger.info(dayLine.getId().getObj()
							+ " isGreaterThen120 true");
					/* 周线刚站上年线，成交量增加50% */
					// if(!isWeekLineStandOnYearLine(dayLine,session))
					// {
					// logger.info(dayLine.getId().getObj()+" isWeekLineStandOnYearLine false");
					// continue;
					// }
					// logger.info(dayLine.getId().getObj()+" isWeekLineStandOnYearLine true");
					/* MACD由绿变红 */
					if (!StrategyUtils.isMACDChangeToRed(dayLine, session)) {
						logger.info(dayLine.getId().getObj()
								+ " isMACDChangeToRed false");
						continue;
					}
					logger.info(dayLine.getId().getObj()
							+ " isMACDChangeToRed true");

					String s = dayLine.getId().getObj();
					s = s.replaceAll(".stk", "");
					s = s.replaceAll("SH", "");
					s = s.replaceAll("SZ", "");
					fileWriter.write(s + "\r\n");
					sb.append(s + "\r\n");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			fileWriter.flush();
			fileWriter.close();
			SendMailUtil themail = new SendMailUtil("email.cindasc.com");
			themail.sendMail("smtp.163.com", "15021080090@163.com",
					"15021080090@163.com", "loveshanshan", "974030275@qq.com",
					day + "-macd-stock", file);

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

	}

}
