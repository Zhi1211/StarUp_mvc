package com.web.store.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.ProductBean;
import com.web.store.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	
	@Autowired
	SessionFactory factory;
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAllProducts() {
		String hql = "FROM ProductBean";
		List<ProductBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	@Override
	public ProductBean getProductById(int productId) {
		String hql = "FROM ProductBean pb WHERE pb.prod_id = :pid";
		ProductBean pb = new ProductBean();
		Session session = factory.getCurrentSession();
		pb = (ProductBean) session.createQuery(hql)
											    	.setParameter("pid", productId).getSingleResult();		
		return pb;
	}
	@Override
	public int updateProduct(ProductBean bean, long sizeInBytes) {
		int n = 0; 
		String hql = "UPDATE ProductBean SET" 
				+ " prodName = :prodName,"
				+ " prodCompany = :prodCompany,"
				+ " prodPrice = :prodPrice,"
				+ " prodImg = :prodImg,"
				+ " prodIntro = :prodIntro,"
				+ " prodImgName = :prodImgName,"
				+ " prodStock =:prodStock,"
				+ " prodCategory = :prodCategory,"
				+ " prodType = :prodType"
				+ " WHERE prod_id = :prod_id";
		if (sizeInBytes == 0) { // 不修改圖片
			n = updateProduct(bean);
			return n;
		}		
		Session session = factory.getCurrentSession();		
		session.createQuery(hql).setParameter("prodName", bean.getProdName())
									.setParameter("prodCompany", bean.getProdCompany())
									.setParameter("prodPrice", bean.getProdPrice())
									.setParameter("prodImg", bean.getProdImg())
									.setParameter("prodIntro", bean.getProdIntro())
									.setParameter("prodImgName", bean.getProdImgName())
									.setParameter("prodStock", bean.getProdStock())
									.setParameter("prodCategory", bean.getProdCategory())
									.setParameter("prodType", bean.getProdType())
									.setParameter("prod_id", bean.getProd_id())
									.executeUpdate();
		n++;
		System.out.println("更新一筆資料成功，n = "+n);
		return n;
	}
	@Override
	public int updateProduct(ProductBean bean) {
		int n = 0; 
		String hql = "UPDATE ProductBean SET" 
				+ " prodName = :prodName,"
				+ " prodCompany = :prodCompany,"
				+ " prodPrice = :prodPrice,"
				+ " prodIntro = :prodIntro,"
				+ " prodStock =:prodStock,"
				+ " prodCategory = :prodCategory,"
				+ " prodType = :prodType"
				+ " WHERE prod_id = :prod_id";
		Session session = factory.getCurrentSession();		
		session.createQuery(hql).setParameter("prodName", bean.getProdName())
														.setParameter("prodCompany", bean.getProdCompany())
														.setParameter("prodPrice", bean.getProdPrice())
														.setParameter("prodIntro", bean.getProdIntro())
														.setParameter("prodStock", bean.getProdStock())
														.setParameter("prodCategory", bean.getProdCategory())
														.setParameter("prodType", bean.getProdType())
														.setParameter("prod_id", bean.getProd_id())
														.executeUpdate();
		n++;
		System.out.println("更新一筆資料成功，n = "+n);
		return n;
	}
	@Override
	public void addProduct(ProductBean bean) {
		Session session = factory.getCurrentSession();
		session.save(bean);
		
	}
}
