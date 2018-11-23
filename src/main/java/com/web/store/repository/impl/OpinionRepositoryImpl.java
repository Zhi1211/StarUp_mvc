package com.web.store.repository.impl;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.OpinionBean;
import com.web.store.model.WorksBean;
import com.web.store.repository.OpinionRepository;

import _00_init.util.HibernateUtil;


@Repository
public class OpinionRepositoryImpl implements OpinionRepository{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	SessionFactory factory;
	
	public OpinionRepositoryImpl(){
	}

	@Override
	public int saveOpinion(OpinionBean ob){
		int count = 0;
		Session session = factory.getCurrentSession();
		session.save(ob);
		count++;
		return count;		
	}

	@Override
	public List<OpinionBean> getAllOpinion() {
		List<OpinionBean> list = new ArrayList<>();
		try {
			String hql = "FROM OpinionBean";
			Session session = factory.getCurrentSession();
			list = session.createQuery(hql).getResultList();						
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}	
	