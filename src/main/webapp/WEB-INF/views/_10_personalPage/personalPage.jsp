
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/fragment/header.jsp" />
        <div class="container-fluid" style="display: flex ; color:white"> 
            <div class="col-lg-3" >                  
                <div class="personalImg">     
                    <img class="img-circle pImg" src="getUserPicture/${userBean.user_id }">
                </div> 
                <div>
                <p></p>
                    <div><h4>${userBean.nickname}</h4></div>  
                    <div>
                        <ul>
                            <li>${userBean.account}</li>                                   
                            <li>
                                <div class="introduction">
                                  ${introduction}
                                </div>
                            </li>                                                      
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mainContent" id="personalMainContent">
            <ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#works" v-on:click="getWorks(${userBean.user_id})"> <button type="button"  class="btn btn-info btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>
				  <li><a data-toggle="tab" href="#mail"> <button type="button" class="btn btn-primary btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button></a></li>
				  <li><a data-toggle="tab" href="#post"><button type="button" class="btn btn-warning btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button></a></li>
				  <li><a data-toggle="tab" href="#collection"><button type="button" class="btn btn-danger btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">收藏</button></a></li>
				  <li><a data-toggle="tab" href="#maintain"> <button type="button" class="btn btn-success btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button></a></li>
			</ul>				
				<div class="tab-content" style="margin-top:20px; border:1px solid black">
				  <div id="works" class="tab-pane fade in active">
				    <h3>作品</h3>
				     <div class="works">                 
	                    <div class="pieceOfWork" v-for="work in works">
	                            <h4>{{work.worksName}}</h4>
	                            <div class="workImg">
	                                <img src="#"style="width:100%; height:100%">
	                            </div>
	                            <div class="workText">
	                                    {{work.worksIntro}}
	                            </div>
	                    </div>
                </div>
				  </div>
				  <div id="mail" class="tab-pane fade">
				    <h3>信件</h3>
				    <p>Some content.</p>
				  </div>
				  <div id="post" class="tab-pane fade">
				    <h3>發表</h3>
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
				  </div>
				  <div id="collection" class="tab-pane fade">
				    <h3>收藏</h3>
				    <p>Some content in menu 2.</p>
				  </div>
				  <div id="maintain" class="tab-pane fade">
				    <h3>維護</h3>
				    <p>Some content in menu 2.</p>
				  </div>
				</div>               
            </div>
        </div> 
        <script src="js/jquery-3.3.1.min.js"></script>   
        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.js"></script>
        <script>
        	var app = new Vue({
        		el:'#personalMainContent',
        		data:{
        			works:[],
        		},
        	 
        		methods:{
        			getWorks:function(userId){
        				alert();
        				var _self = this;
        				$.ajax({
            			    type: "POST",    
            			    url: 'userWorks?userId='+userId,
            			    contentType:'application/json',		
            			    dataType: 'json',
            	            success : function(data) { 
            	            	_self.works = data;
            	            	console.log(_self.works)
            	            }  
            	        });
        			},
        		},        		
        	})
        </script>
</body>
<jsp:include page="/fragment/footer.jsp" />

