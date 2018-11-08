package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
