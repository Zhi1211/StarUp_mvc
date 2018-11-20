package com.web.store.repository;

import java.sql.Connection;
import java.util.List;

import com.web.store.model.MessageBean;

public interface MessageDao {
	
	void setConnection(Connection con);
	
	int insertMessage(MessageBean mb);
	
	List<MessageBean> getReceivedMessages(Integer toId);
	
	List<MessageBean> getDeliveredMessages(Integer fromId);
}
