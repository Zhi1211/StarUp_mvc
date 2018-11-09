<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/fragment/header.jsp" />
<div class="content container-fluid"
	style="padding: 0px; display: flex; flex-wrap: wrap;">
	<h2>感謝您的購買</h2>
	<p>您的訂購清單如下：</p>
	<div>
		<table class="table table-striped table-dark">
				<tr>
					<td>商品</td>
					<td>數量</td>
					<td>小計</td>
				</tr>
				<c:forEach varStatus="os" var="opi" items="${orderedProduct.items}">
					<tr>
						<td>${opi.description}</td>
						<td>${opi.quantity}</td>
						<td>${opi.quantity * opi.unitPrice}</td>
					</tr>
				</c:forEach>
				<tr>
					<td>總金額</td>
					<td>${orderedProduct.totalAmount}</td>
				</tr>
				<tr>
					<td>運送地址</td>
					<td>${orderedProduct.shippingAddress}</td>
				</tr>
		</table>
	</div>
</div>
<jsp:include page="/fragment/footer.jsp" />