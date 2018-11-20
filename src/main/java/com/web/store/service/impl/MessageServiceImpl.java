package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.MessageBean;
import com.web.store.repository.MessageDao;
import com.web.store.service.MessageService;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDao mdao;
	
	public MessageServiceImpl() {
		super();
	}

	@Override
	public int insertMessage(MessageBean mb) {
		return mdao.insertMessage(mb);
	}

	@Override
	public List<MessageBean> getReceivedMessages(Integer toId) {
		return mdao.getReceivedMessages(toId);
	}

	@Override
	public List<MessageBean> getDeliveredMessages(Integer fromId) {
		return mdao.getDeliveredMessages(fromId);
	}

	@Override
	public List<MessageBean> getUnreadMessages(Integer toId) {
		return mdao.getUnreadMessages(toId);
	}

}
