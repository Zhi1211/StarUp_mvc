package com.web.store.service;

import java.util.List;

import com.web.store.model.MessageBean;

public interface MessageService {
	
	int insertMessage(MessageBean mb);
	
	List<MessageBean> getMessages(Integer toId);
	
	
}
