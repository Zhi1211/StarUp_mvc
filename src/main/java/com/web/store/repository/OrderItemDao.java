package com.web.store.repository;

import java.sql.Connection;

import com.web.store.model.OrderItemBean;

public interface OrderItemDao {
	
	// 由OrderItemBean取得商品價格(eProduct#Price)。
	
	void setConnection(Connection con);
	
	double findItemAmount(OrderItemBean oib);
	
	int updateProductStock(OrderItemBean oib);
}
