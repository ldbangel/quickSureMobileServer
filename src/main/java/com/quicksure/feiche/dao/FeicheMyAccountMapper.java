package com.quicksure.feiche.dao;

import java.util.List;
import java.util.Map;

import com.quicksure.feiche.entity.LudifcBaseinfor;

public interface FeicheMyAccountMapper {
	//获取待支付订单总数
	public int getMyOrdersCount1(Map<String,Object> map);
	
	//获取已支付订单总数
	public int getMyOrdersCount2(Map<String,Object> map);
	
	//获取暂存订单总数
	public int getMyOrdersCount3(Map<String,Object> map);
	
	//获取已撤销订单总数
	public int getMyOrdersCount4(Map<String,Object> map);
	
	//获取点击的tab菜单订单数据
	public List<LudifcBaseinfor> getMyOrders(Map<String,Object> map);
	
	//获取待支付前十条数据
	public List<LudifcBaseinfor> getMyOrdersTopTen1(Map<String,Object> map);
	
	//获取已支付前十条数据
	public List<LudifcBaseinfor> getMyOrdersTopTen2(Map<String,Object> map);
	
	//获取暂存前十条数据
	public List<LudifcBaseinfor> getMyOrdersTopTen3(Map<String,Object> map);
	
	//获取已撤销前十条数据
	public List<LudifcBaseinfor> getMyOrdersTopTen4(Map<String,Object> map);
	
	//继续支付时获取一条订单数据
	public LudifcBaseinfor getInsuranceByOrderNo(String orderno);
}
