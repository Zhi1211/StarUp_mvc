package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model.ProductBean;
import com.web.store.model.UserBean;
import com.web.store.service.UserService;

import _00_init.util.GlobalService;

@Controller
public class RegisterController {
	@Autowired
	ServletContext context;
	@Autowired
	UserService userService;
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	
	@RequestMapping(value =  "/register",method = RequestMethod.GET)
	public String getRegisterForm(Model model) {
		UserBean ub = new UserBean();
		model.addAttribute("userBean", ub);
		return "_01_register/Register";
	}

	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	public String processUserRegister(Model model, HttpServletRequest request,
		@ModelAttribute("userBean") UserBean ub,BindingResult result,
		@ModelAttribute("errorMsg")HashMap<String,String> errorMsg) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8") ;
		String [] suppressedField = result.getSuppressedFields();
		if(suppressedField.length > 0 ) {
			throw new RuntimeException("嘗試傳入不合法的欄位 :"
								+StringUtils.arrayToCommaDelimitedString(suppressedField));
		}
		errorMsg = new HashMap<String, String>();
		Map<String, String> oKMsg = new HashMap<String, String>();
		HttpSession session = request.getSession();
		model.addAttribute("MsgMap", errorMsg);
		session.setAttribute("MsgOK", oKMsg);
		Blob photo = null;
		String account = ub.getAccount();
		String password = ub.getPassword();
		String password2 = ub.getPassword2();
		String name = ub.getName();
		String nickname = ub.getNickname();
		String gender = ub.getGender();
		String birthday = ub.getBirthday();
		String phone = ub.getPhone();
		String address = ub.getAddress();
		String userIntro = ub.getUserIntro();
		
		MultipartFile userImage = ub.getUserImage();
		String originalFilename = userImage.getOriginalFilename();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date = df.format(new Date());
//		ub.setRegTime(date);
		//建立Blob物件，交由Hibernate寫入資料庫
		if(userImage != null && !userImage.isEmpty()) {
			ub.setPhotoName(originalFilename);
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));	
			try {
				byte[] b = userImage.getBytes();
				photo = new SerialBlob(b);
				ub.setPhoto(photo);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, ub.getUser_id()+ext);
				userImage.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}
	
		try {
		  if(userIntro.trim().length() != 0 ) {
				  Clob introduction = new SerialClob(userIntro.toCharArray());
				  ub.setIntroduction(introduction);
				} else {
				  String defaultIntro = "這個人很懶，什麼都沒寫...";	
				  Clob introduction = new SerialClob(defaultIntro.toCharArray());
					ub.setIntroduction(introduction);
				}
					}catch(Exception e) {
						e.printStackTrace();
					}
		
			if (account == null || account.trim().length() == 0) {
				errorMsg.put("errorIDEmpty", "帳號未輸入");
			} else {
				session.setAttribute("account", account);
			}
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼未輸入");
			}
			if (password2 == null || password2.trim().length() == 0) {
				errorMsg.put("errorPassword2Empty", "密碼未輸入");
			}
			if (password.trim().length() > 0 && password2.trim().length() > 0) {
				if (!password.trim().equals(password2.trim())) {
					errorMsg.put("errorPassword2Empty", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}
			if (name == null || name.trim().length() == 0) {
				errorMsg.put("errorName", "姓名未輸入");
			} else {
				session.setAttribute("name", name);
			}
			if (nickname == null || name.trim().length() == 0) {
				errorMsg.put("errorNickname", "暱稱未輸入");
			} else {
				session.setAttribute("nickname", nickname);
			}
			if (gender == null || gender.trim().length() == 0) {
				errorMsg.put("errorGender", "性別未輸入");
			} else {
				session.setAttribute("gender", gender);
			}
			if (birthday == null || birthday.trim().length() == 0) {
				errorMsg.put("errorBirthday", "生日未輸入");
			} else {
				session.setAttribute("birthday", birthday);
			}
			if (phone == null || phone.trim().length() == 0) {
				errorMsg.put("errorPhone", "電話未輸入");
			} else {
				session.setAttribute("phone", phone);
			}
			if (address == null || address.trim().length() == 0) {
				errorMsg.put("errorAddress", "地址未輸入");
			} else {
				session.setAttribute("address", address);
			}
			if (photo == null) {
				errorMsg.put("errorPhoto", "頭像圖片未上傳");
			} 
		
		if (errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			if (!matcher.matches()) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\\\"等四組資料組合而成，且長度不能小於八個字元");
			}
		}
		if (!errorMsg.isEmpty()) {
			return "forward:/registerError";
		}
		try {
			if (userService.idExists(account)) {
				errorMsg.put("errorIDDup", "此帳號已存在，請換新帳號");
			} else if (userService.nicknameExists(nickname)) {
				errorMsg.put(" ", "此暱稱已存在，請換新暱稱");
			} else {
				password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
				ub.setPassword(password);
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				String regday = ts.toString(); // 1029改String
				ub.setRegTime(regday);
				Date bir = java.sql.Date.valueOf(birthday);
				String bday = bir.toString(); // 1029改String
				ub.setBirthday(bday);
				int n = userService.saveUser(ub);
				if (n == 1) {
					oKMsg.put("InsertOK", "<Font color='red'>輸入OK</Font>");					
					return "_01_register/RegisterSuccess";
				} else {
					errorMsg.put("errorIDDup", "新增此筆資料有誤");
				}
			}
			if (!errorMsg.isEmpty()) {
				return "forward:/registerError";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			return "forward:/registerError";
		}
 		return "forward:/registerError";
	}
		@RequestMapping(value = "/registerError")
		public String registerError(@ModelAttribute("errorMsg")HashMap<String,String> errorMsg,Model model) {
			model.addAttribute("errorMsg", errorMsg);
			return "_01_register/Register";
		}
		@RequestMapping(value="/getUserPhoto/{userId}", method = RequestMethod.GET)
		public ResponseEntity<byte[]> getPicture
		(HttpServletResponse resp,@PathVariable Integer userId){
			String filePath = "/resources/images/NoImage.jpg";
			UserBean bean = userService.getUser(userId);
			HttpHeaders headers = new HttpHeaders();
			String filename = "";
			int len = 0;
			byte[] media = null;
			if(bean!=null) {
				Blob blob = bean.getPhoto();
				filename = bean.getPhotoName();
				if(blob!= null) {
					try {
						len = (int) blob.length();
						media = blob.getBytes(1, len);
					} catch (SQLException e) {
						throw new RuntimeException("ProductController的getPicture()發生error"+e.getMessage());
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
//			System.out.println("mediaType = "+mediaType);
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
//		@RequestMapping(value= "/checkAccount")
//		public String checkAccount() {
//			return null;
//		}
//		@RequestMapping(value= "/checkNickname")
//		public String checkNickname() {
//			return null;
//		}
}
