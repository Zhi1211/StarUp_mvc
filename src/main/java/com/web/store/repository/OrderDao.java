package com.web.store.repository;

import java.sql.Connection;

import com.web.store.model.OrderBean;

public interface OrderDao {
	
	void setConnection(Connection con);
	
	void insertOrder(OrderBean ob);
	
	OrderBean getOrder(Integer orderNo);
}
