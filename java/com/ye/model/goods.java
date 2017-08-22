package com.yexc.model;

public class goods {
	
	
    public goods(int goodsId, String goodsName, int goodPrice, int goodsNumber) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodPrice = goodPrice;
		this.goodsNumber = goodsNumber;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(int goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	private int goodsId;
    private String goodsName;
    private int goodPrice;
    private int goodsNumber;

}
