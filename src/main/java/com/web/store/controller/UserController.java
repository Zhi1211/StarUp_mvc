package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.web.store.model.UserBean;
import com.web.store.service.UserService;


@Controller
public class UserController {
	@Autowired
	ServletContext context;
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
	
	@RequestMapping(value="/getUserPicture/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getUserPicture
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
					throw new RuntimeException("UserController的getPicture()發生error"+e.getMessage());
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
//		System.out.println("mediaType = "+mediaType);
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
	@RequestMapping(value="/updateUser/{id}", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<UserBean> updateUser(@PathVariable("id") int userId,
			@ModelAttribute UserBean userBean,BindingResult result, HttpServletRequest request){
		String [] suppressedField = result.getSuppressedFields();
		userBean.setUser_id(userId);;
		long sizeInByte = 0;
		if(suppressedField.length > 0 ) {
			throw new RuntimeException("嘗試傳入不合法的欄位 :"
								+StringUtils.arrayToCommaDelimitedString(suppressedField));
		}
		Clob introduction;
		String userIntro = userBean.getUserIntro();
		try {
			introduction = new SerialClob(userIntro.toCharArray());
			userBean.setIntroduction(introduction);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		MultipartFile userImage = userBean.getUserImage();
		String originalFilename = userImage.getOriginalFilename();		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		
		//建立Blob物件，交由Hibernate寫入資料庫
		if(userImage != null && !userImage.isEmpty()) {
			userBean.setPhotoName(originalFilename);
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));	
			try {
				byte[] b = userImage.getBytes();
				Blob blob = new SerialBlob(b);					
				userBean.setPhoto(blob);	
				sizeInByte = blob.length();
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, userBean.getUser_id()+ext);				
				userImage.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}else {
			userService.updateUser(userBean);
			return new ResponseEntity<UserBean>(userBean,HttpStatus.OK);
		} 		
		userService.updateUser(userBean,sizeInByte);
		return new ResponseEntity<UserBean>(userBean,HttpStatus.OK);
	}
}
