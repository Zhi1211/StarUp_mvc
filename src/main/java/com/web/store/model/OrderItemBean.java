package com.web.store.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderItems")
public class OrderItemBean {
	Integer seqno;
	Integer prod_id;
	String description;
	Integer quantity;
	Integer unitPrice;
//	Double discount;
	OrderBean orderBean;
	
	public OrderItemBean() {
		super();
	}

	public OrderItemBean(Integer seqno, Integer prod_id, String description, Integer quantity, 
			Integer unitPrice) {
		super();
		this.seqno = seqno;
		this.prod_id = prod_id;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	
//	public Double getDiscount() {
//	return discount;
//}
//
//public void setDiscount(Double discount) {
//	this.discount = discount;
//}
	
	@ManyToOne
	@JoinColumn(name="FK_Order_Id")
	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
}
