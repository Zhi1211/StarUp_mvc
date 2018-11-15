package com.web.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.store.model.OrderBean;
import com.web.store.model.UserBean;

@Repository
public interface UserDao {
		UserBean getUser(int pk);

		int saveUser(UserBean ub);
		
		boolean idExists(String account);
		
		boolean nicknameExists(String nickname);
		
		List<UserBean> checkLogin(String account,String password);
		
		UserBean checkIDPassword(String account, String password);
		
		List<UserBean> getAllUsers();
		
		int deleteUser(int pk);

		int updateUser(UserBean ub, long sizeInBytes);

		void updateUnpaidOrderAmount(OrderBean ob);

		UserBean queryUser(String id);

		UserBean getUser2(String account);

		int updateUser(UserBean userBean);
}
