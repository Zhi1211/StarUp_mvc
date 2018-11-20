<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!------------------------------------ Search -------------------------------------->


<div class="carousel-inner"
	style="background-color: rgba(35, 36, 38, 0.8);">
	<div class="carousel-item active"
		style="width: 100%; height: 600px; overflow: hidden;">
		
			<div class="carousel-caption" style="margin-bottom: 150px;">

				<h1 style="text-shadow: 0px 0px 5px #f4dc42;">✯✯ STAR UP !
					Design Action ✯✯</h1>
				<p
					style="font-family: 'Josefin Sans', sans-serif; font-size: 26px; text-shadow: 0px 0px 5px #f4dc42;">
					<form
					class="form-inline my-10 my-xs-0"
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
		
		<jsp:include page="/fragment/header.jsp" />
	</div>
</div>
<!-- 作品列表 -->

	<div class="grid">
<c:forEach var="works" items="${worksBean}">
			<div class="item lightbox-gallery" style="margin: 10px;">
				<a href="#example1+${works.works_id}"><img id="works${works.works_id}"
					src="mainWorksPicture/${works.works_id}"
					style="max-width: 350px; box-shadow: 1px 1px 3px white; pointer: cursor"
					data-image-hd="mainWorksPicture/${works.works_id}"
					alt="${works.worksName}"></a>
				<div style="color: white; font-size: 14px; font-weight: 200; margin-top: 5px">${works.worksName}</div>
			</div>
</c:forEach>
	</div>   




<%-- <div class="row"  style="display: flex; margin: 0 auto;">     
	<c:forEach var="works" items="${worksBean}">
		<div class=""style="margin-top: 50px; max-width: 370px;">
			<img class="rounded-circle" height='45px' width='45px'
				src="getUserPicture/${works.user_Id}" onclick="location.href= 'worksDetail?id=${works.works_id}'">
			<p class="p-username ">By ${works.author}</p>

			<div class="square">
				<p class="p-title">${works.worksName}</p>
				<div class="p-img">
					<img src="mainWorksPicture/${works.works_id}"
						style="max-width: 350px;">
				</div>
				<div>
					<p class="p-font">${works.worksIntro}</p>
				</div>
			</div>

			<!--             <p class="p-time">2 days ago</p> -->
		</div>
	</c:forEach>
</div> --%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/waterfall.min.js"></script> 
<script>
      var grid = document.querySelector('.grid');
      waterfall(grid);
      window.addEventListener('resize', function () {
      waterfall(grid);
      });
    </script>
<script type="text/javascript">


//Create a lightbox
(function() {
   
    $('.lightbox-gallery img').click(function(e) {
      var worksId = $(this).attr('id').substring(5);	
      e.preventDefault();
      var $lightbox = $("<div class='lightbox'></div>");
      var $img = $("<img class='theBox'>");
      var $caption = $("<p class='caption'></p>");
      var $cross = $("<i class='far fa-times-circle fa-2x' id='closeBtn' style='margin-right:5px; cursor:pointer;color:white;'></i>");
      carousel = "<div id='carouselExampleIndicators' class='carousel slide' data-ride='carousel'>"+
					      "<ol class='carousel-indicators'>"+
					      "<li data-target='#carouselExampleIndicators' data-slide-to='0' class='active'></li>"+
					      "<li data-target='#carouselExampleIndicators' data-slide-to='1'></li>"+
					      "<li data-target='#carouselExampleIndicators' data-slide-to='2'></li>"+
					    "</ol>"+
					    "<div class='carousel-inner'>"+
					      "<div class='carousel-item active'>"+
					        "<img class='d-block w-100' src='mainWorksPicture/${works.works_id}"+worksId+"' >"+
					      "</div>"+
					      "<div class='carousel-item'>"+
					        "<img class='d-block w-100' src='readCaptionImg_1/${works.works_id}"+worksId+"' alt='Second slide'>"+
					      "</div>"+
					      "<div class='carousel-item'>"+
					        "<img class='d-block w-100' src='readCaptionImg_2/${works.works_id}"+worksId+"' alt='Third slide'>"+
					      "</div>"+
					    "</div>"+
					    "<a class='carousel-control-prev' href='#carouselExampleIndicators' role='button' data-slide='prev'>"+
					      "<span class='carousel-control-prev-icon' aria-hidden='true'></span>"+
					      "<span class='sr-only'>Previous</span>"+
					    "</a>"+
					    "<a class='carousel-control-next' href='#carouselExampleIndicators' role='button' data-slide='next'>"+
					      "<span class='carousel-control-next-icon' aria-hidden='true'></span>"+
					      "<span class='sr-only'>Next</span>"+
					    "</a>"+
					  "</div>";
      var $carousel = $(carousel)
      // Add image and caption to lightbox
      $lightbox     
        .append($cross)
        .append($caption)
        .append($carousel);      
      // Add lighbox to document   
      $('body').append($lightbox);

      // Get image link and description
      var cap = $(this).attr("alt"); 
      // Add data to lighbox
      $caption.text(cap);
      // Show lightbox
      $lightbox.fadeIn('fast');
  
      $cross.click(function() {
        $lightbox.remove();
      });
    });
  }());
  
</script>
</html>
					<jsp:include page="/fragment/footer.jsp" />