package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.web.store.model.WorksBean;
import com.web.store.service.WorksService;

@Controller
public class WorksControlller {
	@Autowired
	WorksService worksService;
	@Autowired
	ServletContext context;

	@RequestMapping("/works")
	public String listWorks(Model model) {
		List<WorksBean> list = worksService.getAllWorkss();
		model.addAttribute("workss", list);
		return "/_06_works/listWorks";
	}
//___________________________________________________________________________________________________
	
	@RequestMapping(value="/getPicture/{wordId}", method = RequestMethod.GET)
	public ResponseEntity<List> getPicture
	(HttpServletResponse resp,@PathVariable Integer wordId){
		String filePath = "/resources/images/NoImage.jpg";
		WorksBean bean = worksService.getWorksById(wordId);
		HttpHeaders headers = new HttpHeaders();
		String[] filename= null;
		int len = 0;
		byte[] media_0 = null;
		byte[] media_1 = null;
		byte[] media_2 = null;
		
			if(bean!=null) {
				Blob[] blob = {bean.getWorksImg(),bean.getCaptionImg_1(),bean.getCaptionImg_2()};
			filename[0]=bean.getWorksImgName();
			filename[1]=bean.getCaptionImgName_1();
			filename[2]=bean.getCaptionImgName_2();
			
			if(blob[0] !=null) {
				try {
					len = (int)blob[0].length();
					media_0=blob[0].getBytes(1, len);
				}catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生error"+e.getMessage());
				}
			}else {				
					media_0 = toByteArray(filePath);
					filename[0] = filePath;	
			}
			if(blob[1] !=null) {
				try {
					len = (int)blob[1].length();
					media_1=blob[1].getBytes(1, len);
				}catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生error"+e.getMessage());
				}
			}else {				
					media_1 = toByteArray(filePath);
					filename[1] = filePath;	
			}
			
			if(blob[2] !=null) {
				try {
					len = (int)blob[2].length();
					media_2=blob[2].getBytes(1, len);
				}catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生error"+e.getMessage());
				}
			}else {				
					media_2 = toByteArray(filePath);
					filename[2] = filePath;	
			}
		
		}else {			
			media_0 = toByteArray(filePath);
			filename[0] = filePath;
			}
		
				
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		for(int y = 0 ;y<3;y++) {
			String[] mimeType = null;
			mimeType[y]= context.getMimeType(filename[y]);
			MediaType[] mediaType = null;
			mediaType[y] = MediaType.valueOf(mimeType[y]);
			headers.setContentType(mediaType[y]);
		}

//		System.out.println("mediaType = "+mediaType);
		List<byte[]> list = new ArrayList<>();
		list.add(media_0);
		list.add(media_1);
		list.add(media_2);
		ResponseEntity<List> responseEntity = new ResponseEntity<>(list,headers,HttpStatus.OK);
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
//___________________________________________________________________________________________________
	@RequestMapping("/worksDetail")
    public String getWorksById(@RequestParam("id")Integer id, Model model) {
    	model.addAttribute("works", worksService.getWorksById(id));
    	return "/_03_works/worksDetail";
    }
	
	@RequestMapping(value= "/worksMaintain", produces="application/json")
	public @ResponseBody byte[] wokesMaintainPage(Model model) throws UnsupportedEncodingException {	
		List<WorksBean> list = worksService.getAllWorkss();
		byte[] WorksJson = new Gson().toJson(list).getBytes("UTF-8");
		return WorksJson;
	}
