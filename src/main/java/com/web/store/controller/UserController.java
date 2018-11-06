package com.web.store.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.store.model.UserBean;
import com.web.store.service.UserService;


@Controller
public class UserController {
	@Autowired
	ServletContext sc;
	@Autowired
	UserService userService;
	public UserController() {
		
	}
	
	@RequestMapping(value="/listAllUsers", produces="application/json")
	public @ResponseBody byte[] listAllUsers(Model model) throws UnsupportedEncodingException {
		List<UserBean> list = userService.getAllUsers();
		byte[] userJson = new Gson().toJson(list).getBytes("UTF-8");
		return userJson;
	}

}
