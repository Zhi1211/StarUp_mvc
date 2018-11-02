package com.web.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.store.model.ProductBean;
@Repository
public interface ProductRepository {
		List<ProductBean> getAllProducts();

		ProductBean getProductById(int productId);

		int updateProduct(ProductBean bean);

		int updateProduct(ProductBean bean, long sizeInBytes);

		void addProduct(ProductBean bean);


	
}
