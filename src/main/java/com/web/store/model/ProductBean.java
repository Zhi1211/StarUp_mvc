package com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="product")
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer prod_id;
	private String prodName;
	private String prodType;
	private Blob prodImg;
	private String prodImgName;
	private Integer prodStock;
	private Integer prodPrice;
	private String prodCompany;
	private String prodIntro;
	private String prodUpDate;
	private String prodOutDate;
	private String prodCategory;
	private MultipartFile  productImage;
	
	@Transient
	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public ProductBean(Integer prod_id, String prodName, String prodType, Blob prodImg, String prodImgName,
			Integer prodStock, Integer prodPrice, String prodCompany, String prodIntro,String prodUpDate, String prodOutDate, String prodCategory ) 
		{
		super();
		this.prod_id = prod_id;
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodImg = prodImg;
		this.prodImgName = prodImgName;
		this.prodStock = prodStock;
		this.prodPrice = prodPrice;
		this.prodCompany = prodCompany;
		this.prodIntro = prodIntro;
		this.prodUpDate = prodUpDate;
		this.prodOutDate = prodOutDate;
		this.prodCategory = prodCategory;
	}

	public ProductBean() {
		super();
	}
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public Blob getProdImg() {
		return prodImg;
	}

	public void setProdImg(Blob prodImg) {
		this.prodImg = prodImg;
	}

	public Integer getProdStock() {
		return prodStock;
	}

	public void setProdStock(Integer prodStock) {
		this.prodStock = prodStock;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdIntro() {
		return prodIntro;
	}

	public void setProdIntro(String prodIntro) {
		this.prodIntro = prodIntro;
	}

	public String getProdUpDate() {
		return prodUpDate;
	}

	public void setProdUpDate(String date) {
		this.prodUpDate = date;
	}

	public String getProdOutDate() {
		return prodOutDate;
	}

	public void setProdOutDate(String prodOutDate) { // 1029把productOutDate修正為prodOutDate
		this.prodOutDate = prodOutDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProdImgName() {
		return prodImgName;
	}

	public void setProdImgName(String prodImgName) {
		this.prodImgName = prodImgName;
	}

	public String getProdCompany() {
		return prodCompany;
	}

	public void setProdCompany(String prodCompany) {
		this.prodCompany = prodCompany;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	@Transient
	private String  priceStr = null;	
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	@Transient
	private String  stockStr = null;	
	public void setStockStr(String stockStr) {
		this.stockStr = stockStr;
	}

}
