<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/fragment/header.jsp" />
<div class="content container-fluid"
	style="padding: 0px;">
	<p class="text-warning" style="text-align:center; font-size:40px;margin:0px">~ Thank You ~</p>
	<p class="text-warning" style="text-align:center; font-size:30px">感謝您的購買</p>
	<div class="itemBox col-lg-5"
		style="border-radius: 5px; background-color: rgb(255, 255, 255); box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.6); display: flex; margin-bottom: 5px; padding:10px; margin:0px auto">
		<div class="orderInfo" style="font-size: 16px">
			<table
				style="background-color: rgba(10, 10, 10, 0); margin: 20px 15px;">
				<tr>				
					<th style="width: 300px"><span class="badge badge-primary">品名</span></th>
					<th style="width: 200px"><span class="badge badge-primary">數量</span></th>
					<th style="width: 120px"><span class="badge badge-primary">單價</span></th>				  			
				</tr>
				<c:forEach varStatus="os" var="opi" items="${shoppingOverOrder.items}">
				<c:set var="description" value="${opi.description}"/>
					<tr>
						<td>${description.substring(description.lastIndexOf(" "))}</td>
						<td>${opi.quantity}</td>
						<td>${opi.quantity * opi.unitPrice}</td>
					</tr>
				</c:forEach>	
			</table>
			<div style="margin-left:17px">
				總金額 : ${shoppingOverOrder.totalAmount}
					<br>
				運送地址 : ${shoppingOverOrder.shippingAddress}
			</div>
		</div>
	</div>
	<div style="text-align:center">
			<button class="btn btn-primary" style="margin-top:10px" onclick="location.href='<c:url value='/products'/>';">回商城</button>
	</div>

</div>
<jsp:include page="/fragment/footer.jsp" />