package com.web.store.repository.impl;

import java.sql.Connection;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;
import com.web.store.repository.OrderDao;

//本類別
//1. 新增一筆訂單到orders表格
//2. 查詢orders表格內的單筆訂單
//3. 查詢orders表格內的所有訂單

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	SessionFactory factory;
	
	private String account = null;
	int orderNo = 0;
	
	public OrderDaoImpl() {
		
	};
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Override
	public void setConnection(Connection con) {
	}

	@Override
	public void insertOrder(OrderBean ob) {
		Set<OrderItemBean> set = ob.getItems();
		for(OrderItemBean oib : set) {
			System.out.println(oib);
			System.out.println("----------------------------");
		}
		try {
			Session session = factory.getCurrentSession();
			session.save(ob);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderBean getOrder(Integer orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}

}
