
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="funcName" value="REG" scope="session" />
<jsp:useBean id="SYSTEM" class="_00_init.util.GlobalService" scope="application" />
<jsp:include page="/fragment/header.jsp" />

</div>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="position:absolute; z-index:0; top:0px;">
	<ol class="carousel-indicators">
		<li data-target="#carouselExampleIndicators" data-slide-to="0"
			class="active"></li>
		<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" >
		<div class="carousel-item active" style="width:100%; height:600px; overflow:hidden;">
			<img class="d-block w-100" src="image/598d4e5d7024d.png" alt="First slide" >
			<div class="container" style="display:flex; justify-content:center">
	            <div class="carousel-caption">
		              <h1 style="text-shadow: 0px 0px 5px #f4dc42;">STAR UP !</h1>
		              <p style="margin-bottom:130px;font-family:'Josefin Sans', sans-serif; font-size:26px; text-shadow: 0px 0px 5px #f4dc42;">
		              If We Are Sparkling Star,<br> We Won't Need To Worry Where We Spark.</p>
	            </div>
          </div>
		</div>
		<div class="carousel-item" style="width:100%; height:600px; overflow:hidden;">
			<img class="d-block w-100" src="image/5b33a326571eb.jpg" alt="Second slide">
			<div class="container" style="display:flex; justify-content:center">
	            <div class="carousel-caption">
		              <h1 style="text-shadow: 0px 0px 5px #f4dc42;">STAR UP !</h1>
		              <p style="margin-bottom:130px;font-family:'Josefin Sans', sans-serif; font-size:26px; text-shadow: 0px 0px 5px #f4dc42;">
		              If We Are Sparkling Star,<br> We Won't Need To Worry Where We Spark.</p>
	            </div>  
          </div>
		</div>
		<div class="carousel-item" style="width:100%; height:600px; overflow:hidden;">
			<img class="d-block w-100" src="image/5b44a3ac357d3.jpg" alt="Third slide">
			<div class="container" style="display:flex; justify-content:center">
	            <div class="carousel-caption">
		              <h1 style="text-shadow: 0px 0px 5px #f4dc42;">STAR UP !</h1>
		              <p style="margin-bottom:130px;font-family:'Josefin Sans', sans-serif; font-size:26px; text-shadow: 0px 0px 5px #f4dc42;">
		              If We Are Sparkling Star, <br>We Won't Need To Worry Where We Spark.</p>
	            </div>
          </div>
		</div>
	</div>
	<a class="carousel-control-prev" href="#carouselExampleIndicators"       
		role="button" data-slide="prev"> <span
		class="carousel-control-prev-icon" aria-hidden="true"></span> <span
		class="sr-only">Previous</span>
	</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
		role="button" data-slide="next"> <span
		class="carousel-control-next-icon" aria-hidden="true"></span> <span
		class="sr-only">Next</span>
	</a>
</div>
<div style="position:absolute; top:600px; width:99vw">

<!-- <div style="height:2000px;"> -->
<%-- 	<c:if test="${!empty LoginOK}"> --%>
<%-- 		<input id="temporarySaveUserId" type="hidden" value="${LoginOK.user_id}"> --%>
<%-- 		<input id="temporarySaveUnreadMessageNumber" type="hidden" value="${unreadMessageNumber}"> --%>
<%-- 	</c:if> --%>
<!-- </div> -->
<script type="text/javascript">
// var unreadMessageNumber = parseInt(document.getElementById('temporarySaveUnreadMessageNumber').value);
// if (unreadMessageNumber > 0 ) {
// 	alert('您尚有 ' + unreadMessageNumber + ' 封訊息尚未閱讀。');
// }
</script>
<div style="height:2000px; position:absolute; top:600px; width:99vw">
	 <div class="break" style="background-color:black; color:white; text-align:center;">
        <p style="padding:5px 0px">What's your execellent plan !?</p>
    </div>
	<div style="text-align:center; margin-top:10px; display:flex; justify-content:center">
	  <div>
		<a href="works"><img src="image/5b0a647ae296d.png" style="width:350px; margin: 0px 20px;"></a>
		<p style="font-weight:100; font-size:40px; color:white">作品</p>
	  </div>	
	  <div>
	  	<a href="products"><img src="image/5b0a64c409e54.png" style="width:350px; margin: 0px 20px; "></a>
	  	<p style="font-weight:100; font-size:40px; color:white">商城</p>
	  </div>
	  <div>
		<img src="image/5b0a7f49d467f.png" style="width:350px; margin: 0px 20px;">
		<p style="font-weight:100; font-size:40px; color:white">主頁</p>
	  </div>
	</div>
	<div class="break" style="background-color:black; color:white; text-align:center;">
        <p style="padding:5px 0px">What's your execellent plan !?</p>
    </div>
	<div>
		<div class="container">
		<form method="POST" action="<c:url value='saveOpinion'/>" id="opinionForm">			         
				<legend class="text-warning" style="text-align:center">意見回覆</legend>
				<p>${MsgMap.InsertNG}${MsgMap.errorSaveData}</p>   
				<section class="container col-sm-6">    
					<!--意見回覆表格內容 -->
					<div class="form-group col-sm-12" style="margin:0px auto;">
						<div class="form-group col-sm-8">     
					
								<label class="text-warning" for="opinionName">姓名:</label> 
								<input type="text"
									class="form-control" name="opinionName"
									value="${param.opinionName}" id="opinionName"
									aria-describedby="emailHelp" placeholder="請輸入姓名">
								<p style="color: #b2b2b2;">
									<small>${MsgMap.errorIDEmpty}${MsgMap.errorIDDUp}</small>
								</p>
						</div>
							<div>
							<div class="form-group col-sm-8">
								<label class="text-warning" for="opinionMail">電子郵件:</label> <input type="email"
									class="form-control" name="opinionMail"
									value="${param.opinionMail}" id="opinionMail"
									aria-describedby="emailHelp" placeholder="請輸入常用信箱">
								<p style="color: #b2b2b2;">
									<small>${MsgMap.errorIDEmpty}${MsgMap.errorIDDUp}</small>
								</p>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<label class="text-warning" for="opinionPhone"> 連絡電話:</label> <input type="text"
								class="form-control" name="opinionPhone"
								value="${param.opinionPhone}" id="opinionPhone"
								aria-describedby="emailHelp" placeholder="請輸入連絡電話">
							<p style="color: #b2b2b2;">
								<small>${MsgMap.errorIDEmpty}${MsgMap.errorIDDUp}</small>
							</p>
						</div>
						<div class="form-group col-sm-10">
							<label class="text-warning" for="introduction">訪客意見:</label>
							<textarea class="form-control" name="opinionField"
								id="opinionField" rows="5"></textarea>
						</div>
					<input type="submit" class="btn btn-primary btn-lg btn-block"
						name="submit" id="submit"  style="margin-bottom: 100px;" value="送出">   
					</div>
				</section>
		
			</form>     
	</div>
	</div>
</div>

   
${logoutMessage}${MsgOK.InsertOK}
<BR>
<BR>
<%
	// 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
	session.removeAttribute("MsgOK");
%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" ></script>
<script type="text/javascript">
	// this is the id of the form
$("#opinionForm").submit(function(e) {
    var form = $(this);
    var url = form.attr('action');
    $.ajax({
           type: "POST",
           url: url,
           data: form.serialize(), // serializes the form's elements.
           success: function(data)
           {
            // show response from the php script.
           }
         });
    e.preventDefault(); // avoid to execute the actual submit of the form.
});
</script>
<!--

//-->
</script>
</body>
<jsp:include page="/fragment/footer.jsp" />  