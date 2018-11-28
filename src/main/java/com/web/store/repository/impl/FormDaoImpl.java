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
	
	@Override
	public FormBean getFormById(Integer form_id) {
		String hql = "FROM FormBean pb WHERE pb.form_id = :pid";
		FormBean fb = new FormBean();
		Session session = factory.getCurrentSession();
		fb = (FormBean) session.createQuery(hql)
											    	.setParameter("pid", form_id).getSingleResult();		
		return fb;
	}

	@Override
	public void updateFormStatus(Integer form_id,String status) {
		String hql = "UPDATE FormBean pb SET status = :status WHERE pb.form_id = :pid";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("status", status)
												  .setParameter("pid", form_id).executeUpdate();
		
	}

}

