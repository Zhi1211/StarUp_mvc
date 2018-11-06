package com.web.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.store.model.ProductBean;
@Repository
public interface ProductRepository {
		List<ProductBean> getAllProducts();

		ProductBean getProductById(int productId);
		// 修改一筆商品，不包含圖片更改
		int updateProduct(ProductBean bean);
		// 修改一筆商品
		int updateProduct(ProductBean bean, long sizeInBytes);
		// 新增一筆商品
		void addProduct(ProductBean bean);

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
