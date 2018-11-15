
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
                            <li>連絡信箱：${userBean.account}</li>                                   
                            <li>性別：${userBean.gender}</li>
                            <li>生日：${userBean.birthday}</li>  
                            <li>
                                <div class="introduction">
                                  ${introduction}
                                </div>  
                            </li>                                                                                 
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mainContent col-lg-9" id="personalMainContent">   
            <ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#works" v-on:click="getWorks(${userBean.user_id})"> <button type="button"  class="btn btn-info btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">作品</button></a></li>
				  <li><a data-toggle="tab" href="#mail"> <button type="button" class="btn btn-primary btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">信件</button></a></li>
				  <li><a data-toggle="tab" href="#post"><button type="button" class="btn btn-warning btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">發表</button></a></li>
				  <li><a data-toggle="tab" href="#collection"><button type="button" class="btn btn-danger btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">收藏</button></a></li>
				  <li><a data-toggle="tab" href="#maintain"> <button type="button" class="btn btn-success btn-circle btn-xl btnFeature" style="padding: 5px; box-shadow: 3px 3px rgb(46, 46, 46)">維護</button></a></li>
			</ul>				
				<div class="tab-content" style="margin:20px 0px 70px 0px;">
				  <div id="works" class="tab-pane fade in active">
				    <h3>作品</h3>
				     <div class="works">                 
	                    <div class="pieceOfWork" v-for="work in works">
	                            <h6 style="display:block">{{work.worksName}}</h6>    
	                            <div style="display:flex">
		                            <div class="workImg">
		                                <img v-bind:src="'mainWorksPicture/'+ work.works_id" style="max-width:500px;">
		                            </div>
		                            <div class="workText">  
		                                    {{work.worksIntro}}
		                            </div>	
		                             <div class="workComment">
                                    <a v-bind:href="'testComment?worksId='+work.works_id" >留言測試</a>
                                </div>                            
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
                <div class="u-box box"
                    style="width: 100%; height: 100%; margin: 0 auto;">
                    <form ENCTYPE="multipart/form-data" method="POST"
                        action="<c:url value='works.do' />" id="works.do">
                        <div class="u-container container">

                            <div class="col-xs-0"
                                style="width: 100%; height: 750px; margin-top: 20px;">


                                <p class="u-title">作品名稱</p>

                                <div class="form-group" style="margin-right: 350px;">
                                    <input type="text" class="form-control" name="worksName"
                                        id="worksName" placeholder="請輸入作品名">

                                    <div class="img-box" id="dropZone"
                                        style="margin-top: 50px; margin-bottom: 20px; margin-left: 50px;">
                                        <img alt="" src="" id="previewImg_1" style="width: 100%;">
                                    </div>
                                    <span style="float: left; margin-left: 50px;"> <input
                                        type="file" class="form-control-file" name="worksImg"
                                        id="worksImg" id="exampleInputFile"
                                        aria-describedby="fileHelp">

                                    </span>
                                </div>
                                <br>

                                <p class="u-dis" style="margin-top: 20px;">作品介紹</p>
                                <textarea class="form-control" name="worksIntro" id="worksIntro"
                                    rows="9" style="width: 450px; height: 150px;"></textarea>

                            </div>

                        </div>
                        <div style="margin:20px;">
                            發布更多作品<input type="radio" value="false"
                                name="user_past_employ_status" id="pemp_yes" checked style="margin-left: 30px;"><label
                                for="pemp_yes"style="margin-left: 5px;">Yes</label> <input style="margin-left: 20px;" type="radio" value="true"
                                name="user_past_employ_status" id="pemp_no"><label
                                for="pemp_no" style="margin-left: 5px;">No</label>
                        </div>
                        <div class="u-Advanced container template">
                            <!-- first-box -->
                            <div class="aimg-box col-xs-0" id="dropZone"
                                style="margin-top: 50px; margin-bottom: 20px; margin-left: 50px;">
                                <img alt="" src="" id="previewImg_2" style="width: 100%">
                            </div>
                            <span style="margin-left: 50px;"> <input type="file"
                                class="form-control-file" name="captionImg_1" id="captionImg_1"
                                id="exampleInputFile" aria-describedby="fileHelp">

                            </span><br>
                            <p class="dis-font" style="margin-top: 20px; margin-left: 50px;">作品★敘述</p>
                            <textarea class="form-control" name="detail_1" id="detail_1"
                                rows="9"
                                style="width: 450px; height: 150px; margin-left: 150px;"></textarea>

                            <!-- second-box -->
                            <div style="width: 500px; height: 650px;">
                                <div class="aimg-box box col-xs-0" id="dropZone"
                                    style="margin-top: 50px; margin-bottom: 20px;">
                                    <img alt="" src="" id="previewImg_3" style="width: 100%">
                                </div>

                                <span style="float: left; margin-left: 50px;"> <input
                                    type="file" class="form-control-file" name="captionImg_2"
                                    id="captionImg_2" id="exampleInputFile"
                                    aria-describedby="fileHelp">
                                </span><br>
                                <p class="dis-font" style="margin-top: 20px; margin-left: 50px;">作品★敘述</p>
                                <textarea class="form-control" name="detail_2" id="detail_2"
                                    rows="9"
                                    style="width: 450px; height: 150px; margin-left: 50px;"></textarea>
                            </div>

                        </div>

                        <div style="width: 85%; margin: 0 auto; margin-top: 30px;">
                            <input type="submit" class="btn btn-danger" name="submit"
                                id="submit" value="發布作品" style="width: 85%;">
                        </div>
                    </form>
                </div>
				  </div>               
				  <div id="collection" class="tab-pane fade">
				    <h3>收藏</h3>
				    <p>Some content in menu 2.</p>
				  </div>
				  <div id="maintain" class="tab-pane fade">
				    <h3>修改個人資訊<span class="text-warning">*double click to modify</span></h3>
				    	<form id="editUser" enctype="multipart/form-data" action="updateUser/${userBean.user_id}" method="POST">   				    		
				    		<div style="display:flex;background-color: rgba(0, 0, 0, 0.397); padding:10px;border-radius:5px">
				    		<div class="col-lg-8" style="line-height:28px;font-weight:400">
				    		  	<ul>
									<li>
					    		  		帳號：<span>${userBean.account}</span>
									</li>
									<li>
					    		  		姓名：<span>${userBean.name}</span>
									</li>
									<li>
					    		  		性別：<span>${userBean.gender}</span>
									</li>
									<li>
					    		  		生日：<span>${userBean.birthday}</span>
									</li>
									<li>
					    		  		暱稱：<span class="edit" id="editNickname">${userBean.nickname}</span>
									</li>
									<li>
				    		  			聯絡電話：<span class="edit" id="editPhone">${userBean.phone}</span>  		
				    		  		</li>
									<li>
				    		  			送貨地址：<span class="edit" id="editAddress">${userBean.address}</span>  		
				    		  		</li>
				    		  		<li>				    		 
				    		  			關於我：<span class="edit" id="editIntro">${introduction}</span> 
				    		  		</li> 		      							    		  					    	 				    		  	
				    		  	</ul>	
				    		</div>       
				    		<div style="margin: 0px auto">
				    		    <div style="border:1px solid grey; width:150px;height:150px;background-color:rgba(0, 0, 0, 0.397);">
					    			<img id="prviewUserImg" style="border:1px solid black; max-width:150px;">
				    		    </div>   				         		
				    			<label for="userImage">請選擇要上傳的圖片</label>  
					            <input id="userImage" type="file" class="form-control-file" name="userImage"/>
				    		</div>
				    		</div> 
				    		<div style="margin: 10px auto;display:flex; justify-content:center;">
					    		<button v-on:click="confirmUpdate(${userBean.user_id })" class="btn btn-info" style="margin-right:10px;">保存</button>
					    		<button class="btn btn-warning">取消</button>
				    		</div>     				    	
				    		<input type="hidden" name="nickname" id="nickname">
				    		<input type="hidden" name="phone" id="phone">
				    		<input type="hidden" name="address" id="address">
				    		<input type="hidden" name="userIntro" id="userIntro">				    						    				    		
				    	</form>
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
        			confirmUpdate:function(userId){
        				var nickname = $('#editNickname').text();
        				var phone = $('#editPhone').text();
        				var address = $('#editAddress').text();
        				var intro = $('#editIntro').text();
        				$("input[name='nickname']").val(nickname);
        				$("input[name='phone']").val(phone);
        				$("input[name='address']").val(address);
        				$("input[name='userIntro']").val(intro);
        				$.ajax({
            			    type: "POST",    
            			    cache:false,
            			    url: $(this).attr('action'),            			    
            			    data: $('#editUser').serialize(),            			    
            			    dataType: 'json',            			  
            	            success : function() { 
            	          
            	            }  
            	        });				
        			}
        		},        		
        	})
        	$("#userImage").change(function(){
        		  readURL(this);
        		});
        		function readURL(input){
        		  if(input.files && input.files[0]){
        		    var reader = new FileReader();
        		    reader.onload = function (e) {
        		       $("#prviewUserImg").attr('src', e.target.result);
        		    }
        		    reader.readAsDataURL(input.files[0]);
        		  }
        		}
        	
        	$('#editUser .edit').dblclick(function(e){
        		console.log("click")
        		$(this).prop('contenteditable',true).focus();
        	})
        	.blur(function(e){
        		$(this).prop('contenteditable',false)
        	})
        </script>
</body>
<jsp:include page="/fragment/footer.jsp" />

