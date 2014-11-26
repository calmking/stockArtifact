package com.jf.stock.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	/**
	 * 是否同一时间�?
	 * @param d1 比较的日�?
	 * @param d2 比较的日�?
	 * @param field demo：Calendar.MONTH
	 * @return
	 * @throws ParseException 
	 */
	public static boolean isSameTimeRange(int d1,int d2,int field) throws ParseException
	{
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		 Calendar c1 = Calendar.getInstance();
		 Calendar c2 = Calendar.getInstance();
		 c1.setTime(format.parse(d1+""));
		 c2.setTime(format.parse(d2+""));
		 return isSameTimeRange(c1,c2,field);
	}
	
	public static boolean isSameTimeRange(Calendar c1,Calendar c2,int field)
	{
		 if (field==Constant.MONTH_I)
		 {
			 c1.set(Calendar.DAY_OF_YEAR, c1.get(Calendar.DAY_OF_MONTH)-1);
			 c2.set(Calendar.DAY_OF_YEAR, c2.get(Calendar.DAY_OF_MONTH)-1);
		 }
		 
		 if (field==Constant.SEASON_I)
		 {
			 int m1 = c1.get(Calendar.MONTH)+1;
			 int m2 = c2.get(Calendar.MONTH)+1;
			 
			 int s1 = getSeason(m1);
			 int s2 = getSeason(m2);
			 
			 return s1==s2;
			 
		 }
		 
		 if (c1.get(field) != c2.get(field))
		 {
			 return false;
		 }
		 else
		 {
			 return true;
		 }
	}
	
	/**
	 * 返回当前是第几季 
	 * @param month �?1�?�?
	 * @return
	 */
	public static int getSeason(int month)
	{
		if (month>=1 && month<=3)
		{
			return 1;
		}
		else if (month >=4 && month <= 6)
		{
			return 2;
		}
		else if (month >= 7 && month <=9 )
		{
			return 3;
		}
		else
		{
			return 4;
		}
			
	}
	
   public static int getNowDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(format.format(new Date()));
    }
   
   public static int getInt(Object obj)
   {
	   return Integer.valueOf(obj+"");
   }
   
   public static float getFloat(Object obj)
   {
	   return Float.valueOf(obj+"");
   }
   
   public static String getString(Object obj)
   {
	   return obj+"";
   }
   
   public static double getDouble(Object obj)
   {
	   return Double.valueOf(obj+"");
   }
   
   public static long getLong(Object obj)
   {
	   return Long.valueOf(obj+"");
   }
   
	public static void main(String[] args) {
	}
	
}
