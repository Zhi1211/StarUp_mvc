<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/fragment/header.jsp" />


<!-- 上傳作品頁面 -->
<div class="u-box"
	style="width: 100%; height: 2100px; display: -ms-inline-grid; margin-left: 250px;">
	<div class="u-container">

		<div
			style="float: right; width: 500px; height: 800px; margin-top: 20px;">
			<span style=""> <img class="rounded-circle" height='55px'
				width='55px' src="../images/dir.jpg" style="margin-top: 30px;">
			</span>
			<p class="u-username"
				style="float: right; margin-top: 40px; margin-right: 150px;">By
				此木創遊 The Wood Games</p>
			<br>
			<p class="u-username"
				style="float: right; margin-right: 420px; margin-top: 15px;">作品名稱</p>
			<br>
			<div class="form-group" style="margin-right: 230px;">
				<input type="text" class="form-control" placeholder="輸入作品名稱"
					id="inputDefault" style="">

				<p class="u-dis" style="margin-top: 20px;">作品概述</p>
				<textarea class="form-control" id="exampleTextarea" rows="9"
					style="width: 450px; height: 350px;"></textarea>


				<div>
					<button type="button" class="btn btn-danger"
						style="width: 450px; margin-top: 30px; margin-left: 10px;">發布作品</button>
				</div>

			</div>
		</div>
		<div class="img-box" id="dropZone"
			style="margin-top: 50px; margin-bottom: 20px; margin-left: 50px;">
			<img alt="" src="" id="previewImg" style="width: 100%">
		</div>
		<span style="float: left; margin-left: 50px;"> <input
			id="uploadImg" type="file" class="form-control-file" name="prodPhoto"
			id="exampleInputFile" aria-describedby="fileHelp">

		</span>

	</div>

	<div class="u-Advanced">


<!-- first-box -->

		<div class="aimg-box" id="dropZone"
			style="margin-top: 50px; margin-bottom: 20px; margin-left: 150px;">
			<img alt="" src="" id="previewImg" style="width: 100%">
		</div>
		<span style="float: left; margin-left: 150px;"> <input
			id="uploadImg" type="file" class="form-control-file" name="prodPhoto"
			id="exampleInputFile" aria-describedby="fileHelp">

		</span><br>
		<p class="dis-font" style="margin-top: 20px; margin-left: 150px;">作品概述</p>
		<textarea class="form-control" id="exampleTextarea" rows="9"
			style="width: 450px; height: 150px; margin-left: 150px;"></textarea>
			
<!-- second-box -->			
			<div style="width:500px;height: 650px;float:right;margin-top:-620px;margin-right:200px;" >
		<div class="aimg-box" id="dropZone"
			style="margin-top: 50px; margin-bottom: 20px; margin-left: 50px; ">
			<img alt="" src="" id="previewImg" style="width: 100%">
		</div>
		<span style="float: left; margin-left: 50px;"> <input
			id="uploadImg" type="file" class="form-control-file" name="prodPhoto"
			id="exampleInputFile" aria-describedby="fileHelp">

		</span><br>
		<p class="dis-font" style="margin-top: 20px; margin-left: 50px;">作品概述</p>
		<textarea class="form-control" id="exampleTextarea" rows="9"
			style="width: 450px; height: 150px; margin-left: 50px;"></textarea>


	</div>
	
	</div>



</div>


<jsp:include page="/fragment/footer.jsp" />
