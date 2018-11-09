package com.web.store.service;

import org.springframework.stereotype.Service;
import com.web.store.model.UserBean;

@Service
// 定義進行登入時系統必須執行的功能
public interface LoginService {
	public UserBean checkIDPassword(String account, String password) ;
}
