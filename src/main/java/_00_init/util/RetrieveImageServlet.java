//package _00_init.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.sql.SQLException;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import _01_register.model.UserBean;
//import _01_register.service.UserService;
//import _03_listProducts.model.ProductBean;
//import _03_listProducts.service.ProductService;
//import _03_listProducts.service.impl.ProductServiceImpl;
//import _06_works.service.WorksService;
//
//
//// 
//// 程式功能：
//// 本Servlet 類別會依據傳入的主鍵呼叫Service元件以讀取該主鍵所對應的紀錄，取出該紀錄內的BLOB欄，
//// 進而讀取存放在BLOB欄內的圖片資料，然後傳回給提出請求的瀏覽器。
//
//@WebServlet("/Util/getImage")
//public class RetrieveImageServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	@SuppressWarnings("resource")
//	synchronized protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		OutputStream os = null;
//		InputStream is = null;
//		String fileName = null;
//		HttpSession session = request.getSession();
//		try {
//			ServletContext sc;
//			WebApplicationContext ctx;
//			// 讀取瀏覽器傳送來的主鍵
//			String id = request.getParameter("id");
//			String account = (String) session.getAttribute("account");
//			System.out.println("id: " +id);
//			System.out.println("account: "+account);
//			// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
//			String type = request.getParameter("type"); 
//			switch(type.toUpperCase()){
//				case "USER":
////					UserDao userDao = new UserDaoImpl();
//					sc = getServletContext();
//					ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//					UserService service = ctx.getBean(UserService.class);
//					int nId = 0;
//					try {
//						nId = Integer.parseInt(id);
//					}catch(NumberFormatException ex) {
//						ex.printStackTrace();
//					}
//					try{
//						UserBean bean1 = service.getUser(nId);						
//						System.out.println(bean1.getName());
//						is = bean1.getPhoto().getBinaryStream();
//						fileName = bean1.getPhotoName();
//						System.out.println(fileName);
//						break;
//					}catch(NullPointerException e) {
//						e.printStackTrace();
//					}
//				case "USER2":			
//					sc = getServletContext();
//					ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//					UserService service2 = ctx.getBean(UserService.class);
//					UserBean bean2 = service2.getUser2(account);						
//					System.out.println(bean2.getName());
//					is = bean2.getPhoto().getBinaryStream();
//					fileName = bean2.getPhotoName();
//					System.out.println(fileName);
//					break;
//					
//				case "PRODUCT":
//					nId = 0;
//					sc = getServletContext();
//					ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//					ProductService productService = ctx.getBean(ProductService.class);
//					try {
//						nId = Integer.parseInt(id);
//					}catch(NumberFormatException ex) {
//						ex.printStackTrace();
//					}
//					ProductBean bean3 = productService.getProd(nId);
//					System.out.println(bean3);
//					is = bean3.getProdImg().getBinaryStream();  
//					fileName = bean3.getProdImgName();
//					break;
////				case "WORKS":
////					nId = 0;
////					sc = getServletContext();
////					ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
////					WorksService worksService = ctx.getBean(ProductService.class);
////					try {
////						nId = Integer.parseInt(id);
////					}catch(NumberFormatException ex) {
////						ex.printStackTrace();
////					}
////					ProductBean bean3 = productService.getProd(nId);
////					System.out.println(bean3);
////					is = bean3.getProdImg().getBinaryStream();  
////					fileName = bean3.getProdImgName();
////					break;
////			}
//			}
//
//			// 由圖片檔的檔名來得到檔案的MIME型態
//			String mimeType = getServletContext().getMimeType(fileName);
//			System.out.println("mimeType = "+mimeType);
//			// 設定輸出資料的MIME型態
//			response.setContentType(mimeType);
//			// 取得能寫出非文字資料的OutputStream物件
//						
//			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.jpg)	
//			if (is == null) {
//				is = getServletContext().getResourceAsStream(
//							"/images/NoImage.jpg");
//			}
//			os = response.getOutputStream();	
//			System.out.println("準備讀出照片");
//			// 由InputStream讀取位元組，然後由OutputStream寫出
//			int len = 0;
//			byte[] bytes = new byte[8192];
//			while ((len = is.read(bytes)) != -1) {
//				os.write(bytes, 0, len);
//			}
//		} catch(SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("Util.RetrieveImageServlet#doGet()發生SQLException: " + ex.getMessage());
//		} finally{
//			is.close();
//			os.close();
//		}
//	}
//}