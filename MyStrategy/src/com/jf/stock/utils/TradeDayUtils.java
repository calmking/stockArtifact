package com.jf.stock.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class TradeDayUtils {
	static Logger logger = Logger.getLogger(TradeDayUtils.class);
	private static List<String> holidayList = null;
	/**
	 * 初始化节假日 
	 */
	public synchronized static void initHolidayList()
	{
		if(holidayList!=null)
			return;
		StringBuffer sb = new StringBuffer();
		try{
			String path = TradeDayUtils.class.getClassLoader().getResource("holidays.txt").getPath().substring(1);
			BufferedReader input = new BufferedReader(new FileReader(path));
			String s = "";
			while((s = input.readLine())!=null){
				sb.append(s);
			}
			input.close();
			logger.info("<<加载法定假借日成功，节假日为："+sb.toString());
		}catch(Exception e)
		{
			logger.error("<<未找到法定节假日配置文件holidays.txt");
			e.printStackTrace();
		}
		holidayList = new ArrayList<String>();
		String[] array = sb.toString().split(",");
		for(int i = 0 ; i < array.length&&array[i].length()!=0 ; i++)
		{
			holidayList.add(array[i]);
		}
	}
	/**
	 * 获取节假日
	 * @return
	 */
	public static List<String> getHolidayList(){
		if(holidayList  == null)
			initHolidayList();
		return holidayList;
	}
	/**
	 * 是否是交易日
	 * @param day 格式yyyyMMdd
	 * @return
	 */
	public static boolean isTradeDay(String day)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return isTradeDay(format.parse(day));
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("",e);
			return false;
		}
	}
	
	public static boolean isTradeDay(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isTradeDay(calendar);
	}
	public static boolean isTradeDay(Calendar calendar)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if( dayOfWeek==Calendar.SUNDAY || dayOfWeek==Calendar.SATURDAY)
		{
			return false;
		}
		else
		{
			//法定节假日
			if(getHolidayList().contains(format.format(calendar.getTime())))
			{
				return false;
			}
			return true;
		}
	}
	/**
	 * 获取上一个交易日
	 * @param day 日期 yyyyMMdd
	 * @return
	 */
	public static String getPreTradeDay(String day)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(day));
			while(true)
			{
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
				if(isTradeDay(calendar))
					break;
			}
			return format.format(calendar.getTime());
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("",e);
			return null;
		}
	}
	
	public static String getPreTradeDay()
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			while(true)
			{
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
				if(isTradeDay(calendar))
					break;
			}
			return format.format(calendar.getTime());
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("",e);
			return null;
		}
	}
	
	/**
	 * 获取下一个交易日
	 * @param day 日期 yyyyMMdd
	 * @return
	 */
	public static String getNextTradeDay(String day)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(day));
			while(true)
			{
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
				if(isTradeDay(calendar))
					break;
			}
			return format.format(calendar.getTime());
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("",e);
			return null;
		}
	}
	
	public static String getNextTradeDay()
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			while(true)
			{
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
				if(isTradeDay(calendar))
					break;
			}
			return format.format(calendar.getTime());
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("",e);
			return null;
		}
	}
	
	
	/**
	 * 获取该股票当天开盘时间,返回格式yyyyMMdd hh:mm:ss
	 * @param symbol
	 * @param day 日期 格式yyyyMMdd
	 * @return
	 */
	public static String getOpenTime(String symbol,String day)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(day));
			
			if(symbol.contains("SH") || symbol.contains("SZ"))
			{
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 30);
			}
			
			if(symbol.contains("SR") || symbol.contains("cu") || symbol.contains("m"))
			{
				calendar.set(Calendar.HOUR_OF_DAY, 9);
			}
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			return format1.format(calendar.getTime());
		}catch(Exception e)
		{
			logger.error("",e);
			return null;
		}
	}
	
	/**
	 * 获取endDay之前n个交易日数组，如果endDay为交易日，那么返回结果包括它
	 * @param endDay 格式yyyyMMdd
	 * @param n
	 * @return
	 */
	public static String[] getPreNTradeDays(String endDay,int n)
	{
		String[] days = new String[n];
		int i = n - 1;
		while( i >= 0)
		{
			if( i == (n-1) )
			{
				if(isTradeDay(endDay))
				{
					days[i] = endDay;
				}
				else
				{
					days[i] = getPreTradeDay(endDay);
				}
			}
			else
			{
				days[i] = getPreTradeDay(days[i+1]);
			}
			i--;
		}
		
		return days;
	}
	
	/**
	 * 获取今日之前n个交易日数组，如果今日为交易日，那么返回结果包括它
	 * @param n
	 * @return
	 */
	public static String[] getPreNTradeDays(int n)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String now = format.format(new Date());
		String[] days = new String[n];
		int i = n - 1;
		while( i >= 0)
		{
			if( i == (n-1) )
			{
				if(isTradeDay(now))
				{
					days[i] = now;
				}
				else
				{
					days[i] = getPreTradeDay(now);
				}
			}
			else
			{
				days[i] = getPreTradeDay(days[i+1]);
			}
			i--;
		}
		
		return days;
	}
	
	/**
	 * 获取sday至eday之间的所有交易日
	 * @param sday 格式yyyyMMdd
	 * @param eday 格式yyyyMMdd
	 * @throws ParseException 
	 */
	public static List<String> getTradeDays(String sday,String eday) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(format.parse(sday));
		Calendar ecalendar = Calendar.getInstance();
		ecalendar.setTime(format.parse(eday));
		
		return getTradeDays(scalendar,ecalendar);
		
	}
	/**
	 * 获取scalendar至ecalendar之间的所有交易日
	 * @param scalendar 
	 * @param ecalendar 
	 * @throws ParseException 
	 */
	public static List<String> getTradeDays(Calendar scalendar,Calendar ecalendar) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		List<String> list = new ArrayList<String>();
		while(scalendar.compareTo(ecalendar) <= 0 )
		{
			if(isTradeDay(scalendar))
			{
				list.add(format.format(scalendar.getTime()));
			}
			scalendar.set(Calendar.DAY_OF_MONTH, scalendar.get(Calendar.DAY_OF_MONTH)+1);
		}
		
		return list;
		
	}
	
	public static void main(String[] args) throws ParseException {
		
		for(String s: getTradeDays("20131212","20140101"))
		{
			System.out.println(s);
		}
	}
}
