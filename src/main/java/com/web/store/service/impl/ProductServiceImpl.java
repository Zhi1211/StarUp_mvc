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

	@Override
	public int getTotalPages() {
		return prodRepository.getTotalPages();
	}

	@Override
	public List<ProductBean> getPageProds() {
		return prodRepository.getPageProds();
	}

	@Override
	public int getPageNo() {
		return prodRepository.getPageNo();
	}

	@Override
	public void setPageNo(int pageNo) {
		prodRepository.setPageNo(pageNo);
	}

	@Override
	public int getRecordsPerPage() {
		return prodRepository.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		prodRepository.setRecordsPerPage(recordsPerPage);
	}

	@Override
	public long getRecordCounts() {
		return prodRepository.getRecordCounts();
	}

	@Override
	public void setProdId(int prod_id) {
		prodRepository.setProdId(prod_id);
	}

	@Override
	public ProductBean getProd() {
		return prodRepository.getProd();
	}

	@Override
	public ProductBean queryProd(int prod_id) {
		return prodRepository.queryProd(prod_id);
	}

	@Override
	public int deleteProd(int prod_id) {
		return prodRepository.deleteProd(prod_id);
	}

	@Override
	public List<String> getProdType() {
		return prodRepository.getProdType();
	}

	@Override
	public void setSelected(String selected) {
		prodRepository.setSelected(selected);
	}

	@Override
	public String getSelected() {
		return prodRepository.getSelected();
	}

	@Override
	public String getProdTypeTag() {
		return prodRepository.getProdTypeTag();
	}

	@Override
	public String getTagName() {
		return prodRepository.getTagName();
	}

	@Override
	public void setTagName(String tagName) {
		prodRepository.setTagName(tagName);
	}

	@Override
	public List<ProductBean> getCategoryProds() {
		return prodRepository.getCategoryProds();
	}

	@Override
	public void setProdCategory(String prodCategory) {
		prodRepository.setProdCategory(prodCategory);
	}

	@Override
	public void setProdType(String prodType) {
		prodRepository.setProdType(prodType);
	}

	@Override
	public List<ProductBean> getTypeProds() {
		return prodRepository.getTypeProds();
	}

}
