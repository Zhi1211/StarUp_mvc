package com.web.store.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.FormBean;
import com.web.store.repository.FormDao;
@Repository
public class FormDaoImpl  implements FormDao{
	@Autowired
	SessionFactory factory;
	
	public FormDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormBean> getAllForms() {
		Session session=factory.getCurrentSession();
		String hql = "FROM FormBean";
		List<FormBean> fb=session.createQuery(hql).getResultList();
		
		return fb;
	}

	@Override
	public void processForm(FormBean fb) {
		Session session=factory.getCurrentSession();
		session.save(fb);
	}

}
