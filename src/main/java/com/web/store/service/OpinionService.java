package com.web.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.OpinionBean;
import com.web.store.model.WorksBean;


@Service
@Transactional
public interface OpinionService {

	int saveOpinion(OpinionBean ob);

	List<OpinionBean> getAllOpinion();


}