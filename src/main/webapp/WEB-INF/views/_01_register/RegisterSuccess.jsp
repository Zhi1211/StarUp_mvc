
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
			<img class="img-circle" src="${pageContext.request.contextPath}/Util/getImage?account=${LoginOK.account}&type=USER2" width="80"
				height="80" style="position: absolute; right: 20px;">
			<div style="clear: both;"></div>
			<p class="card-text">
				帳號：<%=session.getAttribute("account")%></p>
			<p class="card-text">
				姓名：<%=session.getAttribute("name")%></p>
			<p class="card-text">
				暱稱：<%=session.getAttribute("nickname")%></p>
			<p class="card-text">
				性別：<%=session.getAttribute("gender")%></p>
			<p class="card-text">
				生日：<%=session.getAttribute("birthday")%></p>
			<p class="card-text">
				聯絡電話：<%=session.getAttribute("phone")%></p>
			<p class="card-text">
				地址：<%=session.getAttribute("address")%></p>
		</div>

	</div>
</div>


<jsp:include page="/fragment/footer.jsp" />
