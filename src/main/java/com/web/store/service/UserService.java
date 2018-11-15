package com.web.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.store.model.OrderBean;
import com.web.store.model.UserBean;


@Service
public interface UserService {
	boolean idExists(String id);
	
	int saveUser(UserBean ub);
	
	void updateUnpaidOrderAmount(OrderBean ob);
	
	UserBean queryUser(String id);
	
	UserBean getUser(int nId);
	
	boolean nicknameExists(String nickname);
	
	UserBean getUser2(String account);
	
	List<UserBean> getAllUsers();
	
	int updateUser(UserBean ub, long sizeInBytes);

	int updateUser(UserBean userBean);
}
