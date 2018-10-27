package com.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
   
    
    @RequestMapping("/")
	public String index() {
		// 視圖的邏輯名稱，經由視圖解析器的解析後會得到真實視圖的位置: 
		//   /WEB-INF/views/index.jsp
		return "index";  
	}
    
   
    
}
