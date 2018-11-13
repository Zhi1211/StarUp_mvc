<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/fragment/header.jsp" />
<!-- -------------------------navgation end----------------------------- -->
<div class="content container-fluid" style="display: flex; padding: 0px;">
	<table class="table table-striped table-dark">
		<tr height="50">
			<th align="center"><h3>${LoginOK.nickname}的訂單明細</h3></th>
		</tr>
		<tr height="36">
			<td>
				<table>
					<tr>
						<td align="left" width="500px">出貨地址:${memberOrder.shippingAddress}</td>
						<td align="center" width="200px">訂購日期:${memberOrder.orderDate}</td>
						<td align="right" width="150px">訂單編號:${memberOrder.orderNo}</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="36">
			<th width="300px" align="center">商品資訊</th>
			<th width="100px" align="center">數量</th>
			<th width="100px" align="center">總價</th>
		</tr>
		<c:forEach var="aBean" varStatus="stat" items="${memberOrder.items}">
			<tr>
				<td align="center">${aBean.description}</td>
				<td align="center">${aBean.quantity}</td>
				<td align="center">${aBean.quantity * aBean.unitPrice}元</td>
				<c:set var="totalPrice" value="${totalPrice + aBean.quantity * aBean.unitPrice}" />
			</tr>
		</c:forEach>
		<tr>
			<td align="center">&nbsp;</td>
			<td width="300px" align="center">合計</td>
			<td width="300px" align="center">
				<fmt:formatNumber value="${totalPrice}" pattern="#,###,###"/>元
			</td>
		</tr>
	</table>
</div>

<jsp:include page="/fragment/footer.jsp" />