package com.web.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.store.model.OrderedItem;
import com.web.store.model.ProductBean;
import com.web.store.model.ShoppingCart;
import com.web.store.service.ProductService;

@Controller
public class ProductController {
		@Autowired
		ProductService prodService;
		@Autowired
		ServletContext context;
		
		@RequestMapping("/products")
		public String listProduct(Model model) {
			List<ProductBean> list = prodService.getAllProducts();
			model.addAttribute("products", list);			
			return "/_03_product/listProducts";
		}
		@RequestMapping(value="/getPicture/{prodId}", method = RequestMethod.GET)
		public ResponseEntity<byte[]> getPicture
		(HttpServletResponse resp,@PathVariable Integer prodId){
			String filePath = "/resources/images/NoImage.jpg";
			ProductBean bean = prodService.getProductById(prodId);
			HttpHeaders headers = new HttpHeaders();
			String filename = "";
			int len = 0;
			byte[] media = null;
			if(bean!=null) {
				Blob blob = bean.getProdImg();
				filename = bean.getProdImgName();
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
		@RequestMapping("/productDetail")
	    public String getProductById(@RequestParam("id")Integer id, Model model) {
	    	model.addAttribute("product", prodService.getProductById(id));
	    	return "/_03_product/productDetail";
	    }
		
	
		@RequestMapping(value= "/productMaintain")
		public String productMaintainPage(Model model) {
			List<ProductBean> list = prodService.getAllProducts();
			model.addAttribute("products", list);
			return "_20_productMaintain/ProductMaintainList";
		}
//		-------------------更新商品-------------------
		@RequestMapping(value = "/modifyProduct")
		public String getProductModifyForm(Model model, @RequestParam(value = "id") int id) {
			ProductBean pb = new ProductBean();
			model.addAttribute("product", prodService.getProductById(id));
			model.addAttribute("productBean", pb);
	    	return "/_20_productMaintain/ProductMaintainPage";
		}
		@RequestMapping(value = "/modifyProduct", method = RequestMethod.POST,
				produces="text/html;charset=UTF-8")
		public String processProductMaintain(@ModelAttribute("productBean") ProductBean pb,
				@RequestParam(value = "id") int id,
				BindingResult result, HttpServletRequest request) throws UnsupportedEncodingException {			
			String [] suppressedField = result.getSuppressedFields();
			pb.setProd_id(id);
			long sizeInByte = 0;
			if(suppressedField.length > 0 ) {
				throw new RuntimeException("嘗試傳入不合法的欄位 :"
									+StringUtils.arrayToCommaDelimitedString(suppressedField));
			}
			MultipartFile productImage = pb.getProductImage();
			String originalFilename = productImage.getOriginalFilename();
			pb.setProdImgName(originalFilename);
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");
			
			//建立Blob物件，交由Hibernate寫入資料庫
			if(productImage != null && !productImage.isEmpty()) {
				pb.setProdImgName(originalFilename);
				String ext = originalFilename.substring(originalFilename.lastIndexOf("."));	
				try {
					byte[] b = productImage.getBytes();
					Blob blob = new SerialBlob(b);					
					pb.setProdImg(blob);				
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
					File file = new File(imageFolder, pb.getProd_id()+ext);				
					productImage.transferTo(file);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
			}
			prodService.updateProduct(pb,sizeInByte);
			return "redirect:/productMaintain";
		}
		
		@RequestMapping(value="/modifyProduct/{id}", method=RequestMethod.PUT)
		public ResponseEntity<ProductBean> updateProduct(@PathVariable("id") int id, @RequestBody ProductBean productBean){
			System.out.println("更新商品資訊 "+id);
			ProductBean pb = prodService.getProductById(id);
			if(pb == null) {
				System.out.println("該商品不存在");
				return new ResponseEntity<ProductBean>(HttpStatus.NOT_FOUND);
			}
			pb.setProdName(productBean.getProdName());
			
			return null;
		}
//		-------------------新增商品-------------------
		@RequestMapping(value="/addProduct", method=RequestMethod.GET)
		public String getAddProductForm(Model model) {
			ProductBean pb = new ProductBean();
			model.addAttribute("productBean", pb);
			return "_20_productMaintain/AddProduct"; 
		}
		@RequestMapping(value="/addProduct", method=RequestMethod.POST, produces="text/html;charset=UTF-8")
		public String processAddNewProductForm(@ModelAttribute("productBean") ProductBean pb,
																				 BindingResult result, HttpServletRequest request) {		
			String [] suppressedField = result.getSuppressedFields();
			if(suppressedField.length > 0 ) {
				throw new RuntimeException("嘗試傳入不合法的欄位 :"
									+StringUtils.arrayToCommaDelimitedString(suppressedField));
			}
			MultipartFile productImage = pb.getProductImage();
			String originalFilename = productImage.getOriginalFilename();
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			pb.setProdUpDate(date);
			//建立Blob物件，交由Hibernate寫入資料庫
			if(productImage != null && !productImage.isEmpty()) {
				pb.setProdImgName(originalFilename);
				String ext = originalFilename.substring(originalFilename.lastIndexOf("."));	
				try {
					byte[] b = productImage.getBytes();
					Blob blob = new SerialBlob(b);
					pb.setProdImg(blob);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
				
				try {
					File imageFolder = new File(rootDirectory+"images");
					if(!imageFolder.exists()) {
						imageFolder.mkdirs();
					}
					File file = new File(imageFolder, pb.getProd_id()+ext);
					productImage.transferTo(file);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常"+ e.getMessage());
				}
			}
			prodService.addProduct(pb);
			return "redirect:/products";
		}
		
		@RequestMapping(value="/addProductToCart", 
				params= {"prod_id", "prodName", "prodPrice", "prodCompany", "prodType", "prodIntro", "pageNo", "qty"}, 
				method=RequestMethod.POST, produces="text/html;charset=UTF-8")
		public String addProductsIntoShoppingCart(HttpServletRequest request, Model model, 
				@RequestParam(value="prod_id") String idStr, 
				@RequestParam(value="prodName") String prodName, 
				@RequestParam(value="prodPrice") String priceStr, 
				@RequestParam(value="prodCompany") String prodCompany, 
				@RequestParam(value="prodType") String prodType, 
				@RequestParam(value="prodIntro") String prodIntro, 
				@RequestParam(value="pageNo") String pageNo, 
				@RequestParam(value="qty") String qtyStr) {
			// 只要舊的Session物件，如果找不到，不要建立新的Session物件，直接傳回 null
			if(request.getSession(false) != null) {
				HttpSession session = request.getSession(false);
				// 取出存放在session物件內的ShoppingCart物件
				ShoppingCart cart = (ShoppingCart)session.getAttribute("ShoppingCart");
				// 如果找不到ShoppingCart物件
				if(cart == null) {
					// 就新建ShoppingCart物件
					cart = new ShoppingCart();
					// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
					session.setAttribute("ShoppingCart", cart);
				}
				Integer prod_id = 0;
				Integer prodPrice = 0;
				Integer qty = 0;
				if (pageNo == null) {
					pageNo = "1";
				}
				try {
					// 進行資料型態的轉換
					prod_id = Integer.parseInt(idStr.trim());
					prodPrice = Integer.parseInt(priceStr.trim());
					qty = Integer.parseInt(qtyStr.trim());
				} catch(NumberFormatException e) {
					e.printStackTrace();
				}
				// 將訂單資料封裝到OrderedItem物件內
				OrderedItem oi = new OrderedItem(prod_id, prodName, prodPrice, prodCompany, 
						prodType, prodIntro, qty);
				// 將OrderedItem物件內加入ShoppingCart的物件內
				cart.addToCart(prod_id, oi);
			}
			return "redirect:/products";
		}
		
		@RequestMapping(value="/showShoppingCart", method=RequestMethod.GET)
		public String showShoppingCart(HttpServletRequest request, Model model) {
			
			return "_04_shoppingCart/shoppingCart";
		}
		
		@RequestMapping(value="/updateShoppingCart/{CMD}/{prod_id}/{newQty}", method=RequestMethod.POST)
		public String UpdateShoppingCart(@ModelAttribute("ShoppingCart") ShoppingCart sc, 
				@PathVariable("CMD") String cmd, 
				@PathVariable("prod_id") String prod_idStr, 
				@PathVariable("newQty") String newQtyStr) {
			Integer prod_id = Integer.parseInt(prod_idStr.trim());
			if(cmd.equalsIgnoreCase("DEL")) {
				sc.deleteProduct(prod_id);
			} else if(cmd.equalsIgnoreCase("MOD")) {
				Integer newQty = Integer.parseInt(newQtyStr.trim());
				sc.modifyQty(prod_id, newQty);
			}
			return "redirect:/showShoppingCart";
		}
		
}
