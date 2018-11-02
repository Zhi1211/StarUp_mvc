package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.ProductBean;
import com.web.store.repository.ProductRepository;
import com.web.store.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository	prodRepository;		
	
	@Override
	public List<ProductBean> getAllProducts() {		
		return prodRepository.getAllProducts();
	}

	@Override
	public ProductBean getProductById(int productId) {
		return prodRepository.getProductById(productId);
	}

	@Override
	public int updateProduct(ProductBean bean,  long sizeInBytes) {
		return prodRepository.updateProduct(bean, sizeInBytes);
	}
	@Override
	public void addProduct(ProductBean bean) {
		prodRepository.addProduct(bean);
	}

}
