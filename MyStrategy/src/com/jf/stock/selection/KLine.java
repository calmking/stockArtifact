package com.jf.stock.selection;

public class KLine {
	private int date;
	private int time;
	private double open;
	private double close;
	private double high;
	private double low;
	private double volume;
	private int ccl;
	private double preClose;
	private double amount;
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getCcl() {
		return ccl;
	}
	public void setCcl(int ccl) {
		this.ccl = ccl;
	}
	public double getPreClose() {
		return preClose;
	}
	public void setPreClose(double preClose) {
		this.preClose = preClose;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public KLine clone(){
		KLine kLine = new KLine();
		kLine.setAmount(this.amount);
		kLine.setCcl(this.ccl);
		kLine.setClose(this.close);
		kLine.setDate(this.date);
		kLine.setHigh(this.high);
		kLine.setLow(this.low);
		kLine.setOpen(this.open);
		kLine.setPreClose(this.preClose);
		kLine.setTime(this.time);
		kLine.setVolume(this.volume);
		return kLine;
	}
}