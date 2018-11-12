
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
<c:set var="funcName" value="REG" scope="session" />
<div class="container col-sm-4">
	<div class="card text-white bg-info mb-3" style="max-width: 50rem;">
		<div class="card-header">註冊成功</div>
		<div class="card-body">
			<h4 class="card-title" style="float: left;"><%=session.getAttribute("nickname")%>
				，Welcome !
			</h4>									     
			<img class="img-circle" src="getUserPhoto/${sessionScope.account }" width="80"
				height="80" style="position: absolute; right: 20px;">
			<div style="clear: both;"></div>
			<p class="card-text">
				帳號：${sessionScope.account}</p>
			<p class="card-text">
				姓名：${sessionScope.name}</p>
			<p class="card-text">
				暱稱：${sessionScope.nickname}</p>
			<p class="card-text">
				性別：${sessionScope.gender}</p>
			<p class="card-text">
				生日：${sessionScope.birthday}</p>
			<p class="card-text">
				聯絡電話：${sessionScope.phone}</p>
			<p class="card-text">
				地址：${sessionScope.address}</p>
		</div>

	</div>
</div>


<jsp:include page="/fragment/footer.jsp" />
