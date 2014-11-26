package com.jf.stock.utils;


public enum DataType {
	
	MONTH_NO_RIGHT    (Constant.MONTH_I,Constant.MONTH_S,Constant.MONTHBAR, Constant.NO_RIGHT,    DBConstant.MONTH_BAR,   DBConstant.MONTH_MA),
	MONTH_FRONT_RIGHT (Constant.MONTH_I,Constant.MONTH_S, Constant.MONTHBAR, Constant.FRONT_RIGHT, DBConstant.MONTH_BAR_V, DBConstant.MONTH_MA_V),
	MONTH_BACK_RIGHT  (Constant.MONTH_I,Constant.MONTH_S, Constant.MONTHBAR, Constant.BACK_RIGHT,  DBConstant.MONTH_BAR_B, DBConstant.MONTH_MA_B),
	DAY_NO_RIGHT      (Constant.DAY_I,Constant.DAY_S,   Constant.DAYBAR,   Constant.NO_RIGHT,    DBConstant.DAY_BAR,     DBConstant.DAY_MA),
	DAY_FRONT_RIGHT   (Constant.DAY_I,Constant.DAY_S,   Constant.DAYBAR,   Constant.FRONT_RIGHT, DBConstant.DAY_BAR_V,   DBConstant.DAY_MA_V),
	DAY_BACK_RIGHT    (Constant.DAY_I,Constant.DAY_S,   Constant.DAYBAR,   Constant.BACK_RIGHT,  DBConstant.DAY_BAR_B,   DBConstant.DAY_MA_B),
	WEEK_NO_RIGHT     (Constant.WEEK_I,Constant.WEEK_S,  Constant.WEEKBAR,  Constant.NO_RIGHT,    DBConstant.WEEK_BAR,    DBConstant.WEEK_MA),
	WEEK_FRONT_RIGHT  (Constant.WEEK_I,Constant.WEEK_S,  Constant.WEEKBAR,  Constant.FRONT_RIGHT, DBConstant.WEEK_BAR_V,  DBConstant.WEEK_MA_V),
	WEEK_BACK_RIGHT   (Constant.WEEK_I,Constant.WEEK_S , Constant.WEEKBAR,  Constant.BACK_RIGHT,  DBConstant.WEEK_BAR_B,  DBConstant.WEEK_MA_B),
	SEASON_NO_RIGHT   (Constant.SEASON_I,Constant.SEASON_S,Constant.SEASONBAR,Constant.NO_RIGHT,    DBConstant.SEASON_BAR,  DBConstant.SEASON_MA),
	SEASON_FRONT_RIGHT(Constant.SEASON_I,Constant.SEASON_S,Constant.SEASONBAR,Constant.FRONT_RIGHT, DBConstant.SEASON_BAR_V,DBConstant.SEASON_MA_V),
	SEASON_BACK_RIGHT (Constant.SEASON_I,Constant.SEASON_S,Constant.SEASONBAR,Constant.BACK_RIGHT,  DBConstant.SEASON_BAR_B,DBConstant.SEASON_MA_B),
	YEAR_NO_RIGHT     (Constant.YEAR_I, Constant.YEAR_S, Constant.YEARBAR,  Constant.NO_RIGHT,    DBConstant.YEAR_BAR,    DBConstant.YEAR_MA),
	YEAR_FRONT_RIGHT  (Constant.YEAR_I, Constant.YEAR_S, Constant.YEARBAR,  Constant.FRONT_RIGHT, DBConstant.YEAR_BAR_V,  DBConstant.YEAR_MA_V),
	YEAR_BACK_RIGHT   (Constant.YEAR_I, Constant.YEAR_S, Constant.YEARBAR,  Constant.BACK_RIGHT,  DBConstant.YEAR_BAR_B,  DBConstant.YEAR_MA_B);
	
	public int    calendarField;
	public String period;
	public String nmqField;
	public String fqType;
	public String kLineTN;
	public String maLineTN;
	
	private DataType(int calendarField,String period,String nmqField,String fqType,String tableName,String maLineTN){
		this.calendarField = calendarField;
		this.period   = period;
		this.nmqField      = nmqField;
		this.fqType  = fqType;
		this.kLineTN = tableName;
		this.maLineTN = maLineTN;
	}
	
}