//----------------------新增作品-----------------------	
//	@RequestMapping(value="/addWorks, method=RequestMethod.GET)
//			public String getAddWorksForm(Model model) {
//		WorksBean wb = new WorksBean();
//		model.addAttribute("WorksBean", wb);
//		return ""; 
//	}

	@RequestMapping(value = "/addWorks", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String processAddNewWorksForm(@ModelAttribute("worksbean") WorksBean wb, BindingResult result,
			HttpServletRequest request) {
		String[] suppressedField = result.getSuppressedFields();
		if (suppressedField.length > 0) {
			throw new RuntimeException("嘗試傳入不合法的欄位 :" + StringUtils.arrayToCommaDelimitedString(suppressedField));
		}
		MultipartFile  worksPoto = wb.getWorksPoto();
		MultipartFile  captionPoto_1 = wb.getCaptionPoto_1();
		MultipartFile  captionPoto_2 = wb.getCaptionPoto_2();
		String originalFilename_0 = worksPoto .getOriginalFilename();
		String originalFilename_1 = captionPoto_1 .getOriginalFilename();
		String originalFilename_2 = captionPoto_2 .getOriginalFilename();
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		wb.setWorksUpDate(date);
		
		// 建立Blob物件，交由Hibernate寫入資料庫
		//建立worksImg圖片
		if(worksPoto != null && !worksPoto.isEmpty()) {
			wb.setWorksImgName(originalFilename_0);
			String ext_0 = originalFilename_0.substring(originalFilename_0.lastIndexOf("."));	
			try {
				byte[] b = worksPoto.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setWorksImg(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_0);
				worksPoto.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}
		//建立captionImg_1圖片
		if(captionPoto_1 != null && !captionPoto_1.isEmpty()) {
			wb.setCaptionImgName_1(originalFilename_1);
			String ext_1 = originalFilename_1.substring(originalFilename_1.lastIndexOf("."));	
			try {
				byte[] b = captionPoto_1.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setCaptionImg_1(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_1);
				captionPoto_1.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}	
		//建立captionImg_2圖片
		if(captionPoto_2 != null && !captionPoto_2.isEmpty()) {
			wb.setCaptionImgName_2(originalFilename_2);
			String ext_2 = originalFilename_2.substring(originalFilename_2.lastIndexOf("."));	
			try {
				byte[] b = captionPoto_2.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setCaptionImg_2(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_2);
				captionPoto_2.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}
		worksService.addWorks(wb);
		return "redirect:/Works";
	}
	

//----------------------更新作品-----------------------	
//	@RequestMapping(value = "/modifyWorks")
//	public String getWorksModifyForm(Model model, @RequestParam(value = "id") int id) {
//		WorksBean wb = new WorksBean();
//		model.addAttribute("Works", woksService.getWorksById(id));
//		model.addAttribute("WorksBean", wb);
//    	return "";
//	}
	@RequestMapping(value = "/modifyWorks", method = RequestMethod.POST,
			produces="text/html;charset=UTF-8")
	public String processWorksMaintain(@ModelAttribute("WorksBean") WorksBean wb,
			@RequestParam(value = "id") int id,
			BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException {			
		String [] suppressedField = result.getSuppressedFields();
		wb.setWorks_id(id);
		long sizeInBytes= 0;
		long sizeInBytes_1= 0;
		long sizeInBytes_2= 0;
		
		if(suppressedField.length > 0 ) {
			throw new RuntimeException("嘗試傳入不合法的欄位 :"
								+StringUtils.arrayToCommaDelimitedString(suppressedField));
		}
		
		MultipartFile  worksPoto = wb.getWorksPoto();
		MultipartFile  captionPoto_1 = wb.getCaptionPoto_1();
		MultipartFile  captionPoto_2 = wb.getCaptionPoto_2();
		String originalFilename_0 = worksPoto .getOriginalFilename();
		String originalFilename_1 = captionPoto_1 .getOriginalFilename();
		String originalFilename_2 = captionPoto_2 .getOriginalFilename();
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		wb.setWorksUpDate(date);
		
		// 建立Blob物件，交由Hibernate寫入資料庫
		//建立worksImg圖片
		if(worksPoto != null && !worksPoto.isEmpty()) {
			wb.setWorksImgName(originalFilename_0);
			String ext_0 = originalFilename_0.substring(originalFilename_0.lastIndexOf("."));	
			try {
				byte[] b = worksPoto.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setWorksImg(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_0);
				worksPoto.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}
		//建立captionImg_1圖片
		if(captionPoto_1 != null && !captionPoto_1.isEmpty()) {
			wb.setCaptionImgName_1(originalFilename_1);
			String ext_1 = originalFilename_1.substring(originalFilename_1.lastIndexOf("."));	
			try {
				byte[] b = captionPoto_1.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setCaptionImg_1(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_1);
				captionPoto_1.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}	
		//建立captionImg_2圖片
		if(captionPoto_2 != null && !captionPoto_2.isEmpty()) {
			wb.setCaptionImgName_2(originalFilename_2);
			String ext_2 = originalFilename_2.substring(originalFilename_2.lastIndexOf("."));	
			try {
				byte[] b = captionPoto_2.getBytes();
				Blob blob = new SerialBlob(b);
				wb.setCaptionImg_2(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
			
			try {
				File imageFolder = new File(rootDirectory+"images");
				if(!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, wb.getWorks_id()+ext_2);
				captionPoto_2.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
			}
		}
								
		worksService.updateWorks( wb,sizeInBytes,sizeInBytes_1,sizeInBytes_2) ;
		return "redirect:/backstage";
	}
	
	@RequestMapping(value="/modifyWorks/{id}", method=RequestMethod.PUT)
	public ResponseEntity<WorksBean> updateWorks(@PathVariable("id") int id, @RequestBody WorksBean worksBean){
		System.out.println("更新作品資訊 "+id);
		WorksBean wb = worksService.getWorksById(id);
		if(wb == null) {
			System.out.println("該作品不存在");
			return new ResponseEntity<WorksBean>(HttpStatus.NOT_FOUND);
		}
		wb.setWorksName(worksBean.getWorksName());
		
		return null;
	}
//----------------------刪除作品-----------------------
	@RequestMapping(value = "/deleteWorks", method = RequestMethod.POST)
	@ResponseBody
	public void processDeleteWorks(@RequestParam(value = "id") int id) {
		worksService.deleteWorks(id);
	}

}
