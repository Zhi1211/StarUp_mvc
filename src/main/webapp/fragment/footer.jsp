<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Footer -->
		<div style="padding:50px"></div>  
        <footer class="page-footer font-small"  style="z-index:10;background-color:rgba(51, 51, 51, 0.9);">
       <!-- Copyright -->
           <div class="footer-copyright text-center py-3 text-info">
           		<a>使用規範 ｜</a><a class="text-info" href=" <c:url value='/_09_opinion/opinion.jsp'/>" style="text-decoration:none;">意見回饋 ｜</a><a>關於我們 ｜</a>
           		© 2018 Copyright :  NTUT JAVA009 Team2
           </div>
       <!-- Copyright -->
        </footer>
       <!-- Footer -->   
         
       <script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>          
       <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
   	   <script src="${pageContext.request.contextPath}/js/action.js" type="text/javascript">
<!--

//-->
</script>
<script type="text/javascript">
<!-- _04_shoppingCart/shoppingCart.jsp -->
function shoppingContinue() {
	if (confirm("要繼續購物嗎?")) {
		document.forms[0].action="<c:url value='/products' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		
	}
}
<!-- _04_shoppingCart/shoppingCart.jsp -->
function confirmDelete(n) {
	if (confirm("確定刪除此項商品?")) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&prod_id=" + n +"' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		
	}
}
<!-- _04_shoppingCart/shoppingCart.jsp -->
function modify(key, qty, index) {
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
	if(confirm("確定將此商品的數量由 " + qty + " 改為 ") + newQty + " ? "){
// 		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&prod_id=" + key + "&newQty=" +newQty + "' />";
		var cmd = "MOD";
// 		document.forms[0].action="<c:url value='/" + cmd + "/" + key + "/" + newQty + "/updateShoppingCart' />";
		document.forms[0].action="<c:url value='/updateShoppingCart/" + cmd + "/" + key + "/" + newQty + "/update' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
<!-- _04_shoppingCart/shoppingCart.jsp -->
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57)){
		return false;
	}
	return true;
}
<!-- _04_shoppingCart/shoppingCart.jsp -->
function Checkout(qty) {
	if (qty == 0) {
		alert("無購買任何商品，不須結帳");
		return false;
	}
	if (confirm("再次確認訂單內容?")){
		document.forms[0].action="<c:url value='checkout.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return true;
	} else {
		return false;
	}
}
<!-- _04_shoppingCart/shoppingCart.jsp -->
function Abort(){
	if (confirm("確定放棄購物?")){
		document.forms[0].action="<c:url value='abort.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		
	}
}
<!-- _04_shoppingCart/checkout.jsp -->
function cancelOrder() {
	if (confirm("確定取消此份訂單?")) {
		document.forms[0].finalDecision.value = "CANCEL";
		document.forms[0].action = "<c:url value='ProcessOrder.do' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
<!-- _04_shoppingCart/checkout.jsp -->
function reconfirmOrder(){
	var sa = document.getElementById('ShippingAddress').value;
	if (sa === "") {
		window.alert('出貨地址不得為空白');
		return;
	}
	if (confirm("確定送出此份訂單?")) {
		// 接收此資料的Servlet會使用finalDecision參數的值
		document.formCheckout.finalDecision.value = "ORDER";
		document.formCheckout.action = "<c:url value='ProcessOrder.do' />";
		document.formCheckout.method = "POST";
		document.formCheckout.submit();
		return;
	} else {
		return;
	}
}
</script>
   	   
   </body>
</html>