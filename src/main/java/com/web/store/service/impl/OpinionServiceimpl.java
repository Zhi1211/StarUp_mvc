package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.OpinionBean;
import com.web.store.repository.OpinionRepository;
import com.web.store.service.OpinionService;



@Service
@Transactional
public class OpinionServiceimpl implements OpinionService{

	@Autowired
	private OpinionRepository	 opinionRepository;

	@Override
	public int saveOpinion(OpinionBean ob) {
		return opinionRepository.saveOpinion(ob);
	}

	@Override
	public List<OpinionBean> getAllOpinion() {
		return opinionRepository. getAllOpinion();
	}		
	
}
