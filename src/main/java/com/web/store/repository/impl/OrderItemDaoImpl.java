package com.web.store.repository.impl;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.OrderItemBean;
import com.web.store.model.ProductBean;
import com.web.store.repository.OrderItemDao;
import com.web.store.ude.ProductStockException;

//一張合格的訂單必須經過下列檢查
//1. 檢查訂購之商品的數量是否足夠。
// 此功能寫在本類別的updateProductStock()方法內，參考該方法的說明。

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	
	@Autowired
	SessionFactory factory;
	
	public OrderItemDaoImpl() {
		
	}
	
	@Override
	public void setConnection(Connection con) {
		
	}
	
	// 計算客戶欲購買之某項商品(以OrderItemBean物件oib來表示)的小記金額(subtotal)，
	// 計算公式為：商品的數量 * 商品的單價 (* 商品的折扣)
	@Override
	public double findItemAmount(OrderItemBean oib) {
		double subtotal = oib.getQuantity() * oib.getUnitPrice();
		return subtotal;
	}
	
	// 功能：更新商品的數量，更新之前必須先檢查訂購之商品的數量是否足夠
	// 說明：商品的數量必須大於訂單明細中的訂購數量，否則丟出ProductStockException
	// int prod_id：產品代號
	// int orderedQuantity：訂購數量
	// 步驟1 先檢查商品的庫存數量(Product表格的stock欄位)是否大於訂購之數量
	// 如果Product表格中的庫存數量(Product#stock)小於訂購數量(orderedQuantity)
	// 丟出ProductStockException，結束本方法。
	// 步驟2 更新Prdouct表格的stock 欄位： Product表格的stock -= orderQuantity;
	@Override
	public int updateProductStock(OrderItemBean oib) {
		int n = 0;
		int prodStock = 0;
		String hql0 = "FROM ProductBean WHERE prod_id = :prodId";
		String hql1 = "UPDATE ProductBean SET prodStock = prodStock - :stk WHERE prod_id = :prodId";
		Session session = factory.getCurrentSession();
		ProductBean bean = (ProductBean)session.createQuery(hql0)
											 .setParameter("prodId", oib.getProd_id())
											 .getSingleResult();
		prodStock = bean.getProdStock();
		if (prodStock < oib.getQuantity()) {
			throw new ProductStockException("庫存數量不足： prod_id： " + oib.getProd_id() + 
					" 在庫量 " + prodStock + ", 訂購量： " + oib.getQuantity());
		}
		n= session.createQuery(hql1)
				  .setParameter("stk", oib.getQuantity())
				  .setParameter("prodId", oib.getProd_id())
				  .executeUpdate();
		return n;
	}

}
