package com.jf.stock.utils;

public class Constant {
	/**
	 * 交易所列表
	 */
	public static final String[] EXCHANGEARRAY = new String[]{"sh","sz","hk","us"};
	
	public static final String EXCHANGE_SH = "sh";
	public static final String EXCHANGE_SZ = "sz";
	public static final String EXCHANGE_HK = "hk";
	public static final String EXCHANGE_US = "us";
	
	/*
	 * 与Calenda静态值对应
	 */
	public static final int MONTH_I  = 2;
	public static final int DAY_I    = 6;
	public static final int WEEK_I   = 3;
	public static final int SEASON_I = 7;
	public static final int YEAR_I   = 1;
	
	public static final String MONTH_S  = "month";
	public static final String DAY_S    = "day";
	public static final String WEEK_S   = "week";
	public static final String SEASON_S = "season";
	public static final String YEAR_S   = "year";
	
	/*
	 ***********************以下NMQ专用常量********************************
	 */
	public static final String NO_RIGHT    = "no";
	public static final String FRONT_RIGHT = "front";
	public static final String BACK_RIGHT  = "back";
	
	
	public static final String CMD_KLINE = "kline";
	public static final String CMD_MA    = "MAKline";
	public static final String CMD_CLEAR = "clear";
	public static final String CLEAR_KEY = "clearByParams";
	
	public static final String NMQ_TOPIC = "rda";
	
	public static final String MA5  = "MA5";
	public static final String MA10 = "MA10";
	public static final String MA20 = "MA20";
	public static final String MA30 = "MA30";
	
	public static final String SECONDBAR  = "SecondBar";
	public static final String DAYBAR     = "DayBar";
	public static final String FIVEDAYBAR = "FiveDayBar";
	public static final String WEEKBAR    = "WeekBar";
	public static final String MONTHBAR   = "MonthBar";
	public static final String SEASONBAR  = "QuarterBar";
	public static final String YEARBAR    = "YearBar";
	
	/*
	 * activemq 相关常量 
	 */
	public static final String DATATYPE_MARKETINFO = "marketinfo";
	public static final String TOPIC_A = "AstockTopic";
	public static final String TOPIC_HK = "HKstockTopic";
	public static final String TOPIC_US = "USstockTopic";
}
