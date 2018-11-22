package com.web.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.store.model.OpinionBean;

@Repository
public interface OpinionRepository {

	int saveOpinion(OpinionBean ob);

	List<OpinionBean> getAllOpinion();
}