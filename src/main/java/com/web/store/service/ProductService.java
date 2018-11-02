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
	
}
