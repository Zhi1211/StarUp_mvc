package com.web.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.web.store.model.ProductBean;

@Service
public interface ProductService {
	List<ProductBean> getAllProducts();

	ProductBean getProductById(int productId);
	

	public int updateProduct(ProductBean bean, long sizeInBytes);

	void addProduct(ProductBean pb);

	void deleteProduct(int id); 
	
	int getTotalPages();
	
	List<ProductBean> getPageProds();
	
	int getPageNo();
	
	void setPageNo(int pageNo);
	
	int getRecordsPerPage();
	
	void setRecordsPerPage(int recordsPerPage);
	
	// 計算紀錄總筆數
	long getRecordCounts();
	
	void setProdId(int prod_id);
	
	ProductBean getProd();
	
	// 依prodId來查詢單筆記錄
	ProductBean queryProd(int prod_id);
	
	// 依prodId來刪除單筆記錄
	int deleteProd(int prod_id);
	
	List<String> getProdType();
	
	void setSelected(String selected);
	
	String getSelected();
	
	String getProdTypeTag();
	
	String getTagName();

	void setTagName(String tagName);
	
	List<ProductBean> getCategoryProds();
	
	void setProdCategory(String prodCategory);

	void setProdType(String prodType);
	
	List<ProductBean> getTypeProds();
}
