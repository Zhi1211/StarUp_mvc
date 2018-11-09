
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/fragment/header.jsp" />
<div class="content container-fluid"
	style="padding: 0px; display: flex; flex-wrap: wrap;">
	<form action="<c:url value='/processShoppingOrder/Order/orderReady' />" method="POST" name="formCheckout">
		<div class="checkoutBox container"
			style="width: 100%; border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); margin: 20px 0px 5px 150px; padding: 20px">
			<h5>訂單資訊</h5>
			<p></p>
			<div>
				<table>
					<tr>
						<td><label for="receiverAccount">會員帳號：${LoginOK.account}</label></td>
						<td><label for="customerNamer">客戶姓名：${LoginOK.name}</label></td>
						<td><label for="receiveDate">訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></label></td>
						<td><label for="customerAddr">會員地址：${LoginOK.address}</label>
					</tr>
					<tr>
						<td><label for="receiver">收件人：<input size="10" type="text" id="Receiver" name="Receiver" value=""></label></td>
						<td><label for="deliverAddr">送貨地址：<input size="50" type="text" id="ShippingAddress" name="ShippingAddress" value=""></label></td>
						<td><label for="taxId">統一編號：<input size="10" type="text" id="BNO" name="BNO" value=""></label></td>
						<td><label for="companyName">發票抬頭：<input size="50" type="text" id="InvoiceTitle" name="InvoiceTitle" value=""></label></td>
					</tr>
				</table>
			</div>
			<div class="checkoutBtn"
				style="line-height: 100px; margin-right: 30px">
				<!-- <button type="button" class="btn btn-info">付款</button>
                        <button type="button" class="btn btn-warning">放棄此次購物</button> -->
			</div>
		</div>
		<div class="checkoutInfo"
			style="width: 89%; border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); margin: 0px 0px 5px 150px; padding: 20px">
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
	<script>
	<!-- 取消訂單 -->
	function cancelOrder() {
		if (confirm("確定取消此份訂單?")) {
// 			document.forms[0].finalDecision.value = "CANCEL";
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
// 			document.formCheckout.finalDecision.value = "ORDER";
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
