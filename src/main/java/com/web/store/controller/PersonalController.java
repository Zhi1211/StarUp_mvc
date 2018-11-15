package com.web.store.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.web.store.model.UserBean;
import com.web.store.model.WorkCommentBean;
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
	/* 測試跳轉作品詳細頁面 */
	@RequestMapping(value="/testComment")
	public String testComment(Model model, @RequestParam("worksId") Integer worksId) {
		WorksBean wb = null;
		List<WorkCommentBean> list = new ArrayList<>();
		if (worksId != null) {
			wb = worksService.getWorksById(worksId);			
		}
		if (wb != null && wb.getComment() != null) {
			WorkCommentBean wcb = null;
			Clob totalComment = wb.getComment();
			try {
				String totalCommentStr = totalComment.getSubString(1, (int)totalComment.length() - 2);
				String[] oneCommentElementStr = null;
				if (totalCommentStr.contains("-#")) {
					String[] totalCommentStrArr = totalCommentStr.split("-#");
					for(String oneCommentStr : totalCommentStrArr) {
						oneCommentElementStr = oneCommentStr.split("/=");
						wcb = new WorkCommentBean(
								Integer.parseInt(oneCommentElementStr[0]), 
								oneCommentElementStr[1], 
								oneCommentElementStr[2], 
								oneCommentElementStr[3]);
						list.add(wcb);
					}
				} else {
					oneCommentElementStr = totalCommentStr.split("/=");
					wcb = new WorkCommentBean(
							Integer.parseInt(oneCommentElementStr[0]), 
							oneCommentElementStr[1], 
							oneCommentElementStr[2], 
							oneCommentElementStr[3]);
					list.add(wcb);
				}
				model.addAttribute("commentsElement", list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("oneWork", wb);
		return "_06_workUp/works";
	}
	/* 測試存入留言 */
	@RequestMapping(value="/updateComment", params= {"newComment", "workId"}, method=RequestMethod.POST, 
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public void updateComment(HttpServletRequest request, Model model, 
			@RequestParam("workId")String workIdStr, 
			@RequestParam("newComment")String newComment) {
		WorksBean wb = null;
		if(workIdStr != null) {
			Integer works_id = Integer.parseInt(workIdStr);
			wb = worksService.getWorksById(works_id);
		}
		UserBean ub = (UserBean)request.getSession(false).getAttribute("LoginOK");
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		String commentDate = ts.toString();
		//String comment = request.getParameter("newComment");
		String totalCommentStrNew = "";
		if (wb.getComment() != null) {
			Clob totalComment = wb.getComment();
			try {
				String totalCommentStr = totalComment.getSubString(1, (int)totalComment.length());
				totalCommentStrNew = totalCommentStr + ub.getUser_id() +"/=" +
						ub.getNickname() +"/=" + commentDate + "/=" + newComment + "-#";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			totalCommentStrNew = ub.getUser_id() + "/=" + ub.getNickname() + "/=" + 
								commentDate + "/=" + newComment + "-#";
		}
		try {
			Clob totalCommentNew = new SerialClob(totalCommentStrNew.toCharArray());
			wb.setComment(totalCommentNew);
			worksService.updateWorksComment(wb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
