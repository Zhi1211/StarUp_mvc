package com.web.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.WorksBean;




@Service
@Transactional
public interface WorksService {

int getTotalPages();
	
	long getRecordCounts();
	
	List<WorksBean> getAllWorkss();
	
	WorksBean getWorksById(int worksId);
	
	int updateWorks(WorksBean bean, long sizeInBytes, long sizeInBytes_1, long sizeInBytes_2) ;

	int updateWorks(WorksBean bean);
	
	void addWorks(WorksBean wbean);
	
	List<WorksBean> getPageProds();
	
	 int getPageNo();
	 
	 void setPageNo(int pageNo);
	 
	 int getRecordsPerPage();
	 
	 void setRecordsPerPage(int recordsPerPage);
	
	 void setWoksId(int  works_id);
	 
	 WorksBean getWorks();
	 
	 WorksBean queryWorks(int works_id);
	 
	 int deleteWorks(int works_id);
	 
}
