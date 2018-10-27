<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="text/html">
<title></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/sketchy/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-5cy8WdlNAGqQwyB33aLiqJoRQQxZsc3TDUkSTahHAx2gMK3o0te7Xqm+nNLe4Ou3"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/index_main.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet">  
 <link href="${pageContext.request.contextPath}/css/personal.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/css/upload.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/css/worksList.css" rel="stylesheet">
</head>
 
<body>
	<div class="container-fluid" style="position: relative; z-index: 10;">
		<!-- -------------------------navgation----------------------------- -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="/StarUp/index.jsp">Star Up</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarColor03" aria-controls="navbarColor03"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarColor03">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="/StarUp/index.jsp">首頁
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/_10_personalPage/DisplayPersonalPage'/>">個人主頁</a></li>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> 探索作品 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">繪圖作品</a> <a
								class="dropdown-item" href="#">文字作品</a> <a class="dropdown-item"
								href="#">影音作品</a>
						</div></li>															
					<li class="nav-item disabled"><a class="nav-link" href="#">企劃招募</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/_03_listProducts/DisplayPageProducts'/>">商城</a></li>					
				</ul>
				<c:if test="${empty LoginOK}">
					<span class="badge badge-pill badge-warning"><a href="#"
						style="text-decoration: none;"
						onclick="document.getElementById('id01').style.display='block'">登入</a>
					</span> &nbsp;&nbsp;&nbsp; 
				</c:if>
				<c:if test="${!empty LoginOK}">
					<span class="badge badge-pill badge-warning"><a href="<c:url value='/_02_login/logout.jsp' />" style="text-decoration:none;">登出</a>
					</span> &nbsp;&nbsp;&nbsp; 
					<img class="rounded-circle" height='45px' width='45px'"
						src='${pageContext.request.contextPath}/Util/getImage?id=${LoginOK.user_id}&type=USER'>
				</c:if>
				<!-- 				<span class="badge badge-pill badge-warning"><a href="login.jsp" style="text-decoration: none;">登入</a></span> -->
				<!-- 				&nbsp;&nbsp;&nbsp; -->
				<c:if test="${empty LoginOK}">
					<span class="badge badge-pill badge-info"><a
						href="/StarUp/_01_register/Register.jsp" style="text-decoration: none;">註冊</a></span>
					&nbsp;&nbsp;&nbsp;
				</c:if>
			<!------------------------------------ Login Modal -------------------------------------->
					<div id="id01" class="modal">
						<span
							onclick="document.getElementById('id01').style.display='none'"
							class="close" title="Close Modal">&times;</span>
				
				<!-- Modal Content -->
				<form class="modal-content animate border-warning" action="<c:url value='/login.do' />" method="POST"
					style="width: 300px; height: 370px; margin: 100px auto; padding: 30px; text-align: center; background-color:rgba(255, 255, 255,0.9);">
					<div class="container">
					 <h1 class="h3 mb-3 font-weight-normal">Please Login</h1>
						<label for="account"  style="margin: 15px;"><b>帳號</b></label>
						 <input
							type="text" id="account" placeholder="Enter Username" name="account"
							value="${requestScope.user}" required autofocus>  <br>
						<label for="password" style="margin: 15px;"><b>密碼</b></label>
						 <input
							type="password" id="pswd" placeholder="Enter Password" name="password"
							value="${requestScope.password}" required> &nbsp;
						<br>
						<br>
						
						<label> <input type="checkbox" checked="checked"
							name="remember" style="margin: 0px 10px; text-decoration:none;"> Remember me
						</label>
						<br>
						<button type="submit" class="btn btn-outline-primary"
							style="margin: 10px;">Login</button>
						<button type="button"
							onclick="document.getElementById('id01').style.display='none'"
							class="cancelbtn btn btn-outline-secondary">Cancel</button>
						<br>
						<button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('account').value='Codingggg@gmail.com'"
						><i class="fas fa-user-circle"></i></button>
						<button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('pswd').value='Do!ng123'"
						><i class="fas fa-unlock-alt"></i></button>
					</div>
				</form>
			</div>
	
		
			<!------------------------------------ Login Modal End-------------------------------------->
					
	</div>
	</nav>



	<!-- -------------------------navgation end----------------------------- -->