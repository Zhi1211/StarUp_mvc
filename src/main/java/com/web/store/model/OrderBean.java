package com.web.store.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//本類別存放訂單資料
@Entity
@Table(name="orders")
public class OrderBean {
	
	Integer orderNo;	//訂單編號
	String account;		//訂購者帳號
	Double totalAmount;	//訂購總金額
	String shippingAddress;	//出貨地址
	String bno;		//發票統一編號
	String invoiceTitle;	//發票抬頭
	Date orderDate;	//訂貨日期
	Date shippingDate;	//出貨日期
	String cancelTag;	//取消標籤
	Set<OrderItemBean> items = new LinkedHashSet<>();
	
	public OrderBean() {
		super();
	}
	
	public OrderBean(Integer orderNo, String account, Double totalAmount, String shippingAddress, String bno,
			String invoiceTitle, Date orderDate, Date shippingDate, Set<OrderItemBean> items) {
		super();
		this.orderNo = orderNo;
		this.account = account;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.bno = bno;
		this.invoiceTitle = invoiceTitle;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.items = items;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	//說明多方表格的orderBean欄位為外鍵欄位，對照的主鍵為一方表格的orderno欄
	@OneToMany(mappedBy="orderBean", cascade= {CascadeType.ALL})
	public Set<OrderItemBean> getItems() {
		return items;
	}
	
	public void setItems(Set<OrderItemBean> items) {
		this.items = items;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getCancelTag() {
		return cancelTag;
	}

	public void setCancelTag(String cancelTag) {
		this.cancelTag = cancelTag;
	}
	
}
