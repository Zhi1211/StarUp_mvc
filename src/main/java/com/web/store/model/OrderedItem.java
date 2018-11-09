package com.web.store.model;

import java.sql.Blob;

public class OrderedItem {
	Integer prod_id = 0;
	String prodName = "";
	Integer prodPrice = 0;
	String prodCompany = "";
	String prodType = "";
	String prodIntro = "";
	Integer qty = 0;
//	Blob prodImg = null;
	
	public OrderedItem() {
		super();
	}
	
	public OrderedItem(Integer prod_id, String prodName, Integer prodPrice, String prodCompany, String prodType,
			String prodIntro, Integer qty) {
		super();
		this.prod_id = prod_id;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodCompany = prodCompany;
		this.prodType = prodType;
		this.prodIntro = prodIntro;
		this.qty = qty;
	}

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdCompany() {
		return prodCompany;
	}

	public void setProdCompany(String prodCompany) {
		this.prodCompany = prodCompany;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdIntro() {
		return prodIntro;
	}

	public void setProdIntro(String prodIntro) {
		this.prodIntro = prodIntro;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

//	public Blob getProdImg() {
//		return prodImg;
//	}
//
//	public void setProdImg(Blob prodImg) {
//		this.prodImg = prodImg;
//	}
	
}
