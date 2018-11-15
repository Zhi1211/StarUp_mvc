package com.web.store.repository.impl;

//import java.sql.Connection;
import java.util.List;

import javax.persistence.Query;
//import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model.OrderBean;
import com.web.store.model.UserBean;
import com.web.store.repository.UserDao;
import com.web.store.service.impl.UserNotFoundException;
import com.web.store.ude.UnpaidOrderAmountExceedingException;

import _00_init.util.GlobalService;

@Repository
public class UserDaoImpl implements UserDao {
//	private DataSource ds = null;
//	private Connection conn = null;
	
	@Autowired
	SessionFactory factory;
	
	public UserDaoImpl() {
	}
	
	public int saveUser(UserBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
			session.save(mb);
			count++;
		return count;
	}
	
	// 判斷參數account(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@SuppressWarnings("unchecked")
	public boolean idExists(String account) {
		boolean exist = false;
		String hql = "FROM UserBean WHERE account = :account";
		Session session = factory.getCurrentSession();

			List<UserBean> list = session.createQuery(hql)
					.setParameter("account", account)
					.getResultList();

			exist = (list.isEmpty()) ? false : true;

		return exist;
	}
	
	@SuppressWarnings("unchecked")
	public boolean nicknameExists(String nickname) {
		boolean exist = false;
		String hql = "FROM UserBean WHERE nickname = :nickname";
		Session session = factory.getCurrentSession();

			List<UserBean> list = session.createQuery(hql)
					.setParameter("nickname", nickname)
					.getResultList();

			exist = (list.isEmpty()) ? false : true;

		return exist;
	}
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@SuppressWarnings({ "unchecked", "unused" })
	public List<UserBean> checkLogin(String account,String password) {
		int i = 0;
		Session session = factory.getCurrentSession();
		List<UserBean> list = null;
		String hql = "From UserBean u WHERE u.account = :account and u.password = :password";

			Query query = session.createQuery(hql);
			query.setParameter("account", account);
			query.setParameter("password", password);
			list = query.getResultList();

			i++;

		return list ;
	}
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
		// 否則傳回 null。
		
		@SuppressWarnings("unchecked")
		@Override
		public UserBean checkIDPassword(String account, String password) {
			UserBean mb = null;
			String hql = "FROM UserBean m WHERE m.account = :account and m.password = :pswd";
			
			Session session = factory.getCurrentSession();
			List<UserBean>list = session.createQuery(hql)
					.setParameter("account", account)
					.setParameter("pswd", password)
					.getResultList();
			mb = (list.isEmpty() ? null : list.get(0));

			return mb;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<UserBean> getAllUsers() {
		String hql = "FROM UserBean";
		Session session = factory.getCurrentSession();
		List<UserBean> list = session.createQuery(hql).getResultList();
		return list;
		}

		@Override
		public int deleteUser(int pk) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int updateUser(UserBean ub, long sizeInBytes) {
			int n = 0;
			String hql = "UPDATE UserBean SET nickname = :nickname,"
				    			+ "phone = :phone, address = :address, photo = :photo, introduction = :introduction"
				    			+ " WHERE user_id = :uid";
			if(sizeInBytes == -1) {
				n = updateUser(ub);
				return n;
			}
			Session session = factory.getCurrentSession();
			n = session.createQuery(hql).setParameter("nickname", ub.getNickname())
															.setParameter("phone", ub.getPhone())
															.setParameter("address", ub.getAddress())
															.setParameter("photo", ub.getPhoto())
															.setParameter("introduction", ub.getIntroduction())
															.setParameter("uid", ub.getUser_id())
															.executeUpdate();
			return 0;
		}
		public int updateUser(UserBean ub) {
			int n = 0;
			String hql = "UPDATE UserBean SET nickname = :nickname,"
				    			+ "phone = :phone, address = :address, introduction = :introduction"
				    			+ " WHERE user_id = :uid";
			Session session = factory.getCurrentSession();
			n = session.createQuery(hql).setParameter("nickname", ub.getNickname())
															.setParameter("phone", ub.getPhone())
															.setParameter("address", ub.getAddress())
															.setParameter("introduction", ub.getIntroduction())
															.setParameter("uid", ub.getUser_id())
															.executeUpdate();
			return n;
		}
		@SuppressWarnings("unchecked")
		@Override
		public UserBean getUser(int id) {
			UserBean mb = null;
			String hql = "FROM UserBean WHERE user_id = :mid ";
			Session session = factory.getCurrentSession();
				List<UserBean> list = session.createQuery(hql).setParameter("mid", id).getResultList();
				mb = (list.isEmpty()) ? null : list.get(0);

			return mb;
		}
		@SuppressWarnings("unchecked")
		@Override
		public UserBean getUser2(String account) {
			UserBean mb = null;
			String hql = "FROM UserBean WHERE account = :account ";
			Session session = factory.getCurrentSession();
				List<UserBean> list = session.createQuery(hql).setParameter("account", account).getResultList();
				mb = (list.isEmpty() ? null : list.get(0));

			return mb;
		}	
		@Override
		public void updateUnpaidOrderAmount(OrderBean ob) {
			double currentAmount = ob.getTotalAmount();
			Long unpaidAmount = 0L;
			UserBean ub;
			String hql = "FROM UserBean u WHERE u.account = :account";
			Session session = factory.getCurrentSession();
			ub = (UserBean)session.createQuery(hql)
												 .setParameter("account", ob.getAccount())
												 .getSingleResult();
			if(ub != null) {
				unpaidAmount = ub.getUnpaid_amount();
			}else {
				new UserNotFoundException("查無會員資料");
			}
			Long longUnpaidAmount = Math.round(currentAmount + unpaidAmount);
			if(longUnpaidAmount > GlobalService.ORDER_AMOUNT_LIMIT) {
				throw new UnpaidOrderAmountExceedingException("未付款金額超過限額" + longUnpaidAmount);
			}else {
				String hqlUpdate = "UPDATE UserBean u SET u.unpaid_amount = u.unpaid_amount + :amt "
						+ "WHERE account = :account";
				session.createQuery(hqlUpdate).setParameter("amt", longUnpaidAmount)
											  .setParameter("account", ob.getAccount())
											  .executeUpdate();
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public UserBean queryUser(String account) {
			UserBean ub = null;
			String hql = "FROM UserBean WHERE user_id = :uid";
			Session session = factory.getCurrentSession();
			List<UserBean> list = session.createQuery(hql).setParameter("uid", account)
												.getResultList();
			ub = (list.isEmpty() ? null : list.get(0));
			return ub;
		}

		
}
