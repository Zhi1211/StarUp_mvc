package com.web.store.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.OrderBean;
import com.web.store.model.OrderItemBean;
import com.web.store.model.OrderedItem;
import com.web.store.model.ShoppingCart;
import com.web.store.model.UserBean;
import com.web.store.service.OrderService;

@Controller
public class ShoppingController {
	
	@Autowired
	ServletContext context;
	@Autowired
	OrderService orderService;
	
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
	
	@RequestMapping(value="/showShoppingCart")
	public String showShoppingCart(HttpServletRequest request, Model model) {
		ShoppingCart sc = null;
		if(request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			sc = (ShoppingCart)session.getAttribute("ShoppingCart");
		}
		if (sc != null) {
			return "_04_shoppingCart/shoppingCart";
		} else {
			return "redirect:/";
		}
//		return "_04_shoppingCart/shoppingCart";
	}
	
	@RequestMapping(value="/updateShoppingCart/{CMD}/{prod_id}/{newQty}/update", method=RequestMethod.POST)
	public ResponseEntity<ShoppingCart> UpdateShoppingCart(HttpServletRequest request, 
			@PathVariable("CMD") String cmd, 
			@PathVariable("prod_id") String prod_idStr, 
			@PathVariable("newQty") String newQtyStr) {
		if(request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			ShoppingCart sc = null;
				sc = (ShoppingCart)session.getAttribute("ShoppingCart");
				Integer prod_id = Integer.parseInt(prod_idStr.trim());
				if(cmd.equalsIgnoreCase("DEL")) {
					sc.deleteProduct(prod_id);
				} else if(cmd.equalsIgnoreCase("MOD")) {
					Integer newQty = Integer.parseInt(newQtyStr.trim());
					sc.modifyQty(prod_id, newQty);
				}	
				return new ResponseEntity<ShoppingCart>(sc,HttpStatus.OK);
		}
		return new ResponseEntity<ShoppingCart>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/abortShopping")
	public String abortShopping(HttpServletRequest request) {
		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			ShoppingCart sc = (ShoppingCart)session.getAttribute("ShoppingCart");
			if(sc != null) {
				session.removeAttribute("ShoppingCart");
			}
		}
		return "redirect:/products";
	}
	
	@RequestMapping(value="/checkOutShoppingCart")
	public String checkOutShoppingCart(HttpServletRequest request) {
		ShoppingCart sc = null;
		if(request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			sc = (ShoppingCart)session.getAttribute("ShoppingCart");
		}
		if (sc != null) {
			return "_04_shoppingCart/checkout";
		} else {
			return "redirect:/products";
		}
	}
	
	@RequestMapping(value="/processShoppingOrder/{finalDecision}/orderReady", method=RequestMethod.POST)
	public String ProcessShoppingOrder(Model model, HttpServletRequest request, 
			@PathVariable("finalDecision")String finalDecision) {
		ShoppingCart sc = null;
		UserBean ub = null;
		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			sc = (ShoppingCart)session.getAttribute("ShoppingCart");
			ub = (UserBean)session.getAttribute("LoginOK");
		}
		if (sc == null || ub == null) {
			return "redirect:/";
		}else {
			if (finalDecision.equalsIgnoreCase("CANCEL")) {
				request.getSession(false).removeAttribute("ShoppingCart");
				return "redirect:/products";
			}
			String account = ub.getAccount();									// 取出會員代號
			double totalAmount = Math.round(sc.getSubtotal());					// 計算訂單總金額 
			String shippingAddress = request.getParameter("ShippingAddress");	// 出貨地址
			String bNO = request.getParameter("BNO");							// 發票的統一編號
			String invoiceTitle = request.getParameter("InvoiceTitle");			// 發票的抬頭
			Date today = new Date();											// 新增訂單的時間
			// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
			OrderBean ob = new OrderBean(null, account, totalAmount, shippingAddress, 
					bNO, invoiceTitle, today, null, null);
			// 新建一個存放訂單明細的Set物件：items
			Set<OrderItemBean> items = new HashSet<OrderItemBean>();
			// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean，
			// 然後存入items
			Map<Integer, OrderedItem> cart = sc.getContent();
			// 呼叫keySet()取出cart內所有的key，由於Map內的Key不會重複，因此keySet()方法的傳回值為Set物件
			Set<Integer> set = cart.keySet();
			// 使用for敘述將set內所有的元素(這些元素都是Map物件內的Key)逐一取出，然後經由Map物件的
			// get方法取出Key所對應的value物件。這些value物件就是客戶購買的商品。
			for(Integer k : set) {
				OrderedItem oi = cart.get(k);
				String description = oi.getProdCompany() + " " + oi.getProdType() + " " + oi.getProdName();
				// 由於表格的Primary Key為自動遞增，為了配合Hibernate，在此主鍵設定為null
				// (Hibernate規定：自動遞增的主鍵，其對應之物件的欄位必須是null)，絕對不可以是零。
				OrderItemBean oib = new OrderItemBean(null, oi.getProd_id(), 
						description, oi.getQty(), oi.getProdPrice());
				oib.setOrderBean(ob);
				items.add(oib);
			}
			// 執行到此，購物車內所有購買的商品已經全部轉換為OrderItemBean物件，並放在Items內
			ob.setItems(items);
			model.addAttribute("orderedProduct", ob);
			try {
				orderService.processOrder(ob);
				request.getSession(false).removeAttribute("ShoppingCart");
			} catch(RuntimeException e) {
				e.printStackTrace();
			}
//			return "redirect:/products";
			return "_04_shoppingCart/thankForYourOrder";
		}
	}
}
