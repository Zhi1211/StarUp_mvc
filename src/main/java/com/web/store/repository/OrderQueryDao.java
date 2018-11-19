package com.web.store.repository;

import java.util.List;

import com.web.store.model.OrderBean;

public interface OrderQueryDao {
	
	OrderBean getOrder(Integer orderNo);
	
	List<OrderBean> getAllOrder();
	
	List<OrderBean> getMemberOrders();
	
	OrderBean getShoppingOverOrder();
	
	String getAccount();
	
	void setAccount(String account);
	
}
