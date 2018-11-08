package com.web.store.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {
	private Map<Integer, OrderedItem> cart = new LinkedHashMap<>();
	
	public ShoppingCart() {
		super();
	}
	
	public Map<Integer, OrderedItem> getContent() {
		return cart;
	}
	
	public void addToCart(Integer prod_id, OrderedItem oi) {
		if (oi.getQty() <= 0) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品資料，則客戶第一次購買此商品
		if (cart.get(prod_id) == null) {
			cart.put(prod_id, oi);
		} else {
			// 如果客戶在伺服器端已有此項商品的資料，則客戶【加購】此項商品
			OrderedItem oib = cart.get(prod_id);
			// 加購的數量：oi.getQty()
			// 原有的數量：oib.getQty()
			oib.setQty(oi.getQty() + oib.getQty());
		}
	}
	
	//修改商品的數量
	public boolean modifyQty(Integer prod_id, Integer newQty) {
		if (cart.get(prod_id) != null) {
			OrderedItem oi = cart.get(prod_id);
			oi.setQty(newQty);
			return true;
		} else {
			return false;
		}
	}
	
	// 刪除特定商品
	public int deleteProduct(Integer prod_id) {
		if (cart.get(prod_id) != null) {
			cart.remove(prod_id);	// Map介面的remove()方法
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getItemNumber() {
		return cart.size();
	}
	
	// 計算購物車內所有商品的合計金額
	public double getSubtotal() {
		double subTotal = 0;
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			double prodPrice = cart.get(n).getProdPrice();
			int qty = cart.get(n).getQty();
			subTotal += (prodPrice * qty);
		}
		return subTotal;
	}
	
	public void listCart() {
		Set<Integer> set = cart.keySet();
		for (Integer k : set) {
			System.out.printf("prod_id=%3d, prodPrice=%5.2f " , k, cart.get(k).getProdPrice());
		}
		System.out.println("------------------------------");
	}
}
