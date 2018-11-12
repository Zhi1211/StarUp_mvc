
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
<!-- 顯示購物車中所有商品-------------------------------------------------------------- -->
<div class="content container-fluid"
	style="display: flex; padding: 0px; justify-content: center; flex-wrap: wrap">
	<div class="itemBox"
		style="width: 70%; border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); display: flex; margin-bottom: 5px">
		<div class="orderInfo" style="font-size: 16px">
			<table
				style="background-color: rgba(10, 10, 10, 0); margin: 20px 15px;">
				<tr style="font-size: px">
					<th style="width: 220px"><span class="badge badge-primary"></span></th>
					<th style="width: 300px"><span class="badge badge-primary">品名</span></th>
					<th style="width: 120px"><span class="badge badge-primary">數量</span></th>
					<th style="width: 120px"><span class="badge badge-primary">單價</span></th>
					<th style="width: 120px"><span class="badge badge-primary">小計</span></th>
					<th style="width: 120px"><span class="badge badge-primary">修改</span></th>
					<th style="width: 120px"><span class="badge badge-primary">刪除</span></th>
				</tr>
				<c:set var="cart" value="${ShoppingCart}" />
				<c:forEach varStatus='vs' var='anEntry'
					items='${ShoppingCart.content}'>
					<tr>
						<td style="width: 160px">
							<div class="itemImg"
								style="width: 160px; height: 120px; margin: 15px 0px 15px 20px; box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.438); overflow: hidden">
								<img class="prodImg"
									src="<c:url value='/getPicture/${anEntry.value.prod_id}'/>">
							</div>
						</td>
						<td style="width: 200px">${anEntry.value.prodName}</td>
						<td style="width: 120px">
							<input id="minus${anEtry.value.prod_id}" name="minus" type="button" value="-" 
								onclick="minusModify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index},${anEntry.value.prodPrice})" /> 
							<input id="newQty${vs.index}" name="goodnum" type="text" 
								value="${anEntry.value.qty}" style="width: 25px;" />
							<input id="plus${anEtry.value.prod_id}" name="plus" type="button" value="+" 
								onclick="plusModify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index},${anEntry.value.prodPrice})" /></td>
						<td style="width: 120px">${anEntry.value.prodPrice}</td>
						<td style="width: 120px" class="subtotal">${anEntry.value.prodPrice * anEntry.value.qty}</td>
						<td style="width: 120px"><input type="button" name="update" value="修改"
							onclick="modify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index},${anEntry.value.prodPrice})"></td>
						<td style="width: 120px"><i class="far fa-trash-alt" style="cursor:pointer;"
							onclick="return confirmDelete(${anEntry.key});"></i></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
	<div class="checkoutBox"
		style="width: 70%; height: 100px; border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); display: flex; margin-bottom: 5px; margin-top: 30px">
		<div style="line-height: 100px; margin: 0px auto; font-size: 20px">
			購物車內含有<span style="color: red">${ShoppingCart.itemNumber}</span>
			樣商品，消費總金額<span>${ShoppingCart.subtotal}</span> 元
		</div>
		<div class="checkoutBtn"
			style="line-height: 100px; margin-right: 30px">
			<button type="button" class="btn btn-info"
				onclick="return Checkout(${ShoppingCart.subtotal})">結帳去</button>
			<button type="button" class="btn btn-warnng"
				onclick="return shoppingContinue();">繼續購物</button>
			<button type="button" class="btn btn-warnng"
				onclick="return Abort();">清空購物車</button>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		<!-- 減少購物車中之商品數量 -->
		 function minusModify(key, qty, index, price){
			 var x = "newQty" + index;
			 	var newQty = parseInt(document.getElementById(x).value) - 1;
			 	if (newQty < 0) {
			 		window.alert('數量不能小於0');
			 		return;
			 	}
			 	if (newQty == 0) {
			 		window.alert('請點擊垃圾桶來刪除此項商品');
			 		return;
			 	}
				$.ajax({
					type:'POST',
					url:'updateShoppingCart/mod' + '/' + key + '/' + newQty + '/update',
					success:function(data){	
						//alert(JSON.stringify(data));
						location.reload();
					}
				})				 
		 }
		 <!-- 增加購物車中之商品數量 -->
		 function plusModify(key, qty, index, price){
			 var x = "newQty" + index;
			 	var newQty = parseInt(document.getElementById(x).value) + 1;
			 	if (newQty < 0) {
			 		window.alert('數量不能小於0');
			 		return;
			 	}
				$.ajax({
					type:'POST',
					url:'updateShoppingCart/mod' + '/' + key + '/' + newQty + '/update',
					success:function(data){	
						//alert(JSON.stringify(data));
						location.reload();
					}
				})				 
		 }
		 <!-- 修改購物車中之商品數量 -->
		 function modify(key, qty, index, price){
			 var x = "newQty" + index;
			 	var newQty = document.getElementById(x).value;
			 	if (newQty < 0) {
			 		window.alert('數量不能小於0');
			 		return;
			 	}
			 	if (newQty == 0) {
			 		window.alert('請點擊垃圾桶來刪除此項商品');
			 		document.getElementById(x).value = qty;
			 		return;
			 	}
			 	if (newQty == qty) {
			 		window.alert('與原數量相同，無須修改');
			 		return;
			 	}
			
			 if(confirm("確定將此商品的數量由 " + qty + " 改為 "+ newQty + " ? ") ){
					$.ajax({
						type:'POST',
						url:'updateShoppingCart/mod' + '/' + key + '/' + newQty + '/update',
						success:function(data){	
// 							alert(JSON.stringify(data));
 							location.reload();
						}
					})
					
				} else {
					document.getElementById(x).value = qty;
				}			 
		 }
		 <!-- 刪除購物車中選定之商品 -->
		 function confirmDelete(n) {
			if (confirm("確定刪除此項商品?")) {
				$.ajax({
					type:'POST',
					url:'updateShoppingCart/del' + '/' + n + '/' + 'noQty' + '/update',
					success:function(data){
						location.reload();
					}
				})
			} else {
					
			}
		 }
		 <!-- 繼續購物 -->
		 function shoppingContinue() {
		 	if (confirm("要繼續購物嗎?")) {
		 		document.forms[0].action="<c:url value='/products' />";
		 		document.forms[0].method="POST";
		 		document.forms[0].submit();
		 	} else {
		 		
		 	}
		 }
		 <!-- 放棄購物並清空購物車 -->
		 function Abort(){
		 	if (confirm("確定放棄購物?")){
		 		document.forms[0].action="<c:url value='/abortShopping' />";
		 		document.forms[0].method="POST";
		 		document.forms[0].submit();
		 	} else {
		 		
		 	}
		 }
		 <!-- 將購物車內容加入訂單資料 -->
		 function Checkout(qty) {
		 	if (qty == 0) {
		 		alert("無購買任何商品，不須結帳");
		 		return false;
		 	}
		 	if (confirm("再次確認訂單內容?")){
		 		document.forms[0].action="<c:url value='/checkOutShoppingCart' />";
		 		document.forms[0].method="POST";
		 		document.forms[0].submit();
		 		return true;
		 	} else {
		 		return false;
		 	}
		 }
		 </script>
</div>
<jsp:include page="/fragment/footer.jsp" />
