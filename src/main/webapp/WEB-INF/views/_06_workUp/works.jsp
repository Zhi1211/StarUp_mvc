<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/fragment/header.jsp" />

<!-- 作品詳細頁面 -->

<div class="w-box"
	style="width: 100%; height: 2300px; display: -ms-inline-grid; margin-left: 130px;">
	<div class="w-container">
		<div class="form-group"
			style="margin-top: 80px; margin-right: 220px; width: 550px; height: 400px; float: right;">

			<span style=""> <img class="rounded-circle" height='55px'
				width='55px' src="../images/dir.jpg" />
			</span>
			<p class="w-username" style="margin-left: 65px; margin-top: -40px;">By
				此木創遊 The Wood Games</p>


			<p class="w-username" style="margin-bottom: 10px;">Jorge de
				Menezes的日誌</p>

			<div class="wdis-font">
				<p>作品概述</p>
				<P style="text-align: justify">
					「...我希望天氣很快會好轉，我們可以離開這個群島以履行我的職責。我永遠不會忘記對這片土地的造訪，今後我把這座島嶼命名為─巴布亞島。」
					--1526年，Jorge de Menezes的日誌
					巴布亞是一個生長著各種動植物的地區，探險隊企求發現新的動植物物種，並對這裡的土著居民做研究。只要你能存活下來，你的發現無疑將震撼科學界與整個世界。你和你的團隊必須透過原住民的協助生存下來，與此同時還得與其它探險隊競爭。

				</P>
			</div>

		</div>

		<div class="form-group"
			style="width: 800px; margin-left: 80px; margin-top: 80px;">
			<img src="../images/work01.jpg"
				style="width: 700px; height: 450px; border: 1px solid;">
		</div>


		<div class="form-group"
			style="margin-top: 80px; margin-right: 220px; width: 550px; height: 400px; float: right;">



			<div class="form-group" style="width: 800px; margin-bottom: 100px;">
				<img src="../images/dpke.jpg"
					style="width: 700px; height: 450px; border: 1px solid;">
			</div>


			<div class="wdis-font">
				<p>作品概述</p>
				<P style="text-align: justify">
					故事背景：你們回到19世紀末的世界，巴布亞是一個生長著各種動植物的地區，探險隊企求發現新的動植物物種，並對這裡的土著居民做研究。只要你能存活下來，你的發現無疑將震撼科學界與整個世界。你和你的團隊必須透過原住民的協助生存下來，與此同時還得與其它探險隊競爭。
				</P>
			</div>

		</div>

		<div class="form-group"
			style="margin-top: 80px; margin-right: 220px; margin-bottom: 150px; width: 550px; height: 400px; float: right;">



			<div class="wdis-font">
				<p>作品概述</p>
				<P style="text-align: justify;">
					探險隊企求發現新的動植物物種，並對這裡的土著居民做研究。只要你能存活下來，你的發現無疑將震撼科學界與整個世界。你和你的團隊必須透過原住民的協助生存下來，與此同時還得與其它探險隊競爭。
				</P>
			</div>

		</div>

		<div class="form-group" style="margin-left: 80px;margin-bottom: 50px;">
			<img src="../images/works02.jpg"
				style="width: 700px; height: 450px; border: 1px solid;">
		</div>
	
				
				
				
				
		</div>
				
				<div class="msg-box">
				
					<div class="form-group" style="width: 450px; height: 700px;margin-left: 125px;">

			<form action="saveComment" method="POST">
			<p class="u-dis" style="margin-top: 20px;">留言</p>
			<textarea class="form-control" placeholder="新增留言 . . ."
				id="exampleTextarea" rows="9" style="width: 600px; height: 200px;"></textarea>
			<input type="submit" class="btn btn-danger" name="submit" id="submit"
				value="send" style="width: 600px; margin-top: 20px;">
			</form>
				
				   <div style="margin-left:650px; margin-top:-250px; width:650px;height: 50px;background-color: rgba(255, 255, 255, 0.2);box-shadow: 2px 2px 2px 1px rgb(2, 14, 53);">
				     <p>Simple  喜歡這個作品!!!</p>
				   
				   </div>
				   
				     <div style="margin-left:650px; width:650px;height: 50px;background-color: rgba(255, 255, 255, 0.2);box-shadow: 2px 2px 2px 1px rgb(2, 14, 53);">
				     <p>Kitty  Lorem ipsum dolor sit amet consectetur Lorem ipsum dolor sit amet consectetur Lorem ipsum dolor sit amet consectetur</p>
				   
				   </div>
				   
				     <div style="margin-left:650px; width:650px;height: 50px;background-color: rgba(255, 255, 255, 0.2);box-shadow: 2px 2px 2px 1px rgb(2, 14, 53);">
				     <p>Arrow  ipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit amet</p>
				   
				   </div>
				   
				   
				    <div style="margin-left:650px; width:650px;height: 50px;background-color: rgba(255, 255, 255, 0.2);box-shadow: 2px 2px 2px 1px rgb(2, 14, 53);">
				     <p>Arrow  ipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit amet</p>
				   
				   </div>
				   
				    <div style="margin-left:650px; width:650px;height: 50px;background-color: rgba(255, 255, 255, 0.2);box-shadow: 2px 2px 2px 1px rgb(2, 14, 53);">
				     <p>Arrow  ipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit ametipsum dolor sit amet consectetur Lorem ipsum dolor sit amet</p>
				   
				   </div>
				  
				</div>
				</div>
				
				
	</div>









<!-- first-box -->



<!-- 		<div style="width: 500px; height: 650px; float: left;"> -->
<!-- 			<img class="waimg-box" style="margin-left: 140px;" -->
<!-- 				src="../images/dpke.jpg">  -->
<!-- 			<p style="margin-top: 20px; margin-left:140px;">作品概述</p> -->
<!-- 			<P style="text-align: justify; margin-left:140px;width:500px;"> -->
<!-- 				故事背景：你們回到19世紀末的世界，巴布亞是一個生長著各種動植物的地區，探險隊企求發現新的動植物物種，並對這裡的土著居民做研究。只要你能存活下來，你的發現無疑將震撼科學界與整個世界。你和你的團隊必須透過原住民的協助生存下來，與此同時還得與其它探險隊競爭。 -->
<!-- 			</P> -->

<!-- 		</div> -->
<!-- second-box -->
<!-- 		<div style="width: 500px; height: 650px; float: right;"> -->
<!-- 			<img class="waimg-box" style="margin-left:-140px;" src="../images/works02.jpg">  -->
<!-- 			<p style="margin-top: 20px; margin-left:-140px;">作品概述</p> -->
<!-- 			<P style="text-align: justify; margin-left:-140px;width:500px;"> -->
<!-- 				探險隊企求發現新的動植物物種，並對這裡的土著居民做研究。只要你能存活下來，你的發現無疑將震撼科學界與整個世界。你和你的團隊必須透過原住民的協助生存下來，與此同時還得與其它探險隊競爭。 -->
<!-- 			</P> -->

<!-- 		</div> -->





<jsp:include page="/fragment/footer.jsp" />