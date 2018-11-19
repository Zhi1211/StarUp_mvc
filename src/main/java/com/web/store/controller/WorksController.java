package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.web.store.model.UserBean;
import com.web.store.model.WorksBean;
import com.web.store.service.WorksService;

@Controller
public class WorksController {
	@Autowired
	WorksService worksService;
	@Autowired
	ServletContext context;

	@RequestMapping("/works")
	public String listWorks(Model model) {
		List<WorksBean> list = worksService.getAllWorks();
		model.addAttribute("worksBean", list);
		return "_06_workUp/worksList";
	}
	@RequestMapping("/worksAjax")
	@ResponseBody
	public byte[] listWorksAjax(Model model) throws UnsupportedEncodingException {
		List<WorksBean> list = worksService.getAllWorks();
		byte[] worksJson = new Gson().toJson(list).getBytes("UTF-8");
		return worksJson;
	}
	//___________________________________作品照片讀取(worksImg)_________________________________________
			@RequestMapping(value="/mainWorksPicture/{works_Id}",method=RequestMethod.GET)
			public ResponseEntity<byte[]> getMainPicture(@PathVariable Integer works_Id){
				String filePath = "/resources/images/NoImage.jpg";
				WorksBean wb = worksService.getWorksById(works_Id);
				HttpHeaders headers = new HttpHeaders();
				String filename = "";
				int len = 0;
				byte[] media = null;
				if(wb!=null) {
					Blob blob = wb.getWorksImg();
					filename = wb.getWorksImgName();
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
//				System.out.println("mediaType = "+mediaType);
				headers.setContentType(mediaType);
				ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media,headers,HttpStatus.OK);
				return responseEntity;
			}
			
