package com.web.store.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.UserBean;
import com.web.store.service.UserService;
@Controller
public class PersonalController {
	@Autowired
	ServletContext context;
	@Autowired
	UserService userService;
	
	public PersonalController() {
		
	}
	@RequestMapping(value="/personalPage")
	public String getPersonalPage(@RequestParam(value = "id") Integer userId, Model model) {
		UserBean ub = userService.getUser(userId);
		model.addAttribute("userBean", ub);
		return "_10_personalPage/personalPage";
	}

	@RequestMapping(value="/worksList")
	public String getWorkList() {
		return "_06_workUp/worksList";
	}
	@RequestMapping(value="/upload")
	public String getUploadForm() {
		return "_06_workUp/upload";
	}
}
