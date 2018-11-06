package com.web.store.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.ProductBean;
import com.web.store.repository.ProductRepository;

import _00_init.util.GlobalService;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	
	private static final long serialVersionUID = 1L;
	private int prod_id = 0; 	// 查詢單筆商品會用到此代號
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁10筆
	private int totalPages = -1;
	private String prodCategory;
	private String tagName = "";
	String selected = "";
	private String prodType;
	
	public ProductRepositoryImpl() {
		
	}
	
	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}
	
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
	
	// 查詢某一頁的商品資料，執行本方法前，一定要先設定實例變數pageNo的初值
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getPageProds() {
		List<ProductBean> list = new ArrayList<>();
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		int endRecordNo = (pageNo) * recordsPerPage;
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductBean";
		list = session.createQuery(hql)
					  .setFirstResult(startRecordNo)
					  .setMaxResults(recordsPerPage)
					  .getResultList();
		return list;
	}
	
	@Override
	public int getPageNo() {
		return pageNo;
	}
	
	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		List<Long> list;
		Long count = 0L;	// 必須使用 long 型態
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(*) FROM ProductBean ";
		list = session.createQuery(hql).getResultList();
		if(list != null) {
			count = list.get(0);
		}
		return count;
	}
	
	@Override
	public void setProdId(int prod_id) {
		this.prod_id = prod_id;
	}
	
	@Override
	public ProductBean getProd() {
		ProductBean bean = queryProd(this.prod_id);
		return bean;
	}
	
	// 依prod_Id來查詢單筆記錄
	@Override
	public ProductBean queryProd(int prod_id) {
		ProductBean bean = null;
		Session session = factory.getCurrentSession();
		bean = session.get(ProductBean.class, prod_id);
		return bean;
	}
	
	// 依prod_Id來刪除單筆記錄
	@Override
	public int deleteProd(int prod_id) {
		int n = 0;
		ProductBean bean = new ProductBean();
		bean.setProd_id(prod_id);
		Session session = factory.getCurrentSession();
		session.delete(bean);
		return ++n;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProdType() {
		List<String> list = new ArrayList<>();
		String hql = "SELECT DISTINCT category FROM ProductBean";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@Override
	public void setSelected(String selected) {
		this.selected = selected;
		
	}
	
	@Override
	public String getSelected() {
		return selected;
	}
	
	@Override
	public String getProdTypeTag() {
		String ans = "";
		List<String> list = getProdType();
		ans += "<SELECT name='prodType'>";
		for(String cate : list) {
			if (cate.equals(selected)) {
				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
			} else {
				ans += "<option value='" + cate + "'>" + cate + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;
	}
	
	@Override
	public String getTagName() {
		return tagName;
	}
	
	@Override
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getCategoryProds() {
		List<ProductBean> list = null;
		System.out.println("開始查詢分類商品" + prodCategory);
		String hql = "FROM ProductBean p WHERE p.prodCategory = :category";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("category", prodCategory).getResultList();
		System.out.println(list);
		return list;
	}
	
	@Override
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	
	@Override
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getTypeProds() {
		List<ProductBean> list = null;
		System.out.println("開始查詢類別商品" + prodType);
		String hql = "FROM ProductBean p WHERE p.prodType = :type";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("type", prodType).getResultList();
		System.out.println(list);
		return list;
	}
}
