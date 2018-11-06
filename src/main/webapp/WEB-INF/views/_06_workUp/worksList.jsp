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
			<div class="carousel-caption"
				style="margin-bottom: 150px;">

				<h1 style="text-shadow: 0px 0px 5px #f4dc42;">✯✯ STAR UP !
					Design Action ✯✯</h1>
				<p
					style="font-family: 'Josefin Sans', sans-serif; font-size: 26px; text-shadow: 0px 0px 5px #f4dc42;">
				<form class="form-inline my-10 my-lg-0">
					<input class="form-control mr-sm-2"
						style="width: 500px; margin-left: 375px;" type="search"
						placeholder="搜尋作品" aria-label="Search" type="button">
					<button class="btn btn-outline-warning"
						style="display: flex; justify-content: center;" type="submit">Search</button>
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

<div class="p-box wow fadeIn"
	style="width: 100%; height: 2100px; display: -ms-inline-grid; margin-left: 200px;">
	<div class="p-container">
		<img class="rounded-circle" height='45px' width='45px'
			src="../images/dir.jpg" style="margin-left: 20px; margin-top: 5px;">
		<p class="p-username">
			By 此木創遊 The Wood Games <span class="heart"
				style="margin-left: 1110px;"> <i class="fa fa-heart-o"
				aria-hidden="true"></i>
			</span>
		</p>

		<div class="square">
			<div style="width: 120px; height: 30px;">

				<p class="p-title">牧蟲人生</p>
				<div class="p-img">
					<img src="../images/dpke.jpg" style="width: 370px; height: 270px;">

				</div>
			</div>
			<!-- <p>牧蟲人生</p> -->
			<div>
				<p class="p-font">我們希望這樣的說明可以讓大家了解，並且不要因為是
					骰子遊戲或看了規則而直接套了一個刻板印象，因為實際上《牧蟲人生》玩起來更像是個投資遊戲，
					評估損益更是遊戲中時刻要處理，要玩得好並不容易。我們會考慮拿這款，一部份是規則非常容易上手
					，新手玩家可以很快進入遊戲，策略玩家如果善用分數調整骰子也可以玩得很割喉，有進一步的思考與
					策略性。另一部分是其實有骰就有歡樂，遊戲過程中的對話以及玩家談話間不經意透露的行動方向，有
					時更是影響局勢的關鍵呢。我們希望玩家也可以感受到這款遊戲的有趣之處 🙂</p>
			</div>
		</div>

		<p class="p-time">2 days ago</p>
	</div>

	<div class="p-container">
		<img class="rounded-circle" height='45px' width='45px'
			src="../images/dir.jpg" style="margin-left: 20px; margin-top: 5px;">
		<p class="p-username">By 此木創遊 The Wood Games</p>

		<div class="square">
			<div style="width: 120px; height: 30px;">

				<p class="p-title">牧蟲人生</p>
				<div class="p-img">
					<img src="../images/dpke.jpg" style="width: 370px; height: 270px;">

				</div>
			</div>
			<!-- <p>牧蟲人生</p> -->
			<div>
				<p class="p-font">我們希望這樣的說明可以讓大家了解，並且不要因為是
					骰子遊戲或看了規則而直接套了一個刻板印象，因為實際上《牧蟲人生》玩起來更像是個投資遊戲，
					評估損益更是遊戲中時刻要處理，要玩得好並不容易。我們會考慮拿這款，一部份是規則非常容易上手
					，新手玩家可以很快進入遊戲，策略玩家如果善用分數調整骰子也可以玩得很割喉，有進一步的思考與
					策略性。另一部分是其實有骰就有歡樂，遊戲過程中的對話以及玩家談話間不經意透露的行動方向，有
					時更是影響局勢的關鍵呢。我們希望玩家也可以感受到這款遊戲的有趣之處 🙂</p>
			</div>
		</div>

		<p class="p-time">2 days ago</p>
	</div>

	<div class="p-container">
		<img class="rounded-circle" height='45px' width='45px'
			src="../images/dir.jpg" style="margin-left: 20px; margin-top: 5px;">
		<p class="p-username">
			By 此木創遊 The Wood Games <span class="heart"
				style="margin-left: 1110px;"> <i class="fa fa-heart-o"
				aria-hidden="true"></i>
			</span>
		</p>

		<div class="square">
			<div style="width: 120px; height: 30px;">

				<p class="p-title">牧蟲人生</p>
				<div class="p-img">
					<img src="../images/dpke.jpg" style="width: 370px; height: 270px;">

				</div>
			</div>
			<!-- <p>牧蟲人生</p> -->
			<div>
				<p class="p-font">我們希望這樣的說明可以讓大家了解，並且不要因為是
					骰子遊戲或看了規則而直接套了一個刻板印象，因為實際上《牧蟲人生》玩起來更像是個投資遊戲，
					評估損益更是遊戲中時刻要處理，要玩得好並不容易。我們會考慮拿這款，一部份是規則非常容易上手
					，新手玩家可以很快進入遊戲，策略玩家如果善用分數調整骰子也可以玩得很割喉，有進一步的思考與
					策略性。另一部分是其實有骰就有歡樂，遊戲過程中的對話以及玩家談話間不經意透露的行動方向，有
					時更是影響局勢的關鍵呢。我們希望玩家也可以感受到這款遊戲的有趣之處 🙂</p>
			</div>
		</div>

		<p class="p-time">2 days ago</p>
	</div>

	<div class="p-container">
		<img class="rounded-circle" height='45px' width='45px'
			src="../images/dir.jpg" style="margin-left: 20px; margin-top: 5px;">
		<p class="p-username">
			By 此木創遊 The Wood Games <span class="heart"
				style="margin-left: 1110px;"> <i class="fa fa-heart-o"
				aria-hidden="true"></i>
			</span>
		</p>

		<div class="square">
			<div style="width: 120px; height: 30px;">

				<p class="p-title">牧蟲人生</p>
				<div class="p-img">
					<img src="../images/dpke.jpg" style="width: 370px; height: 270px;">

				</div>
			</div>
			<!-- <p>牧蟲人生</p> -->
			<div>
				<p class="p-font">我們希望這樣的說明可以讓大家了解，並且不要因為是
					骰子遊戲或看了規則而直接套了一個刻板印象，因為實際上《牧蟲人生》玩起來更像是個投資遊戲，
					評估損益更是遊戲中時刻要處理，要玩得好並不容易。我們會考慮拿這款，一部份是規則非常容易上手
					，新手玩家可以很快進入遊戲，策略玩家如果善用分數調整骰子也可以玩得很割喉，有進一步的思考與
					策略性。另一部分是其實有骰就有歡樂，遊戲過程中的對話以及玩家談話間不經意透露的行動方向，有
					時更是影響局勢的關鍵呢。我們希望玩家也可以感受到這款遊戲的有趣之處 🙂</p>
			</div>
		</div>

		<p class="p-time">2 days ago</p>
	</div>

	<!-- 頁數 -->
	<div style="margin-left: 630px; margin-top: 50px; width: 100%;">
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