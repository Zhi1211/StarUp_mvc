<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!------------------------------------ Search -------------------------------------->
<div class="carousel-inner">
	<div class="carousel-item active"
		style="width: 100%; height: 600px; overflow: hidden;">
		<div class="container" style="display: flex; justify-content: center;">
			<div class="carousel-caption" style="margin-bottom: 150px;">

				<h1 style="text-shadow: 0px 0px 5px #f4dc42;">✯✯ STAR UP !
					Design Action ✯✯</h1>
				<p
					style="font-family: 'Josefin Sans', sans-serif; font-size: 26px; text-shadow: 0px 0px 5px #f4dc42;">
			
				<form class="form-inline my-10 my-xs-0"
					style="display: flex; justify-content: center;">
					<input class="form-control mr-xs-2"
						style="width: 500px; display: flex; justify-content: center;"
						type="search" placeholder="搜尋作品" aria-label="Search" type="button">
					<input type="submit" class="btn btn-outline-warning" name="submit"
						id="submit" value="Search">

				</form>

				<p
					style="font-family: '微軟正黑體', sans-serif; font-size: 16px; text-shadow: 0px 0px 5px #ffffff; margin-top: 15px;">
					熱門搜尋:原創桌遊、現代風格、城市紋理、創意合成</p>

			</div>
		</div>
		<jsp:include page="/fragment/header.jsp" />
	</div>
</div>
<!-- 作品列表 -->
<c:forEach var="works" items="${worksBean}">  
<div class="p-box box"
	style="width: 100%; height: 100%; margin: 0 auto;">
	<div class="p-container container-fluid ">
		<img class="rounded-circle" height='45px' width='45px'
			src="getUserPicture/${works.user_Id}" style="margin-left: 20px; margin-top: 5px;">
		<p class="p-username">By ${works.author}</p>  

		<div class="square col-xs-0">
			<div style="width: 120px; height: 30px;">

				<p class="p-title">${works.worksName}</p>  
				<div class="p-img">
					<img src="mainWorksPicture/${works.works_id}" style="width: 370px;">

				</div>
			</div>

			<div>
				<p class="p-font">${works.worksIntro}</p>
			</div>
		</div>

		<p class="p-time">2 days ago</p>
	</div>
</c:forEach>




	<!-- 頁數 -->
	<div
		style="display: flex; width: 100%; justify-content: center; margin-top: 50px;">
		<!-- 	<div style="margin: 0 auto; width: 100%;"> -->
		<ul class="pagination">
			<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a>
			</li>
			<li class="page-item active"><a class="page-link" href="#">1</a>
			</li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a>
			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<li class="page-item"><a class="page-link" href="#">5</a></li>
			<li class="page-item"><a class="page-link" href="#">&raquo;</a>
			</li>
		</ul>
	</div>
</div>





<jsp:include page="/fragment/footer.jsp" />