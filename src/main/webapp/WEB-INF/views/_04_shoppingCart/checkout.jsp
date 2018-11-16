
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/fragment/header.jsp" />
<div class="content container-fluid"
	style="padding: 0px; display: flex; flex-wrap: wrap;">
	<form action="/processShoppingOrder/ORDER/orderReady" name="formCheckout" style="margin:0px auto">
		<div class="checkoutBox container col-lg-10"
			style="border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); padding: 20px">
			<h5>訂單資訊</h5>
			<p></p>
			<div style="display:flex">
				<div>
					<ul>
						<li>訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></li>
						<li>會員帳號：${LoginOK.account}</li>					
						<li>收件人：<input size="10" type="text" id="Receiver" name="Receiver" value="${LoginOK.name}"></li>					
					</ul>
			    </div>
			    <div >
					<ul>						
						<li>送貨地址：<input size="50" type="text" id="ShippingAddress" name="ShippingAddress" value="${LoginOK.address}"></li>
						<li>統一編號：<input size="10" type="text" id="BNO" name="BNO" value=""></li>
						<li>發票抬頭：<input size="50" type="text" id="InvoiceTitle" name="InvoiceTitle" value=""></li>
					</ul>
			    </div>
			</div>
			   
			<div class="checkoutBtn"
				style="line-height: 100px; margin-right: 30px">
				<!-- <button type="button" class="btn btn-info">付款</button>
                        <button type="button" class="btn btn-warning">放棄此次購物</button> -->
			</div> 
		</div>
		<div class="checkoutInfo col-lg-10"
			style="border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); margin: 20px auto; padding: 20px">
			<h5>訂單明細</h5>
			<table
				style="background-color: rgba(10, 10, 10, 0); margin: 20px auto;">
				<tr style="font-size: px">
					<td style="width: 300px"><span class="badge badge-primary">品名</span></td>
					<td style="width: 200px"><span class="badge badge-primary">商品類型</span></td>
					<td style="width: 200px"><span class="badge badge-primary">商品來源</span></td>
					<td style="width: 120px"><span class="badge badge-primary">數量</span></td>
					<td style="width: 120px"><span class="badge badge-primary">單價</span></td>
					<td style="width: 120px"><span class="badge badge-primary">小計</span></td>
				</tr>
				<c:forEach varStatus='vs' var='anEntry' items='${ShoppingCart.content}'>
				<tr>
					<td style="width: 200px">${anEntry.value.prodName}</td>
					<td style="width: 200px">${anEntry.value.prodType}</td>
					<td style="width: 200px">${anEntry.value.prodCompany}</td>
					<td style="width: 120px">${anEntry.value.qty}</td>
					<td style="width: 120px">NT$ ${anEntry.value.prodPrice}</td>
					<td style="width: 120px">NT$ ${anEntry.value.prodPrice * anEntry.value.qty}</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="8"><hr></td>
				</tr>
				<tr>
					<td><input type="hidden" id="finalDecision" name="finalDecision" value=""></td>
					<td style="width: 200px;">總金額：<span
						style="color: red; font-size: 20px">NT$ ${ShoppingCart.subtotal}</span></td>
					<td><input type="button" name="OrderBtn" class="btn btn-info" value="確認付款" onclick="reconfirmOrder();"></td>
					<td><input type="button" name="CancelBtn" class="btn btn-warning" value="取消" onclick="cancelOrder()"></td>
				</tr>
			</table>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	<!-- 取消訂單 -->
	function cancelOrder() {
		if (confirm("確定取消此份訂單?")) {
			document.forms[0].action = "<c:url value='/processShoppingOrder/CANCEL/orderReady' />";
			document.forms[0].method="POST";
			document.forms[0].submit();
			return;
		} else {
			return;
		}
	}  
	<!-- 確認送出訂單 -->
	function reconfirmOrder(){
		var sa = document.getElementById('ShippingAddress').value;
		if (sa === "") {
			window.alert('出貨地址不得為空白');
			return;
		}
		if (confirm("確定送出此份訂單?")) {
			// 接收此資料的Servlet會使用finalDecision參數的值
			
// 			$.ajax({
// 				type:'POST',
// 				url:'processShoppingOrder/ORDER/orderReady',
// 				success:function(data){	
// 				}
// 			})
			document.formCheckout.action = "<c:url value='/processShoppingOrder/ORDER/orderReady' />";
			document.formCheckout.method = "POST";
			document.formCheckout.submit();
			return;
		} else {
			return;
		}
	}
	</script>
</div>
<jsp:include page="/fragment/footer.jsp" />
