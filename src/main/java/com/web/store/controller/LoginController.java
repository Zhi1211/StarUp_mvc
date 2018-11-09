package com.web.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.store.model.UserBean;
import com.web.store.service.LoginService;

import _00_init.util.GlobalService;




@Controller
public class LoginController {
	@Autowired
	ServletContext sc;
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",  method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的Map物件
				Map<String, String> errorMsgMap = new HashMap<String, String>();
		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
				request.setAttribute("ErrorMsgKey", errorMsgMap);
				// 讀取使用者輸入資料
				String account = request.getParameter("account");
				// 設定管理者帳號starup@gamil.com才能進入商城的 維護-商品管理ProductMaintainList.jsp
				if (account.equals("starup@gamil.com")) {
					session.setAttribute("BOSS", account);
				}
				String password = request.getParameter("password");
				String rm = request.getParameter("rememberMe");
				String requestURI = (String) session.getAttribute("requestURI");
				// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
				if (account == null || account.trim().length() == 0) {
					errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
				}
				// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
				if (password == null || password.trim().length() == 0) {
					errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
				}
				// **********Remember Me****************************
				Cookie cookieUser = null;
				Cookie cookiePassword = null;
				Cookie cookieRememberMe = null;
				if (rm != null) {
					cookieUser = new Cookie("user", account);
					cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
					cookieUser.setPath(request.getContextPath());

					String encodePassword = GlobalService.encryptString(password);
					cookiePassword = new Cookie("password", encodePassword);
					cookiePassword.setMaxAge(7 * 24 * 60 * 60);
					cookiePassword.setPath(request.getContextPath());

					cookieRememberMe = new Cookie("rm", "true");
					cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
					cookieRememberMe.setPath(request.getContextPath());
				} else { // 使用者沒有對 RememberMe 打勾
					cookieUser = new Cookie("user", account);
					cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
					cookieUser.setPath(request.getContextPath());

					String encodePassword = GlobalService.encryptString(password);
					cookiePassword = new Cookie("password", encodePassword);
					cookiePassword.setMaxAge(0);
					cookiePassword.setPath(request.getContextPath());

					cookieRememberMe = new Cookie("rm", "false");
					cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
					cookieRememberMe.setPath(request.getContextPath());
				}
				response.addCookie(cookieUser);
				response.addCookie(cookiePassword);
				response.addCookie(cookieRememberMe);
				
				// 將密碼加密兩次，以便與存放在表格內的密碼比對
				password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
				UserBean mb = null;
				try {
					// 呼叫 loginService物件的 checkIDPassword()，傳入account與password兩個參數
					mb = loginService.checkIDPassword(account, password);
					if (mb != null) {
						// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
						session.setAttribute("LoginOK", mb);
					} else {
						// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
						errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
					}
				} catch (RuntimeException ex) {
					errorMsgMap.put("LoginError", ex.getMessage());
				}

				// 5.依照 Business Logic 運算結果來挑選適當的畫面
				// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
				if (errorMsgMap.isEmpty()) {
					// 此時不要用下面兩個敘述，因為瀏覽器的網址列不會改變
					// RequestDispatcher rd = request.getRequestDispatcher("...");
					// rd.forward(request, response);
					if (requestURI != null) {
						requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
						return "redirect:/requestURI";
					} else {		
						return "redirect:/";
					}
				} else {
						return "redirect:/loginError";
				}
			}
			@RequestMapping("/logout")
			public String logout() {
				return "_02_login/logout";
			}
			@RequestMapping("/loginError")
			public String loginError() {
				return "_02_login/loginError"; 
			}
	}