		//___________________________________作品照片讀取(CaptionImg_1)_________________________________________
					@RequestMapping(value="/readCaptionImg_1/{works_Id}",method=RequestMethod.GET)
					public ResponseEntity<byte[]> getReadCaptionImg_1(@PathVariable Integer works_Id){
						String filePath = "/resources/images/NoImage.jpg";
						WorksBean wb = worksService.getWorksById(works_Id);
						HttpHeaders headers = new HttpHeaders();
						String filename = "";
						int len = 0;
						byte[] media = null;
						if(wb!=null) {
							Blob blob = wb.getCaptionImg_1();
							filename = wb.getCaptionImgName_1();
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
//						System.out.println("mediaType = "+mediaType);
						headers.setContentType(mediaType);
						ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media,headers,HttpStatus.OK);
						return responseEntity;
					}
					//___________________________________作品照片讀取(CaptionImg_2)_________________________________________
					@RequestMapping(value="/readCaptionImg_2/{works_Id}",method=RequestMethod.GET)
					public ResponseEntity<byte[]> getReadCaptionImg_2(@PathVariable Integer works_Id){
						String filePath = "/resources/images/NoImage.jpg";
						WorksBean wb = worksService.getWorksById(works_Id);
						HttpHeaders headers = new HttpHeaders();
						String filename = "";
						int len = 0;
						byte[] media = null;
						if(wb!=null) {
							Blob blob = wb.getCaptionImg_2();
							filename = wb.getCaptionImgName_2();
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
//						System.out.println("mediaType = "+mediaType);
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
	

//___________________________________________________________________________________________________
	@RequestMapping("/worksDetail")
    public String getWorksById(@RequestParam("id")Integer id, Model model) {
    	model.addAttribute("works", worksService.getWorksById(id));
    	return "_06_workUp/worksDetail";
    }
	
	@RequestMapping(value= "/worksMaintain", produces="application/json")
	public @ResponseBody byte[] wokesMaintainPage(Model model) throws UnsupportedEncodingException {	
		List<WorksBean> list = worksService.getAllWorks();
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
		MultipartFile  worksPoto = wb.getWorksPhoto();
		MultipartFile  captionPoto_1 = wb.getCaptionPhoto_1();
		MultipartFile  captionPoto_2 = wb.getCaptionPhoto_2();
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
		worksService.saveWorks(wb);
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
//	@RequestMapping(value = "/modifyWorks", method = RequestMethod.POST,
//			produces="text/html;charset=UTF-8")
//	public String processWorksMaintain(@ModelAttribute("WorksBean") WorksBean wb,
//			@RequestParam(value = "id") int id,
//			BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException {			
//		String [] suppressedField = result.getSuppressedFields();
//		wb.setWorks_id(id);
//		long sizeInBytes= 0;
//		long sizeInBytes_1= 0;
//		long sizeInBytes_2= 0;
//		
//		if(suppressedField.length > 0 ) {
//			throw new RuntimeException("嘗試傳入不合法的欄位 :"
//								+StringUtils.arrayToCommaDelimitedString(suppressedField));
//		}
//		
//		MultipartFile  worksPoto = wb.getWorksPhoto();
//		MultipartFile  captionPoto_1 = wb.getCaptionPhoto_1();
//		MultipartFile  captionPoto_2 = wb.getCaptionPhoto_2();
//		String originalFilename_0 = worksPoto .getOriginalFilename();
//		String originalFilename_1 = captionPoto_1 .getOriginalFilename();
//		String originalFilename_2 = captionPoto_2 .getOriginalFilename();
//		
//		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date = df.format(new Date());
//		wb.setWorksUpDate(date);
//		
//		// 建立Blob物件，交由Hibernate寫入資料庫
//		//建立worksImg圖片
//		if(worksPoto != null && !worksPoto.isEmpty()) {
//			wb.setWorksImgName(originalFilename_0);
//			String ext_0 = originalFilename_0.substring(originalFilename_0.lastIndexOf("."));	
//			try {
//				byte[] b = worksPoto.getBytes();
//				Blob blob = new SerialBlob(b);
//				wb.setWorksImg(blob);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//			
//			try {
//				File imageFolder = new File(rootDirectory+"images");
//				if(!imageFolder.exists()) {
//					imageFolder.mkdirs();
//				}
//				File file = new File(imageFolder, wb.getWorks_id()+ext_0);
//				worksPoto.transferTo(file);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//		}
//		//建立captionImg_1圖片
//		if(captionPoto_1 != null && !captionPoto_1.isEmpty()) {
//			wb.setCaptionImgName_1(originalFilename_1);
//			String ext_1 = originalFilename_1.substring(originalFilename_1.lastIndexOf("."));	
//			try {
//				byte[] b = captionPoto_1.getBytes();
//				Blob blob = new SerialBlob(b);
//				wb.setCaptionImg_1(blob);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//			
//			try {
//				File imageFolder = new File(rootDirectory+"images");
//				if(!imageFolder.exists()) {
//					imageFolder.mkdirs();
//				}
//				File file = new File(imageFolder, wb.getWorks_id()+ext_1);
//				captionPoto_1.transferTo(file);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//		}	
//		//建立captionImg_2圖片
//		if(captionPoto_2 != null && !captionPoto_2.isEmpty()) {
//			wb.setCaptionImgName_2(originalFilename_2);
//			String ext_2 = originalFilename_2.substring(originalFilename_2.lastIndexOf("."));	
//			try {
//				byte[] b = captionPoto_2.getBytes();
//				Blob blob = new SerialBlob(b);
//				wb.setCaptionImg_2(blob);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//			
//			try {
//				File imageFolder = new File(rootDirectory+"images");
//				if(!imageFolder.exists()) {
//					imageFolder.mkdirs();
//				}
//				File file = new File(imageFolder, wb.getWorks_id()+ext_2);
//				captionPoto_2.transferTo(file);
//			}catch(Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
//			}
//		}
//								
//		worksService.updateWorks( wb,sizeInBytes,sizeInBytes_1,sizeInBytes_2) ;
//		return "redirect:/backstage";
//	}
	
	@RequestMapping(value="/updateWorks/{id}", method = RequestMethod.POST)
    public ResponseEntity<WorksBean> updateWorks(@PathVariable("id") int worksId,
            @ModelAttribute WorksBean wb,BindingResult result, HttpServletRequest request){
        String [] suppressedField = result.getSuppressedFields();
        WorksBean worksBean = worksService.getWorksById(worksId);
        worksBean.setWorksIntro(wb.getWorksIntro());        
        if(suppressedField.length > 0 ) {
            throw new RuntimeException("嘗試傳入不合法的欄位 :"
                                +StringUtils.arrayToCommaDelimitedString(suppressedField));
        } 
        	worksService.updateWorks(worksBean);
            return new ResponseEntity<WorksBean>(worksBean, HttpStatus.OK);
        }
//----------------------刪除作品-----------------------
	@DeleteMapping(value = "/deleteWorks")
	public ResponseEntity<WorksBean> processDeleteWorks(@RequestParam(value = "id") int id) {
		if(worksService.getWorksById(id) != null) {
			worksService.deleteWorks(id);			
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	@RequestMapping(value = "/saveWorks" ,method = RequestMethod.POST)
	public String worksUpLoad(Model model, HttpServletRequest request,
			@ModelAttribute("worksBean") WorksBean wb,BindingResult result,
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
			
			String worksName = wb.getWorksName();
			String worksIntro = wb.getWorksIntro();
			String detail_1 = wb.getDetail_1();
			String detail_2 = wb.getDetail_2();
			
			Blob worksImg = null;
			Blob captionImg_1 =null;
			Blob captionImg_2 = null;
			
		
			MultipartFile[] multipartFile  =  {wb.getWorksPhoto(),wb.getCaptionPhoto_1(),wb.getCaptionPhoto_2()};
			String [] originalFilename = {multipartFile[0].getOriginalFilename(),multipartFile[1].getOriginalFilename(),multipartFile[2].getOriginalFilename()};
		
			
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");	
			String[] ext= new String[3]; 

			for(int x=0; x<multipartFile.length;x++) {
					if(originalFilename [x] !="") {
						if(originalFilename[x]==multipartFile[0].getOriginalFilename()) {
							wb.setWorksImgName(originalFilename[0]);
						}else if(originalFilename[x]==multipartFile[1].getOriginalFilename()) {
							wb.setCaptionImgName_1(originalFilename[1]);
						}else if(originalFilename[x]==multipartFile[2].getOriginalFilename()) {
							wb.setCaptionImgName_2(originalFilename[2]);
						}
						ext[x] =  originalFilename[x].substring(originalFilename[x].lastIndexOf(".")) ;
						}else {
								originalFilename[x]=null;
								if(x==0) {
									
								}else if(x==1) {
									wb.setCaptionImgName_1(null);
								}else if(x==2) {
									wb.setCaptionImgName_2(null);
								}
								ext[x] =  null ;
								}
				}			
				try {
					byte[] a = multipartFile[0].getBytes();
					worksImg= new SerialBlob(a);
					wb.setWorksImg(worksImg);
					
					
					if(multipartFile[1] !=null ) {
					byte[] b = multipartFile[1].getBytes();
					captionImg_1= new SerialBlob(b);
					wb.setCaptionImg_1(captionImg_1);
					}
					
					if(multipartFile[2] !=null ) {
					byte[] c = multipartFile[2].getBytes();
					captionImg_2= new SerialBlob(c);
					wb.setCaptionImg_2(captionImg_2);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
				

				try {
					File imageFolder = new File(rootDirectory+"images");
					if(!imageFolder.exists()) {
						imageFolder.mkdirs();
					}
					File file_0 = new File(imageFolder, wb.getWorks_id()+ext[0]);				
					multipartFile[0].transferTo(file_0);
					
					
					if(ext[1] != null) {
					File file_1 = new File(imageFolder, wb.getWorks_id()+ext[1]);
					multipartFile[1].transferTo(file_1);
					}
					
					if(ext[2] != null) {
					File file_2 = new File(imageFolder, wb.getWorks_id()+ext[2]);
					multipartFile[2].transferTo(file_2);
					}
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
			

			
			if (detail_1  == null || detail_1 .trim().length() == 0) {
				detail_1  =null;
			} else {
				session.setAttribute("detail_1 ",detail_1 );
			}
			
			if (detail_2  == null || detail_2 .trim().length() == 0) {
				detail_2  =null;
			} else{
				if (detail_1  == null ) {
					detail_1 =detail_2;
				}else {
					session.setAttribute("detail_2 ",detail_2);
				} 
			}


			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			String regday = ts.toString(); // 1029改String
			wb.setWorksUpDate(regday);
			
			UserBean mb = (UserBean) session.getAttribute("LoginOK");
			String Nickname= mb.getNickname();
			wb.setAuthor(Nickname);
			Integer id = mb.getUser_id();
			wb.setUser_Id(id);

			int n = worksService.saveWorks(wb);

			if (n == 1) {
				oKMsg.put("UpLoadWorksOK", "<Font color='red'>輸入OK</Font>");					
			} else {
				errorMsg.put("errorIDDup", "新增此筆資料有誤");
			}
			if (!errorMsg.isEmpty()) {
				return "forward:/upLoadWorksError";
			}
		return "forward:/personalPage?id="+id;
	}

		@RequestMapping(value = "/worksError")
		public String worksError(@ModelAttribute("errorMsg")HashMap<String,String> errorMsg,Model model) {
			model.addAttribute("errorMsg", errorMsg);
			return "_06_works/";
		}
}
