package com.web.store.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.store.model.MessageBean;
import com.web.store.model.UserBean;
import com.web.store.service.MessageService;

@Controller
public class IndexController {
    
	@Autowired
	ServletContext context;
	@Autowired
	MessageService messageService;
    
    @RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 視圖的邏輯名稱，經由視圖解析器的解析後會得到真實視圖的位置: 
		//   /WEB-INF/views/index.jsp
    	if(request.getSession(false) != null) {
    		HttpSession session = request.getSession(false);
    		if (session.getAttribute("LoginOK") != null) {
    			UserBean ub = (UserBean)session.getAttribute("LoginOK");
    			List<MessageBean> unreadMessageList = messageService.getUnreadMessages(ub.getUser_id());
    			Integer unreadMessageNumber = unreadMessageList.size();
    			model.addAttribute("unreadMessageNumber", unreadMessageNumber);
    		}
    	}
		return "index";  
	}   
    @RequestMapping("/backstage")
    public String getBackstagePage() {
    	return "Backstage";
    }
}


