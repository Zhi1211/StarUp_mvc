 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <jsp:include page="/fragment/header.jsp" />
 <c:set var="funcName" value="REG" scope="session" />
 <div class="container">
      <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='/register.do' />"  id="register.do">
    <fieldset>
        <legend>會員註冊</legend> <p>${MsgMap.InsertNG}${MsgMap.errorSaveData}</p>
    	<section class="container col-sm-6">
        <div class="form-group col-sm-8">
        <label for="account">帳號</label> 
        <input type="email" class="form-control" name="account" id="userAccount" aria-describedby="emailHelp" placeholder="請輸入註冊用信箱">
        <p style="color:red;"><small>${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</small></p>
        </div>
        <div class="form-group col-sm-8">
        <label for="password1">密碼</label>
        <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('password1').value='Do!ng123'"
		><i class="fas fa-user-circle"></i></button>
        <input type="password" class="form-control" name="password" value="${param.password}" id="password1" placeholder="請輸入密碼">
        <p style="color:red;"><small>${MsgMap.errorPasswordEmpty}</small></p>
        </div>
        <div class="form-group col-sm-8">
            <label for="password2">密碼確認</label>
            <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('password2').value='Do!ng123'"
		><i class="fas fa-user-circle"></i></button>
            <input type="password" class="form-control" name="password2"  value="${param.password2}" id="password2" placeholder="請再次確認密碼">
         <p style="color:red;"><small>${MsgMap.errorPasswordEmpty}</small></p>
        </div>
        <div class="form-group col-sm-8">
            <label for="name">姓名</label>
            <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('name').value='林小安'"
		><i class="fas fa-user-circle"></i></button>
            <input type="text" class="form-control"  name="name" value="${param.name}" id="name" placeholder="">
       	  <p style="color:red;"><small>${MsgMap.errorName}</small></p>	
        </div>
        <div class="form-group col-sm-8">
            <label for="nickName">暱稱</label>
         
            <input type="text" class="form-control" name="nickName" value="${param.nickname}" id="nickname" placeholder="">
          <p style="color:red;"><small>${MsgMap.errorNickname}${MsgMap.errorNicknameDup}</small></p>	
        </div>
        <div class="form-check col-sm-3">
        	<label for="gender">性別</label><br>
            <label class="form-check-label">
            <input type="radio" class="form-check-input" name="gender"  name="genderRadios" id="genderMale" value="male" checked="">   男
            </label>
            <label class="form-check-label">
            <input type="radio" class="form-check-input" name="gender" id="genderFemale" value="female">   女
            </label>
        </div>
        </section>
        <section class="container col-sm-6">
        <div class="form-group col-sm-8">
            <label for="birthday">生日</label>
            <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('birthday').value='1992-06-18'"
		><i class="fas fa-user-circle"></i></button>
            <input type="text" class="form-control" name="birthday" value="${param.birthday}" id="birthday" placeholder="yyyy-mm-dd">
          <p style="color:red;"><small>${MsgMap.errorBirthday}</small></p>	
        </div>
        <div class="form-group col-sm-8">
            <label for="phone">聯絡電話</label>
            <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('phone').value='0912345678'"
		><i class="fas fa-user-circle"></i></button>
            <input type="text" class="form-control" name="phone" value="${param.phone}" id="phone" placeholder="">
          <p style="color:red;"><small>${MsgMap.errorPhone}</small></p>
        </div>
        <div class="form-group col-sm-10">
            <label for="address">地址</label>
            <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('address').value='台北市中正區'"
		><i class="fas fa-user-circle"></i></button>
            <input type="text" class="form-control" name="address" value="${param.address}" id="address" placeholder="">
        	<p style="color:red;"><small>${MsgMap.errorAddress}</small></p>
        </div>
  		<div class="form-group col-sm-8">
            <label for="photo">頭像</label>
            <input type="file" class="form-control-file" name="photo" id="exampleInputFile" aria-describedby="fileHelp">
            <small id="fileHelp" class="form-text" style="color:red;">檔案大小勿超過5mb</small>
        </div>
        <div class="form-group col-sm-10">
        <label for="introduction">關於我</label>
        <button type="button" class="btn btn-outline-info btn-sm"
							onclick="document.getElementById('introduction').value='hfeiqrheifhen'"
		><i class="fas fa-user-circle"></i></button>
        <textarea class="form-control" name="introduction" id="introduction" rows="3" placeholder="創作類別、創作風格、創作經歷．．．"></textarea>
        </div>
        </section>
    </fieldset>
        <input type="submit" class="btn btn-primary btn-lg btn-block" name="submit" id="submit" value="Submit" style="margin-bottom:50px;">
</div>
</body>
 <jsp:include page="/fragment/footer.jsp" />
