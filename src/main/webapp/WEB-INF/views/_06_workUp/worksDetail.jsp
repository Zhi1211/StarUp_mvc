
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/fragment/header.jsp" />
		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
		  <ol class="carousel-indicators">
		    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		  </ol>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="d-block w-100" src="mainWorksPicture/${works.works_id}" alt="First slide" style="height:800px">
		    </div>
		   <div class="carousel-item">
			  <img src="readCaptionImg_1/${works.works_id}" alt="...">
				  <div class="carousel-caption d-none d-md-block">
				    <h5>...</h5>
				    <p>...</p>
				  </div>
			  </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="readCaptionImg_2/${works.works_id}" alt="Third slide" style="height:800px">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>



















<%-- <!-- 作品詳細頁面 -->   
<div class="w-box box" style="">   
	<div class="container-fluid" style="margin: 0 auto; padding:20px 100px">		
<!-- =================作品1介面 ===================-->
<c:if test="${!empty works.detail_1 && !empty works.captionImg_1}">
	<div style="display:flex">
		<div class="form-group" style="max-width: 600px; ">
			<img src="mainWorksPicture/${works.works_id}"
				style="max-width: 600px; max-height:800px">
		</div>		
		<div style="padding: 0px 30px; flex-grow:1">
		    <h3 style="font-weight:100; color:white;">${works.worksName}</h3>
			<div>				
				<P style="color:white">${works.worksIntro}</P>
			</div>
			
			<!-- ================作品2介面================ -->
	<c:if test="${!empty works.detail_2 && !empty works.captionImg_2}">	
		<div style="display:flex; justify-content:flex-end">
				<img src="readCaptionImg_1/${works.works_id}" style="max-width:300px; max-height:400px"">      
				<div>
					<div class="wdis-font">			
						<P class="w-font">${works.detail_1}</P>
					</div>
				</div>
		</div>
	</c:if>

<!-- ===============作品3介面================= -->
		<div class="wdis-font">			
			<P class="w-font">${works.detail_2}</P>
		</div>
		<div>
			<img src="readCaptionImg_2/${works.works_id}" style="max-width:300px; max-height:400px; justify-content:flex-end">
		</div>

			
			
		</div>
		<div style="display:flex; justify-content:flex-end ">			 
			<img class="rounded-circle" height='45px' width='45px' src="getUserPicture/${works.user_Id}" />
			<h4 style="color:white;line-height:45px; margin-left:10px; font-weight:200">${works.author}</h4>
		</div>
	</div>
</c:if>


				</c:forEach>
	</div>

</div>





<!-- =================留言區================== -->

<div class="msg-box container">

	<div class="form-group" style="">


		<p class="wdis-font" style="margin-top: 20px;">留言</p>
		<textarea class="form-control" placeholder="新增留言 . . ."
			id="exampleTextarea" rows="9" style="width: 50%; height: 200px;"></textarea>
		<input type="submit" class="btn btn-danger" name="submit" id="submit"
			value="send" style="width: 50%; margin-top: 20px;">


		<div class="minbox">
			<p>Simple 喜歡這個作品!!!</p>

		</div>


	</div>
</div>
 --%>
<jsp:include page="/fragment/footer.jsp" />