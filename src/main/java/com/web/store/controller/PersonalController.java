package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.web.store.model.FormBean;
import com.web.store.model.MessageBean;
import com.web.store.model.UserBean;
import com.web.store.model.WorkCommentBean;
import com.web.store.model.WorksBean;
import com.web.store.service.FormService;
import com.web.store.service.MessageService;
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
	@Autowired
	FormService formService;
	@Autowired
	MessageService messageService;
	
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
		messageService.changeUnreadMessageToRead(userId);
		List<MessageBean> receivedMessages = messageService.getReceivedMessages(userId);
		List<MessageBean> deliveredMessage = messageService.getDeliveredMessages(userId);
		model.addAttribute("receivedMessages", receivedMessages);
		model.addAttribute("deliveredMessage", deliveredMessage);
		return "_10_personalPage/personalPage";
	}
	@RequestMapping(value="/personalPageReadOnly")
	public String getPersonalPageReadOnly(@RequestParam(value = "id") Integer userId, Model model) {
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
		return "_10_personalPage/personalPageReadOnly";
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
		return "_06_workUp/worksList";
	}
	/* 測試存入留言 */
	@RequestMapping(value="/updateComment", params= {"newComment", "workId"}, method=RequestMethod.POST, 
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateComment(HttpServletRequest request, Model model, 
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
		String totalCommentStrNew = "";
		/**留言內容以WorkCommentBean暫存，每筆留言以"-#"做區隔，每筆留言之元素以"/="做區隔，
		  *範例:( 留言者ID /= 留言者暱稱 /= 留言時間 /= 留言內容 -#)作為儲存格式(不包含空白)
		  **/
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
		return "success";
	}
//	/* 測試存入留言 */
//	@RequestMapping(value="/updateComment", params= {"newComment", "workId"}, method=RequestMethod.POST, 
//			produces="text/html;charset=UTF-8")
//	public String updateComment(HttpServletRequest request, Model model, 
//			@RequestParam("workId")String workIdStr, 
//			@RequestParam("newComment")String newComment) {
//		WorksBean wb = null;
//		if(workIdStr != null) {
//			Integer works_id = Integer.parseInt(workIdStr);
//			wb = worksService.getWorksById(works_id);
//		}
//		UserBean ub = (UserBean)request.getSession(false).getAttribute("LoginOK");
//		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
//		String commentDate = ts.toString();
//		String totalCommentStrNew = "";
//		/**留言內容以WorkCommentBean暫存，每筆留言以"-#"做區隔，每筆留言之元素以"/="做區隔，
//		 *範例:( 留言者ID /= 留言者暱稱 /= 留言時間 /= 留言內容 -#)作為儲存格式(不包含空白)
//		 **/
//		if (wb.getComment() != null) {
//			Clob totalComment = wb.getComment();
//			try {
//				String totalCommentStr = totalComment.getSubString(1, (int)totalComment.length());
//				totalCommentStrNew = totalCommentStr + ub.getUser_id() +"/=" +
//						ub.getNickname() +"/=" + commentDate + "/=" + newComment + "-#";
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else {
//			totalCommentStrNew = ub.getUser_id() + "/=" + ub.getNickname() + "/=" + 
//					commentDate + "/=" + newComment + "-#";
//		}
//		try {
//			Clob totalCommentNew = new SerialClob(totalCommentStrNew.toCharArray());
//			wb.setComment(totalCommentNew);
//			worksService.updateWorksComment(wb);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return "redirect:/works";
//	}
	
//	-------------------申請商品上架表單ajax-------------------
	@RequestMapping(value="/addForm", method=RequestMethod.POST)	
	public ResponseEntity<FormBean> processAddForm(HttpServletRequest request,@ModelAttribute FormBean fb) throws SerialException, SQLException {
		System.out.println(fb);		
			MultipartFile formImage = fb.getFormImage();
			String originalFilename = formImage.getOriginalFilename();
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			fb.setSubmitDate(date);
			//建立Blob物件，交由Hibernate寫入資料庫
			if(formImage != null && !formImage.isEmpty()) {
				fb.setFormImgName(originalFilename);
				String ext = originalFilename.substring(originalFilename.lastIndexOf("."));	
				try {
					byte[] b = formImage.getBytes();
					Blob blob = new SerialBlob(b);
					fb.setFormImg(blob);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
				try {
					File imageFolder = new File(rootDirectory+"images");
					if(!imageFolder.exists()) {
						imageFolder.mkdirs();
					}
					File file = new File(imageFolder, fb.getForm_id()+ext);
					formImage.transferTo(file);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
			}	
			fb.setStatus("待審核");
			formService.processForm(fb);
			
			return new ResponseEntity<FormBean>(HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getAllForms", produces="application/json")
    public @ResponseBody byte[] getAllForms(Model model) throws UnsupportedEncodingException {    
        List<FormBean> list = formService.getAllForms();
        byte[] FormJson = new Gson().toJson(list).getBytes("UTF-8");
        return FormJson;
    }
    @RequestMapping(value="/getFormImg/{form_Id}",method=RequestMethod.GET)
    public ResponseEntity<byte[]> getFormImg(@PathVariable("form_Id") Integer form_Id){
        String filePath = "/resources/images/NoImage.jpg";
        FormBean fb = formService.getFormById(form_Id);
        HttpHeaders headers = new HttpHeaders();
        String filename = "";
        int len = 0;
        byte[] media = null;
        if(fb!=null) {
            Blob blob = fb.getFormImg();
            filename = fb.getFormImgName();
            if(blob!= null) {
                try {
                    len = (int) blob.length();
                    media = blob.getBytes(1, len);
                } catch (SQLException e) {
                    throw new RuntimeException("FormController的getPicture()發生error"+e.getMessage());
                }
            }else {
                media = toByteArray(filePath);
                filename = filePath;
            }
        }else {
            media = toByteArray(filePath);
            filename = filePath;
        }
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        String mimeType = context.getMimeType(filename);
        MediaType mediaType = MediaType.valueOf(mimeType);
//        System.out.println("mediaType = "+mediaType);
        headers.setContentType(mediaType);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media,headers,HttpStatus.OK);
        return responseEntity;
    }
    private byte[] toByteArray(String filePath) {
        byte[] b= null;
        try {
            String realPath = context.getRealPath(filePath);
            File file = new File(realPath);
            long size = file.length();
            b= new byte[(int)size];
            InputStream fis = context.getResourceAsStream(filePath);
            fis.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

	
	/*測試送出(存入)信件*/
	@RequestMapping(value="/sendMail", params= {"receiverNickname", "messageTitle", "messageContent"}
					, produces="text/html;charset=UTF-8", method=RequestMethod.POST)
	public String sendMail(HttpServletRequest request, Model model, 
			@RequestParam("receiverNickname")String receiverNickname, 
			@RequestParam("messageTitle")String messageTitle, 
			@RequestParam("messageContent")String messageContent) {
		int afterSendMailTag = 0;
		/*取得信件送出者資料*/
		UserBean fromUser = (UserBean)request.getSession(false).getAttribute("LoginOK");
		/*若暱稱對應到使用者時，依照暱稱取得信件收取者資料*/
		if (userService.nicknameExists(receiverNickname)) {
			UserBean toUser = userService.getUserByNickname(receiverNickname);
			/*取得信件送出時間*/
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String posttime = df.format(new Date());
			/* MessageBean(主鍵,送件者ID,送件者暱稱,收件者ID,收件者暱稱,發信件時間,信件標題,信件內容,未讀(已讀)標記) */
			MessageBean mb = new MessageBean(null, 
					fromUser.getUser_id(), 
					fromUser.getNickname(), 
					toUser.getUser_id(), 
					toUser.getNickname(), 
					posttime, 
					messageTitle, 
					messageContent, 
					0);
			afterSendMailTag = messageService.insertMessage(mb);
		}
		return "redirect:/personalPage?id=" + fromUser.getUser_id();
	}
	/*管理者審核商品上架時，送出審核信件*/
	@RequestMapping(value="/reviewMail/{form_id}/{review}")
	@ResponseBody
	public void sendReviewMail(HttpServletRequest request, 
			@PathVariable("review")String review,
			@PathVariable("form_id")Integer form_id) {
		UserBean user = (UserBean)request.getSession(false).getAttribute("LoginOK");
		UserBean master = userService.getUser2("starup@gamil.com");
		FormBean fb = formService.getFormById(form_id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		int afterSendMailTag = 0;
		String applicationSuccess = user.getNickname() + 
				"您好，恭喜您！先前您所填寫的商品上架表單，經過審核後，已通過商品上架的審核，您的" + 
				fb.getFormProdName() + 
				"商品已完成上架，感謝您！";
		String applicationFailed = user.getNickname() + 
				"您好，先前您所填寫的商品上架表單，經過審核後，因商品含有侵權的可能性或不當的商品名稱與敘述，"
				+ "未通過商品上架的審核，很抱歉，目前無法完成此商品的上架！";
		MessageBean successMb = new MessageBean(null, 
				 master.getUser_id(), 
				 master.getNickname(), 
				 user.getUser_id(), 
				 user.getNickname(), 
				 date, 
				 "申請上架通過", 
				 applicationSuccess, 
				 0);
		MessageBean failedMb = new MessageBean(null, 
				 master.getUser_id(), 
				 master.getNickname(), 
				 user.getUser_id(), 
				 user.getNickname(), 
				 date, 
				 "申請上架未通過", 
				 applicationFailed, 
				 0);
		if (review.equalsIgnoreCase("approved")) {
			formService.updateFormStatus(form_id, "通過審核");
			afterSendMailTag = messageService.insertMessage(successMb);
		} else if (review.equalsIgnoreCase("notApproved")) {
			formService.updateFormStatus(form_id, "未通過審核");
			afterSendMailTag = messageService.insertMessage(failedMb);
		}
	}
}