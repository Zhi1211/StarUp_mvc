package com.web.store.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.store.model.UserBean;
import com.web.store.model.WorksBean;
import com.web.store.service.UserService;
import com.web.store.service.WorksService;
@Controller
public class PersonalController {
	@Autowired
	ServletContext context;
	@Autowired
	UserService userService;
	@Autowired
	WorksService worksService;
	public PersonalController() {
		
	}
	@RequestMapping(value="/personalPage")
	public String getPersonalPage(@RequestParam(value = "id") Integer userId, Model model) {
		UserBean ub = userService.getUser(userId);
		Clob introduction = ub.getIntroduction();
		String intro = null;
		try {
			intro = introduction.getSubString(1, (int) ub.getIntroduction().length());
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		model.addAttribute("userBean", ub);
		model.addAttribute("introduction", intro);
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
	@RequestMapping(value="/userWorks", method=RequestMethod.POST)
	public @ResponseBody byte[] listUserWorks(@RequestParam(value="userId") Integer userId) throws UnsupportedEncodingException {
		System.out.println("111111");
		List<WorksBean> list = worksService.getWorksByUserId(userId);
		byte[] worksJson = new Gson().toJson(list).getBytes("UTF-8");
		return worksJson;	
	}
	@RequestMapping(value="/testComment")
    public String testComment(Model model, @RequestParam("worksId") Integer worksId) {
        WorksBean wb = null;
        if (worksId != null) {
            wb = worksService.getWorksById(worksId);            
        }
        model.addAttribute("oneWork", wb);
        return "_06_workUp/works";
    }
}
