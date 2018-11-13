<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/fragment/header.jsp" />
<!-- -------------------------navgation end----------------------------- -->
<div class="content container-fluid" style="display: flex; padding: 0px;">
	<table class="table table-striped table-dark">
		<tr height="50">
			<th colspan="4" align="center">${LoginOK.nickname}的訂購紀錄</th>
		</tr>
		<tr height="36">
			<th>訂單編號</th>
			<th>訂購日期</th>
			<th>總金額</th>
			<th>送貨地址</th>
		</tr>
		
		<c:forEach var="anOrderBean" varStatus="stat" items="${memberOrders}">
			<tr height="30">
				<td width="86" align="center">
					<a href="<c:url value='/showOneOrderDetail/${anOrderBean.orderNo}/anOrderShow' />" >
						${anOrderBean.orderNo}
					</a>
				</td>
				<td width="100" align="center">${anOrderBean.orderDate}</td>
				<td width="80" align="right">${anOrderBean.totalAmount}</td>
				<td width="400" align="left">&nbsp;${anOrderBean.shippingAddress}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<jsp:include page="/fragment/footer.jsp" />