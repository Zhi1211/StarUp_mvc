package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.OrderBean;
import com.web.store.repository.OrderQueryDao;

@Repository
public class OrderQueryDaoImpl implements OrderQueryDao {
	
	@Autowired
	SessionFactory factory;
	
	private String account = "";
	
	public OrderQueryDaoImpl() {
		
	}
	
	@Override
	public OrderBean getOrder(Integer orderNo) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		ob = session.get(OrderBean.class, orderNo);
		return ob;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getAllOrder() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderBean> getMemberOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean WHERE account = :account ORDER BY orderDate desc";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("account", account).getResultList();
		return list;
	}
	
	@Override
	public OrderBean getShoppingOverOrder() {
		OrderBean ob = null;
		String hql = "FROM OrderBean WHERE account = :account ORDER BY orderDate desc LIMIT 1";
		Session session = factory.getCurrentSession();
		ob = (OrderBean)session.createQuery(hql).setParameter("account", account).getSingleResult();
		return ob;
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

}
