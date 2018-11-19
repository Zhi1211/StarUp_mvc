package com.web.store.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.WorksBean;
import com.web.store.repository.WorksRepository;

@Repository
public class WorksRepositoryImpl implements WorksRepository {
	private static final long serialVersionUID = 1L;

	private int works_id = 0; // 查詢單筆作品使用代號
	private int pageNo = 0; // 存放目前顯示之頁面編號
	private int recordsPerPage = 5; // 預設值：每頁7筆
	private int totalPages = -1;
	private String tagName = "";
	String selected = "";

	public WorksRepositoryImpl() {
	}

	@Autowired
	SessionFactory factory;

	// 計算作品總共有幾頁
	@Override
	public int getTotalPages() {
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	// 計算資料庫有幾筆作品資料
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		List<Long> list;
		Long count = 0L; // 必須使用long型態
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(*) FROM WorksBean";
		list = session.createQuery(hql).getResultList();
		if (list != null) {
			count = list.get(0);
		}
		return count;
	}

	// 搜尋所有作品
	@SuppressWarnings("unchecked")
	@Override
	public List<WorksBean> getAllWorks() {
		List<WorksBean> list = new ArrayList<>();
		try {
			String hql = "FROM WorksBean";
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).getResultList();						
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public WorksBean getWorksById(int worksId) {
		String hql = "FROM WorksBean wb WHERE wb.works_id= :wid";
		WorksBean wb = new WorksBean();
		Session session = factory.getCurrentSession();
		wb = (WorksBean)session.createQuery(hql)
																.setParameter("wid",worksId).getSingleResult();
	return wb;
	}
	
	//修改一筆資料(含修改圖片)
		@Override
		public int updateWorks(WorksBean bean, long sizeInBytes, long sizeInBytes_1, long sizeInBytes_2) {
			int n = 0;
			String hql = "UPDATE WorksBean SET " 
					+ " worksName=:worksName, "
					+ "worksIntro=:worksIntro,"
					+ "  worksImgName=:worksImgName,"
					+ " worksImg = :WorksImg,"
					+ " detail_1=:detail_1,"
					+ " captionImgName_1=:captionImgName_1,"
					+ " captionImg_1=:captionImg_1,"
					+ "detail_2=:detail_2,"
					+ " captionImgName_2=:captionImgName_2,"
					+ " captionImg_2=:captionImg_2"
					+" WHERE works_id = : works_id";
			
			if (sizeInBytes ==0 ||sizeInBytes_1 == 0||sizeInBytes_2 == 0) {
				n = updateWorks(bean);
				return n;
			}
			
			Session session = factory.getCurrentSession();		
			n = session.createQuery(hql).setParameter("worksName", bean.getWorksName())
										.setParameter("worksIntro", bean.getWorksIntro())
										.setParameter("worksImgName", bean.getWorksImgName())
										.setParameter("WorksImg", bean.getWorksImg())
										.setParameter("detail_1", bean.getDetail_1())
										.setParameter("captionImgName_1", bean.getCaptionImgName_1())
										.setParameter("captionImg_1", bean.getCaptionImg_1())
										.setParameter("detail_2", bean.getDetail_2())
										.setParameter("captionImgName_2", bean.getCaptionImgName_2())
										.setParameter("captionImg_2", bean.getCaptionImg_2())
										.executeUpdate();
			n++;
			System.out.println("更新一筆資料成功_n="+n);
			return n;
		}
		
		//修改一筆資料(只修改文字)
		@Override
		public int updateWorks(WorksBean bean) {
			int n = 0;
			String hql = "UPDATE WorksBean SET " 
					+ " worksName=:worksName,"
					+ "  worksIntro=:worksIntro, "
					+ " detail_1=:detail_1,"
					+ " detail_2=:detail_2"
					+" WHERE works_id = : works_id";	
			
			Session session = factory.getCurrentSession();		
			n = session.createQuery(hql).setParameter("worksName", bean.getWorksName())
										.setParameter("worksIntro", bean.getWorksIntro())
										.setParameter("detail_1", bean.getDetail_1())
										.setParameter("detail_2", bean.getDetail_2())
										.executeUpdate();
			n++;
			System.out.println("更新一筆資料成功，n = "+n);
			return n;
		}

		//新增作品
		@Override
		public int saveWorks(WorksBean wbean) {
			int n = 0;
			Session session = factory.getCurrentSession();
			session.save(wbean);
			n++;
			return n;
		}
		
		
		// 查詢某一頁的商品資料，執行本方法前，一定要先設定實例變數pageNo的初值
		@SuppressWarnings("unchecked")
		@Override	
		public List<WorksBean> getPageWorks() {
			List<WorksBean> list = new ArrayList<>();
			// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
			int startRecordNo = (pageNo - 1) * recordsPerPage;
//			int endRecordNo = (pageNo) * recordsPerPage;
			Session session = factory.getCurrentSession();
			String hql = "FROM WorksBean";
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
		public int getRecordsPerPage(){
			return recordsPerPage;
		}
		
		@Override
		public void setRecordsPerPage(int recordsPerPage) {
			this.recordsPerPage = recordsPerPage;
		}
		
		@Override
		public void setWoksId(int  works_id) {
			this.works_id = works_id;
		}
		
		@Override
		public WorksBean getWorks() {
			WorksBean wbean = queryWorks(this.works_id);
			return wbean;
		}
		// 依prod_Id來查詢單筆記錄
		@Override
		public WorksBean queryWorks(int works_id) {
			WorksBean  wbean = null;
			Session session = factory.getCurrentSession();
			wbean = session.get(WorksBean.class,works_id);
			return wbean;
		}
		
		// 依prod_Id來刪除單筆記錄
		@Override
		public int deleteWorks(int works_id) {
			int n = 0;
			WorksBean wbean = new WorksBean();
			wbean.setWorks_id(works_id);
			Session session = factory.getCurrentSession();
			session.delete(wbean);
			return n++;
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<WorksBean> getWorksByUserId(int userId) {
			String hql = "FROM WorksBean wb WHERE wb.user_Id = :uid";
			List<WorksBean> list = null;
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).setParameter("uid", userId).getResultList();
			return list;
		}

		@Override
		public int updateWorksComment(WorksBean bean) {
			int n = 0;
			String hql = "UPDATE WorksBean SET " 
					+" comment = :comment"
					+" WHERE works_id = :works_id";
			Session session = factory.getCurrentSession();
			n = session.createQuery(hql).setParameter("comment", bean.getComment())
										.setParameter("works_id", bean.getWorks_id())
										.executeUpdate();
			n++;
			System.out.println("更新一筆留言資料成功，n = "+n);
			return n;
		}
}
