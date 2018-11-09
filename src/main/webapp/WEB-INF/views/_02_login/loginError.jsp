
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />  
<c:set var="funcName" value="REG" scope="session" />
  <div class="card border-primary mb-3 col-lg-3" style="background-color:rgba(255, 255, 255,0.5); margin:30px auto;">
	<form class="form-signin" action="login.do" method="POST">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please try again...</h1>
      <h6 class="h6 mb-3 font-weight-normal" style="margin:0px auto; color:#ba5555">${ErrorMsgKey.LoginError}</h6>
      <label for="inputEmail"><b>帳號</b></label>
      <input type="text" id="inputAccount" name="account" class="form-control" placeholder="Enter Username" required autofocus >
      <label for="inputPassword" style="margin-top:20px;"><b>密碼</b></label>
      <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Enter Password" required >
	 <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me" style="text-decoration:none; margin:0px 20px; margin-top:30px"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-bottom:40px;">Login</button>
      <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('inputAccount').value='annieLee@gmail.com'"
						><i class="fas fa-user-circle"></i></button>
						<button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('inputPassword').value='Do!ng123'"
						><i class="fas fa-unlock-alt"></i></button>
    </form>
   </div>

<jsp:include page="/fragment/footer.jsp" />  
