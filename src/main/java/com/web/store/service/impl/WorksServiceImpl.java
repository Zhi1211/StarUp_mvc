package com.web.store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.WorksBean;
import com.web.store.repository.WorksRepository;
import com.web.store.service.WorksService;




@Service
@Transactional
public class WorksServiceImpl implements WorksService {
	private WorksRepository	worksRepository;		
	
	@Override
	public int getTotalPages() {
		return worksRepository.getTotalPages();
	}

	@Override
	public long getRecordCounts() {
		return worksRepository.getRecordCounts() ;
	}

	@Override
	public List<WorksBean> getAllWorkss() {
		return worksRepository.getAllWorkss();
	}

	@Override
	public WorksBean getWorksById(int worksId) {
		return  worksRepository.getWorksById(worksId);
	}

	@Override
	public int updateWorks(WorksBean bean, long sizeInBytes, long sizeInBytes_1, long sizeInBytes_2) {
		return worksRepository.updateWorks( bean,sizeInBytes, sizeInBytes_1,sizeInBytes_2) ;
	}

	@Override
	public int updateWorks(WorksBean bean) {
		// TODO Auto-generated method stub
		return worksRepository.updateWorks( bean) ;
	}

	@Override
	public void addWorks(WorksBean wbean) {
		worksRepository. addWorks(wbean);
	}

	@Override
	public List<WorksBean> getPageProds() {
		return  worksRepository.getPageProds();
	}

	@Override
	public int getPageNo() {
		return worksRepository.getPageNo();
	}

	@Override
	public void setPageNo(int pageNo) {
		worksRepository.setPageNo( pageNo);
		
	}

	@Override
	public int getRecordsPerPage() {
		return worksRepository.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		worksRepository.setRecordsPerPage(recordsPerPage) ;
	}

	@Override
	public void setWoksId(int works_id) {
		worksRepository. setWoksId(works_id)  ;
		
	}

	@Override
	public WorksBean getWorks() {
		return	worksRepository. getWorks() ;
	}

	@Override
	public WorksBean queryWorks(int works_id) {
		return worksRepository. queryWorks( works_id) ;
	}

	@Override
	public int deleteWorks(int works_id) {
		return worksRepository. deleteWorks( works_id);
	}
}