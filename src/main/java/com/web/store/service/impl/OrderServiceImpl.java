package com.web.store.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;
import com.web.store.repository.OrderDao;
import com.web.store.repository.OrderItemDao;
import com.web.store.repository.OrderQueryDao;
import com.web.store.repository.UserDao;
import com.web.store.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserDao udao;
	@Autowired
	private OrderDao odao;
	@Autowired
	private OrderItemDao oidao;
	@Autowired
	private OrderQueryDao oqdao;
	
	public OrderServiceImpl() {
		
	}
	
	// 這是一個交易
	@Override
	public void processOrder(OrderBean ob) {
		// 檢查所有訂單明細所訂購的商品之庫存數量是否足夠
		checkStock(ob);
		// 儲存訂單
		odao.insertOrder(ob);
		// 檢查未付款餘額是否超過限額，並更新未付款餘額
		udao.updateUnpaidOrderAmount(ob);
	}
	
	private void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getItems();
		for(OrderItemBean oib : items) {
			oidao.updateProductStock(oib);
		}
	}
	
	public OrderDao getOrderDao() {
		return odao;
	}
	
	public void setOrderDao(OrderDao odao) {
		this.odao = odao;
	}
	
	@Override
	public OrderBean getOrder(Integer orderNo) {
		return odao.getOrder(orderNo);
	}

	@Override
	public List<OrderBean> getMemberOrders() {
		return oqdao.getMemberOrders();
	}

	@Override
	public String getAccount() {
		return oqdao.getAccount();
	}

	@Override
	public void setAccount(String account) {
		oqdao.setAccount(account);
	}

	@Override
	public List<OrderBean> getAllOrders() {
		return oqdao.getAllOrder();
	}

	@Override
	public OrderBean getShoppingOverOrder() {
		return oqdao.getShoppingOverOrder();
	}

}
