<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>   
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<jsp:include page="/fragment/header.jsp" />
     
        <!-- -------------------------navgation end----------------------------- -->
        <div class="content container-fluid" style="display: flex; padding: 0px;">
            <!-- 側邊欄 --> 
            <div class="section col-lg-2" style="width:100px; padding: 20px 20px;margin-left:20px;">
                <nav class="nav flex-column nav-tabs">
                    <a class="nav-link active" href="<c:url value='/_03_listProducts/DisplayPageProducts'/>">分類商品</a>
                    <a class="nav-link" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&prod_type=文創周邊'/>" style="color:#dfc2ef; font-weight:400;">
                    	<i class="fas fa-gift"></i>  文創周邊
                    </a>
                    		
                    			<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=暖心'/>">暖心小物</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=城市'/>">城市紋理</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=質感'/>">質感選物</a>
    						
                    <a class="nav-link" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&prod_type=原創桌遊'/>" style="color:#dfc2ef; font-weight:400;">
                    	<i class="fas fa-user-astronaut"></i>  原創桌遊
                    </a>
                    		
                    			<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=1~3人'/>">建議遊玩：1 ~ 3人</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=3~5人'/>">建議遊玩：3 ~ 5人</a>
    							<a class="dropdown-item" href="<c:url value='/_03_listProducts/DisplayPageProducts?page=1&category=5~10人'/>">建議遊玩：5 ~ 10人</a>
    						
                </nav>
              
                <nav class="nav flex-column nav-tabs">
                        <a class="nav-link active">購物專區</a>
                        <a class="nav-link" href="<c:url value='/showShoppingCart' />" style="color:#dfc2ef; font-weight:400;">結帳</a>
                        <a class="nav-link" href="#" style="color:#dfc2ef; font-weight:400;">訂單查詢</a>
                </nav> 
            </div>
               
            <div class="container col-lg-10" style=" display: flex;margin-top:20px">    
                    <div class="imgBlock" >   
                        <img class="prodDetailImg" src="<c:url value='/getProductPicture/${product.prod_id}'/>">
                    </div>                               
                <div class="prodInfo col-lg-5" >
                    <div class="upper" style="padding: 20px; background-color: rgba(255, 255, 255, 0.767); border-radius: 5px; box-shadow: 1px 1px 3px black">
                        <form id="productForm" action="<c:url value='/addProductToCart' />" method="POST">
                        <h4>${product.prodName}</h4>
                        <h5>NT$ ${product.prodPrice}</h5>
                        <div class="styled-select blue semi-square">
	                        <select name="qty" id="prodQty" style="width:230px; text-align: center;" onchange = "caculateSubtotal(qty.value,${product.prodPrice})">
	                            <option disabled selected>請選擇購買數量</option>
	                            <option value="1">1</option>
	                            <option value="2">2</option>
	                            <option value="3">3</option>
	                            <option value="4">4</option>
	                            <option value="5">5</option>
	                            <option value="6">6</option>
	                            <option value="7">7</option>
	                            <option value="8">8</option>
	                            <option value="9">9</option>
	                            <option value="10">10</option>
	                        </select>
                        </div>    
                    
                        <p>小計：NT$ <span id="subtotal"></span></p>
                        <Input type='hidden' name='prod_id' value='${product.prod_id}'><P/>
			            <Input type='hidden' name='prodName' value='${product.prodName}'><P/>
			            <Input type='hidden' name='prodPrice' value='${product.prodPrice}'><P/>
			            <Input type='hidden' name='prodCompany' value='${product.prodCompany}'><P/>
			            <Input type='hidden' name='prodIntro' value='${product.prodIntro}'><P/>
			            <Input type='hidden' name='prodType' value='${product.prodType}'><P/>
			            <Input type='hidden' name='pageNo' value='${prodPageNo}'><P/> 
                        <button class="btn btn-info" type="submit" style="width: 100%;"><h4 style="font-weight:300;font-size:20px">加入購物車</h4> </button>   
                        </form>
                    </div>               
		                    <div class="lower" style=" margin: 10px 0px; padding: 20px 10px; background-color: rgba(255, 255, 255, 0.733); border-radius: 5px; box-shadow: 1px 1px 3px black">
		                          <p style="font-size:20px; color:#123d82; text-align:center; margin:0px;">商品說明</p>   
						                   <p style="line-height:30px;width:310px; margin-left:30px;word-break: break-all;">   
						                   ${product.prodIntro}
											 <hr>
		                    </div>
                  <!--   <button class="btn btn-outline-warning" style="width: 350px">想要了解更多商品背後的故事嗎？</button> -->                
                </div>     
            </div>
        </div>
        <script src="js/jquery-3.3.1.min.js"></script>             	   
        <script>
        $('#productForm').on('submit',function(e){
				e.preventDefault(); 	 									
				$.ajax({
			    type: "POST",  
			    url: $(this).attr('action'),
			    data: $("#productForm").serialize(),
			    dataType: 'json',        	            
	        }); 		
				alert("成功啦(*´▽`*)");
			})          	        
				
			function caculateSubtotal(qty,price){        		
        		var subtotal = parseInt(qty) * price;
        		document.getElementById('subtotal').innerText = subtotal;
        	}	
        </script>   
     <jsp:include page="/fragment/footer.jsp" /> 