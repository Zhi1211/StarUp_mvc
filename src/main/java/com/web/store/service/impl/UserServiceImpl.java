package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.OrderBean;
import com.web.store.model.UserBean;
import com.web.store.repository.UserDao;
import com.web.store.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao  dao ;
	
	public UserServiceImpl() {
		//this.dao = new UserDaoImpl();
	}

	@Override
	public int saveUser(UserBean ub) {
		return dao.saveUser(ub);
	}

	@Override
	public boolean idExists(String id) {
		return dao.idExists(id);
	}

	@Override
	public UserBean queryUser(String id) {
		return dao.queryUser(id);
	}

	@Override
	public void updateUnpaidOrderAmount(OrderBean ob) {
		dao.updateUnpaidOrderAmount(ob);
	}

	@Override
	public UserBean getUser(int nId) {
		return dao.getUser(nId);
	}

	@Override
	public boolean nicknameExists(String nickname) {
		return dao.nicknameExists(nickname);
	}

	@Override
	public UserBean getUser2(String account) {
		return dao.getUser2(account);
	}

	@Override
	public List<UserBean> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public int updateUser(UserBean ub, long sizeInBytes) {
		return dao.updateUser(ub, sizeInBytes);
	}

	@Override
	public int updateUser(UserBean userBean) {
		return dao.updateUser(userBean);
	}

	@Override
	public UserBean getUserByNickname(String nickname) {
		return dao.getUserByNickname(nickname);
	}

}
