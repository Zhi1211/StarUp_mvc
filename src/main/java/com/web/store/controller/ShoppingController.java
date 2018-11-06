package com.web.store.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.store.model.OrderedItem;
import com.web.store.model.ShoppingCart;

@Controller
public class ShoppingController {
	
	@Autowired
	ServletContext context;
	
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
