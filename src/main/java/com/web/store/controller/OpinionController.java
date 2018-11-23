package com.web.store.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model.OpinionBean;
import com.web.store.model.UserBean;
import com.web.store.model.WorksBean;
import com.web.store.service.OpinionService;



@Controller
public class OpinionController {
	@Autowired
	OpinionService opinionService;
	@Autowired
	ServletContext context;
	
	//________________________讀取意見回覆____________________________________________________________	
		@RequestMapping("/opinionForm")
		public String opinionForm(Model model) {
			List<OpinionBean> list = opinionService.getAllOpinion();
			model.addAttribute("worksBean", list);
			return "_06_workUp/worksList";
		}

	//_________________________________新增意見回覆__________________________________________________________________
	@RequestMapping(value = "/saveOpinion", method = RequestMethod.POST)
	@ResponseBody
	public String proposeOpinion(Model model, HttpServletRequest request, @ModelAttribute("opinionBean") OpinionBean ob,
			BindingResult result, @ModelAttribute("errorMsg") HashMap<String, String> errorMsg)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String[] suppressedField = result.getSuppressedFields();
		if (suppressedField.length > 0) {
			throw new RuntimeException("嘗試傳入不合法的欄位 :" + StringUtils.arrayToCommaDelimitedString(suppressedField));
		}

		errorMsg = new HashMap<String, String>();
		Map<String, String> oKMsg = new HashMap<String, String>();
		HttpSession session = request.getSession();
		model.addAttribute("MsgMap", errorMsg);
		session.setAttribute("MsgOK", oKMsg);

		String opinionName = ob.getOpinionName();
		String opinionMail = ob.getOpinionMail();
		String opinionField = ob.getOpinionField();

			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		String regday = ts.toString(); // 1029改String
		ob.setOpinionUpTime(regday);


		int n = opinionService.saveOpinion(ob);

		if (n == 1) {
			oKMsg.put("opioionUpLoad_OK", "<Font color='red'>輸入OK</Font>");
		}
		return "success";
	}
//_____________________________________________________________________________________________
	

}
