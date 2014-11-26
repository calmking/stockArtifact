package com.jf.stock.utils;


public enum MAType {

	MA5 (5,DBConstant.MA5AVGPRICE, DBConstant.MA5VOLUME, DBConstant.MA5CCL, Constant.MA5),
	MA10(5,DBConstant.MA10AVGPRICE,DBConstant.MA10VOLUME,DBConstant.MA10CCL,Constant.MA10),
	MA20(5,DBConstant.MA20AVGPRICE,DBConstant.MA20VOLUME,DBConstant.MA20CCL,Constant.MA20),
	MA30(5,DBConstant.MA30AVGPRICE,DBConstant.MA30VOLUME,DBConstant.MA30CCL,Constant.MA30);
	
	public Integer count;
	public String priceFN;
	public String volumeFN;
	public String cclFN;
	public String nmqField;
	
	private MAType(int count,String priceFN,String volumeFN,String cclFN,String nmqField) {
		this.count = count;
		this.priceFN = priceFN;
		this.volumeFN = volumeFN;
		this.cclFN = cclFN;
		this.nmqField = nmqField;
	}
}
