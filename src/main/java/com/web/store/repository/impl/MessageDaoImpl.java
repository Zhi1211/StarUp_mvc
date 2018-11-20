package com.web.store.repository.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.MessageBean;
import com.web.store.repository.MessageDao;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public void setConnection(Connection con) {
		
	}

	@Override
	public int insertMessage(MessageBean mb) {
		int updateCount = 0;
		try {
			Session session = factory.getCurrentSession();
			session.save(mb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getReceivedMessages(Integer toId) {
		List<MessageBean> list = null;
		String hql = "FROM MessageBean WHERE toId = :toId ";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("toId", toId).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getDeliveredMessages(Integer fromId) {
		List<MessageBean> list = null;
		String hql = "FROM MessageBean WHERE fromId = :fromId ";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("fromId", fromId).getResultList();
		return list;
	}

}
