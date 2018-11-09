
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
<div style="height:2000px;"></div>


   
${logoutMessage}${MsgOK.InsertOK}
<BR>
<BR>
<%
	// 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
	session.removeAttribute("MsgOK");
%>

</body>
<jsp:include page="/fragment/footer.jsp" />  